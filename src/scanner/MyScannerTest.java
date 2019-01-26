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
	        
	      //2nd token test(while token) 
	        System.out.println("while token test");
	        TokenType expected1 = TokenType.WHILE;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected1, myToken.getTokenType());
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
	      //2nd token test(while token) 
	        System.out.println("while token test");
	        TokenType expected1 = TokenType.WHILE;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected1, myToken.getTokenType());
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
	      //2nd token test(while token) 
	        System.out.println("while token test");
	        TokenType expected1 = TokenType.WHILE;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected1, myToken.getTokenType());
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
	      //2nd token test(while token) 
	        System.out.println("while token test");
	        TokenType expected1 = TokenType.WHILE;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected1, myToken.getTokenType());
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
	      //2nd token test(while token) 
	        System.out.println("while token test");
	        TokenType expected1 = TokenType.WHILE;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected1, myToken.getTokenType());
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
	      //2nd token test(while token) 
	        System.out.println("while token test");
	        TokenType expected1 = TokenType.WHILE;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected1, myToken.getTokenType());
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
	      //2nd token test(while token) 
	        System.out.println("while token test");
	        TokenType expected1 = TokenType.WHILE;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected1, myToken.getTokenType());
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
	      //2nd token test(while token) 
	        System.out.println("while token test");
	        TokenType expected1 = TokenType.WHILE;
	        
	        try {
	            myToken = instance.nextToken();
	            assertEquals(expected1, myToken.getTokenType());
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

