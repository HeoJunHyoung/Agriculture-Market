package market.agriculture.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import market.agriculture.dto.security.CustomMemberDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

/**
 * UsernamePasswordAuthenticationFilter는 인증 자체는 수행하지 않음
 * ㄴ 대신 AuthenticationManager에게 인증을 위임하는 역할을 수행
 * ㄴ 그래서 AuthenticationToken이라는 DTO에 데이터(username, password)를 담아서 return해줌
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtUtil;

    /**
     * LoginFilter는 의존성 주입에 AuthenticationManager을 인자로 받아야 하기 때문에 SecurityConfig에서 주입하도록 해야함
     */
    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 위에서 설명한대로 attemptAuthentication은 단순히 인증을 시도
     * ㄴ 여기서 인증 시도란, 인증 자체를 수행하는게 아니라, AuthenticationManager에게 인증을 위임할건데, 이때 필요한 데이터를 담아서 return해주는 역할
     * ㄴ attemptAuthentication이라는 메서드는 사용자 호출이 아니라, "/login" 경로로 요청이 오면 자동으로 실행됨
     * ㄴ 이후 AuthenticationManager는 CustomUserDetailsService를 통해 DB에서 유저 정보를 조회하고 인증
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 클라이언트 요청(request)으로부터 username(ID)와 password(PW) 추출
        String username = obtainUsername(request);
        String password = obtainPassword(request);

        // authenticationManager가 인증을 할 때 UsernamePasswordAuthenticationToken DTO를 기반으로 하기 때문에 데이터 삽입하여 리턴
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);
        return authenticationManager.authenticate(authToken);
    }

    /**
     * 로그인 성공 시, JWTUtil을 사용하여 JWT Token 생성
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {

        CustomMemberDetails customMemberDetails = (CustomMemberDetails) authentication.getPrincipal();
        String username = customMemberDetails.getUsername();
        Long memberId = customMemberDetails.getId();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        String token = jwtUtil.createJwt(username, role, 60 * 60 * 1000L, memberId);

        response.addHeader("Authorization", "Bearer " + token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(401);
    }
}
