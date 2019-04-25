package syntaxtree;

/**
 * This a class I created in order to handle while statements
 * 
 * @author erikayavaca
 *
 */
public class WhileStatementNode extends StatementNode {
	private ExpressionNode test;
	private StatementNode doStatement;

	/**
	 * @return the test
	 */
	public ExpressionNode getTest() {
		return test;
	}

	/**
	 * @param test
	 *            the test to set
	 */
	public void setTest(ExpressionNode test) {
		this.test = test;
	}

	/**
	 * @return the doStatement
	 */
	public StatementNode getDoStatement() {
		return doStatement;
	}

	/**
	 * @param doStatement
	 *            the doStatement to set
	 */
	public void setDoStatement(StatementNode doStatement) {
		this.doStatement = doStatement;
	}

	@Override
	public String indentedToString(int level) {
		String answer = this.indentation(level);
		answer += "While:\n";
		answer += this.test.indentedToString(level + 1);
		answer += this.indentation(level) + "Do:\n" + this.doStatement.indentedToString(level + 1);
		return answer;
	}

}
