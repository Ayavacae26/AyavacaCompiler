package symboltable;

import static org.junit.Assert.*;
import org.junit.Test;

import symboltable.SymbolTable.Kind;
import symboltable.SymbolTable.Type;




public class ToStringTest {
	/**
	 * Here I am testing the toString override that occured in the Sy
	 */
	@Test
	public void test() {
		SymbolTable symbols = new SymbolTable();
		symbols.addKind("red", Kind.VARIABLE, Type.REAL);
		symbols.addKind("green", Kind.ARRAY, Type.NULL);
		symbols.addKind("blue", Kind.FUNCTION, Type.INTEGER);
		symbols.addKind("yellow", Kind.PROGRAM, Type.NULL);
		symbols.addKind("black", Kind.PROCEDURE, Type.REAL);
        /* symbols.addProgram("RED");
	   //symbols.addArray("GREEN");
	  //symbols.addFunction("YELLOW");
		//symbols.addVariable("RUBY ", INTEGER);
*/		
		System.out.println(symbols.toString());
	}

}
