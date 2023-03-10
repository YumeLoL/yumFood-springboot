package com.yumfood.filter;

import com.alibaba.fastjson.JSON;
import com.yumfood.common.BaseContext;
import com.yumfood.common.R;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;

/**
 * Request Interceptor
 * Check if the user has already login
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    // Path matching unit
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // get request URI
        String requestURI = request.getRequestURI();// /backend/index.html
        log.info("......The intercepted requests......：{}",requestURI);

        // define urls that can get pass directly
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
        };

        // define a check method, compare requestURI and urls
        boolean check = check(urls, requestURI);

        // if check is true, go pass
        if(check){
            log.info("......The request URL: {} can go pass......",requestURI);
            filterChain.doFilter(request,response);
            return;
        }

        // if check is false
        // have to check if the user has already login
        if(request.getSession().getAttribute("employee") != null){
            log.info("......The user has already login，the id is：{} ......",request.getSession().getAttribute(
                    "employee"));

            // get current logined employee's id from session
            Long currentLoginEmpId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(currentLoginEmpId);

            filterChain.doFilter(request,response);
            return;
        }


        // If not logged in, returns the data to the front-end page (js/request.js)
        log.info("......The user does not login......");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));

    }


    /**
     * Path matching method
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);

            if(match){
                return true;
            }
        }
        return false;
    }

}



