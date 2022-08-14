package com.skywalker.ageinyears.service;

import io.github.bucket4j.ConsumptionProbe;

public interface RateLimiterService {
    ConsumptionProbe probeTokenConsumption(String key);
    long getTokensPerSecond();
}
