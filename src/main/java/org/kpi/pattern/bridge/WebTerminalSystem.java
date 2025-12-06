package org.kpi.pattern.bridge;

/**
 * RefinedAbstraction: Розширює інтерфейс абстракції.
 * Може додавати додаткову логіку (логування, перевірки) перед викликом двигуна.
 */
public class WebTerminalSystem extends TerminalSystem {

    public WebTerminalSystem(ExecutionEngine engine) {
        super(engine);
    }

    @Override
    public String run(String command) {
        // Тут може бути специфічна логіка для Веб-терміналу (наприклад, валідація)
        if (command == null || command.trim().isEmpty()) {
            return "";
        }

        // Делегування виконання конкретному двигуну через міст
        return engine.execute(command);
    }
}