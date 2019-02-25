package symbol_table;
import java.util.Hashtable;
import java.util.Set;
/*
 *  This is a java class, that will be using a hashmap to keep track 
 *  of different id types such as array,function, variable and program ids.
 *  They appear in the object form of(String, DataType)
 * 
 * @author Erik Ayavaca-Tirado
 */
public class SymbolTable {
	
	private Hashtable<String , DataType> symbols = new Hashtable<String, DataType>();
	
	
	/**
	 * Enum to track the different types of symbols that
	 * can be stored in the symboltable
	 */
	private enum Kind{
		PROGRAM,VARIABLE,ARRAY,FUNCTION,PROCEDURE
		
	}
	
	private class DataType{
		public String name;
		public Kind kind;
		public String type;	
	}

}
