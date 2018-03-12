package milunas.twitt.security;

import milunas.twitt.exception.UnauthorizedException;
import milunas.twitt.model.Account;
import milunas.twitt.repository.AccountRepository;
import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    private final AccountRepository repository;

    public AuthorizationFilter(AccountRepository repository){
        this.repository = repository;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("Bearer");
        String username = request.getHeader("User");
        System.out.println(username);
        if(username!=null && token!=null){
            Account user = repository.findByUsername(username);
            if(user !=null){
                if(token.equals(user.getUserSecurityToken())){
                    filterChain.doFilter(servletRequest,servletResponse);
                    return;
                }
            }
        }
        throw new UnauthorizedException();
    }

    @Override
    public void destroy() {

    }

}
