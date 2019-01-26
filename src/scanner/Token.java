
// token class

public class Token {
	private String lexeme;
	private TokenType tokentype;

	public Token(String lexeme, TokenType tokentype) {
		this.lexeme = lexeme;
		this.tokentype = tokentype;
	}

	public String getlexeme() {
		return this.lexeme;
	}

	public void setlexeme(String lexeme) {
		this.lexeme = lexeme;
	}

	public TokenType getTokenType() {

		return this.tokentype;
	}

	public void setTokenType(TokenType tokentype) {
		this.tokentype = tokentype;
	}

	public String toString() {
		return ("Token Type: " + tokentype + " lexeme is: " + lexeme);
	}
}


