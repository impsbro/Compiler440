package parser.states;

/**
 * JMCC_31
 * State for EXP4
 * @author Daniel Breitigan
 *
 */
public class JMCC_31 extends State 
{

    @Override
    public void shiftEXP5() throws ParserException
    {
        changeToState(new JMCC_33());
    }
    
    @Override
    public void shiftEXP6() throws ParserException
    {
        changeToState(new JMCC_32());
    }
    
    @Override
    public void shiftEXP7() throws ParserException
    {
        changeToState(new JMCC_29());
    }
    
    @Override
    public void shiftTrue() throws ParserException
    {
        changeToState(new JCTM_15());
    }
    
    @Override
    public void shiftNew() throws ParserException
    {
        changeToState(new JCTM_19());
    }
    
    @Override
    public void shiftIntegerLiteral() throws ParserException
    {
        changeToState(new JCTM_18());
    }
    
    @Override
    public void shiftExclamation() throws ParserException
    {
        changeToState(new JCTM_27());
    }
    
    @Override
    public void shiftFalse() throws ParserException
    {
        changeToState(new JCTM_16());
    }
    
    @Override
    public void shiftId() throws ParserException
    {
        changeToState(new JCTM_1());
    }
    
    @Override
    public void shiftLeftPara() throws ParserException
    {
        changeToState(new JCTM_29());
    }
    
    @Override
    public void shiftThis() throws ParserException
    {
        changeToState(new JCTM_17());
    }
}