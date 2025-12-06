package org.kpi.pattern.interpreter;

import org.springframework.stereotype.Component;

/**
 * TerminalExpression: Реалізує інтерпретацію для помилок.
 */
@Component
public class ErrorExpression implements Expression {

    @Override
    public boolean interpret(String context) {
        if (context == null) return false;
        return context.trim().startsWith("Error") ||
                context.contains("Exception") ||
                context.startsWith("[Error]");
    }

    @Override
    public String getStyle() {
        return "color: red; font-weight: bold;";
    }
}