package syntaxtree;

import static org.junit.Assert.*;
import org.junit.Test;
import scanner.*;

/**
 * stand alone junit test for building a syntax tree.
 * @author Erik Ayavaca
 */
public class SyntaxTreeTest {
	/**
	 * builds a full tree such as the money.pas example and tests the
	 * indentedToString method
	 */
	@Test
	public void testMoneyEx() {
		ProgramNode pNode = new ProgramNode("sample");
		DeclarationsNode dNode = new DeclarationsNode();
		
	
		
		
		
	////////Taken from prof's test money.pas program
	String actualString = pNode.indentedToString( 0);
	
	 String expectedString = "Program: sample\n" +
	"|-- Declarations\n" +
	"|-- --- Name: dollars\n" +
	"|-- --- Name: yen\n" +
	"|-- --- Name: bitcoins\n" +
	"|-- SubProgramDeclarations\n" +
	"|-- Compound Statement\n" +
	"|-- --- Assignment\n" +
	"|-- --- --- Name: dollars\n" +
	"|-- --- --- Value: 1000000\n" +
	"|-- --- Assignment\n" +
	"|-- --- --- Name: yen\n" +
	"|-- --- --- Operation: MULTIPLY\n" +
	"|-- --- --- --- Name: dollars\n" +
	"|-- --- --- --- Value: 110\n" +
	"|-- --- Assignment\n" +
	"|-- --- --- Name: bitcoins\n" +
	"|-- --- --- Operation: DIVIDE\n" +
	"|-- --- --- --- Name: dollars\n" +
	"|-- --- --- --- Value: 3900\n";


	 assertEquals( expectedString, actualString);
	
	
	
	
	}

}
