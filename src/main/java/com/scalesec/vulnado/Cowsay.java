package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Cowsay {
private Cowsay() {}
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    String cmd = "/usr/games/cowsay '" + input + "'";
    Logger logger = Logger.getLogger(Cowsay.class.getName());
logger.info(cmd);
    String sanitizedInput = sanitizeInput(input);
String cmd = "/usr/games/cowsay '" + sanitizedInput + "'";
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
      logger.log(Level.SEVERE, "Exception occurred", e);
    }
    return output.toString();
  }
}
