package parser.states.JMCC;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import parser.Parser;
import parser.states.ParserException;
import parser.states.State;
import parser.states.JMCC.JMCC_0;
import parser.states.JMCC.JMCC_1;
import parser.states.JMCC.JMCC_10;
import parser.states.JMCC.JMCC_3;
import parser.states.JMCC.JMCC_4;
import parser.states.JMCC.JMCC_5;
import tokenizer.Token;
import tokenizer.TokenTypes;

/**
 * 
 * @author Chris Hersh
 *
 */
public class TestJMCC_4
{
	/**
	 * Reset Parser
	 */
    @Before
    public void setUp()
    {
        Parser.resetParser();
    }
    
    /**
     * Make sure and works
     * @throws ParserException
     */
    @Test
    public void testShiftAnd() throws ParserException
    {
        Parser p = Parser.getInstance();
        State s = new JMCC_4();
        
        Token inpToken = new Token("||", TokenTypes.And.name(), 6);
        
        p.getInputStack().push(inpToken);
        
        assertFalse(p.getInputStack().empty());
        assertEquals(p.getInputStack().peek(), inpToken);
        assertTrue(p.getHoldStack().empty());
        assertTrue(p.getStateStack().empty());
        
        //Make the current state the one we're testing
        p.changeState(s);
        
        p.nextState();
        
        assertTrue(p.getInputStack().empty());
        assertFalse(p.getHoldStack().empty());
        assertFalse(p.getStateStack().empty());
        
        assertEquals(p.getHoldStack().peek(), inpToken);
        assertEquals(p.getStateStack().peek(), s);
        
        assertTrue(p.getCurrentState() instanceof JMCC_10);
    }
    
    /**
     * Make sure OP2 works
     * @throws ParserException
     */
    @Test
    public void testShiftOP2() throws ParserException
    {
        Parser p = Parser.getInstance();
        State s = new JMCC_4();
        
        Token inpToken = new Token("||", TokenTypes.OP2.name(), 6);
        
        p.getInputStack().push(inpToken);
        
        assertFalse(p.getInputStack().empty());
        assertEquals(p.getInputStack().peek(), inpToken);
        assertTrue(p.getHoldStack().empty());
        assertTrue(p.getStateStack().empty());
        
        //Make the current state the one we're testing
        p.changeState(s);
        
        p.nextState();
        
        assertTrue(p.getInputStack().empty());
        assertFalse(p.getHoldStack().empty());
        assertFalse(p.getStateStack().empty());
        
        assertEquals(p.getHoldStack().peek(), inpToken);
        assertEquals(p.getStateStack().peek(), s);
        
        assertTrue(p.getCurrentState() instanceof JMCC_5);
    }
    
    /**
     * Make sure the reduce works
     * @throws ParserException
     */
    @Test
    public void testInvalidState() throws ParserException
    {
        Parser parser = Parser.getInstance();
        State state = new JMCC_4();
        ArrayList<Token> tokens = new ArrayList<Token>();
        tokens.add(new Token("id", TokenTypes.EXP1.name(), 1));
        tokens.add(new Token("id", TokenTypes.OP1.name(), 1));
        tokens.add(new Token("id", TokenTypes.EXP2.name(), 1));

        parser.pushHoldStack(tokens.get(2));
        parser.pushHoldStack(tokens.get(1));
        parser.pushHoldStack(tokens.get(0));

        parser.pushStateStack(new JMCC_0());
        parser.pushStateStack(new JMCC_1());
        parser.pushStateStack(new JMCC_3());

        state.invalidState();

        assertFalse(parser.getInputStack().empty());
        assertTrue(parser.getHoldStack().empty());
        assertTrue(parser.getStateStack().empty());
        Token token = new Token(TokenTypes.EXP1, tokens);
        assertEquals(parser.peekInputStack().getLineNumber(), token.getLineNumber());
        assertEquals(parser.peekInputStack().getToken(), token.getToken());
        assertEquals(parser.peekInputStack().getTokenName(), token.getTokenName());
        assertEquals(parser.peekInputStack().getChildren(), token.getChildren());
        assertEquals(parser.getCurrentState().getClass(), new JMCC_0().getClass());
    }
}
