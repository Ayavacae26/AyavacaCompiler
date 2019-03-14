Erik Ayavaca-Tirado

CSC 451

Pascal Compiler 

ReadMe

In this project I am going to create a the scanner for our Pascal language as described in the
grammar given in class.  The scanner will be created using the JFlex tool.  This is just the 
begining part for the pascal compiler. This project will be divided in 4 main sections:
1. Scanner 
2. Parser 
3. Symbol Table 
4. Syntax Tree

This project on the main level will contain
1. SDD folder - most recent version of the Software Design Document. Word and PDF version
2.  Src Folder - All current code for the compiler

In the Src Folder there exist the following packages:
1. scanner 
2. parser
3. symboltable
4. compiler
5. syntaxtree

In the scanner package these are the following files:
1. EAScanner.jflex   
2. LookUpTable.java  
3. Main.java   
4. MyScanner.java   
5. MyScannerTest.java  
6. Token.java  
7. TokenType.java   
8. flex1.7.0.jar  
9. test.txt

In the parser package these are the following files:
1. Recognizer.java   
2. RecognizerTest.java
3. Parser.java

In the symboltable package, these are the following files:
1. SymbolTable.java    
2. SymbolTableTest.java
3. ToStringTest.java

In the compiler package, there are the following files:
1. CompilerMain.java

In the syntaxtree package, there are the following files:
1. AssignmentStatementNode.java
2. CompoundStatementNode.java
3. DeclarationsNode.java
4. ExpressionNode.java
5. IfStatementNode.java
6. OperationNode.java
7. ProgramNode.java
8. StatementNode.java
9. SubProgramDeclarationsNode.java
10. SubProgramNode.java
11. SyntaxTreeNode.java
12. SyntaxTreeTest.java
13. ValueNode.java
14. VariableNode.java


