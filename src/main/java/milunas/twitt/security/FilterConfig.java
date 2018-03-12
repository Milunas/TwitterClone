package milunas.twitt.security;

import milunas.twitt.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {

    @Autowired
    private AccountRepository repository;


    @Bean
    public FilterRegistrationBean filterRegistration(){
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(authorizationFilter());
    registration.addUrlPatterns("/api/*");
    registration.setName("auth");
    registration.setOrder(1);
    return registration;
}

    public Filter authorizationFilter(){
        return new AuthorizationFilter(repository);
    }

}
