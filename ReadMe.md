Erik Ayavaca-Tirado
CSC 451
Pascal Compiler 

ReadMe

In this project I am going to create a compiler for our Pascal language as described in the
grammar given in class.  The scanner will be created using the JFlex tool. This project will be divided in a few main sections:
1. Scanner 
2. Parser 
3. Symbol Table 
4. Syntax Tree
5. Compiler
6. Semantic Analysis
7. Code Generation

This project on the main level will contain:
1. SDD folder - most recent version of the Software Design Document. Word and PDF version
2. Src Folder - All current code for the compiler
3. Product Folder - user manual and  along with a .jar executable (upcoming)

In the Src Folder there exist/will exist the following packages:
1. scanner 
2. parser
3. symboltable
4. compiler
5. syntaxtree
6. symanticanalysis(needs improvement)
7. codegeneration (currently working on)

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
4. ParserTest.java

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
15. ProcedureStatementNode.java
16. UnaryOperationNode.java
17. WhileStatementNode.java

In the semanticanalysis package, there are the following files:
1. SemanticAnalysis.java
2. SemanticAnalysisTest.java

In the codegen package, there are the following files:
1. CodeGeneration.java
2. CodeGenerationTest.java

Extra's 
For the extras I am going to try to implement arrays within this compiler
What I did for this part:
created a constructor within the symbol table for adding in the array.
tried to implement arrays in the parser, specifically Type type function, was unsuccessful, so i commented the code out 





