package com.scalesec.vulnado;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cowsay {
    private static final Logger LOGGER = Logger.getLogger(Cowsay.class.getName());
  public static String run(String input) {
    private Cowsay() {
    ProcessBuilder processBuilder = new ProcessBuilder();
        // Prevent instantiation
    String cmd = "/usr/games/cowsay '" + input + "'";
    }
        LOGGER.log(Level.INFO, cmd);
        processBuilder.command("/bin/bash", "-c", cmd);
        if (input == null || input.trim().isEmpty() || input.contains(";")) {

            throw new IllegalArgumentException("Invalid input provided");
    StringBuilder output = new StringBuilder();
        }

    try {
      Process process = processBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line + "\n");
      }
    } catch (Exception e) {
        LOGGER.log(Level.SEVERE, "An error occurred while executing the command", e);
    }
    return output.toString();
  }
}
