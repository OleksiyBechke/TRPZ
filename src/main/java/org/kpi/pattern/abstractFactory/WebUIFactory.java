package org.kpi.pattern.abstractFactory;

public interface WebUIFactory {
    WebPrompt createPrompt();
    WebButton createButton();
}