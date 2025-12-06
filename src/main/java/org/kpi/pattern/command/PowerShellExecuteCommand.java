package org.kpi.pattern.command;

import org.kpi.service.PowerShellService;

/**
 * ConcreteCommand.
 * Пов'язує дію (execute) з одержувачем (PowerShellService).
 */
public class PowerShellExecuteCommand implements Command {

    private final PowerShellService service;
    private final String script;

    public PowerShellExecuteCommand(PowerShellService service, String script) {
        this.service = service;
        this.script = script;
    }

    @Override
    public String execute() {
        return service.runCommand(script);
    }

    public String getCommandText() {
        return script;
    }
}
