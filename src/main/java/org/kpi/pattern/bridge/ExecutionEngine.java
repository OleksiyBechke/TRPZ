package org.kpi.pattern.bridge;

/**
 * Implementor: Визначає інтерфейс для реалізації виконання команд.
 * Це "низькорівнева" частина моста.
 */
public interface ExecutionEngine {
    String execute(String command);
}