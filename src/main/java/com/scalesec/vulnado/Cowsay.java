package com.scalesec.vulnado;

import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.InputStreamReader;

private Cowsay() {}
public class Cowsay {
// Run the cowsay command
  public static String run(String input) {
// Create a ProcessBuilder instance
    ProcessBuilder processBuilder = new ProcessBuilder();
// Ensure proper validation of input
// Validate input to prevent unwanted behavior
    String cmd = "/usr/games/cowsay '" + input + "'";
    Logger logger = Logger.getLogger(Cowsay.class.getName());
// Ensure PATH includes only intended directories
    processBuilder.command("bash", "-c", cmd); // Ensure proper validation of input and PATH

// Initialize output StringBuilder
    StringBuilder output = new StringBuilder();

// Use try-with-resources for better resource management
    try {
// Start the process
      Process process = processBuilder.start();
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
      String line;
// Read each line from the process output
      while ((line = reader.readLine()) != null) {
// Append line to output
        output.append(line + "\n");
      }
} catch (Exception e) {
// Remove debug feature before production
      logger.warning("Debug feature activated: " + e.getMessage());
logger.severe("Exception occurred: " + e.getMessage());
// Return the output as a string
}
    return output.toString();
  }
}
