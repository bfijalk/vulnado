package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cowsay {
import java.util.logging.Logger;
  public static String run(String input) {
private static final Logger logger = Logger.getLogger(Cowsay.class.getName());
    ProcessBuilder processBuilder = new ProcessBuilder();
private Cowsay() {}
    String cmd = "/usr/games/cowsay '" + input + "'";
    logger.info(cmd);
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
      logger.error("Error executing command.", e);
    }
    return output.toString();
  }
}
