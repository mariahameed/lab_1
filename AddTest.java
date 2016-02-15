package Test_Program;

import static org.junit.Assert.*;

import org.junit.Test;

public class AddTest {

	@Test
	public void addTest() throws Exception {
		My_JUnit_Class junit = new My_JUnit_Class();
		int x[][] = {{2,2},{1,1}};
		int y[][] = {{1,1},{0,0}};
		int result[][] = junit.add(x,y );
		int r1[][] = {{3,3},{1,1}};
		assertEquals(r1,result);
	}
}
