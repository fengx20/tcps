package com.gxuwz.fx.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * websocket过滤器，允许resful请求访问
 */
@WebFilter(urlPatterns = "/*")
public class WebSocketFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("执行filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 设置跨域
        HttpServletResponse httpServletResponse1 = (HttpServletResponse) response;
        httpServletResponse1.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse1.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        httpServletResponse1.setHeader("Access-Control-Max-Age", "3600");
        httpServletResponse1.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        // 设置request的编码方式
        /*
         * if(httpServletRequest.getMethod().equalsIgnoreCase("GET")) {
         * if(!(httpServletRequest instanceof GetRequest)) { httpServletRequest = new
         * GetRequest(httpServletRequest, "UTF-8");//处理get请求编码 } }
         *//*else {
          req.setCharacterEncoding("UTF-8");//处理post请求编码
      }*/
        chain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {

        System.out.println("GIME over");
    }
}
