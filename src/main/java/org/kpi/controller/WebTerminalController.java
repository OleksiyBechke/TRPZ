package org.kpi.controller;

import org.kpi.pattern.command.PowerShellExecuteCommand;
import org.kpi.service.CommandInvoker;
import org.kpi.service.PowerShellService;
import org.kpi.pattern.strategy.ThemeContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebTerminalController {

    private final ThemeContext themeContext;
    private final PowerShellService powerShellService;
    private final CommandInvoker commandInvoker;

    public WebTerminalController(ThemeContext themeContext,
                                 PowerShellService powerShellService,
                                 CommandInvoker commandInvoker) {
        this.themeContext = themeContext;
        this.powerShellService = powerShellService;
        this.commandInvoker = commandInvoker;
    }

    @GetMapping("/")
    public String index(Model model) {
        setupModel(model);
        return "index";
    }

    @PostMapping("/execute")
    public String execute(@RequestParam String commandText, Model model) {
        PowerShellExecuteCommand command = new PowerShellExecuteCommand(powerShellService, commandText);

        commandInvoker.executeCommand(command);

        return "redirect:/";
    }

    @PostMapping("/change-theme")
    public String changeTheme(@RequestParam String themeName) {
        themeContext.setStrategy(themeName);
        return "redirect:/";
    }

    private void setupModel(Model model) {
        var strategy = themeContext.getCurrentStrategy();
        model.addAttribute("theme", strategy);
        model.addAttribute("currentThemeName", strategy.getClass().getSimpleName().toLowerCase().contains("dark") ? "dark" : "light");

        model.addAttribute("history", commandInvoker.getHistory());
    }
}