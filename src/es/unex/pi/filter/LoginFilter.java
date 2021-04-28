package es.unex.pi.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//Comlete the urlpattern for this Filter

@WebFilter(dispatcherTypes = {DispatcherType.REQUEST},
			urlPatterns = {"/user/*, /product/*, /favorite/*"})
public class LoginFilter implements Filter{

	private FilterConfig fc;
	
	public void init (FilterConfig fc) {

		//complete the code here
		this.fc = fc;
	}


	public void doFilter(ServletRequest request,
						ServletResponse response,
						FilterChain chain) throws IOException, ServletException {
		
		// complete the code here

		System.out.println("LoginServler : doFilter");

		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession(true);
		

		// If there is not a session established you must redirect to LoginServlet.do otherwise, follow the usual process.
		
		if(session.getAttribute("user") == null) {
			res.sendRedirect(req.getContextPath() + "/LoginServlet.do");
		}else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
		
	}

	public void destroy() {
		// do the cleanup staff
	}

}
