package parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import scanner.MyScanner;
import scanner.Token;
import scanner.TokenType;
import symbol_table.SymbolTable;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;

/**
 * The program recognizes whether an input string of tokens
 * is an expression.
 * To use a parser, create an instance pointing at a file,
 * and then call the top-level function, <code>exp()</code>.
 * If the functions returns without an error, the file
 * contains an acceptable expression.
 * @author Erik Ayavaca-Tirado
 */
public class Recognizer {
    
    ///////////////////////////////
    //    Instance Variables
    ///////////////////////////////
    
    private Token lookahead;
    
    private MyScanner scanner;
    
    private SymbolTable symbolTable;
    
    ///////////////////////////////
    //       Constructors
    ///////////////////////////////
    
    public Recognizer( String text, boolean isFilename) {
        if( isFilename) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("");
        } catch (FileNotFoundException ex) {
            error( "No file");
        }
        InputStreamReader isr = new InputStreamReader( fis);
        scanner = new MyScanner( isr);
                 
        }
        else {
            scanner = new MyScanner( new StringReader( text));
        }
        try {
            lookahead = scanner.nextToken();
        } catch (IOException ex) {
            error( "Scan error");
        }
        
    }
    
    ///////////////////////////////
    //       Methods
    ///////////////////////////////
    
    /**
     * Executes the rule for the program non-terminal symbol in
     * the expression grammar.
     */
    public void program() {
    	match(TokenType.PROGRAM);
    	String programName = lookahead.getlexeme();
    	match(TokenType.ID);
    	if(!symbolTable.addProgram(programName))
    		error("name already exists in symbol");
    	match(TokenType.SEMICOLON);
    	declarations();
    	subprogram_declarations();
    	compound_statement();
    	match(TokenType.PERIOD);
    }
    
    /**
     * Executes the rule for the identifer_list non-terminal symbol in
     * the expression grammar.
     */
    public void identifer_list() {
    	match(TokenType.ID);
    	if(lookahead.getTokenType() == TokenType.COMMA) {
    		match(TokenType.COMMA);
    		identifer_list();
    	}
    	else {
    		
    		}
    }
    
    /**
     * Executes the rule for the declarations non-terminal symbol in
     * the expression grammar.
     */
    public void declarations() {
    	if(lookahead.getTokenType() == TokenType.VAR) {
    		match(TokenType.VAR);
    		identifer_list();
    		match(TokenType.COLON);
    		type();
    		match(TokenType.SEMICOLON);
    		declarations();
    	}
    	else {
    		// lamda option
    	}
    }
    
    /**
     * creating private variable
     * to check if type is valid for 1st part
     */
    private boolean isType(TokenType input) {
    	if (input ==TokenType.INTEGER || input == TokenType.REAL) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    
    /**
     * Executes the rule for the type non-terminal symbol in
     * the expression grammar.
     */
    public void type() {
    	if(isType(lookahead.getTokenType())) {
    		standard_type();
    	}
    	else if(lookahead.getTokenType() == TokenType.ARRAY) {
    		match(TokenType.ARRAY);
    		match(TokenType.LEFTBRACKET);
    		match(TokenType.NUMBER);
    		match(TokenType.COLON);
    		match(TokenType.NUMBER);
    		match(TokenType.RIGHTBRACKET);
    		match(TokenType.OF);
    		standard_type();
    	}
    	else {
    		error("Error in type. ");
    	}
    }
    
    /**
     * Executes the rule for the standard_type non-terminal symbol in
     * the expression grammar.
     */
    public void standard_type() {
    	if(lookahead.getTokenType() == TokenType.INTEGER) {
    		match(TokenType.INTEGER);
    	}
    	else if(lookahead.getTokenType() == TokenType.REAL) {
    		match(TokenType.REAL);
    	}
    	else {
    	error("Error in standard_type");		
    	}
    }
    
    /**
     * Executes the rule for the subprogram_declarations non-terminal symbol in
     * the expression grammar.
     */
    public void subprogram_declarations() {
    if(lookahead.getTokenType() == TokenType.FUNCTION || lookahead.getTokenType() == TokenType.PROCEDURE) {
    	subprogram_declaration();
    	match(TokenType.SEMICOLON);
    	subprogram_declarations();
    }
    else {
    	//lamda
    }
    }
    
    
    /**
     * Executes the rule for the subprogram_declaration non-terminal symbol in
     * the expression grammar.
     */
    public void subprogram_declaration() {
    	subprogram_head();
    	declarations();
    	compound_statement();
    }
    
    /**
     * Executes the rule for the subprogram_head non-terminal symbol in
     * the expression grammar.
     */
    public void subprogram_head() {
		if(lookahead.getTokenType() == TokenType.FUNCTION)
		{
			match(TokenType.FUNCTION);
			String functionName = lookahead.getlexeme();
			match(TokenType.ID);
			if(!symbolTable.addFunction(functionName))
				error("name already exists in symbol table");
			arguments();
			match(TokenType.COLON);
			standard_type();
			match(TokenType.SEMICOLON);
		}
		else if(lookahead.getTokenType() == TokenType.PROCEDURE){
			match(TokenType.PROCEDURE);
			String procedureName = lookahead.getlexeme();
			match(TokenType.ID);
			if(!symbolTable.addProcedure(procedureName))
				error("name already exists in symbol table");
			arguments();
			match(TokenType.SEMICOLON);
		}
		else
		{
			error("Error in subprogram_head.");
		}	
	}
    
    /**
     * Executes the rule for the subprogram_head non-terminal symbol in
     * the expression grammar.
     */
    public void arguments() {
    	if(lookahead.getTokenType() == TokenType.LEFTPARENTHESES) {
    		match(TokenType.LEFTPARENTHESES);
    		parameter_list();
    		match(TokenType.RIGHTPARENTHESES);
    	}
    	else {
    		//lamda option
    	}
    }
    
    /**
     * Executes the rule for the parameter_list non-terminal symbol in
     * the expression grammar.
     */
    public void parameter_list() {
    	identifer_list();
    match(TokenType.COLON);
    type();
    if(lookahead.getTokenType() == TokenType.SEMICOLON){
            match(TokenType.COLON);
            parameter_list();
    }
    }
    
    /**
     * Executes the rule for the compound_statement non-terminal symbol in
     * the expression grammar.
     */
    public void compound_statement() {
    	 match(TokenType.BEGIN);
    	 optional_statements();
    	 match(TokenType.END);
    }
    
    /**
     * Executes the rule for the optional_statement non-terminal symbol in
     * the expression grammar.
     */
    public void optional_statements() {
    	 if(lookahead.getTokenType() == TokenType.BEGIN || lookahead.getTokenType() == TokenType.ID 
    			 || lookahead.getTokenType() == TokenType.IF || lookahead.getTokenType() == TokenType.WHILE || lookahead.getTokenType() == TokenType.WRITE
    			 || lookahead.getTokenType() == TokenType.READ) {
    	 statement_list();
    	 }
    	 else {
    		 //lamda
    	 }
    }
  
    /**
     * Executes the rule for the statement_list non-terminal symbol in
     * the expression grammar.
     */
    public void statement_list() {
    	if(lookahead.getTokenType() == TokenType.BEGIN || lookahead.getTokenType() == TokenType.ID 
   			 || lookahead.getTokenType() == TokenType.IF || lookahead.getTokenType() == TokenType.WHILE || lookahead.getTokenType() == TokenType.WRITE
   			 || lookahead.getTokenType() == TokenType.READ) {
    		statement();
    		if(lookahead.getTokenType() == TokenType.SEMICOLON) {
    			match(TokenType.SEMICOLON);
    			statement_list();
    		}
    		else {
    			// nothing
    		}
    	}
    }
    
    /**
     * Executes the rule for the  statement non-terminal symbol in
     * the expression grammar.
     */
    public void statement() {
    	// Varible or procedure_statement
    	if(lookahead.getTokenType() == TokenType.ID) {
    			variable();
    			match(TokenType.ASSIGN);
    			expression();	
    		}
    		//else {   commented out 
    			//procedure_statement();
    		//}
    	//compound statement
    	else if (lookahead.getTokenType() == TokenType.BEGIN) {
    		compound_statement();	
    	}
    	else if(lookahead.getTokenType() == TokenType.IF) {
    		match(TokenType.IF);
    		expression();
    		match(TokenType.THEN);
    		statement();
    		match(TokenType.ELSE);
    		statement();
    	}
    	else if(lookahead.getTokenType() == TokenType.WHILE) {
    		match(TokenType.WHILE);
    		expression();
    		match(TokenType.DO);
    		statement();
    	}
    else if(lookahead.getTokenType() == TokenType.READ) {
    	match(TokenType.READ);
    	match(TokenType.LEFTPARENTHESES);
    	match(TokenType.ID);
    	match(TokenType.RIGHTPARENTHESES);
    }
    else if(lookahead.getTokenType() == TokenType.WRITE) {
    	match(TokenType.WRITE);
    	match(TokenType.LEFTPARENTHESES);
    	expression();
    	match(TokenType.RIGHTPARENTHESES);
    }
    else if(lookahead.getTokenType() == TokenType.RETURN) {
    	match(TokenType.RETURN);
    expression();
    }
    else
	{
		error("Error in statement");
	}
    }
    
    /**
     * Executes the rule for the variable non-terminal symbol in
     * the expression grammar.
     */
    public void variable() {
    		match(TokenType.ID);
    	if(lookahead.getTokenType() == TokenType.LEFTBRACKET) {
    		match(TokenType.LEFTBRACKET);
    		expression();
    		match(TokenType.RIGHTBRACKET);
    	}
    }
    
    /**
     * Executes the rule for the procedure_statement non-terminal symbol in
     * the expression grammar.
     */
    public void procedure_statement() {
    if(lookahead.getTokenType() == TokenType.ID) {
    		match(TokenType.ID);
    	}
    	else {
    		match(TokenType.ID);
    		match(TokenType.RIGHTPARENTHESES);
    		expression_list();
    		match(TokenType.LEFTPARENTHESES);
    	}
    }
    
    /**
     * Executes the rule for the expression_list non-terminal symbol in
     * the expression grammar.
     */
    public void expression_list() {
    	if(lookahead.getTokenType() == TokenType.ID|| lookahead.getTokenType() == TokenType.NUMBER 
      			 || lookahead.getTokenType() == TokenType.LEFTPARENTHESES || lookahead.getTokenType() == TokenType.NOT || lookahead.getTokenType() == TokenType.PLUS || lookahead.getTokenType() == TokenType.MINUS){
       		expression();
       		if(lookahead.getTokenType() == TokenType.COMMA) {
       			match(TokenType.COMMA);
       			expression_list();
       		}
       		else {
       			// nothing
       		}
       	}		
	}
    
    private boolean isRelop(TokenType input) {
		if(input == TokenType.EQUALS|| 
				input == TokenType.GUILLEMENTS|| 
				input == TokenType.LESS_THAN|| 
				input == TokenType.GREATER_THAN ||
				input == TokenType.LESS_THAN_OR_EQUAL||
				input == TokenType.GREATER_THAN_OR_EQUAL)
			return true;
		else return false;		
	}
    
    /**
     * Executes the rule for the expression non-terminal symbol in
     * the expression grammar.
     */
    public void expression() {
    	if(lookahead.getTokenType() == TokenType.ID|| lookahead.getTokenType() == TokenType.NUMBER || lookahead.getTokenType() == TokenType.LEFTPARENTHESES 
    		|| lookahead.getTokenType() == TokenType.NOT || lookahead.getTokenType() == TokenType.PLUS || lookahead.getTokenType() == TokenType.MINUS){
    		simple_expression();
    		if(isRelop(lookahead.getTokenType())) {
    			match(TokenType.EQUALS);
    			match(TokenType.GUILLEMENTS);
    			match(TokenType.LESS_THAN);
    			match(TokenType.GREATER_THAN);
    			match(TokenType.LESS_THAN_OR_EQUAL);
    			match(TokenType.GREATER_THAN_OR_EQUAL);
    			simple_expression();
    		}
    	}
    }
    
    /**
     * Executes the rule for the simple_expression non-terminal symbol in
     * the expression grammar.
     */
    public void simple_expression() {
    	if(lookahead.getTokenType() == TokenType.ID|| lookahead.getTokenType() == TokenType.NUMBER || lookahead.getTokenType() == TokenType.LEFTPARENTHESES 
    		|| lookahead.getTokenType() == TokenType.NOT){
    		term();
    		simple_part();
    	}
    	else if (lookahead.getTokenType() == TokenType.PLUS || lookahead.getTokenType() == TokenType.MINUS) {
    		sign();
    		term();
    		simple_part();
    	}
    		
    	}
    
    private boolean isAddop(TokenType input) {
		if(input == TokenType.PLUS|| 
				input == TokenType.MINUS|| 
				input == TokenType.OR|| 
				input == TokenType.GREATER_THAN)
			return true;
		else return false;		
    
    }
    
    /**
     * Executes the rule for the simple_part non-terminal symbol in
     * the expression grammar.
     */
    public void simple_part() {
    	if(isAddop(lookahead.getTokenType())) {
			match(TokenType.PLUS);
			match(TokenType.MINUS);
			match(TokenType.OR);
			term();
			simple_part();
		}
    	else {
    		//lamda option
    	}
    	
    }
    
    /** Executes the rule for the term non-terminal symbol in
    * the expression grammar.
    */
   public void term() {
	if(lookahead.getTokenType() == TokenType.ID|| lookahead.getTokenType() == TokenType.NUMBER || lookahead.getTokenType() == TokenType.LEFTPARENTHESES 
    		|| lookahead.getTokenType() == TokenType.NOT) { 
    factor();
    term_part();
   }
} 
   
   private boolean isMulop(TokenType input) {
		if(input == TokenType.ASTERISK || 
				input == TokenType.SLASH || 
				input == TokenType.DIV || 
				input == TokenType.MOD ||
				input ==TokenType.AND)
			return true;
		else return false;		
   
   }
   
   /** Executes the rule for the term_part non-terminal symbol in
    * the expression grammar.
    */
   public void term_part() {
	  	if(isMulop(lookahead.getTokenType())) {
				match(TokenType.ASTERISK);
				match(TokenType.SLASH);
				match(TokenType.DIV);
				match(TokenType.MOD);
				match(TokenType.AND);
				factor();
				term_part();	
	  	}
   }
    
   /** Executes the rule for the factor non-terminal symbol in
    * the expression grammar.
    */
   public void factor() {
	   if(lookahead.getTokenType() == TokenType.ID) {
		   match(TokenType.ID);
		   if(lookahead.getTokenType() == TokenType.LEFTBRACKET) {
			   match(TokenType.LEFTBRACKET);
			   expression();
			   match(TokenType.RIGHTBRACKET);
		   }
		   else if(lookahead.getTokenType() == TokenType.LEFTPARENTHESES) {
			   match(TokenType.LEFTPARENTHESES);
			   expression_list();
			   match(TokenType.RIGHTPARENTHESES);
		   }
	   }
	   else if(lookahead.getTokenType() == TokenType.NUMBER) {
		   match(TokenType.NUMBER);
	   }
	   else if(lookahead.getTokenType() == TokenType.LEFTPARENTHESES) {
		   match(TokenType.LEFTPARENTHESES);
		   expression();
		   match(TokenType.RIGHTPARENTHESES);
	   }
	   else if (lookahead.getTokenType() ==TokenType.NOT) {
		   match(TokenType.NOT);
		   factor();
	   }
	   else {
		   error("factor error");
	   }   
   }
   
   /** Executes the rule for the sign non-terminal symbol in
    * the expression grammar.
    */
   public void sign() {
	   if(lookahead.getTokenType() == TokenType.PLUS) {
		   match(TokenType.PLUS);
	   }
	   else if(lookahead.getTokenType() == TokenType.MINUS) {
		   match(TokenType.MINUS);
	   }
	   else {
		   error("sign error");
	   }
	   
   }
    
    
    /**
     * Matches the expected token.
     * If the current token in the input stream from the scanner
     * matches the token that is expected, the current token is
     * consumed and the scanner will move on to the next token
     * in the input.
     * The null at the end of the file returned by the
     * scanner is replaced with a fake token containing no
     * type.
     * @param expected The expected token type.
     */
    public void match( TokenType expected) {
        System.out.println("match( " + expected + ")");
        if( this.lookahead.getTokenType() == expected) {
            try {
                this.lookahead = scanner.nextToken();
                if( this.lookahead == null) {
                    this.lookahead = new Token( "End of File", null);
                }
            } catch (IOException ex) {
                error( "Scanner exception");
            }
        }
        else {
            error("Match of " + expected + " found " + this.lookahead.getTokenType()
                    + " instead.");
        }
    }
    
    /**
     * Errors out of the parser.
     * Prints an error message and then exits the program.
     * @param message The error message to print.
     */
    public void error( String message) {
        System.out.println("Error" + message);
        throw new RuntimeException("error");
        //System.exit( 1);
    }
}
