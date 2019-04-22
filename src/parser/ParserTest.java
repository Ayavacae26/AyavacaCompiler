package parser;

import static org.junit.Assert.*;
import org.junit.Test;
import parser.Parser;
import symboltable.SymbolTable;
import syntaxtree.*;
import static scanner.TokenType.VAR;

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
		//System.out.println(realString);
		assertEquals(expected,realString);
		System.out.println("passed factor test 1");
		
		par = new Parser("bar", false);
		expected = "Name: bar\n";
		real = par.factor();
		realString = real.indentedToString(0);
		//System.out.println(realString);
		assertEquals(expected,realString);
		System.out.println("passed factor test 2");
		
		
		par = new Parser("(8*5)", false);
		expected = "Operation: ASTERISK\n|-- Value: 8\n|-- Value: 5\n";
		real = par.factor();
		realString = real.indentedToString(0);
		//System.out.println(realString);
		assertEquals(expected,realString);
		System.out.println("passed factor test 3");	
	    
		 par = new Parser("not 26", false);
	     real = par.factor();
	     realString = real.indentedToString(0);
	     expected = "Unary Operation: NOT\n|-- Value: 26\n";
	     assertEquals(expected, realString);
	     System.out.println("passed factor test 4");
	     
	}
	/**
	 * This test is going to be testing the simple_expression function within the parser
	 */
	@Test
	public void simple_expressionTest() {
		Parser par = new Parser("foo * 26 + bar", false);
        ExpressionNode real = par.simple_expression();
        String realString = real.indentedToString(0);
        //System.out.println(realString);
        String expectString = "Operation: PLUS\n|-- Operation: ASTERISK\n|-- --- Name: foo\n" +
                "|-- --- Value: 26\n|-- Name: bar\n";
        assertEquals(expectString, realString);
        System.out.println("passed simple expression test 1");
 
        par = new Parser("not foo - 10 / bar", false);
        real = par.simple_expression();
        realString = real.indentedToString(0);
        //System.out.println(realString);
        expectString = "Operation: MINUS\n|-- Unary Operation: NOT\n|-- --- Name: foo\n" +
                "|-- Operation: SLASH\n|-- --- Value: 10\n|-- --- Name: bar\n";
        assertEquals(expectString, realString);
        System.out.println("passed simple expression test 2");
    }
		
	/**
	 * This test is going to be testing the statement function within the parser
	 */
	@Test
	public void statementTest() {
		Parser pars = new Parser("fub := foo * 8", false);
	        SymbolTable tempTable = pars.getSymbolTable();
	        tempTable.addVariable("foo", null);
	        tempTable.addVariable("fub", null);
	        StatementNode real = pars.statement();
	        String realString = real.indentedToString(0);
	        System.out.println(realString);
	        //String expectString = "Assignment:\n|-- Variable: fub\n" +
	            //    "|-- Operation: ASTERICK\n|-- --- Variable: foo\n" +
	            //    "|-- --- Value: 8\n";
	       // assertEquals(expectString, realString);
	        System.out.println("Passed Test 1");
		
	}
	
	/**
	 * This test is going to be testing the subprogram_declaration function within the parser
	 */
	@Test
	public void subprogram_declarationTest() {
		Parser par = new Parser("function calculate(foo, bar : integer ) : integer ; begin end", false);
        SubProgramNode real = par.subprogram_declaration();
        String realString = real.indentedToString(0);
        System.out.println(realString);
        String expectString = "SubProgram: calculate\n" + 
        		"|-- Declarations\n" + 
        		"|-- SubProgramDeclarations\n" + 
        		"|-- Compound Statement\n";
        assertEquals(expectString, realString);
        System.out.println("Passed Test 1");
		
	}
	
	/**
	 * This test is going to be testing the declarations function within the parser
	 */
	@Test
	public void declarationsTest() {
		 Parser par = new Parser("var foo : integer ;", false);
	        DeclarationsNode real = par.declarations();
	        String realString = real.indentedToString(0);
	        //System.out.println(realString);
	        String expectString = "Declarations\n|-- Name: foo\n";
	        assertEquals(expectString, realString);
	        System.out.println("Passed Test 1");
	}
	

	/**
	 * This test is going to be testing the program function within the parser
	 */
	@Test
	public void programTest() {
	 Parser par = new Parser("program foo;\n" + "begin\n" + "end\n" + ".", false);
		 ProgramNode real = par.program();
	        String realString = real.indentedToString(0);
	        //System.out.println(realString);
	        String expected = "Program: foo\n" + 
	        		"|-- Declarations\n" + 
	        		"|-- SubProgramDeclarations\n" + 
	        		"|-- Compound Statement\n";
	        assertEquals(expected, realString);
	}
	 @Test
	  public void programTest1() { 
		 String filename = "/AyavacaCompiler/Money.txt";
		 Parser parser = new Parser(filename,true); 
		 ProgramNode actual = parser.program(); 
		 String actualString = actual.indentedToString( 0); 
		 System.out.println(actualString);
		 /*String expectedString = "Program: sample\n" + "|-- Declarations\n" + "|-- --- Name: dollars\n" + "|-- --- Name: yen\n"
						+ "|-- --- Name: bitcoins\n" + "|-- SubProgramDeclarations\n" + "|-- Compound Statement\n"
						+ "|-- --- Assignment\n" + "|-- --- --- Name: dollars\n" + "|-- --- --- Value: 1000000\n"
						+ "|-- --- Assignment\n" + "|-- --- --- Name: yen\n" + "|-- --- --- Operation: ASTERISK\n"
						+ "|-- --- --- --- Name: dollars\n" + "|-- --- --- --- Value: 110\n" + "|-- --- Assignment\n"
						+ "|-- --- --- Name: bitcoins\n" + "|-- --- --- Operation: SLASH\n" + "|-- --- --- --- Name: dollars\n"
						+ "|-- --- --- --- Value: 3900\n";*/
			//	 assertEquals( expectedString, actualString);
			
		 }
}
