package org.kpi.pattern.command;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Invoker (Ініціатор).
 * Викликає виконання команди та зберігає історію.
 */
@Service
public class CommandInvoker {

    @Getter
    private final List<String> history = new ArrayList<>();

    public void executeCommand(Command command) {
        if (command instanceof PowerShellExecuteCommand psCmd) {
            history.add("PS User> " + psCmd.getCommandText());
        }

        String result = command.execute();

        if (result != null && !result.isEmpty()) {
            history.add(result);
        } else {
            history.add("");
        }
    }

    public void clearHistory() {
        history.clear();
    }
}