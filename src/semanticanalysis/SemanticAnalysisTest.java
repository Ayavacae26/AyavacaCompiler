package semanticanalysis;

import static org.junit.Assert.*;
import org.junit.Test;
import parser.Parser;


public class SemanticAnalysisTest {
		//test fails
		@Test
		public void testSemantic() {
			Parser par = new Parser("program foo;\n" + "begin\n" + "end\n" + ".", false);
			SemanticAnalysis s = new SemanticAnalysis(par.program(), par.getSymbolTable());
			String st = s.analyzer().indentedToString(0);
			System.out.println(st);	
		}
		

}
