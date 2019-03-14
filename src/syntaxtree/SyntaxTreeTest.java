package syntaxtree;

import static org.junit.Assert.*;
import org.junit.Test;
import scanner.*;

/**
 * stand alone junit test for building a syntax tree.
 * 
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
		// Defining currency as variable nodes, this is done for Dollars,yen and bitcoin
		VariableNode dollars = new VariableNode("dollars");
		VariableNode yen = new VariableNode("yen");
		VariableNode bitcoin = new VariableNode("bitcoin");
		// Adding the variable nodes to dNode
		dNode.addVariable(dollars);
		dNode.addVariable(yen);
		dNode.addVariable(bitcoin);
		// adding the dnodes to pNode
		pNode.setVariables(dNode);

		// subprogramDeclations nodes and compoundStatement nodes
		SubProgramDeclarationsNode SPDNode = new SubProgramDeclarationsNode();
		pNode.setFunctions(SPDNode);
		CompoundStatementNode CSNode = new CompoundStatementNode();

		// 1st assignment dealing with dollars
		AssignmentStatementNode aNodeDollars = new AssignmentStatementNode();
		// setting aNodeDollars to lvalue of dollars
		aNodeDollars.setLvalue(dollars);
		// 1000000 is assigned to aNodeDollars as an expression
		aNodeDollars.setExpression(new ValueNode("1000000"));

		// 2nd assignment dealing with yen
		AssignmentStatementNode aNodeYen = new AssignmentStatementNode();
		// setting aNodeYen to lvalue of yen
		aNodeYen.setLvalue(dollars);
		// creating the times/multiply operation node
		OperationNode yenTimes = new OperationNode(TokenType.ASTERISK);
		yenTimes.setLeft(dollars);
		yenTimes.setRight(new ValueNode("110"));
		// expression of yenTimes to assignment node aNodeYen
		aNodeYen.setExpression(yenTimes);

		// 3rd assignment dealing with bitcoins

		//////// Taken from prof's test money.pas program
		String actualString = pNode.indentedToString(0);

		String expectedString = "Program: sample\n" + "|-- Declarations\n" + "|-- --- Name: dollars\n"
				+ "|-- --- Name: yen\n" + "|-- --- Name: bitcoins\n" + "|-- SubProgramDeclarations\n"
				+ "|-- Compound Statement\n" + "|-- --- Assignment\n" + "|-- --- --- Name: dollars\n"
				+ "|-- --- --- Value: 1000000\n" + "|-- --- Assignment\n" + "|-- --- --- Name: yen\n"
				+ "|-- --- --- Operation: MULTIPLY\n" + "|-- --- --- --- Name: dollars\n"
				+ "|-- --- --- --- Value: 110\n" + "|-- --- Assignment\n" + "|-- --- --- Name: bitcoins\n"
				+ "|-- --- --- Operation: DIVIDE\n" + "|-- --- --- --- Name: dollars\n"
				+ "|-- --- --- --- Value: 3900\n";

		assertEquals(expectedString, actualString);
	}

}
