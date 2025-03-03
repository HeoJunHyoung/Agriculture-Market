//package market.agriculture.jwt;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import market.agriculture.dto.CustomMemberDetails;
//import market.agriculture.entity.Member;
//import market.agriculture.entity.enumerate.Role;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class JwtFilter extends OncePerRequestFilter {
//
//    private final JwtUtil jwtUtil;
//
//    public JwtFilter(JwtUtil jwtUtil) {
//        this.jwtUtil = jwtUtil;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        //request에서 Authorization 헤더를 찾음
//        String accessToken= request.getHeader("Authorization");
//
////        System.out.println(accessToken);
//
//        //Authorization 헤더 검증
//        if (accessToken == null) {
//
////            System.out.println("token null");
//            filterChain.doFilter(request, response);
//
//            //조건이 해당되면 메소드 종료 (필수)
//            return;
//        }
//
//
//        if(jwtUtil.isExpired(accessToken)){
//            filterChain.doFilter(request,response);
//            PrintWriter writer = response.getWriter();
//            writer.print("access token expired");
//            System.out.println("access token expired");
//            //다음 필터로 넘기면 안됨.
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//
//        String category = jwtUtil.getCategory(accessToken);
////        System.out.println(category);
//
//        if(!category.equals("access")){
//            PrintWriter writer = response.getWriter();
//            writer.print("invalid access token");
//            System.out.println("invalid access token");
//
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//
//        String username = jwtUtil.getUsername(accessToken);
//        String role = jwtUtil.getRole(accessToken);
//
//        Member member = Member.createMember(username,Role.valueOf(role));
//        CustomMemberDetails customMemberDetails = new CustomMemberDetails(member);
//
//        //스프링 시큐리티 인증 토큰 생성
//        Authentication authToken = new UsernamePasswordAuthenticationToken(customMemberDetails, null, customMemberDetails.getAuthorities());
//
//        //세션에 사용자 등록
//        SecurityContextHolder.getContext().setAuthentication(authToken);
//
//        filterChain.doFilter(request, response);
//    }
//}
