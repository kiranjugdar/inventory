package com.jugdar.inventory;

import java.util.Scanner;

import com.jugdar.inventory.service.helper.CommandInterpreter;

public class App {

	public static void main(String[] args) {

		CommandInterpreter commandInterpreter = new CommandInterpreter();
		Scanner scanner = null;

		try {
			// create a scanner so we can read the command-line input
			scanner = new Scanner(System.in).useDelimiter("\\n");

			String command = "";

			while (true) {
				System.out.println("Enter command: ");
				command = scanner.next();
				{
					if (command.equals("quit") || command.equals("q")) {
						System.out.println("Done. Thank you.");
						break;
					}

					commandInterpreter.run(command);

				}
			}
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

	}
}
