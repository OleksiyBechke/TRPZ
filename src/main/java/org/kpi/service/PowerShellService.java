package org.kpi.service;

import org.kpi.pattern.bridge.ExecutionEngine;
import org.kpi.pattern.bridge.TerminalSystem;
import org.kpi.pattern.bridge.WebTerminalSystem;
import org.kpi.pattern.bridge.WindowsPowerShellEngine;
import org.kpi.pattern.bridge.TestMockEngine;
import org.springframework.stereotype.Service;

@Service
public class PowerShellService {

    private TerminalSystem terminalSystem;

    public PowerShellService() {
        ExecutionEngine defaultEngine = new WindowsPowerShellEngine();
        this.terminalSystem = new WebTerminalSystem(defaultEngine);
    }

    public String runCommand(String commandText) {
        return terminalSystem.run(commandText);
    }

    public void switchEngine(String engineType) {
        if ("mock".equalsIgnoreCase(engineType)) {
            terminalSystem.setEngine(new TestMockEngine());
        } else {
            terminalSystem.setEngine(new WindowsPowerShellEngine());
        }
    }

    public String getCurrentEngineName() {
        return terminalSystem.toString();
    }
}