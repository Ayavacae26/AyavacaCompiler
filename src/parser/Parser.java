package parser;

import scanner.*;
import symboltable.*;
import symboltable.SymbolTable.Type;
import syntaxtree.*;
import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;

/**
 * The program recognizes whether an input string of tokens is an expression. To
 * use a parser, create an instance pointing at a file, and then call the
 * top-level function, <code>exp()</code>. If the functions returns without an
 * error, the file contains an acceptable expression.
 * 
 * @author Erik Ayavaca-Tirado
 */
public class Parser {

	///////////////////////////////
	// Instance Variables
	///////////////////////////////

	private Token lookahead;

	private MyScanner scanner;

	private SymbolTable symbolTable;

	///////////////////////////////
	// Constructors
	///////////////////////////////

	public Parser(String text, boolean isFilename) {
		symbolTable = new SymbolTable();
		if (isFilename) {
			String filename = text;
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(filename);
			} catch (FileNotFoundException ex) {
				error("No file");
			}
			InputStreamReader isr = new InputStreamReader(fis);
			scanner = new MyScanner(isr);

		} else {
			scanner = new MyScanner(new StringReader(text));
		}
		try {
			lookahead = scanner.nextToken();
		} catch (IOException ex) {
			error("Scan error");
		}

	}

	///////////////////////////////
	// Methods
	///////////////////////////////

	/**
	 * Executes the rule for the program non-terminal symbol in the expression
	 * grammar.
	 */
	public ProgramNode program() {
		match(TokenType.PROGRAM);
		ProgramNode programNode = new ProgramNode(lookahead.getlexeme());
		String programName = lookahead.getlexeme();
		match(TokenType.ID);
		if (!symbolTable.addProgram(programName))
            error("name already exists in symbol table");
		match(TokenType.SEMICOLON);
		programNode.setVariables(declarations());
		programNode.setFunctions(subprogram_declarations());
		programNode.setMain(compound_statement());
		match(TokenType.PERIOD);
		return programNode;
	}

	/**
	 * Executes the rule for the identifer_list non-terminal symbol in the
	 * expression grammar.
	 */
	public ArrayList<String> identifer_list() {
		ArrayList<String> idlist = new ArrayList<String>();
		idlist.add(lookahead.getlexeme());
		match(TokenType.ID);
		if (lookahead.getTokenType() == TokenType.COMMA) {
			match(TokenType.COMMA);
			idlist.addAll(identifer_list());
		} 
		//else
		return idlist;	
		}
	

	/**
	 * Executes the rule for the declarations non-terminal symbol in the expression
	 * grammar.
	 */
	public DeclarationsNode declarations() {
		DeclarationsNode declarationsNode = new DeclarationsNode();
		if (lookahead.getTokenType() == TokenType.VAR) {
			match(TokenType.VAR);
			ArrayList<String> idlist = identifer_list();
			match(TokenType.COLON);
			Type t = type();
			for(int a = 0; a < idlist.size(); a++) {
				declarationsNode.addVariable(new VariableNode(idlist.get(a),null));
				symbolTable.addVariable(idlist.get(a), t);
			}
			//Type t = type(idlist);
			match(TokenType.SEMICOLON);
			declarationsNode.addDeclarations(declarations());
		} //else lamda
		return declarationsNode;
	}

	/**
	 * creating private variable to check if type is valid for 1st part
	 */
	private boolean isType(TokenType input) {
		if (input == TokenType.INTEGER || input == TokenType.REAL) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Executes the rule for the type non-terminal symbol in the expression grammar.
	 * public Type type(ArrayList<String> idlist)// was goint to use for array
	 */
	public Type type() {
		int beginindex;
		int endindex;
		Type t = null;
		if (isType(lookahead.getTokenType())) {
			t = standard_type();
		} else if (lookahead.getTokenType() == TokenType.ARRAY) {
			match(TokenType.ARRAY);
			match(TokenType.LEFTBRACKET);
			beginindex = Integer.parseInt(lookahead.getlexeme());
			match(TokenType.NUMBER);
			match(TokenType.COLON);
			endindex = Integer.parseInt(lookahead.getlexeme());
			match(TokenType.NUMBER);
			match(TokenType.RIGHTBRACKET);
			match(TokenType.OF);
			t = standard_type();
			/*for(String list : idlist) {
				symbolTable.addArray(list, t, beginindex, endindex);
			}*/
		} else {
			error("Error in type. ");
		}
		return t;
	}

	/**
	 * Executes the rule for the standard_type non-terminal symbol in the expression
	 * grammar.
	 */
	public Type standard_type() {
		Type t = null;
		if (lookahead.getTokenType() == TokenType.INTEGER) {
			t = Type.INTEGER;
			match(TokenType.INTEGER);
		} else if (lookahead.getTokenType() == TokenType.REAL) {
			t = Type.REAL;
			match(TokenType.REAL);
		} else {
			error("Error in standard_type");
		}
		return t;
	}

	/**
	 * Executes the rule for the subprogram_declarations non-terminal symbol in the
	 * expression grammar.
	 */
	public SubProgramDeclarationsNode subprogram_declarations() {
		SubProgramDeclarationsNode SPDNode = new SubProgramDeclarationsNode();
		if (lookahead.getTokenType() == TokenType.FUNCTION || lookahead.getTokenType() == TokenType.PROCEDURE) {
			SPDNode.addSubProgramDeclaration(subprogram_declaration());
			match(TokenType.SEMICOLON);
			SPDNode.addall(subprogram_declarations().getProcs());
		}//else lamda
		return SPDNode;
	}

	/**
	 * Executes the rule for the subprogram_declaration non-terminal symbol in the
	 * expression grammar.
	 */
	public SubProgramNode subprogram_declaration() {
		SubProgramNode SPN = subprogram_head();
		SPN.setVariables(declarations());
		SPN.setFunctions(subprogram_declarations());
		SPN.setMain(compound_statement());
		return SPN;
	}

	/**
	 * Executes the rule for the subprogram_head non-terminal symbol in the
	 * expression grammar.
	 */
	public SubProgramNode subprogram_head() {
		  SubProgramNode SpN = null;
		  if (lookahead.getTokenType() == TokenType.FUNCTION) {
			match(TokenType.FUNCTION);
			String functionName = lookahead.getlexeme();
			match(TokenType.ID);
			 if (!symbolTable.addFunction(functionName, null)) 
	            error(functionName + " already in symbol table");
			SpN = new SubProgramNode(functionName);
			arguments();
			match(TokenType.COLON);
			Type t = standard_type();
			symbolTable.setType(functionName, t);
			match(TokenType.SEMICOLON);
		} else if (lookahead.getTokenType() == TokenType.PROCEDURE) {
			match(TokenType.PROCEDURE);
			String procedureName = lookahead.getlexeme();
			match(TokenType.ID);
			 if (!symbolTable.addProcedure(procedureName))
				 error(procedureName + " already in symbol table");
			SpN = new SubProgramNode(procedureName);
			arguments();
			match(TokenType.SEMICOLON);
		} else {
			error("Error in subprogram_head.");
		}
		return SpN;
	}

	/**
	 * Executes the rule for the subprogram_head non-terminal symbol in the
	 * expression grammar.
	 */
	public void arguments() {
		if (lookahead.getTokenType() == TokenType.LEFTPARENTHESES) {
			match(TokenType.LEFTPARENTHESES);
			parameter_list();
			match(TokenType.RIGHTPARENTHESES);
		} else {
			// lamda option
		}
	}

	/**
	 * Executes the rule for the parameter_list non-terminal symbol in the
	 * expression grammar.
	 */
	public ArrayList<VariableNode> parameter_list() {
		ArrayList<String> idlist = identifer_list();
		ArrayList<VariableNode> args = new ArrayList<VariableNode>();
		match(TokenType.COLON);
		type();
		if (lookahead.getTokenType() == TokenType.SEMICOLON) {
			match(TokenType.COLON);
			parameter_list();
		}
		return args;
	}

	/**
	 * Executes the rule for the compound_statement non-terminal symbol in the
	 * expression grammar.
	 */
	public CompoundStatementNode compound_statement() {
		CompoundStatementNode csNode = new CompoundStatementNode();
		match(TokenType.BEGIN);
		ArrayList<StatementNode> statementList = optional_statements();
		
		// add statements one by one into compound statement node 
		for(StatementNode statement: statementList)
		{
			csNode.addStatement(statement);
		}
		
		match(TokenType.END);
		return csNode;
	}

	/**
	 * Executes the rule for the optional_statement non-terminal symbol in the
	 * expression grammar.
	 */
	public ArrayList<StatementNode> optional_statements() {
//		CompoundStatementNode CSNode = new CompoundStatementNode();
		ArrayList<StatementNode> optional = new ArrayList<StatementNode>();
		
		if (lookahead.getTokenType() == TokenType.BEGIN || lookahead.getTokenType() == TokenType.ID
				|| lookahead.getTokenType() == TokenType.IF || lookahead.getTokenType() == TokenType.WHILE
				|| lookahead.getTokenType() == TokenType.WRITE || lookahead.getTokenType() == TokenType.READ) {
			optional = statement_list();
		}// else lamda
		return optional;
	}

	
	/**
	 * Executes the rule for the statement_list non-terminal symbol in the
	 * expression grammar.
	 */
	public ArrayList<StatementNode> statement_list() {
		 ArrayList<StatementNode> sNode = new ArrayList<StatementNode>();
		if (lookahead.getTokenType() == TokenType.BEGIN || lookahead.getTokenType() == TokenType.ID
				|| lookahead.getTokenType() == TokenType.IF || lookahead.getTokenType() == TokenType.WHILE
				|| lookahead.getTokenType() == TokenType.WRITE || lookahead.getTokenType() == TokenType.READ) {
			sNode.add(statement());
			if (lookahead.getTokenType() == TokenType.SEMICOLON) {
				match(TokenType.SEMICOLON);
				sNode.addAll(statement_list());
			} else {
				// nothing
			}
		}
		return sNode;
	}

	/**
	 * Executes the rule for the statement non-terminal symbol in the expression
	 * grammar.
	 */
	public StatementNode statement() {
		StatementNode sNode = null;
		// Variable or procedure_statement
		if (lookahead !=null && lookahead.getTokenType() == TokenType.ID ) {
			//System.out.println(symbolTable.toString());
			if(this.symbolTable.isVariable(lookahead.getlexeme())) {
			AssignmentStatementNode assign = new AssignmentStatementNode();
			VariableNode varNode = variable();
			assign.setLvalue(varNode);
			match(TokenType.ASSIGN);
			ExpressionNode exp = expression();
			assign.setExpression(exp);
				if(varNode.getType() != exp.getType()) error("mismatch at " + varNode.getName());
			return assign;
			}
		 else if (this.symbolTable.isProcedure(lookahead.getlexeme())) {
			return procedure_statement();
		} else error(lookahead.getlexeme() + " not in symbol table");
        }
		// compound statement
		else if (lookahead.getTokenType() == TokenType.BEGIN) {
			sNode = compound_statement();
		} else if (lookahead.getTokenType() == TokenType.IF) {
			IfStatementNode ifNode = new IfStatementNode();
			match(TokenType.IF);
			ifNode.setTest(expression());
			match(TokenType.THEN);
			ifNode.setThenStatement(statement());
			match(TokenType.ELSE);
			ifNode.setElseStatement(statement());
			return ifNode;
		} else if (lookahead.getTokenType() == TokenType.WHILE) {
			WhileStatementNode whileNode = new WhileStatementNode();
			match(TokenType.WHILE);
			whileNode.setTest(expression());
			match(TokenType.DO);
			whileNode.setDoStatement(statement());
			return whileNode;
		} else if (lookahead.getTokenType() == TokenType.READ) {
			match(TokenType.READ);
			match(TokenType.LEFTPARENTHESES);
			match(TokenType.ID);
			match(TokenType.RIGHTPARENTHESES);
		} else if (lookahead.getTokenType() == TokenType.WRITE) {
			match(TokenType.WRITE);
			match(TokenType.LEFTPARENTHESES);
			expression();
			match(TokenType.RIGHTPARENTHESES);
		} else if (lookahead.getTokenType() == TokenType.RETURN) {
			match(TokenType.RETURN);
			expression();
		} else {
			error("Error in statement");
		}
		return sNode;
	}

	/**
	 * Executes the rule for the variable non-terminal symbol in the expression
	 * grammar.
	 */
	public VariableNode variable() {
		String variableName = lookahead.getlexeme();
		VariableNode var = new VariableNode(variableName);
		var.setType(symbolTable.getType(variableName));
		match(TokenType.ID);
		//symbolTable.addVariable(variableName);
		if (lookahead.getTokenType() == TokenType.LEFTBRACKET) {
			match(TokenType.LEFTBRACKET);
			expression();
			match(TokenType.RIGHTBRACKET);
		}
		return var;
	}

	/**
	 * Executes the rule for the procedure_statement non-terminal symbol in the
	 * expression grammar.
	 */
	public ProcedureStatementNode procedure_statement() {
		ProcedureStatementNode proNode = new ProcedureStatementNode();
		String prodName = lookahead.getlexeme();
		proNode.setVariable(new VariableNode(lookahead.getlexeme(),null));
		match(TokenType.ID);
	    if (lookahead.getTokenType() == TokenType.LEFTPARENTHESES){
			match(TokenType.LEFTPARENTHESES);
			proNode.addAllExpNode(expression_list());
			match(TokenType.RIGHTPARENTHESES);
		}
		return proNode;
	}

	/**
	 * Executes the rule for the expression_list non-terminal symbol in the
	 * expression grammar.
	 */
	public  ArrayList<ExpressionNode> expression_list() {
		ArrayList<ExpressionNode> exp = new ArrayList();
		if (lookahead.getTokenType() == TokenType.ID || lookahead.getTokenType() == TokenType.NUMBER
				|| lookahead.getTokenType() == TokenType.LEFTPARENTHESES || lookahead.getTokenType() == TokenType.NOT
				|| lookahead.getTokenType() == TokenType.PLUS || lookahead.getTokenType() == TokenType.MINUS) {
			exp.add(expression());
			if (lookahead.getTokenType() == TokenType.COMMA) {
				match(TokenType.COMMA);
				exp.addAll(expression_list());
			} else {
				// nothing
			}
		}
		return exp;
	}

	private boolean isRelop(TokenType input) {
		if (input == TokenType.EQUALS || input == TokenType.GUILLEMENTS || input == TokenType.LESS_THAN
				|| input == TokenType.GREATER_THAN || input == TokenType.LESS_THAN_OR_EQUAL
				|| input == TokenType.GREATER_THAN_OR_EQUAL)
			return true;
		else
			return false;
	}

	/**
	 * Executes the rule for the expression non-terminal symbol in the expression
	 * grammar.
	 */
	public ExpressionNode expression() {
		ExpressionNode left = null;
		if (lookahead.getTokenType() == TokenType.ID || lookahead.getTokenType() == TokenType.NUMBER
				|| lookahead.getTokenType() == TokenType.LEFTPARENTHESES || lookahead.getTokenType() == TokenType.NOT
				|| lookahead.getTokenType() == TokenType.PLUS || lookahead.getTokenType() == TokenType.MINUS) {
			left = simple_expression();
			if (isRelop(lookahead.getTokenType())) {
				OperationNode op = new OperationNode(lookahead.getTokenType());
				match(lookahead.getTokenType());
				op.setLeft(left);
				op.setRight(simple_expression());
				return op;
			}
		}
		return left;
	}

	/**
	 * Executes the rule for the simple_expression non-terminal symbol in the
	 * expression grammar.
	 */
	public ExpressionNode simple_expression() {
		ExpressionNode ex = null;
		if (lookahead.getTokenType() == TokenType.ID || lookahead.getTokenType() == TokenType.NUMBER
				|| lookahead.getTokenType() == TokenType.LEFTPARENTHESES || lookahead.getTokenType() == TokenType.NOT) {
			ex = term();
			ex = simple_part(ex);
		} else if (lookahead.getTokenType() == TokenType.PLUS || lookahead.getTokenType() == TokenType.MINUS) {
			UnaryOperationNode op = sign();
			ex = term();
			op.setExpression(simple_part(ex));
		}
		return ex;

	}

	private boolean isAddop(TokenType input) {
		if (input == TokenType.PLUS || input == TokenType.MINUS || input == TokenType.OR
				|| input == TokenType.GREATER_THAN)
			return true;
		else
			return false;

	}

	/**
	 * Executes the rule for the simple_part non-terminal symbol in the expression
	 * grammar.
	 */
	public ExpressionNode simple_part(ExpressionNode posLeft) {
		if (isAddop(lookahead.getTokenType())) {
			OperationNode op = new OperationNode(lookahead.getTokenType());
			match(lookahead.getTokenType());
			ExpressionNode right = term();
			op.setLeft(posLeft);
			op.setRight(right);
			return simple_part(op);
		} else {
			// lamda option
		}
		return posLeft;

	}

	/**
	 * Executes the rule for the term non-terminal symbol in the expression grammar.
	 */
	public ExpressionNode term() {
		if (lookahead.getTokenType() == TokenType.ID || lookahead.getTokenType() == TokenType.NUMBER
				|| lookahead.getTokenType() == TokenType.LEFTPARENTHESES || lookahead.getTokenType() == TokenType.NOT) {
			ExpressionNode left = factor();
			return term_part(left);
		}
		return null;
	}

	private boolean isMulop(TokenType input) {
		if (input == TokenType.ASTERISK || input == TokenType.SLASH || input == TokenType.DIV || input == TokenType.MOD
				|| input == TokenType.AND)
			return true;
		else
			return false;

	}

	/**
	 * Executes the rule for the term_part non-terminal symbol in the expression
	 * grammar.
	 */
	public ExpressionNode term_part(ExpressionNode posLeft) {
		if (isMulop(lookahead.getTokenType())) {
			OperationNode op = new OperationNode(lookahead.getTokenType());
			match(lookahead.getTokenType());
			ExpressionNode right = factor();
			op.setLeft(posLeft);
			op.setRight(term_part(right));
			return op;
		}
		return posLeft;
	}

	/**
	 * Executes the rule for the factor non-terminal symbol in the expression
	 * grammar.
	 */
	public ExpressionNode factor() {
		ExpressionNode ex = null;
		if (lookahead.getTokenType() == TokenType.ID) {
			ex = new VariableNode(lookahead.getlexeme());
			match(TokenType.ID);
			if (lookahead.getTokenType() == TokenType.LEFTBRACKET) {
				match(TokenType.LEFTBRACKET);
				expression();
				match(TokenType.RIGHTBRACKET);
			} else if (lookahead.getTokenType() == TokenType.LEFTPARENTHESES) {
				match(TokenType.LEFTPARENTHESES);
				expression_list();
				match(TokenType.RIGHTPARENTHESES);
			}
		} else if (lookahead.getTokenType() == TokenType.NUMBER) {
			String number = lookahead.getlexeme();
			ValueNode value = new ValueNode(number);
			match(TokenType.NUMBER);
			return value;
		} else if (lookahead.getTokenType() == TokenType.LEFTPARENTHESES) {
			match(TokenType.LEFTPARENTHESES);
			ex = expression();
			match(TokenType.RIGHTPARENTHESES);
		} else if (lookahead.getTokenType() == TokenType.NOT) {
			UnaryOperationNode op = new UnaryOperationNode(TokenType.NOT);
			match(TokenType.NOT);
			ex = factor();
			op.setExpression(ex);
			return op;
		} else {
			error("factor error");
		}
		return ex;
	}

	/**
	 * Executes the rule for the sign non-terminal symbol in the expression grammar.
	 */
	public UnaryOperationNode sign() {
		 UnaryOperationNode op = null;
		if (lookahead.getTokenType() == TokenType.PLUS) {
			op = new UnaryOperationNode(TokenType.PLUS);
			match(TokenType.PLUS);
		} else if (lookahead.getTokenType() == TokenType.MINUS) {
			match(TokenType.MINUS);
			op = new UnaryOperationNode(TokenType.MINUS);
		} else {
			error("sign error");
		}
		return op;
	}

	/**
	 * Matches the expected token. If the current token in the input stream from the
	 * scanner matches the token that is expected, the current token is consumed and
	 * the scanner will move on to the next token in the input. The null at the end
	 * of the file returned by the scanner is replaced with a fake token containing
	 * no type.
	 * 
	 * @param expected
	 *            The expected token type.
	 */
	public void match(TokenType expected) {
		System.out.println("match( " + expected + ")");
		if (this.lookahead.getTokenType() == expected) {
			try {
				this.lookahead = scanner.nextToken();
				if (this.lookahead == null) {
					this.lookahead = new Token("End of File", null);
				}
			} catch (IOException ex) {
				error("Scanner exception");
			}
		} else {
			error("Match of " + expected + " found " + this.lookahead.getTokenType() + " instead.");
		}
	}

	/**
	 * Errors out of the parser. Prints an error message and then exits the program.
	 * 
	 * @param message
	 *            The error message to print.
	 */
	public void error(String message) {
		System.out.println("Error" + message);
		throw new RuntimeException("error");
		// System.exit( 1);
	}

	/**
	 * this function will saves a a filename in the directory where it is ran
	 * 
	 * @param filename is the filename for filecreated
	 */
	public void writeToFile() {
		PrintWriter printWriter;
		try {
			printWriter = new PrintWriter(new BufferedWriter(new FileWriter(System.getProperty("user.dir") + "/" + "output.symboltable")));
			printWriter.println(this.symbolTable.toString());
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public SymbolTable getSymbolTable() {
        return symbolTable;
    }

}