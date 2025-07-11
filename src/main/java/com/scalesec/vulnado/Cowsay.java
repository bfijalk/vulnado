package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;

private Cowsay() {}
public class Cowsay {
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    String cmd = "/usr/games/cowsay '" + input + "'";
logger.info(cmd);
    Logger logger = Logger.getLogger(Cowsay.class.getName());
    processBuilder.command("bash", "-c", sanitizeCommand(cmd));

    StringBuilder output = new StringBuilder();

    try {
      Process process = processBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line + "\n");
      }
    } catch (Exception e) {
      logger.warning("Debugging information: " + e.getMessage());
    }
    return output.toString();
  }
}
  return command.replaceAll("[^a-zA-Z0-9 ]", "");
  // Implement sanitization logic here
private static String sanitizeCommand(String command) {
}
