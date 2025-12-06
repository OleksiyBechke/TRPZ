package org.kpi.pattern.abstractFactory.dark;

import org.kpi.pattern.abstractFactory.WebPrompt;

public class DarkPrompt implements WebPrompt {
    @Override
    public String renderHtml() {
        return "<span style='color: lightgreen; font-weight: bold;'>PS Dark User&gt;</span>";
    }
}