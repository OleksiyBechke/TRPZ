package org.kpi.pattern.abstractFactory.dark;

import org.kpi.pattern.abstractFactory.WebButton;
import org.kpi.pattern.abstractFactory.WebPrompt;
import org.kpi.pattern.abstractFactory.WebUIFactory;

public class DarkUIFactory implements WebUIFactory {
    @Override
    public WebPrompt createPrompt() {
        return new DarkPrompt();
    }

    @Override
    public WebButton createButton() {
        return new DarkButton();
    }
}