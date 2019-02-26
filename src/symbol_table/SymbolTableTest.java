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
		
	}
	
	@Test
	public void addVariableNameFail() {
		String wrongName = "pop foo";
		SymbolTable test = new SymbolTable();
		//Checking that is no variable of the name
		test.addVariable(wrongName);
		boolean expected = false;
		boolean actual = test.isVariable(wrongName);
		assertEquals(expected,actual);
		
	}

	@Test
	public void addProgramName() {
		String name = "program Foo";
		SymbolTable test = new SymbolTable();
		test.addProgram(name);
		boolean expected = true;
		//returns true for variable named "Variable"
		expected = true;
		boolean actual = test.isProgram(name);
		assertEquals(expected,actual);
		
	}
	
	@Test
	public void addProgramNameFail() {
		String wrongName = "no foo";
		SymbolTable test = new SymbolTable();
		
		//Checking that is no variable of the name
		boolean expected = false;
		boolean actual = test.addProgram(wrongName);
		assertEquals(expected,actual);
		
	}
}
