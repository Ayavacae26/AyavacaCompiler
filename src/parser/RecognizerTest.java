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
	
	/**
	 * This is testing the happy path of the subprogram_declaration function
	 */
	@Test
	public void subprogram_declarationPass() {
		System.out.println("subprogram_declaration pass test");
		String Pass = "procedure pikachu ; var fo : integer ; begin end";
		Recognizer r = new Recognizer(Pass,false);
		try {
			r.subprogram_declaration();
		}
		catch(Exception e) {
			fail(e.getMessage());		
		}
	}
	
	/**
	 * This is testing the happy path of the statement function
	 */
	@Test
	public void statementPass() {
		System.out.println("statement pass test");
		String Pass = "read ( pikachu10 )";
		Recognizer r = new Recognizer(Pass,false);
		try {
			r.statement();
		}
		catch(Exception e) {
			fail(e.getMessage());		
		}
		
	}
	
	/**
	 * This is the testing for the simple expression( good path)
	 */
		@Test
		public void simple_expressionPass() {
		 System.out.println("simple_expression pass test");
			String Pass = "5";
			Recognizer r = new Recognizer(Pass,false);
			try {
				r.simple_expression();
			}
			catch(Exception e) {
				fail(e.getMessage());		
			}
	 }
		
		/**
		 * This is the testing for the factor( good path)
		 */
			@Test
			public void factorPass() {
			 System.out.println("factor pass test");
				String Pass = "Zekrom";
				Recognizer r = new Recognizer(Pass,false);
				try {
					r.factor();
				}
				catch(Exception e) {
					fail(e.getMessage());		
				}
		 }
	

	/**
	 * This is testing the bad path of the program function
	 */
	@Test
	public void programFail() {
		System.out.println("Program fail test");
		String fail = "foo begin end .";
		Recognizer r = new Recognizer(fail,false);
		try {
			r.program();	
			fail("this pascal should fail");
		}
		catch(Exception e) {
			assertEquals("error", e.getMessage());	
		}
	}
	
	/**
	 * This is testing the bad path of the declarations function
	 */
	@Test
	public void declartionsFail() {
		System.out.println("declarations fail test");
		String fail = "10 999 ";
		Recognizer r = new Recognizer(fail,false);
		try {
			r.declarations();	
			fail("this pascal should fail");
		}
		catch(Exception e) {
			assertEquals("error", e.getMessage());	
		}
	}
	

	/**
	 * This is testing the bad path of the subprogram declaration function
	 */
	@Test
	public void subprogram_declarationFail() {
		System.out.println("subprogram_declaration fail test");
		String fail = " Zekrom function";
		Recognizer r = new Recognizer(fail,false);
		try {
			r.declarations();	
			fail("this pascal should fail");
		}
		catch(Exception e) {
			assertEquals("error", e.getMessage());	
		}
	}
	/**
	 * This is testing the bad path of the statement function
	 */
	@Test
	public void statementFail() {
		System.out.println("statement fail test");
		String fail = "program 12 ";
		Recognizer r = new Recognizer(fail,false);
		try {
			r.declarations();	
			fail("this pascal should fail");
		}
		catch(Exception e) {
			assertEquals("error", e.getMessage());	
		}
	}
	

/**
 * This is testing the bad path of the simple_expression function
 */
@Test
public void simple_expressionFail() {
	System.out.println("simple_expression fail test");
	String fail = "array ";
	Recognizer r = new Recognizer(fail,false);
	try {
		r.declarations();	
		fail("this pascal should fail");
	}
	catch(Exception e) {
		assertEquals("error", e.getMessage());	
	}
}

/**
 * This is testing the bad path of the factor function
 */
@Test
public void factorFail() {
	System.out.println("simple_expression fail test");
	String fail = "program";
	Recognizer r = new Recognizer(fail,false);
	try {
		r.declarations();	
		fail("this pascal should fail");
	}
	catch(Exception e) {
		assertEquals("error", e.getMessage());	
	}
}

	

}
