package org.kpi.strategy;

import org.springframework.stereotype.Component;

@Component("dark")
public class DarkThemeStrategy implements WebThemeStrategy {
    @Override
    public String getName() { return "Dark"; }

    @Override
    public String getBackgroundColor() { return "#1e1e1e"; }

    @Override
    public String getTextColor() { return "lightgray"; }

    @Override
    public String getPromptColor() { return "lightgreen"; }

    @Override
    public String getCommandColor() { return "yellow"; }

    @Override
    public String getErrorColor() { return "red"; }
}