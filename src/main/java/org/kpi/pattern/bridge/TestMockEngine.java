package org.kpi.pattern.bridge;

/**
 * ConcreteImplementor B: Тестова реалізація або імітація Linux середовища.
 * Дозволяє перевірити роботу системи без реального виклику PowerShell.
 */
public class TestMockEngine implements ExecutionEngine {

    @Override
    public String execute(String command) {
        return "[MOCK ENGINE] Command '" + command + "' executed successfully.\n" +
                "System status: OK\n" +
                "Time: " + java.time.LocalTime.now();
    }
}