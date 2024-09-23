package ru.innotech.starter.configuration;



import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@AutoConfiguration
@ConditionalOnProperty(prefix = "http.logging", value = "enabled", havingValue = "true")
public class StarterAutoConfiguration implements WebMvcConfigurer{

    LoggingInterceptor loggingInterceptor;

    public StarterAutoConfiguration(LoggingInterceptor loggingInterceptor) {
        this.loggingInterceptor = loggingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
    }

}
