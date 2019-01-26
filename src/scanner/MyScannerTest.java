package scanner;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.StringReader;

/**
*
* @author erikayavaca
*/

public class MyScannerTest {
	    
	    @Test
	    public void test1() {
	        Token myToken = null;
	        String input = "pikachu12 1E-10 end";
	        MyScanner instance = new MyScanner(new StringReader(input));
	        
	        
	        //1st token test 
	        System.out.println("1st token test");
	        TokenType expected = TokenType.ID;
	        
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

