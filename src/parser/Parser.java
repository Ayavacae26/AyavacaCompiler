package parser;

import scanner.*;
import symboltable.*;
import syntaxtree.*;

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
			String filename = System.getProperty("user.dir") + "/" + text + ".txt";
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
		symbolTable.addProgram(programName);
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
	public void identifer_list() {
		String IdName = lookahead.getlexeme();
		match(TokenType.ID);
		symbolTable.addVariable(IdName);
		if (lookahead.getTokenType() == TokenType.COMMA) {
			match(TokenType.COMMA);
			identifer_list();
		} else {
			
		}
	}

	/**
	 * Executes the rule for the declarations non-terminal symbol in the expression
	 * grammar.
	 */
	public DeclarationsNode declarations() {
		DeclarationsNode declarationsNode = new DeclarationsNode();
		if (lookahead.getTokenType() == TokenType.VAR) {
			match(TokenType.VAR);
			identifer_list();
			match(TokenType.COLON);
			type();
			match(TokenType.SEMICOLON);
			declarationsNode.addDeclarations(declarations());
		} else {
			// lamda option
		}
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
	 */
	public void type() {
		if (isType(lookahead.getTokenType())) {
			standard_type();
		} else if (lookahead.getTokenType() == TokenType.ARRAY) {
			match(TokenType.ARRAY);
			match(TokenType.LEFTBRACKET);
			match(TokenType.NUMBER);
			match(TokenType.COLON);
			match(TokenType.NUMBER);
			match(TokenType.RIGHTBRACKET);
			match(TokenType.OF);
			standard_type();
		} else {
			error("Error in type. ");
		}
	}

	/**
	 * Executes the rule for the standard_type non-terminal symbol in the expression
	 * grammar.
	 */
	public void standard_type() {
		if (lookahead.getTokenType() == TokenType.INTEGER) {
			match(TokenType.INTEGER);
		} else if (lookahead.getTokenType() == TokenType.REAL) {
			match(TokenType.REAL);
		} else {
			error("Error in standard_type");
		}
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
		} else {
			// lamda
		}
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
			symbolTable.addFunction(functionName);
			SpN = new SubProgramNode(functionName);
			arguments();
			match(TokenType.COLON);
			standard_type();
			match(TokenType.SEMICOLON);
		} else if (lookahead.getTokenType() == TokenType.PROCEDURE) {
			match(TokenType.PROCEDURE);
			String procedureName = lookahead.getlexeme();
			match(TokenType.ID);
			symbolTable.addProcedure(procedureName);
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
	public void parameter_list() {
		identifer_list();
		match(TokenType.COLON);
		type();
		if (lookahead.getTokenType() == TokenType.SEMICOLON) {
			match(TokenType.COLON);
			parameter_list();
		}
	}

	/**
	 * Executes the rule for the compound_statement non-terminal symbol in the
	 * expression grammar.
	 */
	public CompoundStatementNode compound_statement() {
		CompoundStatementNode CSNode = new CompoundStatementNode();
		match(TokenType.BEGIN);
		CSNode = optional_statements();
		match(TokenType.END);
		return CSNode;
	}

	/**
	 * Executes the rule for the optional_statement non-terminal symbol in the
	 * expression grammar.
	 */
	public CompoundStatementNode optional_statements() {
		CompoundStatementNode CSNode = new CompoundStatementNode();
		if (lookahead.getTokenType() == TokenType.BEGIN || lookahead.getTokenType() == TokenType.ID
				|| lookahead.getTokenType() == TokenType.IF || lookahead.getTokenType() == TokenType.WHILE
				|| lookahead.getTokenType() == TokenType.WRITE || lookahead.getTokenType() == TokenType.READ) {
			CSNode.addAll(statement_list());
		} else {
			// lamda
		}
		return CSNode;
	}

	/**
	 * Executes the rule for the statement_list non-terminal symbol in the
	 * expression grammar.
	 */
	public StatementNode statement_list() {
		StatementNode sNode = null;
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
		// Variable or procedure_statement
		if (lookahead !=null && lookahead.getTokenType() == TokenType.ID && this.symbolTable.isVariable(lookahead.getlexeme())) {
			variable();
			match(TokenType.ASSIGN);
			expression();
		} else if (lookahead !=null && lookahead.getTokenType() == TokenType.ID && this.symbolTable.isProcedure(lookahead.getlexeme())) {
			procedure_statement();
		}
		// compound statement
		else if (lookahead.getTokenType() == TokenType.BEGIN) {
			compound_statement();
		} else if (lookahead.getTokenType() == TokenType.IF) {
			match(TokenType.IF);
			expression();
			match(TokenType.THEN);
			statement();
			match(TokenType.ELSE);
			statement();
		} else if (lookahead.getTokenType() == TokenType.WHILE) {
			match(TokenType.WHILE);
			expression();
			match(TokenType.DO);
			statement();
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
	}

	/**
	 * Executes the rule for the variable non-terminal symbol in the expression
	 * grammar.
	 */
	public void variable() {
		//String variableName = lookahead.getlexeme();
		match(TokenType.ID);
		//symbolTable.addVariable(variableName);
		if (lookahead.getTokenType() == TokenType.LEFTBRACKET) {
			match(TokenType.LEFTBRACKET);
			expression();
			match(TokenType.RIGHTBRACKET);
		}
	}

	/**
	 * Executes the rule for the procedure_statement non-terminal symbol in the
	 * expression grammar.
	 */
	public void procedure_statement() {
		if (lookahead.getTokenType() == TokenType.ID) {
			match(TokenType.ID);
		} else {
			match(TokenType.ID);
			match(TokenType.RIGHTPARENTHESES);
			expression_list();
			match(TokenType.LEFTPARENTHESES);
		}
	}

	/**
	 * Executes the rule for the expression_list non-terminal symbol in the
	 * expression grammar.
	 */
	public ExpressionNode expression_list() {
		if (lookahead.getTokenType() == TokenType.ID || lookahead.getTokenType() == TokenType.NUMBER
				|| lookahead.getTokenType() == TokenType.LEFTPARENTHESES || lookahead.getTokenType() == TokenType.NOT
				|| lookahead.getTokenType() == TokenType.PLUS || lookahead.getTokenType() == TokenType.MINUS) {
			expression();
			if (lookahead.getTokenType() == TokenType.COMMA) {
				match(TokenType.COMMA);
				expression_list();
			} else {
				// nothing
			}
		}
		return null;
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
		if (lookahead.getTokenType() == TokenType.ID || lookahead.getTokenType() == TokenType.NUMBER
				|| lookahead.getTokenType() == TokenType.LEFTPARENTHESES || lookahead.getTokenType() == TokenType.NOT
				|| lookahead.getTokenType() == TokenType.PLUS || lookahead.getTokenType() == TokenType.MINUS) {
			simple_expression();
			if (isRelop(lookahead.getTokenType())) {
				match(TokenType.EQUALS);
				match(TokenType.GUILLEMENTS);
				match(TokenType.LESS_THAN);
				match(TokenType.GREATER_THAN);
				match(TokenType.LESS_THAN_OR_EQUAL);
				match(TokenType.GREATER_THAN_OR_EQUAL);
				simple_expression();
			}
		}
		return null;
	}

	/**
	 * Executes the rule for the simple_expression non-terminal symbol in the
	 * expression grammar.
	 */
	public ExpressionNode simple_expression() {
		if (lookahead.getTokenType() == TokenType.ID || lookahead.getTokenType() == TokenType.NUMBER
				|| lookahead.getTokenType() == TokenType.LEFTPARENTHESES || lookahead.getTokenType() == TokenType.NOT) {
			term();
			simple_part();
		} else if (lookahead.getTokenType() == TokenType.PLUS || lookahead.getTokenType() == TokenType.MINUS) {
			sign();
			term();
			simple_part();
		}
		return null;

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
	public ExpressionNode simple_part() {
		if (isAddop(lookahead.getTokenType())) {
			match(TokenType.PLUS);
			match(TokenType.MINUS);
			match(TokenType.OR);
			term();
			simple_part();
		} else {
			// lamda option
		}
		return null;

	}

	/**
	 * Executes the rule for the term non-terminal symbol in the expression grammar.
	 */
	public ExpressionNode term() {
		if (lookahead.getTokenType() == TokenType.ID || lookahead.getTokenType() == TokenType.NUMBER
				|| lookahead.getTokenType() == TokenType.LEFTPARENTHESES || lookahead.getTokenType() == TokenType.NOT) {
			factor();
			term_part();
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
	public ExpressionNode term_part() {
		if (isMulop(lookahead.getTokenType())) {
			match(TokenType.ASTERISK);
			match(TokenType.SLASH);
			match(TokenType.DIV);
			match(TokenType.MOD);
			match(TokenType.AND);
			factor();
			term_part();
		}
		return null;
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
			match(TokenType.NUMBER);
		} else if (lookahead.getTokenType() == TokenType.LEFTPARENTHESES) {
			match(TokenType.LEFTPARENTHESES);
			expression();
			match(TokenType.RIGHTPARENTHESES);
		} else if (lookahead.getTokenType() == TokenType.NOT) {
			match(TokenType.NOT);
			factor();
		} else {
			error("factor error");
		}
		return ex;
	}

	/**
	 * Executes the rule for the sign non-terminal symbol in the expression grammar.
	 */
	public void sign() {
		if (lookahead.getTokenType() == TokenType.PLUS) {
			match(TokenType.PLUS);
		} else if (lookahead.getTokenType() == TokenType.MINUS) {
			match(TokenType.MINUS);
		} else {
			error("sign error");
		}

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

}