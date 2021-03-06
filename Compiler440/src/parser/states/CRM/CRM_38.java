package parser.states.CRM;

import parser.states.State;
import tokenizer.TokenTypes;
/**
 * @author Chris Kjeldgaard
 */
public class CRM_38 extends State {
	/**
	 * Reduces and places a FORMAL_L token on the stack
	 * 
	 * @author Chris Kjeldgaard
	 */
	@Override
	public void invalidState ()
	{
		reduceNumberOfStates(3, TokenTypes.FORMAL_L);
	}
	/**
	 * Changes to state CRM_39
	 * 
	 * @author Chris Kjeldgaard
	 */
	@Override
	public void shiftComa()
	{
		changeToState(new CRM_39());
	}
}
