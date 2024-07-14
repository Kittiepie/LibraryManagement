package app.filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static org.apache.xmlbeans.impl.common.XBeanDebug.log;

/**
 *
 * @author OwO
 */
@WebFilter(filterName = "AdminAuthorizationFilter", urlPatterns = {"/admin/*"})
public class AdminAuthorizationFilter implements Filter {

    private FilterConfig filterConfig = null;

    public AdminAuthorizationFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    HttpSession session = httpRequest.getSession(false);

    if (session == null) {
        log("Session is null.");
        httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.jsp");
        return;
    }

    Integer userRoleId = (Integer) session.getAttribute("userRoleId");
    if (userRoleId == null) {
        log("UserRoleId is null.");
        session.setAttribute("successMessage", "Not authorized!");
        httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.jsp");
        return;
    }

    if (!(userRoleId == 2 || userRoleId == 4)) {
        log("UserRoleId not authorized: " + userRoleId);
        session.setAttribute("successMessage", "Not authorized!");
        httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.jsp");
        return;
    }

    log("UserRoleId authorized: " + userRoleId);
    chain.doFilter(request, response);
}

    @Override
    public void destroy() {
    }
}