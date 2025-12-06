package org.kpi.pattern.strategy;

import org.springframework.stereotype.Component;

@Component("light")
public class LightThemeStrategy implements WebThemeStrategy {
    @Override
    public String getName() { return "Light"; }

    @Override
    public String getBackgroundColor() { return "#ffffff"; }

    @Override
    public String getTextColor() { return "black"; }

    @Override
    public String getPromptColor() { return "darkblue"; }

    @Override
    public String getCommandColor() { return "blue"; }

    @Override
    public String getErrorColor() { return "red"; }
}