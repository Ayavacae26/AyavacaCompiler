package symbol_table;

import static org.junit.Assert.*;
import org.junit.Test;

public class SymbolTableTest {

	@Test
	public void addVariableName() {
		String name = "variable Foo";
		SymbolTable test = new SymbolTable();
		test.addVariable(name);
		boolean expected = true;
		//returns true for variable named "Variable"
		expected = true;
		boolean actual = test.isVariable(name);
		assertEquals(expected,actual);
		System.out.println("/////////////////////");
	}
	
	@Test
	public void addVariableNameFail() {
		String wrongName = "program foo";
		SymbolTable test = new SymbolTable();
		
		//Checking that is no variable of the name
		test.addVariable(wrongName);
		boolean expected = true;
		boolean actual = test.isVariable(wrongName);
		assertEquals(expected,actual);
		System.out.println("/////////////////////");
	}

}
