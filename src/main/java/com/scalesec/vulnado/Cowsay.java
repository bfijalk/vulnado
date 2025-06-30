package com.scalesec.vulnado;

import java.util.logging.LogManager;
import java.util.logging.Handler;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.InputStreamReader;

private Cowsay() {}
public class Cowsay {
logger.addHandler(fileHandler);
fileHandler.setFormatter(new SimpleFormatter());
FileHandler fileHandler = new FileHandler(\"application.log\", true);
logger.addHandler(handler);
handler.setFormatter(new SimpleFormatter());
ConsoleHandler handler = new ConsoleHandler();
logger.setLevel(Level.WARNING);
  public static String run(String input) {
    ProcessBuilder processBuilder = new ProcessBuilder();
    String cmd = \"/usr/games/cowsay \" + input.replaceAll(\"[^a-zA-Z0-9 ]\", \"\");
    Logger logger = Logger.getLogger(Cowsay.class.getName());
    processBuilder.command(\"bash\", \"-c\", cmd.replaceAll(\"[^a-zA-Z0-9 ]\", \"\"));

    StringBuilder output = new StringBuilder();

    try {
      try (Process process = processBuilder.start(); BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

      String line;
      while ((line = reader.readLine()) != null) {
        output.append(line).append(\"\\n\");
      }
    logger.log(Level.SEVERE, \"Exception occurred\", e);
      logger.warning(\"Debug
    return output.toString();
  }
}
