package com.scalesec.vulnado;

import java.util.logging.Logger;
import java.io.BufferedReader;
import java.util.logging.Level;
import java.io.InputStreamReader;

public class Cowsay {
private static String sanitizeInput(String input) { return input.replaceAll("[^\w\s]", ""); }
private static final Logger LOGGER = Logger.getLogger(Cowsay.class.getName());
private Cowsay() {}
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    String cmd = "/usr/games/cowsay '" + sanitizeInput(input) + "'";
    LOGGER.info(cmd);
    processBuilder.command("/bin/bash", "-c", cmd);

    StringBuilder output = new StringBuilder();

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
