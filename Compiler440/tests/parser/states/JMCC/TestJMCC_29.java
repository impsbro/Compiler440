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
import tokenizer.Token;
import tokenizer.TokenTypes;

/**
 * Test reduce works for JMCC_29
 * @author Daniel Breitigan, Ian Keefer
 *
 */
public class TestJMCC_29
{
    /**
     * Reset the parser
     */
    @Before
    public void setUp()
    {
        Parser.resetParser();
    }
    
    @Test
    public void testReduce() throws ParserException
    {
        Parser parser = Parser.getInstance();
        State state = new JMCC_29();
        ArrayList<Token> tokens = new ArrayList<Token>();
        tokens.add(new Token("id", TokenTypes.EXP7.name(), 1));

        parser.pushHoldStack(tokens.get(0));

        parser.pushStateStack(new JMCC_19());

        state.invalidState();

        assertFalse(parser.getInputStack().empty());
        assertTrue(parser.getHoldStack().empty());
        assertTrue(parser.getStateStack().empty());
        Token token = new Token(TokenTypes.EXP6, tokens);
        assertEquals(parser.peekInputStack().getLineNumber(), token.getLineNumber());
        assertEquals(parser.peekInputStack().getToken(), token.getToken());
        assertEquals(parser.peekInputStack().getTokenName(), token.getTokenName());
        assertEquals(parser.peekInputStack().getChildren(), token.getChildren());
        assertEquals(parser.getCurrentState().getClass(), new JMCC_19().getClass());
    }
}
