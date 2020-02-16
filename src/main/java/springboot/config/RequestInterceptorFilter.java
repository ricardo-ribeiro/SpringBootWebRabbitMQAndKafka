package springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class RequestInterceptorFilter extends OncePerRequestFilter {

    @Value("${custom.security.jwt.header.key:Authorization}")
    private String jwtAuthorizationHeaderKey;

    @Value("${custom.security.jwt.header.value.prefix:Bearer }")
    private String jwtHeaderValuePrefix;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> headerValue = Optional.ofNullable(request.getHeader(jwtAuthorizationHeaderKey));


        filterChain.doFilter(request,response);
    }
}
