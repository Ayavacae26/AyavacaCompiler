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
		
		par = new Parser("foo", false);
		expected = "Name: foo\n";
		real = par.factor();
		realString = real.indentedToString(0);
		System.out.println(realString);
		assertEquals(expected,realString);
		System.out.println("passed factor test 2");
		
	}
	/**
	 * This test is going to be testing the simple_expression function within the parser
	 */
	@Test
	public void simple_expressionTest() {
		Parser par = new Parser("7", false);
		
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
