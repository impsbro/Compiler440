package parser.states.CILS;

import parser.states.ParserException;
import parser.states.State;

public class CILS_17 extends State {

	/**
	 * @author Corey Peregord 
	 * Method used to shift on ( and change to CILS_22
	 */
	@Override
	public void shiftRightPara() throws ParserException
	{
		changeToState(new CILS_22());
	}
}
