package lk.ijse.posbackend.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class CORSFilter extends HttpFilter {
//    @Override
//    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//        System.out.println("CORS Filter");
//        var origin = getServletContext().getInitParameter("origin");
//        if(origin.contains(getServletContext().getInitParameter("origin"))){
//            res.setHeader("Access-Control-Allow-Origin", origin);
//            res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS");
//            res.setHeader("Access-Control-Allow-Headers","Content-Type");
//            res.setHeader("Access-Control-Expose-Headers","Content-Type");
//        }
//        chain.doFilter(req, res);
//
//    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Initialization logic if needed
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) res;
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;

        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, HEAD, PUT");
        httpServletResponse.setHeader("Access-Control-Allow-Headers","Content-Type, Authorization");

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}
