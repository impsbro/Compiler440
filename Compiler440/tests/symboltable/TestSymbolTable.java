package symboltable;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import symboltable.method.impl.PublicMethod;
import symboltable.variable.impl.InstanceObjectVariable;
import symboltable.variable.impl.InstancePrimitiveVariable;
import symboltable.variable.impl.LocalObjectVariable;
import symboltable.variable.impl.LocalPrimitiveVariable;
import symboltable.variable.impl.MethodPrimitiveVariable;

/**
 * @author Curtis Rabe, Mike Zimmerman, Jared Good
 */
public class TestSymbolTable {

	/**
	 * Destroys the symboltable for testing...
	 * This method may or may not be dangerous... Use with caution.
	 */
	@Before
	public void resetSymbolTable()
	{
		SymbolTable.getInstance().destroyMe();
	}
	
	/**
	 * Test the initialization of a symbol table.
	 */
	@Test
	public void testInitialization() {
		assertNotNull(SymbolTable.getInstance());
		assertTrue(SymbolTable.getInstance().variableTableIsEmpty());
		assertTrue(SymbolTable.getInstance().methodTableIsEmpty());
		assertTrue(SymbolTable.getInstance().classTableIsEmpty());
	}
	
	/**
	 * Test the storing of parameters from methods into the symbol table
	 */
	@Test
	public void testStoringParams()
	{
		MethodPrimitiveVariable mpv1 = new MethodPrimitiveVariable("Name1", "ClassName", "MethodName", VariableScope.METHOD, VariableType.INTEGER);
		SymbolTable.getInstance().addVariable("Name1", mpv1);
		assertFalse(SymbolTable.getInstance().variableTableIsEmpty());
		assertTrue(SymbolTable.getInstance().getVariableList("Name1").contains(mpv1));
		//assert that the symbol table contains the added variable
	}
	
	/**
	 * Test the storing of two variables of the same name into the symbol table
	 *  - this uses the same key in the hash map, and is stored in order of adding in the hash map's arrayList
	 */
	@Test
	public void testStoringVariablesSameName()
	{
		InstanceObjectVariable iov1 = new InstanceObjectVariable("Object", "class", "id", VariableScope.INSTANCE, VariableType.BOOLEAN);
		LocalObjectVariable lov1 = new LocalObjectVariable("Object", "class", "id2", "methodName", VariableScope.LOCAL, VariableType.BOOLEAN);
		SymbolTable.getInstance().addVariable("Object", iov1);
		SymbolTable.getInstance().addVariable("Object", lov1);
		assertTrue(SymbolTable.getInstance().getVariableList("Object").contains(iov1));
		assertTrue(SymbolTable.getInstance().getVariableList("Object").contains(lov1));
	}
	
	/**
	 * Test the order of parameters from methods being stored in the symbol table
	 *  - This should access the methodTable in order access the method's arrayList
	 *  		of parameters to ensure order there. They should also be separately
	 *  		added to the variableTable to that that table can properly keep track
	 *  		of all variables input into the program (Curtis Rabe)
	 */
	@Test
	public void testOrderParams()
	{
		MethodPrimitiveVariable mpv1 = new MethodPrimitiveVariable("Name1", "ClassName", "MethodName", VariableScope.METHOD, VariableType.INTEGER);
		MethodPrimitiveVariable mpv2 = new MethodPrimitiveVariable("Name2", "ClassName", "MethodName", VariableScope.METHOD, VariableType.BOOLEAN);
		MethodPrimitiveVariable mpv3 = new MethodPrimitiveVariable("Name3", "ClassName", "MethodName", VariableScope.METHOD, VariableType.INTEGER);
		ArrayList<Variable> params = new ArrayList<Variable>();
		params.add(mpv1);
		params.add(mpv2);
		params.add(mpv3);
		Class c1 = new Class("ClassName",null,  null);
		PublicMethod m1 = new PublicMethod("MethodName", c1, VariableType.INTEGER, params);
		SymbolTable.getInstance().addMethod("MethodName", m1);
		assertTrue(SymbolTable.getInstance().getMethodList("MethodName").contains(m1));
		assertTrue(SymbolTable.getInstance().getVariableList("Name1").contains(mpv1));
		assertTrue(SymbolTable.getInstance().getVariableList("Name2").contains(mpv2));
		assertTrue(SymbolTable.getInstance().getVariableList("Name3").contains(mpv3));
	}
	
