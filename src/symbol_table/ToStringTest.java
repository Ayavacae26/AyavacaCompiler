package symbol_table;

import static org.junit.Assert.*;
import org.junit.Test;

public class ToStringTest {

	@Test
	public void test() {
		SymbolTable symbols = new SymbolTable();
		symbols.addProgram("RED");
		symbols.addProcedure("Blue");
		symbols.addArray("GREEN");
		symbols.addFunction("YELLOW");
		symbols.addVariable("RUBY");
		//String table = "NAME   FUNCTION    TYPE    \n" + "RED      PROGRAM      \n";
		
		System.out.println(symbols.toString());
	}

}
