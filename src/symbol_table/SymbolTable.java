package symbol_table;
import java.util.HashMap;
import java.util.Set;
/*
 *  This is a java class, that will be using a hashmap to keep track 
 *  of different id types such as array,function, variable and program ids.
 *  They appear in the object form of(String, DataType)
 * 
 * @author Erik Ayavaca-Tirado
 */
public class SymbolTable {
	
	private HashMap<String , DataType> symbols = new HashMap<String, DataType>();
	
	
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
	
	/**
	 * This method addVariable checks to see if there exists a name
	 * of the type variable, returns false if found, else it add's in to the hashmap
	 * @param name- name of variable that is being checked by hashmap
	 * @return True if there is no existance of the name.
	 */
	
	public boolean addVariable(String name) {
		DataType ss = new DataType();
	if (symbols.containsKey(name)) {
		return false;
	}
	else {
		ss.name = name;
		ss.kind = Kind.VARIABLE;
		symbols.put(name, ss);
		System.out.println("Adding " + name + " in type " + ss.kind + " to hashtable");
		return true;
	}
	}
	
	/**
	 * This isVariable method checks to see if the kind of id matches
	 * the enum type of variable 
	 * @param name -The variable id name that is being checked in the hash map
	 * @return True if there is a match 
	 */
	public boolean isVariable(String name) {
		DataType data = (DataType) symbols.get(name);
		if(data == null) {
			return false;
		}
		else if(data.kind == Kind.VARIABLE){
			return true;
		}
		return false;
	}

}