	/**
	 * Test the storing of parameters from methods into the symbol table
	 */
	@Test
	public void testStoringNoParams()
	{
		ArrayList<Variable> params = new ArrayList<Variable>();
		Class c1 = new Class("ClassName", null, null);
		PublicMethod m1 = new PublicMethod("MethodName", c1, VariableType.INTEGER, params);
		SymbolTable.getInstance().addMethod("MethodName", m1);
		//Note: No parameters added to "params"
		assertTrue(SymbolTable.getInstance().getMethodList("MethodName").contains(m1));
		assertTrue(SymbolTable.getInstance().variableTableIsEmpty());
	}
	
	/**
	 * Test the storing of variables into the symbol table
	 */
	@Test
	public void testStoringVars()
	{
		MethodPrimitiveVariable mpv1 = new MethodPrimitiveVariable("Name1", "ClassName", "MethodName", VariableScope.METHOD, VariableType.INTEGER);
		LocalPrimitiveVariable lpv1 = new LocalPrimitiveVariable("Name2", "ClassName", "MethodName", VariableScope.LOCAL, VariableType.INTEGER);
		LocalPrimitiveVariable lpv2 = new LocalPrimitiveVariable("Name3", "ClassName", "MethodName", VariableScope.LOCAL, VariableType.INTEGER);
		LocalObjectVariable lov1 = new LocalObjectVariable("Name4", "ClassName", "id", "MethodName", VariableScope.LOCAL, VariableType.OBJECT);

		//passes in the method name and the list of variables within it
		
		SymbolTable.getInstance().addVariable("Name1", mpv1);
		SymbolTable.getInstance().addVariable("Name2", lpv1);
		SymbolTable.getInstance().addVariable("Name3", lpv2);
		SymbolTable.getInstance().addVariable("Name4", lov1);
		assertTrue(SymbolTable.getInstance().getVariableList("Name1").contains(mpv1));
		assertTrue(SymbolTable.getInstance().getVariableList("Name2").contains(lpv1));
		assertTrue(SymbolTable.getInstance().getVariableList("Name3").contains(lpv2));
		assertTrue(SymbolTable.getInstance().getVariableList("Name4").contains(lov1));

	}
	
	/**
	 * By Mike Zimmerman, Curtis Rabe
	 * Test to make sure a class can be stored in the symbol table 
	 */
	@Test
	public void testStoringClasses()
	{
		Class cla1 = new Class("Name1", null,null );

		SymbolTable.getInstance().addClass("Name1", cla1);
		assertEquals(cla1, SymbolTable.getInstance().getClass(cla1.getName()));
	}
	
	/**
	 * By Mike Zimmerman
	 * test to make sure the instance vars from a class are stored and retrievable  
	 */
	@Test
	public void testInstanceVarsInClasses()
	{
		InstancePrimitiveVariable ipv = new InstancePrimitiveVariable("Name1", "cla1", VariableScope.INSTANCE, VariableType.INTEGER);
		
		ArrayList<Variable> vars = new ArrayList<Variable>();
		vars.add(ipv);
		
		Class cla1 = new Class("Name1", vars , null );

		SymbolTable.getInstance().addClass(cla1.getName(), cla1);
		
		assertEquals(cla1, SymbolTable.getInstance().getClass(cla1.getName()));
		assertEquals(vars, SymbolTable.getInstance().getClass(cla1.getName()).getInstanceVars());
		assertEquals("Name1", SymbolTable.getInstance().getClass(cla1.getName()).getInstanceVars().get(0).getName());
		assertEquals("cla1", SymbolTable.getInstance().getClass(cla1.getName()).getInstanceVars().get(0).getClassName());	
		assertEquals(VariableScope.INSTANCE, SymbolTable.getInstance().getClass(cla1.getName()).getInstanceVars().get(0).getScope());
		assertEquals(VariableType.INTEGER, SymbolTable.getInstance().getClass(cla1.getName()).getInstanceVars().get(0).getType());	
	}

}