package org.rxjava.gateway.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerAdapter;
import reactor.core.publisher.Mono;

/**
 * @author happy 2019-06-11 01:04
 */
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
        return http
                //配置异常处理Runner
                .exceptionHandling()
                //配置未授权异常处理Runner
                .authenticationEntryPoint((swe, e) -> Mono
                        .fromRunnable(() -> swe
                                .getResponse()
                                .setStatusCode(HttpStatus.UNAUTHORIZED)
                        )
                )
                //配置拒绝异常处理Runner
                .accessDeniedHandler((swe, e) -> Mono
                        .fromRunnable(() -> swe
                                .getResponse()
                                .setStatusCode(HttpStatus.FORBIDDEN)
                        ))
                .and()
                //配置不可跨域请求
                .csrf().disable()
                //配置表单登陆不可用
                .formLogin().disable()
                //配置http基本身份认证
                .httpBasic().disable()
                //配置授权匹配
                .authorizeExchange()
                //OPTIONS请求均通过
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .matchers(new TokenServerWebExchangeMatcher()).permitAll()
                //其他所有路径均需要授权
                .anyExchange().authenticated().and()
                .build();
    }
}