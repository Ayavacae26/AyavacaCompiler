package compiler;

import parser.Recognizer;

/**
 * This is the main that will run a test program and save the symboltable of a
 * program root directory of user 
 * @author erikayavaca
 *
 */

public class CompilerMain {

	public static void main(String[] args) {
		String filename = "CompilerTest";
		Recognizer recognizer = new Recognizer(filename, true);

		try {
			recognizer.program();
			recognizer.writeToFile();
			System.out.println("file ran with no errors");
		} catch (Exception e) {

		}

	}

}
