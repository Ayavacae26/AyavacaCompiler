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

private HashMap<String, DataType> symbols;
	
	public SymbolTable() {
		symbols = new HashMap<String, DataType>();
	}
	
	/**
	 * 
	 * @param lexeme
	 * @param kind
	 */
	public void addKind(String lexeme, Kind kind) {
		symbols.put(lexeme, new DataType(lexeme,kind));
	}
	
	public Kind getKind(String lexeme) {
		if(this.symbols.containsKey(lexeme)) {
			return this.symbols.get(lexeme).getKind();
		}
		return null;
	}
	
	public boolean isKind(String lexeme, Kind kind) {
		if(getKind(lexeme)==kind) {
			return true;
		}
		return false;
	}
	
	/**
	 * Enum to track the different types of symbols that can be stored in the
	 * symboltable
	 */
	public enum Kind {
		PROGRAM, VARIABLE, ARRAY, FUNCTION, PROCEDURE

	}

	private class DataType {
		public String lexeme;
		public Kind kind;
		public String type;
		
		private DataType(String lexeme, Kind kind) {
			this.lexeme = lexeme;
			this.kind = kind;
		}
		
		private Kind getKind() {
			return this.kind;
		}
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		String sym = String.format("%-20s %-20s %15s ", "NAME", "KIND", "TYPE") + "\n";
		Set<String> keys = symbols.keySet();
		for (String key : keys) {
			sym += String.format("%-20s %-20s %15s ", key, symbols.get(key).kind, symbols.get(key).type) + "\n";
		}
		return sym;
	}
	
	// Original implementation: Not working as planned when trying to itegrate with recognizer, decided to rewrite.  
	/**
	 * This method addVariable checks to see if there exists a name of the type
	 * variable, returns false if found, else it add's in to the hashmap
	 * 
	 * @param name-
	 *            name of variable that is being checked by hashmap
	 * @return True if there is no existance of the name.
	 *//*

	public boolean addVariable(String name) {
		DataType ss = new DataType();
		if (symbols.containsKey(name)) {
			return false;
		} else {
			ss.name = name;
			ss.kind = Kind.VARIABLE;
			symbols.put(name, ss);
			// System.out.println("Adding " + name + " in type " + ss.kind + " to hashmap");
			return true;
		}
	}

	*//**
	 * This isVariable method checks to see if the kind of id matches the enum type
	 * of variable
	 * 
	 * @param name
	 *            -The variable id name that is being checked in the hash map
	 * @return True if there is a match
	 *//*
	public boolean isVariable(String name) {
		DataType data = (DataType) symbols.get(name);
		if (data == null) {
			return false;
		} else if (data.kind == Kind.VARIABLE) {
			return true;
		}
		return false;
	}

	///////////////////////////////////////////////
	*//**
	 * This method addProgram checks to see if there exists a name of the type
	 * variable, returns false if found, else it add's in to the hashmap
	 * 
	 * @param name-
	 *            name of variable that is being checked by hashmap
	 * @return True if there is no existance of the name.
	 *//*

	public boolean addProgram(String name) {
		DataType ss = new DataType();
		if (symbols.containsKey(name)) {
			return false;
		} else {
			ss.name = name;
			ss.kind = Kind.PROGRAM;
			symbols.put(name, ss);
			// System.out.println("Adding " + name + " in type " + ss.kind + " to hashmap");
			return true;
		}
	}

	*//**
	 * This isProgram method checks to see if the kind of id matches the enum type
	 * of variable
	 * 
	 * @param name
	 *            -The variable id name that is being checked in the hash map
	 * @return True if there is a match
	 *//*
	public boolean isProgram(String name) {
		DataType data = (DataType) symbols.get(name);
		if (data == null) {
			return false;
		} else if (data.kind == Kind.PROGRAM) {
			return true;
		}
		return false;
	}

	///////////////////////////////////////////////

	*//**
	 * This method addArray checks to see if there exists a name of the type
	 * variable, returns false if found, else it add's in to the hashmap
	 * 
	 * @param name-
	 *            name of variable that is being checked by hashmap
	 * @return True if there is no existance of the name.
	 *//*

	public boolean addArray(String name) {
		DataType ss = new DataType();
		if (symbols.containsKey(name)) {
			return false;
		} else {
			ss.name = name;
			ss.kind = Kind.PROGRAM;
			symbols.put(name, ss);
			// System.out.println("Adding " + name + " in type " + ss.kind + " to hashmap");
			return true;
		}
	}

	*//**
	 * This isArray method checks to see if the kind of id matches the enum type of
	 * variable
	 * 
	 * @param name
	 *            -The variable id name that is being checked in the hash map
	 * @return True if there is a match
	 *//*
	public boolean isArray(String name) {
		DataType data = (DataType) symbols.get(name);
		if (data == null) {
			return false;
		} else if (data.kind == Kind.PROGRAM) {
			return true;
		}
		return false;
	}

	//////////////////////////////////////

	*//**
	 * This method addFunction checks to see if there exists a name of the type
	 * variable, returns false if found, else it add's in to the hashmap
	 * 
	 * @param name-
	 *            name of variable that is being checked by hashmap
	 * @return True if there is no existance of the name.
	 *//*

	public boolean addFunction(String name) {
		DataType ss = new DataType();
		if (symbols.containsKey(name)) {
			return false;
		} else {
			ss.name = name;
			ss.kind = Kind.FUNCTION;
			symbols.put(name, ss);
			// System.out.println("Adding " + name + " in type " + ss.kind + " to hashmap");
			return true;
		}
	}

	*//**
	 * This isFunction method checks to see if the kind of id matches the enum type
	 * of variable
	 * 
	 * @param name
	 *            -The variable id name that is being checked in the hash map
	 * @return True if there is a match
	 *//*
	public boolean isFunction(String name) {
		DataType data = (DataType) symbols.get(name);
		if (data == null) {
			return false;
		} else if (data.kind == Kind.FUNCTION) {
			return true;
		}
		return false;
	}

	/////////////////////////////////////////
	*//**
	 * This method addProcedure checks to see if there exists a name of the type
	 * variable, returns false if found, else it add's in to the hashmap
	 * 
	 * @param name-
	 *            name of variable that is being checked by hashmap
	 * @return True if there is no existance of the name.
	 *//*

	public boolean addProcedure(String name) {
		DataType ss = new DataType();
		if (symbols.containsKey(name)) {
			return false;
		} else {
			ss.name = name;
			ss.kind = Kind.PROCEDURE;
			symbols.put(name, ss);
			// System.out.println("Adding " + name + " in type " + ss.kind + " to hashmap");
			return true;
		}
	}

	*//**
	 * This isProcedure method checks to see if the kind of id matches the enum type
	 * of variable
	 * 
	 * @param name
	 *            -The variable id name that is being checked in the hash map
	 * @return True if there is a match
	 *//*
	public boolean isProcedure(String name) {
		DataType data = (DataType) symbols.get(name);
		if (data == null) {
			return false;
		} else if (data.kind == Kind.PROCEDURE) {
			return true;
		}
		return false;
	}*/
	
	

}
