package parser.states.JMCC;

import parser.states.ParserException;
import parser.states.State;
import parser.states.JCTM.JCTM_1;
import parser.states.JCTM.JCTM_15;
import parser.states.JCTM.JCTM_16;
import parser.states.JCTM.JCTM_17;
import parser.states.JCTM.JCTM_18;
import parser.states.JCTM.JCTM_19;
import parser.states.JCTM.JCTM_29;

/**
 * 
 * @author Chris Hersh
 *
 */
public class JMCC_3 extends State 
{    
	/**
	 * Shift on EXP2
	 */
    @Override
    public void shiftEXP2() throws ParserException {
        changeToState(new JMCC_4());
    }
    
    /**
     * Shift on EXP3
     */
    @Override
    public void shiftEXP3() throws ParserException {
        changeToState(new JMCC_15());
    }
    
    /**
     * Shift on EXP4
     */
    @Override
    public void shiftEXP4() throws ParserException {
        changeToState(new JMCC_8());
    }
    
	/**
	 * Shift to JMCC_20 if the shift is EXP5.
	 */
	@Override
	public void shiftEXP5()
	{
		changeToState(new JMCC_20());
	}
	
	/**
	 * Shift to JMCC_32 if the shift is 6.
	 */
	@Override
	public void shiftEXP6()
	{
		changeToState(new JMCC_32());
	}
    
	/**
	 * Shift on EXP7
	 */
    @Override
    public void shiftEXP7() throws ParserException {
        changeToState(new JMCC_29());
    }
    
    /**
     * Shift on this
     */
    @Override
    public void shiftThis() throws ParserException {
        changeToState(new JCTM_17());
    }
    
    /**
     * Shift on {
     */
    @Override
    public void shiftLeftBrace() throws ParserException {
        changeToState(new JCTM_29());
    }
    
	/**
	 * Shift on id
	 */
    @Override
    public void shiftId() throws ParserException {
        changeToState(new JCTM_1());
    }
    
    /**
     * Shift on false
     */
    @Override
    public void shiftFalse() throws ParserException {
        changeToState(new JCTM_16());
    }
    
    /**
     * Shift on not
     */
    @Override
    public void shiftNot() throws ParserException {
        changeToState(new JCTM_16());
    }
    
    /**
     * Shift on integer literal
     */
    @Override
    public void shiftIntegerLiteral() throws ParserException {
        changeToState(new JCTM_18());
    }
    
    /**
     * Shift on new
     */
    @Override
    public void shiftNew() throws ParserException {
        changeToState(new JCTM_19());
    }
    
    /**
     * Shift on true
     */
    @Override
    public void shiftTrue() throws ParserException {
        changeToState(new JCTM_15());
    }
}
