package scanner;
import java.util.HashMap;
public class lookUpTable extends HashMap<String, TokenType>{
	public lookUpTable(){
		HashMap<String, TokenType> Map = new HashMap<String, TokenType>();
		//Keywords
                        this.put("program", TokenType.PROGRAM);
                        this.put("while", TokenType.WHILE);
                        this.put("if", TokenType.IF);
                        this.put("integer", TokenType.INTEGER);
                        this.put("div", TokenType.DIV);
                        this.put("mod", TokenType.MOD);
                        this.put("or", TokenType.OR);
                        this.put("and", TokenType.AND);
                        this.put("do", TokenType.DO);
                        this.put("else", TokenType.ELSE);
                        this.put("then", TokenType.THEN);
                        this.put("function", TokenType.FUNCTION);
                        this.put("var", TokenType.VAR);
                        this.put("read", TokenType.READ);
                        this.put("begin", TokenType.BEGIN);
                        this.put("end", TokenType.END);
                        this.put("of", TokenType.OF);
                        this.put("real", TokenType.REAL);
                        this.put("procedure", TokenType.PROCEDURE);
                        this.put("write", TokenType.WRITE);
                        this.put("array", TokenType.ARRAY);
                        this.put("not", TokenType.NOT);
                        this.put("return", TokenType.RETURN);
                
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