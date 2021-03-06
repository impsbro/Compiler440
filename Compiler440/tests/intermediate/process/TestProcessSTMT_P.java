package intermediate.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import symboltable.Class;
import symboltable.VariableType;
import symboltable.method.impl.PublicMethod;
import tokenizer.Token;
import tokenizer.TokenTypes;

/**
 * @author Curtis Rabe, Mike Zimmerman
 * Test class for STMT_P
 *
 */
public class TestProcessSTMT_P {

	/**
	 * A test to make sure STMT_P can properly run through and process its tokens
	 * Note: It is my understanding that the tokens are already built, so 
	 * building them in this structure should be ok
	 */
	@Test
	public void testProcessPass1()
	{
		//first token of EXP_L
		Token t1 = new Token(TokenTypes.STMT_P.name(), 1, null);
		t1.setVisited();
		t1.setType("boolean");
		//EXP_R, the second token of EXP_L
		Token t4 = new Token(TokenTypes.STMT.name(), 1, null);
		t4.setVisited();
		t4.setType("int");
		ArrayList<Token> tkns1 = new ArrayList<Token>();
		tkns1.add(t1);
		tkns1.add(t4);
		//The actual token of EXP_L to test
		Token t5 = new Token(TokenTypes.STMT_P.name(), 1, tkns1);
		Class c1 = new Class("ClassName", null, null);
		PublicMethod pm = new PublicMethod("MethodName", null, VariableType.BOOLEAN, null);
		t5.setParentMethod(pm);
		t5.setParentClass(c1);
		assertFalse(t5.isVisited());
		Token.pass1(t5);
		assertEquals(t5.getType(), t5.getChildren().get(1).getType());
		assertEquals(t5.getChildren().get(0).getParentClass(), t5.getParentClass());
		assertEquals(t5.getChildren().get(0).getParentMethod(), t5.getParentMethod());
		assertEquals(t5.getChildren().get(1).getParentClass(), t5.getParentClass());
		assertEquals(t5.getChildren().get(1).getParentMethod(), t5.getParentMethod());
		assertTrue(t5.isVisited());
	}

	/**
	 * @author Mike Zimmerman
	 * A test to make sure that STMT_P can make it through pass 2 and process its children
	 * @throws ProcessException this is a exception to halt the compilation since types don't match 
	 */
	@Test
	public void testProcessPass2() throws ProcessException
	{
		//Children for STMT_P
		Token t1 = new Token(TokenTypes.STMT_P.name(), 1, null);
		Token t2 = new Token(TokenTypes.STMT.name(), 1, null);
		t1.setVisited();
		t2.setVisited();
		
		// Adds tokens to ArrayList to add to STMT_P
		ArrayList<Token> tkns = new ArrayList<Token>();
		tkns.add(t1);
		tkns.add(t2);
		
		// Main STMT_P		
		Token t3 = new Token(TokenTypes.STMT_P.name(), 1, tkns);
		
		// Checks that the token properly goes through pass 3
		assertFalse(t3.isVisited());
		Token.pass2(t3);
		assertTrue(t3.isVisited());
	}
	
	/**
	 * A test to make sure that STMT_P can make it through pass 3 and process its children
	 * @author Jared Good
	 */
	@Test
	public void testProcessPass3()
	{		
		
		//Children for STMT_P
		Token t1 = new Token(TokenTypes.STMT_P.name(), 1, null);
		Token t2 = new Token(TokenTypes.STMT.name(), 1, null);
		t1.setVisited();
		t2.setVisited();
		
		// Adds tokens to ArrayList to add to STMT_P
		ArrayList<Token> tkns = new ArrayList<Token>();
		tkns.add(t1);
		tkns.add(t2);
		
		// Main STMT_P		
		Token t3 = new Token(TokenTypes.STMT_P.name(), 1, tkns);
		
		// Checks that the token properly goes through pass 3
		assertFalse(t3.isVisited());
		Token.pass3(t3);
		assertTrue(t3.isVisited());
		
	}
	
}
