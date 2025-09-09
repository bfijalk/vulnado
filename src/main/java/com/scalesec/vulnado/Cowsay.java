package com.scalesec.vulnado;

import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.InputStreamReader;

private static final Logger LOGGER = Logger.getLogger(Cowsay.class.getName());
private Cowsay() {
    // Prevent instantiation
}
public class Cowsay {
if (input == null || input.trim().isEmpty() || input.contains(";") || input.contains("&")) {
    throw new IllegalArgumentException("Invalid input provided");
}
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    String cmd = "/usr/games/cowsay " + input.trim();
    LOGGER.info(cmd);
    processBuilder.command("bash", "-c", cmd);

    StringBuilder output = new StringBuilder();

    try {
      Process process = processBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line + "\n");
      }
    } catch (Exception e) {
      LOGGER.severe("An error occurred: " + e.getMessage());
    }
    return output.toString();
  }
}
