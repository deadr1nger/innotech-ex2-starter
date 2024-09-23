package ru.innotech.starter.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;


import java.time.LocalDateTime;
import java.util.Enumeration;

import static org.mockito.Mockito.*;

public class LoggingInterceptorTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private LoggingInterceptor loggingInterceptor;

    @BeforeEach
    void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        loggingInterceptor = new LoggingInterceptor();

        when(request.getHeaderNames()).thenReturn(new Enumeration<String>() {
            private final String[] headers = {"User-Agent", "Accept"};
            private int index = 0;

            @Override
            public boolean hasMoreElements() {
                return index < headers.length;
            }

            @Override
            public String nextElement() {
                return headers[index++];
            }
        });

        when(request.getHeader("User-Agent")).thenReturn("Test-Agent");
        when(request.getHeader("Accept")).thenReturn("application/json");
    }


    @Test
    void testPreHandle() {
        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestURI()).thenReturn("/test-uri");

        loggingInterceptor.preHandle(request, response, null);


    }

    @Test
    void testAfterCompletion() {
        LocalDateTime startTime = LocalDateTime.now().minusSeconds(1);
        ReflectionTestUtils.setField(loggingInterceptor, "startTime", startTime);

        when(response.getStatus()).thenReturn(200);

        loggingInterceptor.afterCompletion(request, response, null, null);


    }
}
