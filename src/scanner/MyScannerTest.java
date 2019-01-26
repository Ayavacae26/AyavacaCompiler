package scanner;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.StringReader;

/**
*
* @author erikayavaca
*/

public class MyScannerTest {
	    //Testing all keywords 
	    @Test
	    public void test1() {
	        Token myToken = null;
	        String input = " program while if integer div mod or and do else then function var read begin end \n" + 
	        		" of real procedure write array not return";
	        MyScanner instance = new MyScanner(new StringReader(input));
	        
	        
	        //1st token(program token)
	        System.out.println("program token test");
	        TokenType expected = TokenType.PROGRAM;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	          
	    //2nd token test(while token) 
	        System.out.println("while token test");
	        TokenType expected1 = TokenType.WHILE;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected1, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	      //3rd token test(if token) 
	        System.out.println("if token test");
	        TokenType expected2 = TokenType.IF;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected2, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	      //4th token test(integer token) 
	        System.out.println("integer token test");
	        TokenType expected3 = TokenType.INTEGER;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected3, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	      //5th token test(div token) 
	        System.out.println("div token test");
	        TokenType expected4 = TokenType.DIV;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected4, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	      //6th token test(mod token) 
	        System.out.println("mod token test");
	        TokenType expected5 = TokenType.MOD;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected5, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	      //7th token test(or token) 
	        System.out.println("or token test");
	        TokenType expected6 = TokenType.OR;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected6, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	      //8th token test(qnd token) 
	        System.out.println("and token test");
	        TokenType expected7 = TokenType.AND;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected7, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	      //9th token test(do token) 
	        System.out.println("do token test");
	        TokenType expected8 = TokenType.DO;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected8, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	      //10th  token test(else token) 
	        System.out.println("else token test");
	        TokenType expected9 = TokenType.ELSE;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected9, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	      //11th token test(then token) 
	        System.out.println("then token test");
	        TokenType expected10 = TokenType.THEN;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected10, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	      //12th token test(function token) 
	        System.out.println("function token test");
	        TokenType expected11 = TokenType.FUNCTION;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected11, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	      //13th token test(var token) 
	        System.out.println("var token test");
	        TokenType expected12 = TokenType.VAR;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected12, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	      //14th token test(read token) 
	        System.out.println("read token test");
	        TokenType expected13 = TokenType.READ;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected13, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	      //15th token test(begin token) 
	        System.out.println("begin token test");
	        TokenType expected14 = TokenType.BEGIN;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected14, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	      //16th token test(end token) 
	        System.out.println("end token test");
	        TokenType expected15 = TokenType.END;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected15, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	      //17th token test(of token) 
	        System.out.println("of token test");
	        TokenType expected16 = TokenType.OF;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected16, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	      //18th token test(real token) 
	        System.out.println("real token test");
	        TokenType expected17 = TokenType.REAL;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected17, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	      //19th token test(procedure token) 
	        System.out.println("while token test");
	        TokenType expected18 = TokenType.PROCEDURE;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected18, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	      //20th token test(write token) 
	        System.out.println("write token test");
	        TokenType expected19 = TokenType.WRITE;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected19, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	      //21st token test(array token) 
	        System.out.println("array token test");
	        TokenType expected20 = TokenType.ARRAY;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected20, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	      //22nd token test(not token) 
	        System.out.println("not token test");
	        TokenType expected21 = TokenType.NOT;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected21, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	      //23rd token test(return token) 
	        System.out.println("return token test");
	        TokenType expected22 = TokenType.RETURN;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected22, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	        
	        }
	    
	  //Testing all symbols 
	    @Test
	    public void test2() {
	        Token myToken = null;
	        String input = "; , : . [ ] ( ) + - = < <= > >= * / := <>";
	        MyScanner instance = new MyScanner(new StringReader(input));
	        
	        //1st token test 
	        System.out.println("1st token test");
	        TokenType expected = TokenType.PROGRAM;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	          
	    //2nd token test 
	        System.out.println("2nd token test");
	        TokenType expected1 = TokenType.NUMBER;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected1, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	        }
	    //Testing types of numbers
	    @Test
	    public void test3() {
	        Token myToken = null;
	        String input = " ";
	        MyScanner instance = new MyScanner(new StringReader(input));
	        
	        //1st token test 
	        System.out.println("1st token test");
	        TokenType expected = TokenType.PROGRAM;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	          
	    //2nd token test 
	        System.out.println("2nd token test");
	        TokenType expected1 = TokenType.NUMBER;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected1, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	        }
	  //Testing illegal chars
	    @Test
	    public void test4() {
	        Token myToken = null;
	        String input = "; , : . [ ] ( ) + - = < <= > >= * / := <>";
	        MyScanner instance = new MyScanner(new StringReader(input));
	        
	        //1st token test 
	        System.out.println("1st token test");
	        TokenType expected = TokenType.PROGRAM;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	          
	    //2nd token test 
	        System.out.println("2nd token test");
	        TokenType expected1 = TokenType.NUMBER;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected1, myToken.getTokenType());
	            System.out.println(myToken);
	            }
	            catch( Exception e) { e.printStackTrace();}
	        
	        }
	            
}

