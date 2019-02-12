package parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import scanner.MyScanner;
import scanner.Token;
import scanner.TokenType;

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
    	match(TokenType.ID);
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
    	if(lookahead.getTokenType() == TokenType.ID) {
    		match(TokenType.ID);
    	}
    	else {
    		match(TokenType.ID);
    		match(TokenType.COMMA);
    		identifer_list();
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
     * Executes the rule for the exp non-terminal symbol in
     * the expression grammar.
     */
    public void exp() {
        term();
        exp_prime();
    }
    
    /**
     * Executes the rule for the exp & prime; non-terminal symbol in
     * the expression grammar.
     */
    public void exp_prime() {
        if( lookahead.getTokenType() == TokenType.PLUS || 
                lookahead.getTokenType() == TokenType.MINUS ) {
            addop();
            term();
            exp_prime();
        }
        else{
            // lambda option
        }
    }
    
    /**
     * Executes the rule for the addop non-terminal symbol in
     * the expression grammar.
     */
    public void addop() {
        if( lookahead.getTokenType() == TokenType.PLUS) {
            match(TokenType.PLUS);
        }
        else if( lookahead.getTokenType() == TokenType.MINUS) {
            match(TokenType.MINUS);
        }
        else {
            error( "Addop");
        }
    }
    
    /**
     * Executes the rule for the term non-terminal symbol in
     * the expression grammar.
     */
    public void term() {
        factor();
        term_prime();
    }
    
    /**
     * Executes the rule for the term&prime; non-terminal symbol in
     * the expression grammar.
     */
    public void term_prime() {
        if( isMulop( lookahead) ) {
            mulop();
            factor();
            term_prime();
        }
        else{
            // lambda option
        }
    }
    
    /**
     * Determines whether or not the given token is
     * a mulop token.
     * @param token The token to check.
     * @return true if the token is a mulop, false otherwise
     */
    private boolean isMulop( Token token) {
        boolean answer = false;
        if( token.getTokenType() == TokenType.ASTERISK || 
                token.getTokenType() == TokenType.SLASH ) {
            answer = true;
        }
        return answer;
    }
    
    /**
     * Executes the rule for the mulop non-terminal symbol in
     * the expression grammar.
     */
    public void mulop() {
        if( lookahead.getTokenType() == TokenType.ASTERISK) {
            match( TokenType.ASTERISK);
        }
        else if( lookahead.getTokenType() == TokenType.SLASH) {
            match(TokenType.SLASH);
        }
        else {
            error( "Mulop");
        }
    }
    
    /**
     * Executes the rule for the factor non-terminal symbol in
     * the expression grammar.
     */
    public void factor() {
        // Executed this decision as a switch instead of an
        // if-else chain. Either way is acceptable.
        switch (lookahead.getTokenType()) {
            case LEFTPARENTHESES:
                match( TokenType.LEFTPARENTHESES);
                exp();
                match( TokenType.RIGHTPARENTHESES);
                break;
            case NUMBER:
                match(TokenType.NUMBER);
                break;
            default:
                error("Factor");
                break;
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
        //System.exit( 1);
    }
}
