package com.skywalker.ageinyears.config.ratelimit;

import com.skywalker.ageinyears.service.RateLimiterService;
import io.github.bucket4j.ConsumptionProbe;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class RateLimitInterceptor implements HandlerInterceptor {

    private final RateLimiterService rateLimiterService;

    public RateLimitInterceptor(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String dob = request.getParameter("dob");

        if (dob == null || dob.isEmpty()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Missing Param: dob");
            return false;
        }

        ConsumptionProbe probe = rateLimiterService.probeTokenConsumption("key");
        long tokensPerSecond = rateLimiterService.getTokensPerSecond();
        long tokensRemaining = probe.getRemainingTokens();

        response.addHeader("X-RateLimit-Limit-Seconds", String.valueOf(tokensPerSecond));
        response.addHeader("X-RateLimit-Limit-Remaining", String.valueOf(tokensRemaining));

        if (!probe.isConsumed()) {
            long waitTime = probe.getNanosToWaitForRefill() / 1_000_000_000;
            response.addHeader("X-Rate-Limit-Retry-After-Seconds", String.valueOf(waitTime));
            response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(), "Request quota exceeded. Please try again");
            return false;
        }

        return true;
    }
}
