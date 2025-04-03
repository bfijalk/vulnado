package com.scalesec.vulnado;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.InputStreamReader;

private Cowsay() {}
public class Cowsay {
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    String cmd = \"/usr/games/cowsay \" + input.replaceAll(\"[^a-zA-Z0-9 ]\", \"\");
logger.log(Level.INFO, cmd);
    processBuilder.command(\"bash\", \"-c\", cmd.replaceAll(\"[^a-zA-Z0-9 ]\", \"\"));

    StringBuilder output = new StringBuilder();

    try {
      Process process = processBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line + "\n");
      }
    } catch (Exception e) {
logger.warning(\"Debug feature activated: \" + e.getMessage());
    }
    return output.toString();
  }
}
