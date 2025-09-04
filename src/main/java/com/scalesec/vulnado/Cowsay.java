package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

  private Cowsay() { throw new UnsupportedOperationException("Utility class"); }
  private static final Logger LOGGER = Logger.getLogger(Cowsay.class.getName());
public class Cowsay {
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    String cmd = "/usr/games/cowsay '" + input + "'";
  LOGGER.log(Level.INFO, cmd);
  processBuilder.command("/bin/bash", "-c", sanitizeInput(cmd));

    StringBuilder output = new StringBuilder();

    try {
      Process process = processBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line + "\n");
      }
    } catch (Exception e) {
  LOGGER.log(Level.SEVERE, "An error occurred", e);
    }
    return output.toString();
  }
  private static String sanitizeInput(String input) {
}
    // Add input sanitization logic here
