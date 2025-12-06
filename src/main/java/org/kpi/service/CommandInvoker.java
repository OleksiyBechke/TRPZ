package org.kpi.service;

import lombok.Getter;
import org.kpi.pattern.command.Command;
import org.kpi.pattern.command.PowerShellExecuteCommand;
import org.kpi.service.SyntaxHighlighter;
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

    public void executeCommand(Command command) {
        if (command instanceof PowerShellExecuteCommand psCmd) {
            String styledPrompt = "<span style='color: #2ecc71; font-weight: bold;'>PS User&gt; </span>";
            history.add(styledPrompt + psCmd.getCommandText());
        }

        String rawResult = command.execute();

        if (rawResult != null && !rawResult.isEmpty()) {
            String[] lines = rawResult.split("\n");
            for (String line : lines) {
                String highlightedLine = syntaxHighlighter.highlight(line);
                history.add(highlightedLine);
            }
        } else {
            history.add("");
        }
    }

    public void clearHistory() {
        history.clear();
    }
}