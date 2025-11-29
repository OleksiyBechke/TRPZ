package org.kpi.service;

import lombok.Getter;
import org.kpi.strategy.DarkThemeStrategy;
import org.kpi.strategy.WebThemeStrategy;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ThemeContext {

    @Getter
    private WebThemeStrategy currentStrategy;
    private final Map<String, WebThemeStrategy> strategies;

    public ThemeContext(Map<String, WebThemeStrategy> strategies) {
        this.strategies = strategies;
        this.currentStrategy = strategies.getOrDefault("dark", new DarkThemeStrategy());
    }

    public void setStrategy(String strategyName) {
        if (strategies.containsKey(strategyName)) {
            this.currentStrategy = strategies.get(strategyName);
        }
    }

}