package parser;

import static org.junit.Assert.*;
import org.junit.Test;
import parser.Parser;
import symboltable.SymbolTable;
import syntaxtree.*;

/**
 * This is a junit test class testing the parser
 * we are going to be testing factor(), simple_expression(), 
 * statement(), subprogram_declaration(), declarations(), and program().
 * @author erikayavaca
 *
 */

public class ParserTest {

	/**
	 * This test is going to be testing the factor function within the parser
	 */
	@Test
	public void factorTest() {
		Parser par = new Parser("26", false);
		String expected = "Value: 26\n";
		ExpressionNode real = par.factor();
		String realString = real.indentedToString(0);
		System.out.println(realString);
		assertEquals(expected,realString);
		System.out.println("passed factor test 1");
		
		par = new Parser("bar", false);
		expected = "Name: bar\n";
		real = par.factor();
		realString = real.indentedToString(0);
		System.out.println(realString);
		assertEquals(expected,realString);
		System.out.println("passed factor test 2");
		
		
		par = new Parser("(8*5)", false);
		expected = "Operation: ASTERISK\n|-- Value: 8\n|-- Value: 5\n";
		real = par.factor();
		realString = real.indentedToString(0);
		System.out.println(realString);
		assertEquals(expected,realString);
		System.out.println("passed factor test 3");	
	    
		 par = new Parser("not 26", false);
	     real = par.factor();
	     realString = real.indentedToString(0);
	     expected = "Unary Operation: NOT\n|-- Value: 26\n";
	     assertEquals(expected, realString);
	     System.out.println("Passed Test 4");
	}
	/**
	 * This test is going to be testing the simple_expression function within the parser
	 */
	@Test
	public void simple_expressionTest() {
		Parser par = new Parser("foo * 10 + bar", false);
        ExpressionNode real = par.simple_expression();
        String realString = real.indentedToString(0);
        System.out.println(realString);
        String expectString = "Operation: PLUS\n|-- Operation: ASTERISK\n|-- --- Name: foo\n" +
                "|-- --- Value: 10\n|-- Name: bar\n";
        assertEquals(expectString, realString);
        System.out.println("Passed Test 1");
		
	}
	/**
	 * This test is going to be testing the statement function within the parser
	 */
	@Test
	public void statementTest() {
		Parser par = new Parser("7", false);
		
	}
	
	/**
	 * This test is going to be testing the subprogram_declaration function within the parser
	 */
	@Test
	public void subprogram_declarationTest() {
		Parser par = new Parser("7", false);
		
	}
	
	/**
	 * This test is going to be testing the declarations function within the parser
	 */
	@Test
	public void declarationsTest() {
		Parser par = new Parser("7", false);
		
	}
	

	/**
	 * This test is going to be testing the program function within the parser
	 */
	@Test
	public void programTest() {
		Parser par = new Parser("7", false);
		
	}
}
