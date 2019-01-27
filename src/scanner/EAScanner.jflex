/**
* In this Jflex file. I will be creating a scanner that has lexem definitions in order
* to recognize  different tokens. 
* Erik Ayavaca
*/

/* Declarations */

package scanner;

%%
//%standalone         /* The produced java file has a main */

%type Token            /* Defines the return TokenType of the scanning function*/

%{
lookUpTable m = new lookUpTable();
%}

%class  MyScanner   /* Names the produced java file */
%function nextToken /* Renames the yylex() function */
//%type   String      /* Defines the return type of the scanning function */\

%eofval{
  return null;
%eofval}

/* -----Patterns------ */

other  = .
letter = [A-Za-z]
//word = {letter}+
digit =[0-9]
digits  = {digit}{digit}*
optional_fraction = (\.{digits})?
optional_exponent = ((E[\+\-]?){digits})?
number  = {digits}{optional_fraction}{optional_exponent}
id = {letter}({letter} | {digit})*
whitespace    = [ \n\t\r]
symbol = [;,:\.\[\]\(\)+-\=<>*/]|:=|<=|>=|<>


%%
/* --------Lexical Rules------ */


{number}  	{
             /** Print out the number that was found. */
            Token token = new Token (yytext(), TokenType.NUMBER);
            //System.out.println(token);
			return token;
            }
            
{id}  	{
             /** Print out the id that was found. */
            String l = yytext();
            TokenType t = m.get(l);
            if(t == null){
                t = TokenType.ID;
             }
            Token token = new Token (l, t);
            //System.out.println(token);
            return token;
            }
            
{symbol}  	{
             /** Print out the symbol that was found. */
            String l = yytext();
            TokenType t = m.get(l);
            Token token = new Token (l, t);
            //System.out.println(token);
            return token;
            }
            
{whitespace}  {  /* Ignore Whitespace */ 
                 //return "";
              } 

{other}    { 
             //System.out.println("Illegal char: '" + yytext() + "' found.");
             return new Token(yytext(), TokenType.ILLEGAL);
            }