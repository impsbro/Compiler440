package parser.states;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import parser.Parser;
import tokenizer.Token;
import tokenizer.TokenTypes;

public class TestCRM_3 {
	@Before
    public void setUp()
    {
        Parser.resetParser();
    }
	
	/**
     * @author Chris Kjeldgaard
     * @throws ParserException
     */
    @Test
	public void testReduce() throws ParserException
	{
	    Parser p = Parser.getInstance();
	    State s = new CRM_3();
	    
	    ArrayList<Token> tokens = new ArrayList<Token>();
	    tokens.add(new Token("VAR_DECL","VAR_DECL",6));
	    tokens.add(new Token("VAR_DECL_L","VAR_DECL_L",5));
	    p.pushHoldStack(tokens.get(1));
	    p.pushHoldStack(tokens.get(0));    
	    p.pushStateStack(new CRM_1());
	    p.pushStateStack(new CRM_2());
	    
	    s.shiftVAR_DECL();
	    
	    assertFalse(p.getInputStack().empty());
	    assertTrue(p.getHoldStack().empty());
	    assertTrue(p.getStateStack().empty());
	    Token testee = new Token(TokenTypes.VAR_DECL_L, tokens);
	    assertEquals(p.peekInputStack().getLineNumber(), testee.getLineNumber());
	    assertEquals(p.peekInputStack().getToken(), testee.getToken());
	    assertEquals(p.peekInputStack().getTokenName(), testee.getTokenName());
	    assertEquals(p.peekInputStack().getChildren(), testee.getChildren());
	    assertEquals(p.getCurrentState().getClass(), new CRM_1().getClass());
	}
}
