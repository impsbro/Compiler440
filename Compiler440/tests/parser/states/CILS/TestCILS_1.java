package parser.states.CILS;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import parser.Parser;
import parser.states.ParserException;
import parser.states.State;
import parser.states.CILS.CILS_1;
import parser.states.CILS.CILS_2;
import parser.states.MDJ.MDJ_1;
import tokenizer.Token;
import tokenizer.TokenTypes;

/**
 * Test all of the state shifts for CILS_1
 * @author Raistlin Hess
 *
 */
public class TestCILS_1
{
	/**
	 * Initialize the Parser
	 */
	@Before
    public void setUp()
    {
        Parser.resetParser();
    }

	/**
	 * Make sure handles STMT_P input correctly.
	 * It should shift to CILS_2
	 */
	@Test
	public void testSTMT_P() throws ParserException
	{
		Parser parser = Parser.getInstance();
    	State state = new CILS_1();
    	Token token = new Token("STMT_P",TokenTypes.STMT_P.name(),1);
    	parser.getInputStack().push(token);
    	
    	assertEquals(parser.getInputStack().peek(), token);
        assertTrue(parser.getHoldStack().empty());
        assertTrue(parser.getStateStack().empty());
        
        state.shiftSTMT_P();
        
        assertTrue(parser.getInputStack().empty());
        assertFalse(parser.getHoldStack().empty());
        assertFalse(parser.getStateStack().empty());
        
        assertEquals(parser.getHoldStack().peek(), token);
        assertEquals(parser.getStateStack().peek(), state);
        assertTrue(parser.getCurrentState() instanceof CILS_2);
	}
	
	/**
	 * Make sure handles reduction correctly.
	 * It should shift to CILS_1
	 */
	@Test
	public void testInvalidState() throws ParserException
	{
		Parser parser = Parser.getInstance();
		State state = new CILS_1();
		Token token = new Token("else", TokenTypes.Else.name(),1);
		parser.changeState(state);
		
		parser.pushInputStack(token);
		
		state.invalidState();
		
		assertFalse(parser.getInputStack().empty());
		assertTrue(parser.getHoldStack().empty());
		assertTrue(parser.getStateStack().empty());
		assertTrue(parser.getCurrentState() instanceof CILS_1);
	}
}
