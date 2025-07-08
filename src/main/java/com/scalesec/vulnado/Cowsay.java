package com.scalesec.vulnado;

import java.util.regex.Pattern;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.InputStreamReader;

private Cowsay() {}
public class Cowsay {
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    String cmd = "/usr/games/cowsay '" + input + "'";
logger.info(cmd);
    Logger logger = Logger.getLogger(Cowsay.class.getName());
    processBuilder.command("bash", "-c", sanitizeInput(cmd));

    StringBuilder output = new StringBuilder();

    try {
      Process process = processBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line + "\n");
      }
    } catch (Exception e) {
      logger.warning("Debug feature activated: " + e.getMessage());
    }
    return output.toString();
  }
    return input;
    }
        throw new IllegalArgumentException("Invalid input detected");
    if (!pattern.matcher(input).matches()) {
    Pattern pattern = Pattern.compile("[a-zA-Z0-9 ]*");
private static String sanitizeInput(String input) {
}
