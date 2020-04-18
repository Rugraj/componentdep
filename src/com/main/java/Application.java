package com.main.java;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Main program to run the system dependency command
 * 
 * @author Rugraj
 *
 */

public class Application {

	/**
	 * Input commands as steram
	 */
	private static Scanner scanner;

	/**
	 * Result as stream
	 */

	private static Map<String, MyCommand> MYCOMMANDS = new HashMap<>();

	static {

		MYCOMMANDS.put("DEPEND", new DependCommand());
		MYCOMMANDS.put("INSTALL", new InstallCommand());
		MYCOMMANDS.put("REMOVE", new RemoveCommand());
		MYCOMMANDS.put("LIST", new ListCommand());
	}

	/**
	 * main class for get the command from input steram
	 * 
	 * @param commandList not used
	 */
	public static void main(String[] commandList) throws Exception {

		try (Scanner stdin = new Scanner(new BufferedInputStream(System.in))) {
			while (stdin.hasNext()) {
				boolean flag = process(stdin.nextLine());

				if (flag) {
					break;
				}
			}
		}

	}

	/**
	 * Process command
	 * 
	 * @param command
	 * @param outputStream
	 */
	public static boolean process(String command) {
		if (command.equals("END")) {
			return true;
		}
		String[] arguments = command.split("[ ]+");
		MyCommand cmd = MYCOMMANDS.get(arguments[0]);
		if (cmd == null)
			throw new IllegalArgumentException("Unknown command " + command);

		System.out.println(command);
		List<String> commandList = new LinkedList<String>(Arrays.asList(arguments));
		commandList.remove(0); // ditch the command piece
		Map<String, Object> success = cmd.executeCommand(commandList);
		success.entrySet().stream().forEach(e -> System.out.println("\t" + e.getKey() + " " + e.getValue()));
		return false;
	}

}
