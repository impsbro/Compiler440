package parser.states;

import tokenizer.TokenTypes;

/**
 *This class is CILS_32 which represents this: STMT -> if ( EXP1 ) STMT
 * 
 * @author Mohammed
 */
public class CILS_32 extends State 
{
	/**
	 * because we do not have no more inputs, reduce to CILS_5
	 */
	@Override
	public void invalidState() throws ParserException
	{
		reduceToState(new CILS_5(), TokenTypes.STMT);
	}
}