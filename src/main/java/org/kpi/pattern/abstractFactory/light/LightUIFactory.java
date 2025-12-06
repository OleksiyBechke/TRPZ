package org.kpi.pattern.abstractFactory.light;

import org.kpi.pattern.abstractFactory.WebButton;
import org.kpi.pattern.abstractFactory.WebPrompt;
import org.kpi.pattern.abstractFactory.WebUIFactory;

public class LightUIFactory implements WebUIFactory {
    @Override
    public WebPrompt createPrompt() {
        return new LightPrompt();
    }

    @Override
    public WebButton createButton() {
        return new LightButton();
    }
}