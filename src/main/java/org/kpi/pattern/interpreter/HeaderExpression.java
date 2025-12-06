package org.kpi.pattern.interpreter;

import org.springframework.stereotype.Component;

/**
 * TerminalExpression: Реалізує інтерпретацію для заголовків або успішних даних.
 */
@Component
public class HeaderExpression implements Expression {

    @Override
    public boolean interpret(String context) {
        if (context == null) return false;
        // Правило: типові заголовки PowerShell таблиць або статус ОК
        return (context.contains("Mode") && context.contains("LastWriteTime")) ||
                context.contains("System status: OK");
    }

    @Override
    public String getStyle() {
        return "color: #f1c40f; font-weight: bold;";
    }
}