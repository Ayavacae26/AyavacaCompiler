package symboltable;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import scanner.TokenType;

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
	 * Enum to track the different types of symbols that can be stored in the
	 * symboltable
	 */
	public enum Kind {
		PROGRAM, VARIABLE, ARRAY, FUNCTION, PROCEDURE

	}

	/**
	 * Enum to track the different types of symbols that can be stored in the
	 * symboltable
	 */
	public enum Type {
		REAL, INTEGER, NULL

	}

	///////////////// WAS OLD WAY to implement /////////////////////////////////
	/**
	 * This method addVariable checks to see if there exists a name of the type
	 * variable, returns false if found, else it add's in to the hashmap
	 * 
	 * @param name-
	 *            name of variable that is being checked by hashmap
	 * @return True if there is no existance of the name.
	 */

	public boolean addVariable(String name, Type type) {
		DataType ss = new DataType(name, Kind.VARIABLE, type);
		if (symbols.containsKey(name)) {
			return false;
		} else {
			ss.name = name;
			ss.kind = Kind.VARIABLE;
			ss.type = type;
			symbols.put(name, ss);
			//System.out.println("Adding " + name + " in type " + ss.kind + " to hashmap");
			return true;
		}
	}

	/**
	 * This isVariable method checks to see if the kind of id matches the enum type
	 * of variable
	 * 
	 * @param name
	 *            -The variable id name that is being checked in the hash map
	 * @return True if there is a match
	 */

	public boolean isVariable(String name) {
		DataType data = (DataType) symbols.get(name);
		if (data == null) {
			return false;
		} else if (data.kind == Kind.VARIABLE) {
			return true;
		}
		return false;
	}

	/**
	 * This method addProgram checks to see if there exists a name of the type
	 * variable, returns false if found, else it add's in to the hashmap
	 * 
	 * @param name-
	 *            name of variable that is being checked by hashmap
	 * @return True if there is no existance of the name.
	 */

	public boolean addProgram(String name) {
		DataType ss = new DataType(name, Kind.PROGRAM);
		if (symbols.containsKey(name)) {
			return false;
		} else {
			ss.name = name;
			ss.kind = Kind.PROGRAM;
			symbols.put(name, ss);
			//System.out.println("Adding " + name + " in type " + ss.kind + " to hashmap");
			return true;
		}
	}

	/**
	 * This isProgram method checks to see if the kind of id matches the enum type
	 * of variable
	 * 
	 * @param name
	 *            -The variable id name that is being checked in the hash map
	 * @return True if there is a match
	 */

	public boolean isProgram(String name) {
		DataType data = (DataType) symbols.get(name);
		if (data == null) {
			return false;
		} else if (data.kind == Kind.PROGRAM) {
			return true;
		}
		return false;
	}

	/**
	 * This method addArray checks to see if there exists a name of the type
	 * variable, returns false if found, else it add's in to the hashmap
	 * 
	 * @param name-
	 *            name of variable that is being checked by hashmap
	 * @return True if there is no existance of the name.
	 */

	public boolean addArray(String name) {
		DataType ss = new DataType(name, Kind.ARRAY);
		if (symbols.containsKey(name)) {
			return false;
		} else {
			ss.name = name;
			ss.kind = Kind.ARRAY;
			symbols.put(name, ss); 
			//System.out.println("Adding " + name + " in type " + ss.kind + " to hashmap");
			return true;
		}
	}

	/**
	 * This isArray method checks to see if the kind of id matches the enum type of
	 * variable
	 * 
	 * @param name
	 *            -The variable id name that is being checked in the hash map
	 * @return True if there is a match
	 */

	public boolean isArray(String name) {
		DataType data = (DataType) symbols.get(name);
		if (data == null) {
			return false;
		} else if (data.kind == Kind.PROGRAM) {
			return true;
		}
		return false;
	}

	/**
		 * This method addFunction checks to see if there exists a name of the type
		 * variable, returns false if found, else it add's in to the hashmap
		 * 
		 * @param name-
		 *            name of variable that is being checked by hashmap
		 * @return True if there is no existance of the name.
		 */
	
	  
	public boolean addFunction(String name, Type type) {
		DataType ss = new DataType(name, Kind.FUNCTION, type);
		if (symbols.containsKey(name)) {
			return false;
		} else {
			ss.name = name;
			ss.kind = Kind.FUNCTION;
			symbols.put(name, ss); //
			//System.out.println("Adding " + name + " in type " + ss.kind + " to hashmap");
			return true;
		}
	}

	/**
	 * This isFunction method checks to see if the kind of id matches the enum type
	 * of variable
	 * 
	 * @param name
	 *            -The variable id name that is being checked in the hash map
	 * @return True if there is a match
	 */

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
	/**
		 * This method addProcedure checks to see if there exists a name of the type
		 * variable, returns false if found, else it add's in to the hashmap
		 * 
		 * @param name-
		 *            name of variable that is being checked by hashmap
		 * @return True if there is no existance of the name.
		 */	 
	public boolean addProcedure(String name) {
		DataType ss = new DataType(name, Kind.PROCEDURE);
		if (symbols.containsKey(name)) {
			return false;
		} else {
			ss.name = name;
			ss.kind = Kind.PROCEDURE;
			symbols.put(name, ss); //
			//System.out.println("Adding " + name + " in type " + ss.kind + " to hashmap");
			return true;
		}
	}

	/**
	 * This isProcedure method checks to see if the kind of id matches the enum type
	 * of variable
	 * 
	 * @param name
	 *            -The variable id name that is being checked in the hash map
	 * @return True if there is a match
	 */
	public boolean isProcedure(String name) {
		DataType data = (DataType) symbols.get(name);
		if (data == null) {
			return false;
		} else if (data.kind == Kind.PROCEDURE) {
			return true;
		}
		return false;
	}

	public class DataType {
		public String name;
		public Kind kind;
		public Type type;
		public int  beginindex;
		public int endindex;
		
		public DataType(String name, Kind kind) {
			this.name = name;
			this.kind = kind;

		}
		
		public DataType(String name, Kind kind, Type type) {
			this.name = name;
			this.kind = kind;
			this.type = type;
		}
		
		// for array 
		public DataType(String name, Kind kind, Type type, int begin, int end) {
			this.name = name;
			this.kind = kind;
			this.beginindex = begin;
			this.endindex = end;
		}
		/**
		 * @return the beginindex
		 */
		public int getBeginindex() {
			return beginindex;
		}
		/**
		 * @param beginindex the beginindex to set
		 */
		public void setBeginindex(int beginindex) {
			this.beginindex = beginindex;
		}
		/**
		 * @return the endindex
		 */
		public int getEndindex() {
			return endindex;
		}
		/**
		 * @param endindex the endindex to set
		 */
		public void setEndindex(int endindex) {
			this.endindex = endindex;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the kind
		 */
		public Kind getKind() {
			return kind;
		}

		/**
		 * @param kind
		 *            the kind to set
		 */
		public void setKind(Kind kind) {
			this.kind = kind;
		}

		/**
		 * @return the type
		 */
		public Type getType() {
			return type;
		}

		/**
		 * @param type
		 *            the type to set
		 */
		public void setType(Type type) {
			this.type = type;
		}

	}

	/**
	 * ToString
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
	
	public Type getType(String varName) {
		return null;
	}

	public boolean doesExist(String varName) {
		return false;
	}

	public void setType(String functionName, Type t) {
		// TODO Auto-generated method stub
		
	}

}

	/**
	 * 
	 * @param name
	 * @param kind
	 * @param type
	 *//*
		 * public void addKind(String name, Kind kind, Type type) { DataType data = new
		 * DataType(name,kind,type); symbols.put(name, data); }
		 * 
		 * public Kind getKind(String name) { if(this.symbols.containsKey(name)) {
		 * return this.symbols.get(name).getKind(); } return null; }
		 * 
		 * public boolean isKind(String name, Kind kind) { if(getKind(name)==kind) {
		 * return true; } return false; }
		 */


