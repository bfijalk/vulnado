package com.scalesec.vulnado;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.InputStreamReader;

private Cowsay() {}
public class Cowsay {
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    String cmd = "/usr/games/cowsay '" + input + "'";
logger.log(Level.INFO, cmd);
// Validate input to prevent command injection
// Ensure PATH is sanitized and does not include unintended directories
    processBuilder.command(\"bash\", \"-c\", \"/usr/games/cowsay '\" + input.replaceAll(\"[\\\\\\\\\"'\\\\\\\\\"]\", \"\") + \"'\");

    StringBuilder output = new StringBuilder();

    try {
      Process process = processBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line + "\n");
      }
    } catch (Exception e) {
logger.log(Level.SEVERE, \"Exception occurred\", e);
// Ensure proper logging for exceptions
// Remove debug feature before production
    }
    return output.toString();
  }
}
