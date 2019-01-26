import java.util.HashMap;


public class lookUpTable extends HashMap<String, TokenType>{
	public lookUpTable(){
		HashMap<String, TokenType> Map = new HashMap<String, TokenType>();
			this.put("AND", TokenType.AND);
			this.put("PROGRAM", TokenType.PROGRAM);
			this.put("ARRAY", TokenType.ARRAY);
			this.put("BEGIN", TokenType.BEGIN);
			this.put("DIV", TokenType.DIV);
			this.put("DO", TokenType.DO);
			this.put("ELSE", TokenType.ELSE);
			this.put("FUNCTION", TokenType.FUNCTION);
			this.put("IF", TokenType.IF);
			this.put("INTERGER", TokenType.INTERGER);
			this.put("DIGIT", TokenType.DIGIT);
			this.put("MOD", TokenType.MOD);
			this.put("NOT", TokenType.NOT);
			this.put("OF", TokenType.OF);
			this.put("OR", TokenType.OR);
			this.put("PROCEDURE", TokenType.PROCEDURE);
			this.put("REAL", TokenType.REAL);
			this.put("THEN", TokenType.THEN);
			this.put("VAR", TokenType.VAR);
			this.put("WHILE", TokenType.WHILE);
		
			//SYMBOLS
			this.put(";", TokenType.SEMICOLON);
			this.put(",", TokenType.COMMA);
			this.put(":", TokenType.COLON);
			this.put(".", TokenType.PERIOD);
			this.put("[", TokenType.LEFTBRACKET);
			this.put("]", TokenType.RIGHTBRACKET);
			this.put("(", TokenType.LEFTPARENTHESES);
			this.put(")", TokenType.RIGHTPARENTHESES);
			this.put("+", TokenType.PLUS);
			this.put("-", TokenType.MINUS);
			this.put("=", TokenType.EQUALS);
			this.put("<", TokenType.LESS_THAN);
			this.put("<=", TokenType.LESS_THAN_OR_EQUAL);
			this.put(">", TokenType.GREATER_THAN);
			this.put(">=", TokenType.GREATER_THAN_OR_EQUAL);
			this.put("*",  TokenType.ASTERISK);
			this.put("/", TokenType.SLASH);
			this.put(":=", TokenType.ASSIGN);
			this.put("<>", TokenType.GUILLEMENTS);
	}
}