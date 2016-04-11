package parser.states;

public class CILS_13 extends State {

	/**
	 * Method used to shift on ( and change to state 13
	 */
	@Override
	public void shiftEXP1() throws ParserException
	{
		changeToState(new CILS_17());
	}
}
