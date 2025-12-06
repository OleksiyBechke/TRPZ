package org.kpi.service;

import org.kpi.pattern.interpreter.ErrorExpression;
import org.kpi.pattern.interpreter.Expression;
import org.kpi.pattern.interpreter.HeaderExpression;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Client / Context: Використовує патерн Interpreter для перетворення
 * "сирого" тексту в HTML з підсвічуванням.
 */
@Service
public class SyntaxHighlighter {

    private final List<Expression> expressions;

    public SyntaxHighlighter(ErrorExpression errorExpr, HeaderExpression headerExpr) {
        this.expressions = new ArrayList<>();
        // Реєструємо правила (порядок може мати значення)
        this.expressions.add(errorExpr);
        this.expressions.add(headerExpr);
    }

    /**
     * Основний метод: приймає текст, проганяє через інтерпретатори
     * і повертає HTML рядок.
     */
    public String highlight(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        // Перевіряємо кожне правило
        for (Expression expr : expressions) {
            if (expr.interpret(text)) {
                // Якщо правило спрацювало, огортаємо текст у span зі стилем
                return "<span style='" + expr.getStyle() + "'>" + escapeHtml(text) + "</span>";
            }
        }

        // Якщо жодне правило не підійшло, повертаємо текст як є (але екранований)
        return "<span>" + escapeHtml(text) + "</span>";
    }

    // Проста екранізація спецсимволів HTML, щоб верстка не поїхала
    private String escapeHtml(String text) {
        return text.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("\n", "<br/>");
    }
}