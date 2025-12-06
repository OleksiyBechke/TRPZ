package org.kpi.pattern.bridge;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * ConcreteImplementor A: Реальне виконання в середовищі Windows.
 */
public class WindowsPowerShellEngine implements ExecutionEngine {

    @Override
    public String execute(String commandText) {
        StringBuilder output = new StringBuilder();
        try {
            ProcessBuilder builder = new ProcessBuilder("powershell.exe", "/c", commandText);
            builder.redirectErrorStream(true);
            Process process = builder.start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), Charset.forName("CP866"))
            );

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            process.waitFor();
        } catch (Exception e) {
            return "Error executing command in Windows Engine: " + e.getMessage();
        }
        return output.toString();
    }
}