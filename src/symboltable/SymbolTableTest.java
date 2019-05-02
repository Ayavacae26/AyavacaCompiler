package symboltable;

import static org.junit.Assert.*;
import org.junit.Test;

import symboltable.SymbolTable.Kind;

public class SymbolTableTest {

	/**
	 * test case checking the addVariable function should return true, which means
	 * it passes
	 */
	@Test
	public void addVariableName() {
		String name = "variable Foo";
		SymbolTable test = new SymbolTable();
		test.addVariable(name, null);
		boolean expected = true;
		// returns true for variable named "Variable"
		expected = true;
		boolean actual = test.isVariable(name);
		assertEquals(expected, actual);

	}

	/**
	 * Test case check isVariable function return false if input string is not good
	 * should pass if both expected and actual are false
	 */
	@Test
	public void addVariableNameFail() {
		String wrongName = "pop foo";
		SymbolTable test = new SymbolTable();
		// Checking that is no variable of the name
		boolean expected = false;
		boolean actual = test.isVariable(wrongName);
		assertEquals(expected, actual);

	}

	/**
	 * test case checking the addProgram function should return true, which means it
	 * passes
	 */

	@Test
	public void addProgramName() {
		String name = "program Foo";
		SymbolTable test = new SymbolTable();
		test.addProgram(name);
		boolean expected = true;
		// returns true for variable named "Variable"
		expected = true;
		boolean actual = test.isProgram(name);
		assertEquals(expected, actual);

	}

	/**
	 * Test case check isProgram function return false if input string is not good
	 * should pass if both expected and actual are false
	 */

	@Test
	public void addProgramNameFail() {
		String wrongName = "no foo";
		SymbolTable test = new SymbolTable();
		// Checking that is no variable of the name
		boolean expected = false;
		boolean actual = test.isProgram(wrongName);
		assertEquals(expected, actual);
	}

	/**
	 * test case checking the addArrayfunction should return true, which means it
	 * passes
	 */

	/*@Test
	public void addArrayName() {
		String name = "array Foo";
		SymbolTable test = new SymbolTable();
		test.addArray(name);
		boolean expected = true;
		// returns true for variable named "array"
		expected = true;
		boolean actual = test.isArray(name);
		assertEquals(expected, actual);

	}
*/
	/**
	 * Test case check isArray function return false if input string is not good
	 * should pass if both expected and actual are false
	 */

	@Test
	public void addArrayNameFail() {
		String wrongName = "no foo";
		SymbolTable test = new SymbolTable();

		// Checking that is no variable of the name
		boolean expected = false;
		boolean actual = test.isArray(wrongName);
		assertEquals(expected, actual);

	}

	/**
	 * test case checking the addFunction function should return true, which means
	 * it passes
	 */

	@Test
	public void addFunctionName() {
		String name = "function Foo";
		SymbolTable test = new SymbolTable();
		test.addFunction(name, null);
		boolean expected = true;
		// returns true for variable named "function"
		expected = true;
		boolean actual = test.isFunction(name);
		assertEquals(expected, actual);

	}

	/**
	 * Test case check isFunction function return false if input string is not good
	 * should pass if both expected and actual are false
	 */

	@Test
	public void addFunctionNameFail() {
		String wrongName = "no foo";
		SymbolTable test = new SymbolTable();
		// Checking that is no variable of the name
		boolean expected = false;
		boolean actual = test.isFunction(wrongName);
		assertEquals(expected, actual);

	}

	/**
	 * test case checking the addProcedure function should return true, which means
	 * it passes
	 */

	@Test
	public void addProcedureName() {
		String name = "function Foo";
		SymbolTable test = new SymbolTable();
		test.addProcedure(name);
		boolean expected = true;
		// returns true for variable named "Procedure"
		expected = true;
		boolean actual = test.isProcedure(name);
		assertEquals(expected, actual);

	}

	/**
	 * Test case check isProcedure function return false if input string is not good
	 * should pass if both expected and actual are false
	 */
	@Test
	public void addProcedureNameFail() {
		String wrongName = "no foo";
		SymbolTable test = new SymbolTable();
		// Checking that is no variable of the name
		boolean expected = false;
		boolean actual = test.isProcedure(wrongName);
		assertEquals(expected, actual);

	}
}
	
