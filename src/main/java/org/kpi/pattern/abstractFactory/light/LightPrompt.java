package org.kpi.pattern.abstractFactory.light;

import org.kpi.pattern.abstractFactory.WebPrompt;

public class LightPrompt implements WebPrompt {
    @Override
    public String renderHtml() {
        return "<span style='color: blue; font-weight: bold;'>PowerShell (Light) $</span>";
    }
}