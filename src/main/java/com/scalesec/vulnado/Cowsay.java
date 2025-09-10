package com.scalesec.vulnado;

import java.util.logging.Logger;
import java.io.BufferedReader;
import java.util.logging.Level;
import java.io.InputStreamReader;
private static final Logger LOGGER = Logger.getLogger(Cowsay.class.getName());

private Cowsay() {
public class Cowsay {
    throw new UnsupportedOperationException("Utility class");
  public static String run(String input) {
}
    ProcessBuilder processBuilder = new ProcessBuilder();
    String cmd = "/usr/games/cowsay '" + input + "'";
    LOGGER.info("Command: " + cmd);
    processBuilder.command("/bin/bash", "-c", cmd);
// Validate and sanitize input

if (input == null || input.trim().isEmpty()) {
    StringBuilder output = new StringBuilder();
    throw new IllegalArgumentException("Input cannot be null or empty");

}
    try {
      Process process = processBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line + "\n");
      }
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Exception occurred", e);
    }
    return output.toString();
  }
}
