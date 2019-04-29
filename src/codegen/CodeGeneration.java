package codegen;

import scanner.TokenType;
import syntaxtree.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class will create code for an Equation tree.
 * 
 * @author Erik Ayavaca-Tirado
 */
public class CodeGeneration {

	private int currentTRegister = 0;


	/**
	 * Making a .asm file based on read in file
	 * 
	 * @param filename
	 * @param root
	 */
	public void writeCodeToFile(String filename, ProgramNode root) {
		PrintWriter write;
		try {
			write = new PrintWriter(
					new BufferedWriter(new FileWriter(filename.substring(0, filename.length() - 4) + ".asm")));
			write.println(writeCodeForRoot(root));
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Assembly code started
	 * 
	 * @param root
	 * @return
	 */
	public  String writeCodeForRoot(ProgramNode root) {
		String code = "";
		code += "    .data\n\n" + "promptuser:    .asciiz \"Enter value: \"" + "\n" + "newline:       .asciiz \"\\n\""
				+ "\n";
		if (root.getVariables() != null) {
			code += writeCode(root.getVariables());
		}

		if (root.getFunctions() != null) {
			for (SubProgramNode spN : root.getFunctions().getProcs()) {
				if (spN.getVariables() != null) {
					code += writeCode(spN.getVariables());
				}
			}
		}
		code += "\n\n    .text\nmain:\n";
		if (root.getFunctions() != null) {
			for (SubProgramNode spN : root.getFunctions().getProcs()) {
				code += writeCode(spN) + "\n";
			}
		}

		if (root.getMain() != null) {
			code += writeCode(root.getMain());
		}
		code += "\njr $ra";
		return (code);
	}
/**
 * For Compound StatementNode 
 * @param node
 * @return
 */
	public String writeCode(CompoundStatementNode node) {
		String code = "";
		ArrayList<StatementNode> state = node.getStateNodes();
		for (StatementNode sN : state) {
			code += writeCode(sN);
		}
		return (code);
	}
	public String writeCode(StatementNode node) {
		String nodeCode = null;
		if (node instanceof AssignmentStatementNode) {
			nodeCode = writeCode((AssignmentStatementNode) node);
		} else if (node instanceof ProcedureStatementNode) {
			nodeCode = writeCode((ProcedureStatementNode) node);
		} else if (node instanceof CompoundStatementNode) {
			nodeCode = writeCode((CompoundStatementNode) node);
		}
		return (nodeCode);
	}
	
	/**
	 * Code for assignment statementNode
	 * Still needs to be implemented 
	 */
	public String writeCode(AssignmentStatementNode node) {
		String code ="";
		
		return code;
		
	}
	
	/**
	 * Code for ProcedureStatementNode
	 * Still needs to be implemented 
	 */
	public String writeCode(ProcedureStatementNode node) {
		String code ="";
		
		return code;
		
	}

	/**
	 * Code for SubProgramNode
	 * @param 
	 * @return
	 */
	public String writeCode(SubProgramNode node) {
		String code = "";
		if (node.getFunctions() != null) {
			for (SubProgramNode spN : node.getFunctions().getProcs()) {
				code += writeCode(spN);
			}
		}
		if (node.getMain() != null) {
			code += writeCode(node.getMain());
		}
		return (code);	}


	 /** Code gen for DeclarationsNode
	 * @param node
	 * @return
	 */
	public String writeCode(DeclarationsNode node) {
		String code = "";
		ArrayList<VariableNode> variables = node.getVariable();
		for (VariableNode vN : variables) {
			code += String.format("%-10s     .word 0\n", vN.getName() + ":");
		}
		return (code);
	}
	
	

	/**
	 * Writes code for the given node. This generic write code takes any
	 * ExpressionNode, and then recasts according to subclass type for dispatching.
	 * 
	 * @param node
	 *            The node for which to write code.
	 * @param reg
	 *            The register in which to put the result.
	 * @return
	 */
	public String writeCode(ExpressionNode node, String reg) {
		String nodeCode = null;
		if (node instanceof OperationNode) {
			nodeCode = writeCode((OperationNode) node, reg);
		} else if (node instanceof ValueNode) {
			nodeCode = writeCode((ValueNode) node, reg);
		}else if (node instanceof VariableNode) {
			nodeCode = writeCode((VariableNode) node, reg);
		}
		return (nodeCode);
	}

	/**
	 * Writes code for an operations node. The code is written by gathering the
	 * child nodes' answers into a pair of registers, and then executing the op on
	 * those registers, placing the result in the given result register.
	 * 
	 * @param opNode
	 *            The operation node to perform.
	 * @param resultRegister
	 *            The register in which to put the result.
	 * @return The code which executes this operation.
	 */
	public String writeCode(OperationNode opNode, String resultRegister) {
		String code;
		ExpressionNode left = opNode.getLeft();
		String leftRegister = "$t" + currentTRegister++;
		code = writeCode(left, leftRegister);
		ExpressionNode right = opNode.getRight();
		String rightRegister = "$t" + currentTRegister++;
		code += writeCode(right, rightRegister);
		TokenType kindOfOp = opNode.getOperation();
		if (kindOfOp == TokenType.PLUS) {
			// add resultregister, left, right
			code += "add    " + resultRegister + ",   " + leftRegister + ",   " + rightRegister + "\n";
		}
		if (kindOfOp == TokenType.MINUS) {
			// add resultregister, left, right
			code += "sub    " + resultRegister + ",   " + leftRegister + ",   " + rightRegister + "\n";
		}
		if (kindOfOp == TokenType.ASTERISK) {
			code += "mult   " + leftRegister + ",   " + rightRegister + "\n";
			code += "mflo   " + resultRegister + "\n";
		}
		this.currentTRegister -= 2;
		return (code);
	}

	/**
	 * Writes code for a value node. The code is written by executing an add
	 * immediate with the value into the destination register. Writes code that
	 * looks like addi $reg, $zero, value
	 * 
	 * @param valNode
	 *            The node containing the value.
	 * @param resultRegister
	 *            The register in which to put the value.
	 * @return The code which executes this value node.
	 */
	public String writeCode(ValueNode valNode, String resultRegister) {
		String value = valNode.getAttribute();
		String code = "addi   " + resultRegister + ",   $zero, " + value + "\n";
		return (code);
	}

	/**
	 * For a variable node.
	 * 
	 * @param vNode
	 * @param resultRegister
	 * @return
	 */
	public  String writeCode(VariableNode vNode, String resultRegister) {
		String name = vNode.getName();
		String code = "lw      " + resultRegister + ",   " + name + "\n";
		return (code);
	}

}