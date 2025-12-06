package org.kpi.pattern.strategy;

/**
 * Інтерфейс Стратегії (Strategy).
 * Визначає алгоритм отримання кольорової схеми для веб-інтерфейсу.
 */
public interface WebThemeStrategy {
    String getName(); // Назва теми для UI
    String getBackgroundColor();
    String getTextColor();
    String getPromptColor();
    String getCommandColor();
    String getErrorColor();
}