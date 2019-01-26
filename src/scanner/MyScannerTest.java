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
	        
	        
	        //1st token 
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

