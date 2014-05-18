package pl.vgtworld.l4d2jsstats.login;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import pl.vgtworld.l4d2jsstats.user.UserService;
import pl.vgtworld.l4d2jsstats.user.dto.UserDto;

@WebFilter(urlPatterns = { "/*" })
public class LoginFilter implements Filter {
	
	@Inject
	private UserService userService;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	@Override
	public void destroy() {
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Cookie[] cookies = httpRequest.getCookies();
		Cookie loginCookie = findCookie("user", cookies);
		Cookie tokenCookie = findCookie("token", cookies);
		if (loginCookie != null && tokenCookie != null) {
			UserDto validatedUser = userService.validateLoginCookies(loginCookie, tokenCookie);
			request.setAttribute("user", validatedUser);
		}
		chain.doFilter(request, response);
	}
	
	private Cookie findCookie(String name, Cookie[] cookies) {
		if (cookies == null) {
			return null;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				return cookie;
			}
		}
		return null;
	}
}
