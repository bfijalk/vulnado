package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;

private static final Logger LOGGER = Logger.getLogger(Cowsay.class.getName());
private Cowsay() {
public class Cowsay {
    // Private constructor to prevent instantiation
  public static String run(String input) {
}
    ProcessBuilder processBuilder = new ProcessBuilder();
    String cmd = "/usr/games/cowsay '" + input + "'";
    LOGGER.info(cmd);
if (input == null || input.trim().isEmpty() || input.contains(";") || input.contains("&")) {
    processBuilder.command("bash", "-c", cmd);
    throw new IllegalArgumentException("Invalid input provided");

}
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
