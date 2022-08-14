package com.skywalker.ageinyears.filters.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

@Component
@Slf4j
public class RequestLogger implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        log.info("Request URI: {}", httpServletRequest.getRequestURI());
        log.info("DOB Param Value: {}", Arrays.toString(httpServletRequest.getParameterValues("dob")));
        log.info("Request Method: {}", httpServletRequest.getMethod());

        chain.doFilter(request, response);

    }

}
