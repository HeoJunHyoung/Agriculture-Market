package market.agriculture.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import market.agriculture.dto.CustomCustomerDetails;
import market.agriculture.dto.CustomSellerDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collection;
import java.util.Iterator;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
//    private final RefreshRepository refreshRepository;


    public LoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username,password,null);

        return authenticationManager.authenticate(authToken);

    }

    //로그인 성공시 실행하는 메소드 (여기서 JWT를 발급하면 됨)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        String username = null;
        if (role.equals("Customer")){
            CustomCustomerDetails customCustomerDetails = (CustomCustomerDetails) authentication.getPrincipal();
            username = customCustomerDetails.getUsername();
        }
        else if(role.equals("Seller")){
            CustomSellerDetails customSellerDetails = (CustomSellerDetails) authentication.getPrincipal();
            username = customSellerDetails.getUsername();
        }


        // 만료시간 약25일
        String accessToken = jwtUtil.createJwt("access","username",role,60  * 60 * 60 * 10000L);
//        String refreshToken = jwtUtil.createJwt("refresh","username",role,24 *60 * 10000L);

        response.setHeader("access",accessToken);
//        response.addCookie(createCookie("refresh",refreshToken));
//        addRefreshEntity(username,refreshToken,jwtUtil.getExpiredtime(refreshToken));
        response.setStatus(HttpStatus.OK.value());
    }

    private Cookie createCookie(String refresh, String refreshToken) {
        Cookie cookie = new Cookie(refresh,refreshToken);
        cookie.setMaxAge(24*60*60);
//        cookie.setSecure(true); //Https 쓸거면 넣어주기
//        cookie.setPath("/"); //쿠키 적용될 범위
        cookie.setHttpOnly(true); //js로 쿠키 접근 못하도록 막아야함.
        return cookie;
    }

    //로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {

        response.setStatus(401);
    }
//    private void addRefreshEntity(String username, String refresh, Long expiredMs) {
//
//        Date date = new Date(System.currentTimeMillis() + expiredMs);
//
//        RefreshEntity refreshEntity = new RefreshEntity();
//        refreshEntity.setUsername(username);
//        refreshEntity.setRefresh(refresh);
//        refreshEntity.setExpiration(date.toString());
//
//        refreshRepository.save(refreshEntity);
//    }

}