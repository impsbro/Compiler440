import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import parser.TestParser;
import symboltable.*;
import tokenizer.TestTokenizer;
import parser.states.*;

@RunWith(Suite.class) 
@Suite.SuiteClasses
( 
	{  
		TestCheckMainMethod.class,
		testClass.class,
		TestClasses.class,
		TestMainMethod.class,
		TestMethods.class,
		TestVariables.class,
		TestParser.class,
		TestTokenizer.class,
		TestClasses.class,
		TestSymbolTable.class,
		TestState.class,
		
		TestJMCC_27.class,
		TestJMCC_5.class,
		TestJMCC_23.class,
		TestJMCC_7.class,
		TestCRM_1.class,
		TestCRM_2.class,
		TestCRM_3.class,
	}
) 
 
public class AllTests 
{ 
} 
