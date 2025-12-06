package org.kpi.controller;

import org.kpi.dto.CommandRequest;
import org.kpi.dto.CommandResponse;
import org.kpi.pattern.command.PowerShellExecuteCommand;
import org.kpi.service.CommandInvoker;
import org.kpi.service.PowerShellService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TerminalApiController {

    private final PowerShellService powerShellService;
    private final CommandInvoker commandInvoker;

    public TerminalApiController(PowerShellService powerShellService, CommandInvoker commandInvoker) {
        this.powerShellService = powerShellService;
        this.commandInvoker = commandInvoker;
    }

    @PostMapping("/execute")
    public CommandResponse executeCommand(@RequestBody CommandRequest request) {
        PowerShellExecuteCommand command = new PowerShellExecuteCommand(powerShellService, request.getCommandText());

        // Виконання через Invoker
        String resultHtml = commandInvoker.executeCommand(command);

        return new CommandResponse(resultHtml);
    }
}