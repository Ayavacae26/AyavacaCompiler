package syntaxtree;
import scanner.TokenType;
/**
 * 
 * @author erikayavaca
 *
 */
public class UnaryOperationNode extends ExpressionNode {

	private ExpressionNode expression;
	private TokenType operation;
	
	public UnaryOperationNode(TokenType operation) {
		this.operation = operation;
	}

	/**
	 * @return the expression
	 */
	public ExpressionNode getExpression() {
		return expression;
	}

	/**
	 * @param expression the expression to set
	 */
	public void setExpression(ExpressionNode expression) {
		this.expression = expression;
	}

	/**
	 * @return the operation
	 */
	public TokenType getOperation() {
		return operation;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(TokenType operation) {
		this.operation = operation;
	}
	
	@Override
	public String toString() {
		return operation.toString();
	}
	
	 @Override
	 public String indentedToString(int level) {
		         String ans = this.indentation(level);
		         ans += "Unary Operation: " + this.operation + "\n";
		         ans += expression.indentedToString(level + 1);
		         return (ans);
}
	 @Override
	 public boolean equals(Object o) {
		         boolean ans = false;
		         if (o instanceof syntaxtree.OperationNode) {
		             syntaxtree.UnaryOperationNode other = (syntaxtree.UnaryOperationNode) o;
		             if ((this.operation == other.operation) && (this.expression.equals(other.expression)))
		            	 ans = true;
		         }
		         return ans;
	 }
	
	
	
}
