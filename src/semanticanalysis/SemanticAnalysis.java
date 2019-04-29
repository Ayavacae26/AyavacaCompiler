package semanticanalysis;

import java.util.ArrayList;
import java.util.HashMap;
import parser.*;
import symboltable.*;
import symboltable.SymbolTable.Type;
import syntaxtree.*;


/**
 * In this following class the following must be achived 
 * Make sure all variables are declared before they are used.
 * Assign a datatype, integer or real, to each expression (declare a type field in ExpressionNode, for example). ---
 * The type of any expression node in the tree should be printed in the tree's indentedToString.
 * Make sure that types match across assignment.
 * 
 * @author erikayavaca
 * 
 */
public class SemanticAnalysis {
	
	ProgramNode proNode = null;
	SymbolTable symbol = null;
	
	private HashMap<String , Type> varTypes = new HashMap<String , Type>();
	
	public  SemanticAnalysis(ProgramNode proNode,SymbolTable symbol) {
		this.proNode = proNode;
		this.symbol = symbol;
	}
	
	public ProgramNode analysis() {
		ArrayList<VariableNode> varNodes = proNode.getVariables().getDeclarations();
		for(int i = 0 ;i < varNodes.size();i++) {
			varTypes.put(varNodes.get(i).getName(), varNodes.get(i).getType());
		}
		
	}
	
	
	
	
	
	
}
