package org.kpi.controller;

import org.kpi.service.ThemeContext;
import org.kpi.strategy.WebThemeStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebTerminalController {

    private final ThemeContext themeContext;

    public WebTerminalController(ThemeContext themeContext) {
        this.themeContext = themeContext;
    }

    @GetMapping("/")
    public String index(Model model) {
        // Отримуємо поточну стратегію
        WebThemeStrategy strategy = themeContext.getCurrentStrategy();

        // Передаємо параметри стратегії у View (HTML)
        model.addAttribute("theme", strategy);

        // Передаємо назву поточної теми для відображення у селекті
        model.addAttribute("currentThemeName", strategy.getClass().getSimpleName().toLowerCase().contains("dark") ? "dark" : "light");

        return "index";
    }

    @PostMapping("/change-theme")
    public String changeTheme(@RequestParam String themeName) {
        // Зміна стратегії "на льоту"
        themeContext.setStrategy(themeName);
        return "redirect:/";
    }
}