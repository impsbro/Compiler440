package intermediate.process;

import tokenizer.Token;
import tokenizer.TokenTypes;

/**
 * @author Curtis Rabe, Jared Good, Mike Zimmerman
 * 
 * The Process class for EXP_L (3 passes included)
 *
 */
public class ProcessEXP_L
{
	/**
	 * Using DFS, checks to make sure that the current token has children, then
	 * processes the first child of EXP_L (which is EXP1), Then processes the second child
	 * (of 2 total children) which is EXP_R. Nothing is added to the symboltable at this step,
	 * because the program would not yet have come across an addable item.
	 * 
	 * @param subject the incoming token
	 * 
	 */
	public static void processPass1(Token subject)
	{
		subject.getChildren().get(0).setParentClass(subject.getParentClass());
		subject.getChildren().get(0).setParentMethod(subject.getParentMethod());
		subject.getChildren().get(1).setParentClass(subject.getParentClass());
		subject.getChildren().get(1).setParentMethod(subject.getParentMethod());
		if(subject.getChildren().get(0).isVisited() == false)
		{
			Token.pass1(subject.getChildren());
		}
		subject.setVisited();
		subject.setType(subject.getChildren().get(0).getType());
		
	}

	/**
	 * @author Mike Zimmerman
	 * the second pass for processing EXP_L
	 * basically just passes to the next token. It does NOT (Should NOT) check for types 
	 * @param subject
	 */
	public static void processPass2(Token subject)  throws ProcessException
	{
		if(subject.getChildren() != null)
		{
			// Do children passes.
			if(subject.getChildren().get(0).isVisited() == false)
			{
				Token.pass2(subject.getChildren());
			}
		}
		subject.setVisited();
	}

	
	/**
	 * Generates intermediate code for EXP_L
	 * 
	 * @author Jared Good
	 * @param subject Token to be processed
	 */
	public static void processPass3(Token subject) 
	{
		// Generates the code for the rule:
		// EXP_L -> EXP1, EXP_R
		if( subject.getChildren().get(0).getTokenName().equals( TokenTypes.EXP1.name() ) )
		{
			// Tokens and string to keep track of code
			Token exp = subject.getChildren().get(0);
			Token expr = subject.getChildren().get(1);
			String code;
			
			// Processes EXP1 and EXP_R to generate their intermediate code
			if(!exp.isVisited())
			{
				Token.pass3(exp);
			}
			
			if(!expr.isVisited())
			{
				Token.pass3(expr);
			}
			
			// Adds EXP1 code to the EXP_L token
			code = exp.getCode().toString();
			subject.getCode().append(code);
			
			// Adds EXP_R code to the EXP_L token
			code = expr.getCode().toString();
			subject.getCode().append(code);
		}	
		
		// Sets the subject to visited
		subject.setVisited();
	}
}
