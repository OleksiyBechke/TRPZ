package org.kpi.service;

import lombok.Getter;
import org.kpi.pattern.command.Command;
import org.kpi.pattern.command.PowerShellExecuteCommand;
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

    private final SyntaxHighlighter syntaxHighlighter;

    public CommandInvoker(SyntaxHighlighter syntaxHighlighter) {
        this.syntaxHighlighter = syntaxHighlighter;
    }

    public String executeCommand(Command command) {
        StringBuilder executionResult = new StringBuilder();

        if (command instanceof PowerShellExecuteCommand psCmd) {
            String styledPrompt = "<span style='color: #2ecc71; font-weight: bold;'>PS User&gt; </span>";
            String commandLine = styledPrompt + psCmd.getCommandText();
            history.add(commandLine);
            executionResult.append(commandLine).append("\n");
        }

        String rawResult = command.execute();

        if (rawResult != null && !rawResult.isEmpty()) {
            String[] lines = rawResult.split("\n");
            for (String line : lines) {
                String highlightedLine = syntaxHighlighter.highlight(line);
                history.add(highlightedLine);
                executionResult.append(highlightedLine).append("\n");
            }
        } else {
            history.add("");
        }

        return executionResult.toString();
    }

    public void clearHistory() {
        history.clear();
    }
}