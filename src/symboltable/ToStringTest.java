package symboltable;

import static org.junit.Assert.*;
import org.junit.Test;



public class ToStringTest {
	/**
	 * Here I am testing the toString override that occured in the Sy
	 */
	@Test
	public void test() {
		SymbolTable symbols = new SymbolTable();
		//symbols.addKind("red", Kind.VARIABLE);
		symbols.addProgram("RED");
		symbols.addArray("GREEN");
		symbols.addFunction("YELLOW");
		symbols.addVariable("RUBY");
		
		System.out.println(symbols.toString());
	}

}
