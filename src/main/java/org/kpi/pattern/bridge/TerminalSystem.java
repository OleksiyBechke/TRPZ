package org.kpi.pattern.bridge;

/**
 * Abstraction: Визначає інтерфейс "високого рівня" і зберігає посилання на Implementor.
 */
public abstract class TerminalSystem {

    protected ExecutionEngine engine;

    public TerminalSystem(ExecutionEngine engine) {
        this.engine = engine;
    }

    public void setEngine(ExecutionEngine engine) {
        this.engine = engine;
    }

    public abstract String run(String command);
}