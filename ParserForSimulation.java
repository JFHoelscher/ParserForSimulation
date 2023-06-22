package helloWorld;

import java.util.*;

/**
 * Command Line Parameter Parser for hypothetical simulator.
 * Export as .jar, can be called as 'java -jar simulator.jar <arguments>'
 * 
 * @author johannes
 *
 */

public class ParserForSimulation{

	public static void main(String[] args) {
		
		// List the expected arguments
		Map<String, CommandLineArgument> cmdLineArguments = new HashMap<String, CommandLineArgument>();
		
		cmdLineArguments.put("file", 
				new CommandLineArgument("path to simulation file", true));
		cmdLineArguments.put("duration", 
				new CommandLineArgument("maximal duration in seconds", true));
		
		parseArguments(args, cmdLineArguments);
		
		// Store the arguments and call the simulation
		String filePath = cmdLineArguments.get("file").getValue();
		int duration = Integer.parseInt(cmdLineArguments.get("duration").getValue());
		
		simulate(filePath, duration);
			
	}

	private static void parseArguments(String[] args, Map<String, CommandLineArgument> cmdLineArguments) {
		for (String arg: args) {
			
			if (arg.equals("-h")) {
				printHelp(cmdLineArguments);
				System.exit(0);
			}
			
			if (arg.startsWith("--")) {
				parseKeywordArgument(cmdLineArguments, arg); 
			}
		}
		
		checkRequirements(cmdLineArguments);
	}

	private static void checkRequirements(Map<String, CommandLineArgument> cmdLineArguments) {
		for (String argName: cmdLineArguments.keySet()) {
			CommandLineArgument cmdLineArgument = cmdLineArguments.get(argName);
			if (cmdLineArgument.isRequired() && (! cmdLineArgument.hasValue())) {
				System.out.println("Argument " + argName + " not defined!");
				printHelp(cmdLineArguments);
	            System.exit(1);
			}
		}
	}

	private static void parseKeywordArgument(Map<String, CommandLineArgument> cmdLineArguments, String arg) {
		String[] splittedArg = arg.split("=");
		String argNameWithDashes = splittedArg[0];
		String argName = argNameWithDashes.substring(2);
		String argValue = splittedArg[1];
		
		CommandLineArgument correspondingArgument = cmdLineArguments.get(argName);
		if (correspondingArgument == null){
			System.out.println("Argument " + argName + " not permitted!");
			printHelp(cmdLineArguments);
		    System.exit(1);
		} else {
			correspondingArgument.setValue(argValue);
		}
	}

	private static void printHelp(Map<String, CommandLineArgument> cmdLineArguments) {
		//TODO: [] for optional arguments
		String helpString = "Usage: java -jar simulation.jar";
		for (String argName: cmdLineArguments.keySet()) {
			String argDescription = cmdLineArguments.get(argName).getDescription();
			helpString += " --" + argName + " <" + argDescription + ">";
		}
		System.out.println(helpString);
	}
	
	public static void simulate(String filePath, int duration) {
		//TODO: check whether file exists
		System.out.println("Running the simulation file at " + filePath 
				+ " for " + duration + "seconds.");
	}

}
