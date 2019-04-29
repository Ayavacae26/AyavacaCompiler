package semanticanalysis;

import java.util.ArrayList;
import java.util.HashMap;
import symboltable.*;
import symboltable.SymbolTable.Type;
import syntaxtree.*;

/**
 * In this following class the following must be achived Make sure all variables
 * are declared before they are used. Assign a datatype, integer or real, to
 * each expression (declare a type field in ExpressionNode, for example). ---
 * The type of any expression node in the tree should be printed in the tree's
 * indentedToString. Make sure that types match across assignment.
 * 
 * @author erikayavaca
 * 
 */
public class SemanticAnalysis {

	ProgramNode proNode = null;
	SymbolTable symbol = null;

	private HashMap<String, Type> varTypes = new HashMap<String, Type>();

	public SemanticAnalysis(ProgramNode proNode, SymbolTable symbol) {
		this.proNode = proNode;
		this.symbol = symbol;
	}

	/**
	 * Taking in varablenode and type and putting them into a hashmap
	 * 
	 * @return
	 */
	public ProgramNode analyzer() {
		ArrayList<VariableNode> varNodes = proNode.getVariables().getDeclarations();
		for (int i = 0; i < varNodes.size(); i++) {
			varTypes.put(varNodes.get(i).getName(), varNodes.get(i).getType());
		}
		// Verifying method call
		verifyVar();
		CompoundStatementNode cNode = proNode.getMain();
		// calls on the assigner method
		assignTypes(cNode);
		for (int i = 0; i < proNode.getFunctions().getProcs().size(); i++) {
			CompoundStatementNode tempCNode = proNode.getFunctions().getProcs().get(i).getMain();
			assignTypes(tempCNode);
		}
		return proNode;
	}

	/**
	 * assigns either type real or integer
	 * 
	 * @param cNode
	 */
	private void assignTypes(CompoundStatementNode cNode) {

		ArrayList<StatementNode> statementList = cNode.getStateNodes();
		for (StatementNode sNode : statementList) {
			if (sNode instanceof AssignmentStatementNode) {
				setTypes(((AssignmentStatementNode) sNode).getExpression());
				if ((varTypes.get(((AssignmentStatementNode) sNode).getLvalue().getName()) != null)) {
					((AssignmentStatementNode) sNode).getLvalue()
							.setType(varTypes.get(((AssignmentStatementNode) sNode).getLvalue().getName()));
				}
			} else if (sNode instanceof IfStatementNode) {
				setTypes(((IfStatementNode) sNode).getTest());
				StatementNode then = (((IfStatementNode) sNode).getThenStatement());
				StatementNode elseStatement = (((IfStatementNode) sNode).getElseStatement());
				CompoundStatementNode ifThen = new CompoundStatementNode();
				ifThen.addStatement(then);
				ifThen.addStatement(elseStatement);
				assignTypes(ifThen);
			} else if (sNode instanceof CompoundStatementNode) {
				assignTypes(((CompoundStatementNode) sNode));
			}
		}

	}

	/**
	 * Setting the expression type( interger,real) for the nodes
	 * 
	 * @param expression
	 */
	private void setTypes(ExpressionNode expression) {
		if (getLeftPos(expression) instanceof OperationNode) {
			setTypes(getLeftPos(expression));
		} else if (getLeftPos(expression) instanceof VariableNode || getLeftPos(expression) instanceof ValueNode) {
			setVariable(getLeftPos(expression));
		}

		if (getRightPos(expression) instanceof OperationNode) {
			setTypes(getRightPos(expression));
		} else if (getRightPos(expression) instanceof VariableNode || getRightPos(expression) instanceof ValueNode) {
			setVariable(getRightPos(expression));
		}

		if (expression instanceof OperationNode) {

			if (getLeftPos(expression).getType() == Type.REAL || getRightPos(expression).getType() == Type.REAL) {
				expression.setType(Type.REAL);
			} else {
				expression.setType(Type.INTEGER);
			}

		}

	}

	/**
	 * setter
	 */
	private void setVariable(ExpressionNode expression) {

		if (expression instanceof ValueNode) {
			if (((ValueNode) expression).getAttribute().contains(".")) {
				expression.setType(Type.REAL);
			} else {
				expression.setType(Type.INTEGER);
			}
		}

	}

	/**
	 * getter
	 */
	private ExpressionNode getRightPos(ExpressionNode expression) {
		ExpressionNode answer = null;

		if (expression instanceof OperationNode) {
			answer = ((OperationNode) expression).getRight();
		}
		return answer;
	}

	/**
	 * getter
	 */
	private ExpressionNode getLeftPos(ExpressionNode expression) {
		ExpressionNode answer = null;

		if (expression instanceof OperationNode) {
			answer = ((OperationNode) expression).getLeft();
		}
		return answer;
	}

	private void verifyVar() {
		ArrayList<String> declaredNames = new ArrayList<String>();
		for (int i = 0; i < proNode.getVariables().getDeclarations().size(); i++) {
			declaredNames.add(proNode.getVariables().getDeclarations().get(i).getName());
		}
		ArrayList<String> usedNames = proNode.getVarNames();
		for (int i = 0; i < usedNames.size(); i++) {
			if (!declaredNames.contains(usedNames.get(i))) {
				System.out.println("Error with declaring variables");
			}
		}

	}

}
