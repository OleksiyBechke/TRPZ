package org.kpi.pattern.interpreter;

/**
 * AbstractExpression: Оголошує інтерфейс для виконання операції інтерпретації.
 */
public interface Expression {
    boolean interpret(String context);

    String getStyle();
}