package parser;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * This is a j unit class which will test specific functions with the Recognizer class.
 * These functions are: program, declarations, subprogram_declaration, statement, simple_expression, factor.
 * each test there will be 2 paths: a good pascal code- pascal is written without any errors
 * bad pascal path - will error if the pascal code is written with errors
 * @author Erik Ayavaca-Tirado
 *
 */

public class RecognizerTest {
	
	/**
	 * This is testing the happy path of the program function
	 */
	@Test
	public void programPass() {
		System.out.println("Program pass test");
		String Pass = "program foo ; begin end .";
		Recognizer r = new Recognizer(Pass,false);
		try {
			r.program();
		}
		catch(Exception e) {
			fail(e.getMessage());		
		}
	}
	
	/**
	 * This is testing the happy path of the declarations function
	 */
	@Test
	public void declarationsPass() {
		System.out.println("declarations pass test");
		String Pass = "var pikachu : integer ; var zekrom : real;";
		Recognizer r = new Recognizer(Pass,false);
		try {
			r.declarations();
		}
		catch(Exception e) {
			fail(e.getMessage());		
		}
	}

}
