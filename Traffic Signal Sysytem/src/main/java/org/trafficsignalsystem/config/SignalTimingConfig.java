package org.trafficsignalsystem.config;

import org.trafficsignalsystem.enums.Color;

import java.util.Map;

public class SignalTimingConfig {
    private final Map<Color, Long> durations;

    public SignalTimingConfig(Map<Color, Long> durations) {
        this.durations = durations;
    }

    public Long getDuration(Color color){
        return durations.get(color);
    }
}
