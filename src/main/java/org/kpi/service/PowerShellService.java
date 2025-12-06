package org.kpi.service;

import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Receiver (Одержувач).
 * Знає, як фактично запустити процес PowerShell.
 */
@Service
public class PowerShellService {

    public String runCommand(String commandText) {
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
            return "Error executing command: " + e.getMessage();
        }
        return output.toString();
    }
}