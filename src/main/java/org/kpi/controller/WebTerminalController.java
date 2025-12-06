package org.kpi.controller;

import org.kpi.pattern.abstractFactory.WebUIFactory;
import org.kpi.pattern.abstractFactory.dark.DarkUIFactory;
import org.kpi.pattern.abstractFactory.light.LightUIFactory;
import org.kpi.service.CommandInvoker;
import org.kpi.pattern.command.PowerShellExecuteCommand;
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

    // Зберігаємо поточний стан вибору двигуна для відображення в UI
    private String currentEngine = "windows";

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

    @PostMapping("/change-engine")
    public String changeEngine(@RequestParam String engineType) {
        this.currentEngine = engineType;
        powerShellService.switchEngine(engineType);
        return "redirect:/";
    }

    private void setupModel(Model model) {
        var strategy = themeContext.getCurrentStrategy();
        String themeName = strategy.getName().toLowerCase();

        WebUIFactory uiFactory;
        if (themeName.contains("dark")) {
            uiFactory = new DarkUIFactory();
        } else {
            uiFactory = new LightUIFactory();
        }

        String promptHtml = uiFactory.createPrompt().renderHtml();
        String buttonHtml = uiFactory.createButton().renderHtml();

        model.addAttribute("theme", strategy);
        model.addAttribute("currentThemeName", themeName.contains("dark") ? "dark" : "light");

        model.addAttribute("currentEngine", currentEngine);

        model.addAttribute("history", commandInvoker.getHistory());
        model.addAttribute("promptHtml", promptHtml);
        model.addAttribute("buttonHtml", buttonHtml);
    }
}