package com.scalesec.vulnado;

import java.io.BufferedReader;
import java.util.logging.Logger;
import java.io.InputStreamReader;

  private Cowsay() {
public class Cowsay {
    // Private constructor to prevent instantiation
  public static String run(String input) {
  }
    ProcessBuilder processBuilder = new ProcessBuilder();
    String cmd = "/usr/games/cowsay '" + input + "'";
    Logger logger = Logger.getLogger(Cowsay.class.getName());
    processBuilder.command("bash", "-c", "/usr/games/cowsay", input);

    StringBuilder output = new StringBuilder();

    try {
      Process process = processBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line + "\n");
      }
    } catch (Exception e) {
    }
    return output.toString();
  }
}
