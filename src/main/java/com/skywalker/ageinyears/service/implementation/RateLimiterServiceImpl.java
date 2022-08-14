package com.skywalker.ageinyears.service.implementation;

import com.skywalker.ageinyears.service.RateLimiterService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterServiceImpl implements RateLimiterService {

    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    @Override
    public ConsumptionProbe probeTokenConsumption(String key) {
        Bucket bucket = resolveBucket(key);
        return bucket.tryConsumeAndReturnRemaining(1);
    }

    private Bucket resolveBucket(String key) {
        return cache.computeIfAbsent(key, this::createNewBucket);
    }

    private Bucket createNewBucket(String key) {
        long tokens = getTokensPerSecond();
        Refill refill = Refill.intervally(tokens, Duration.ofSeconds(1));
        Bandwidth limit = Bandwidth.classic(tokens, refill);
        return Bucket.builder().addLimit(limit).build();
    }

    public long getTokensPerSecond() {
        return 2;
    }
}
