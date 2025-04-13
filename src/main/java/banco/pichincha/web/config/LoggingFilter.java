package banco.pichincha.web.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggingFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        long startTime = System.currentTimeMillis();

        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String fullUri = queryString != null ? uri + "?" + queryString : uri;
        logger.info("Request enviado: METHOD={}, URI={}", request.getMethod(), fullUri);

        filterChain.doFilter(request, response);

        long duration = System.currentTimeMillis() - startTime;
        logger.info("Request completado: METHOD={}, URI={}, STATUS={}, DURACION={}ms",
                request.getMethod(), fullUri, response.getStatus(), duration);
    }
}
