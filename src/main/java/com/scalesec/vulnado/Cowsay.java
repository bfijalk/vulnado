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
    logger.info(cmd);
    processBuilder.command(\"bash\", \"-c\", sanitizeCommand(cmd));

    StringBuilder output = new StringBuilder();

    try {
      Process process = processBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line + "\n");
      }
    } catch (Exception e) {
      logger.log(Level.SEVERE, \"An exception occurred\", e);
    }
    return output.toString();
  }
private static String sanitizeCommand(String command) {\n    // Implement sanitization logic here\n    return command.replaceAll(\"[^a-zA-Z0-9 ]\", \"\");\n}
}
