package project;

import static org.junit.Assert.*;

import org.junit.Test;


public class BinaryMatrixTest {
	@Test
	public void testSetValue() throws Exception {
		BinaryMatrix bm = new BinaryMatrix(3);
		assertEquals(false, bm.getValue(0, 0));
		assertEquals(false, bm.getValue(2, 1));
		assertEquals(false, bm.getValue(1, 2));
		
		bm.setValue(0, 0, true);
		assertEquals(true, bm.getValue(0, 0));
		
		bm.setValue(1, 2, true);
		assertEquals(true, bm.getValue(1, 2));
		
		bm.setValue(2, 1, true);
		assertEquals(true, bm.getValue(2, 1));
	}
	
	@Test
	public void testIsReflexive() throws Exception {
		BinaryMatrix bm = new BinaryMatrix(3);
		bm.setValue(0, 0, true);
		bm.setValue(1, 1, true);
		bm.setValue(2, 2, true);
		
		assertTrue(bm.isReflexive());
		
		bm.setValue(2, 2, false);
		assertFalse(bm.isReflexive());
	}
	
	@Test
	public void testIsSymmetric() throws Exception {
		BinaryMatrix bm = new BinaryMatrix(3);
		bm.setValue(0, 0, true);
		bm.setValue(1, 1, true);
		bm.setValue(2, 2, true);
		bm.setValue(2, 1, true);
		bm.setValue(1, 2, true);
		
		assertTrue(bm.isSymmetric());
		
		bm.setValue(0, 1, true);
		
		assertFalse(bm.isSymmetric());

	}
	
	@Test
	public void testIsAntisymmetric() throws Exception {
		BinaryMatrix bm = new BinaryMatrix(3);
		bm.setValue(0, 0, true);
		bm.setValue(1, 1, true);
		bm.setValue(2, 2, true);
		
		assertTrue(bm.isAntisymmetric());
		
		bm.setValue(0, 1, true);
		
		assertTrue(bm.isAntisymmetric());
		
		bm.setValue(1, 0, true);
		assertFalse(bm.isAntisymmetric());


	}
	
	@Test
	public void testMakeIdentityMatrix() throws Exception {
		BinaryMatrix bm = new BinaryMatrix(3);
		bm.setValue(0, 1, true);
		bm.makeIdentityMatrix();
		assertTrue(bm.isReflexive());
		
		int rowIterator;
		int columnIterator;
		
		for (rowIterator = 0; rowIterator < bm.getSizeOfMatrix(); rowIterator++)
		{
			for (columnIterator = 0; columnIterator < bm.getSizeOfMatrix(); columnIterator++)
			{
				if (rowIterator != columnIterator)
				{
					if (bm.getValue(rowIterator, columnIterator))
					{
						fail();
					}
				}
			}
		}
		
	}
	
	
	
}
