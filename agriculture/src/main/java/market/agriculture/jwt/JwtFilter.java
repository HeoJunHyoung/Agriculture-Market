package market.agriculture.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import market.agriculture.dto.CustomCustomerDetails;
import market.agriculture.dto.CustomSellerDetails;
import market.agriculture.entity.Customer;
import market.agriculture.entity.Seller;
import market.agriculture.entity.enumerate.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //request에서 Authorization 헤더를 찾음
        String accessToken= request.getHeader("access");

//        System.out.println(accessToken);

        //Authorization 헤더 검증
        if (accessToken == null) {

//            System.out.println("token null");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }


        if(jwtUtil.isExpired(accessToken)){
            filterChain.doFilter(request,response);
            PrintWriter writer = response.getWriter();
            writer.print("access token expired");
            System.out.println("access token expired");
            //다음 필터로 넘기면 안됨.
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String category = jwtUtil.getCategory(accessToken);
//        System.out.println(category);

        if(!category.equals("access")){
            PrintWriter writer = response.getWriter();
            writer.print("invalid access token");
            System.out.println("invalid access token");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String username = jwtUtil.getUsername(accessToken);
        String role = jwtUtil.getRole(accessToken);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = null;
        if (role.equals("Customer")){

            Customer customer = new Customer();
            customer.setRole(Role.Customer);
            customer.setUsername(username);

            CustomCustomerDetails customCustomerDetails = new CustomCustomerDetails(customer);
            //UserDetails에 회원 정보 객체 담기
            authToken = new UsernamePasswordAuthenticationToken(customCustomerDetails, null, customCustomerDetails.getAuthorities());

        }
        else if (role.equals("Seller")){

            Seller seller = new Seller();
            seller.setRole(Role.Seller);
            seller.setUsername(username);

            CustomSellerDetails customSellerDetails = new CustomSellerDetails(seller);
            //UserDetails에 회원 정보 객체 담기
            authToken = new UsernamePasswordAuthenticationToken(customSellerDetails, null, customSellerDetails.getAuthorities());
        }
        else{
            System.out.println("JwtFilter role 입력값 이상");

        }

        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
