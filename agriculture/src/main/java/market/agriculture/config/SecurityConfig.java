package market.agriculture.config;

import market.agriculture.jwt.JWTFilter;
import market.agriculture.jwt.JWTUtil;
import market.agriculture.jwt.LoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * formLogin 필터는 기본적으로 UsernamePasswordAuthenticationFilter이다.
     * ㄴ UsernamePasswordAuthenticationFilter은 기본적으로 세션 기반 인증인데, 우리가 원하는것은 JWT 방식이기 때문에, formLogin 방식을 disable() 한다.
     * ㄴ 단, formLogin을 disable()하면, 로그인 필터 적용 자체가 안되므로, UsernamePasswordAuthenticationFilter을 상속받아서 커스텀 로그인 필터를 만들어줘야 한다.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        /**
         * disable 설정
         */
        http
                .formLogin((auth) -> auth.disable())
                .csrf((auth) -> auth.disable())
                .httpBasic((auth) -> auth.disable());

        /**
         * 인가 설정
         */
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "member/join").permitAll()
                        .anyRequest().authenticated());
        /**
         * 필터 추가
         * ㄴ addFilterAt : 현재 formLogin 방식을 disable하면서 세션 기반의 UsernamePasswordAuthenticationFilter도 disable되어 있는 상태
         * ㄴ 따라서 JWT 기반의 커스텀 필터를 만들어 줬는데, 이제 이 필터를 UsernamePasswordAuthenticationFilter 대신에 쓸거기 때문에 addFilterAt을 사용
         */
        http
                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);

        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);


        /**
         * 세션 설정
         */
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

}