///////////////	testing using addkind //////////
	/**
	 * test case checking the addVariable 
	 * should return true, which means it passes
	 
	@Test
	public void addVariableName() {
		String name = "variable Foo";
		SymbolTable test = new SymbolTable();
		test.addKind(name, Kind.VARIABLE, null);
		boolean expected = true;
		//returns true for variable named "Variable"
		expected = true;
		boolean actual = test.isKind(name, Kind.VARIABLE);
		assertEquals(expected,actual);
		
	}
	/**
	 * Test case check isVariable 
	 * return false if input string is not good
	 * should pass if both expected and actual are false
	 *//*
	@Test
	public void addVariableNameFail() {
		String wrongName = "pop foo";
		SymbolTable test = new SymbolTable();
		//Checking that is no variable of the name
		boolean expected = false;
		boolean actual = test.isKind(wrongName,  Kind.VARIABLE);
		assertEquals(expected,actual);
		
	}
	*//**
	 * test case checking the addProgram function
	 * should return true, which means it passes
	 *//*

	@Test
	public void addProgramName() {
		String name = "program Foo";
		SymbolTable test = new SymbolTable();
		test.addKind(name,Kind.PROGRAM,null);
		boolean expected = true;
		//returns true for variable named "Variable"
		expected = true;
		boolean actual = test.isKind(name,Kind.PROGRAM);
		assertEquals(expected,actual);
		
	}
	*//**
	 * Test case check isProgram function
	 * return false if input string is not good
	 * should pass if both expected and actual are false
	 *//*
	
	@Test
	public void addProgramNameFail() {
		String wrongName = "no foo";
		SymbolTable test = new SymbolTable();
		//Checking that is no variable of the name
		boolean expected = false;
		boolean actual = test.isKind(wrongName,Kind.PROGRAM);
		assertEquals(expected,actual);
	}
	
	*//**
	 * test case checking the addArrayfunction
	 * should return true, which means it passes
	 *//*
	
	@Test
	public void addArrayName() {
		String name = "array Foo";
		SymbolTable test = new SymbolTable();
		test.addKind(name,Kind.ARRAY,null);
		boolean expected = true;
		//returns true for variable named "array"
		expected = true;
		boolean actual = test.isKind(name, Kind.ARRAY);
		assertEquals(expected,actual);
		
	}
	*//**
	 * Test case check isArray function
	 * return false if input string is not good
	 * should pass if both expected and actual are false
	 *//*
	
	@Test
	public void addArrayNameFail() {
		String wrongName = "no foo";
		SymbolTable test = new SymbolTable();
		
		//Checking that is no variable of the name
		boolean expected = false;
		boolean actual = test.isKind(wrongName,Kind.ARRAY);
		assertEquals(expected,actual);
		
	}
	
	*//**
	 * test case checking the addFunction function
	 * should return true, which means it passes
	 *//*
	
	@Test
	public void addFunctionName() {
		String name = "function Foo";
		SymbolTable test = new SymbolTable();
		test.addKind(name,Kind.FUNCTION,null);
		boolean expected = true;
		//returns true for variable named "function"
		expected = true;
		boolean actual = test.isKind(name,Kind.FUNCTION);
		assertEquals(expected,actual);
		
	}
	
	*//**
	 * Test case check isFunction function
	 * return false if input string is not good
	 * should pass if both expected and actual are false
	 *//*
	
	@Test
	public void addFunctionNameFail() {
		String wrongName = "no foo";
		SymbolTable test = new SymbolTable();
		//Checking that is no variable of the name
		boolean expected = false;
		boolean actual = test.isKind(wrongName,Kind.FUNCTION);
		assertEquals(expected,actual);
		
	}
	
	*//**
	 * test case checking the addProcedure function
	 * should return true, which means it passes
	 *//*

	@Test
	public void addProcedureName() {
		String name = "function Foo";
		SymbolTable test = new SymbolTable();
		test.addKind(name,Kind.PROCEDURE,null);
		boolean expected = true;
		//returns true for variable named "Procedure"
		expected = true;
		boolean actual = test.isKind(name,Kind.PROCEDURE);
		assertEquals(expected,actual);
		
	}
	
	*//**
	 * Test case check isProcedure function
	 * return false if input string is not good
	 * should pass if both expected and actual are false
	 *//*
	@Test
	public void addProcedureNameFail() {
		String wrongName = "no foo";
		SymbolTable test = new SymbolTable();
		//Checking that is no variable of the name
		boolean expected = false;
		boolean actual = test.isKind(wrongName,Kind.PROCEDURE);
		assertEquals(expected,actual);
		
	}
}

*/