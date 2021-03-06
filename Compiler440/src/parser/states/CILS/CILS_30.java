package parser.states.CILS;

import parser.states.State;
import tokenizer.TokenTypes;

/**
 *This class is CILS_30 which represents this: STMT -> if ( EXP1 ) STMT else STMT •
 * 
 * @author Raistlin Hess
 */
public class CILS_30 extends State 
{
	/**
	 * Since there can be no more inputs, reduce to CILS_5
	 */
	@Override
	public void invalidState()
	{
		reduceNumberOfStates(7, TokenTypes.STMT);
	}
}
