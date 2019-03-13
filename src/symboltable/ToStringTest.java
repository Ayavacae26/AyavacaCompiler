package symbol_table;

import static org.junit.Assert.*;
import org.junit.Test;

import symbol_table.SymbolTable.Kind;

public class ToStringTest {
	/**
	 * Here I am testing the toString override that occured in the Sy
	 */
	@Test
	public void test() {
		SymbolTable symbols = new SymbolTable();
		//symbols.addKind("red", Kind.VARIABLE);
		symbols.addProgram("RED");
		symbols.addProcedure("Blue");
		symbols.addArray("GREEN");
		symbols.addFunction("YELLOW");
		symbols.addVariable("RUBY");
		
		System.out.println(symbols.toString());
	}

}
