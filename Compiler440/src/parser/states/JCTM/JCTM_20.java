package parser.states.JCTM;

import parser.states.ParserException;
import parser.states.State;

/**
 * 
 * @author Matt Mousetis
 *
 */

public class JCTM_20 extends State 
{
	/**
	 * Shift on leftBrace
	 */
    public void shiftLeftBrace() throws ParserException
    {
    	changeToState(new JCTM_21());
    }
}
