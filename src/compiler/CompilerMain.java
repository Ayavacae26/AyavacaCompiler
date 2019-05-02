package compiler;
import parser.*;



/**
 * This is the main that will run a test program and save the symboltable of a
 * program root directory of user 
 * @author erikayavaca
 *
 */

public class CompilerMain {

	public static void main(String[] args) {
		//String filename = "Money.txt";
		String filename = args[0];
		//Parser par = new Parser(filename, true);
		try {
			Recognizer recognizer = new Recognizer(filename, true);
			recognizer.program();
			recognizer.writeToFile();
			System.out.println("recognizer works");
			Parser par = new Parser(filename, true);
			par.program();
		    par.writeToFile();
		    System.out.println("parser works");
		    
			System.out.println("file ran with no errors");
			
		} catch (Exception e) {

		}

	}

}
