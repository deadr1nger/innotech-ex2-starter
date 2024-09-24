package ru.innotech.starter.configuration;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);
    private LocalDateTime startTime;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        startTime = LocalDateTime.now();
        logRequest(request);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logResponse(response, startTime);
    }

    protected void logRequest(HttpServletRequest request) {
        logger.info("Incoming request: method={}, uri={}, headers={}", request.getMethod(), request.getRequestURI(), getHeaders(request));
    }

    protected void logResponse(HttpServletResponse response, LocalDateTime startTime) {
        long processingTime = ChronoUnit.MILLIS.between(startTime, LocalDateTime.now());
        logger.info("Outgoing response: status={}, processingTime={}ms", response.getStatus(), processingTime);
    }

    protected String getHeaders(HttpServletRequest request) {
        StringBuilder headers = new StringBuilder();
        request.getHeaderNames().asIterator().forEachRemaining(header -> headers.append(header).append("=").append(request.getHeader(header)).append("; "));
        return headers.toString();
    }
}
