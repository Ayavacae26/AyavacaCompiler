package syntaxtree;
import java.util.ArrayList;

public class ProcedureStatementNode extends StatementNode{
	private VariableNode variable = null;
	private ArrayList<ExpressionNode> EXN = new ArrayList<ExpressionNode>();
	
	public void addExpNode(ExpressionNode input) {
		EXN.add(input);
		    }
	public void addAllExpNode(ArrayList<ExpressionNode> input) {
		EXN.addAll(input);
			}
	public VariableNode getVariable() {
		        return this.variable;
	}
	public void setVariable(VariableNode input) {
		        this.variable = input;
		  }
	public ArrayList<ExpressionNode> getExpNode() {
		        return EXN;
		    }
	 public void setExpNode(ArrayList<ExpressionNode> input) {
		         this.EXN = input;
		     }
	 @Override
	     public String indentedToString(int level) {
	         String answer = this.indentation(level);
	         answer += "Procedure:";
	         answer += this.variable + "/n";
	         for (ExpressionNode exp : EXN) {
	             answer += exp.indentedToString(level + 1);
	         }
	         return answer;
	     }

}
