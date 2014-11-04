package project;

import static org.junit.Assert.*;

import org.junit.Test;


public class BinaryMatrixTest {
	@Test
	public void testSetValue() throws Exception {
		BinaryMatrix binaryMatrix = new BinaryMatrix(3);
		assertEquals(false, binaryMatrix.getValue(0, 0));
		assertEquals(false, binaryMatrix.getValue(2, 1));
		assertEquals(false, binaryMatrix.getValue(1, 2));
		
		binaryMatrix.setValue(0, 0, true);
		assertEquals(true, binaryMatrix.getValue(0, 0));
		
		binaryMatrix.setValue(1, 2, true);
		assertEquals(true, binaryMatrix.getValue(1, 2));
		
		binaryMatrix.setValue(2, 1, true);
		assertEquals(true, binaryMatrix.getValue(2, 1));
	}
	
	@Test
	public void testIsReflexive() throws Exception {
		BinaryMatrix binaryMatrix = new BinaryMatrix(3);
		binaryMatrix.setValue(0, 0, true);
		binaryMatrix.setValue(1, 1, true);
		binaryMatrix.setValue(2, 2, true);
		
		assertTrue(binaryMatrix.isReflexive());
		
		binaryMatrix.setValue(2, 2, false);
		assertFalse(binaryMatrix.isReflexive());
		
		binaryMatrix = new BinaryMatrix();
		assertTrue(binaryMatrix.isReflexive());
	}
	
	@Test
	public void testIsSymmetric() throws Exception {
		BinaryMatrix binaryMatrix = new BinaryMatrix(3);
		binaryMatrix.setValue(0, 0, true);
		binaryMatrix.setValue(1, 1, true);
		binaryMatrix.setValue(2, 2, true);
		binaryMatrix.setValue(2, 1, true);
		binaryMatrix.setValue(1, 2, true);
		
		assertTrue(binaryMatrix.isSymmetric());
		
		binaryMatrix.setValue(0, 1, true);
		
		assertFalse(binaryMatrix.isSymmetric());
		
		binaryMatrix = new BinaryMatrix();
		assertTrue(binaryMatrix.isSymmetric());

	}
	
	@Test
	public void testIsAntisymmetric() throws Exception {
		BinaryMatrix binaryMatrix = new BinaryMatrix(3);
		binaryMatrix.setValue(0, 0, true);
		binaryMatrix.setValue(1, 1, true);
		binaryMatrix.setValue(2, 2, true);
		
		assertTrue(binaryMatrix.isAntisymmetric());
		
		binaryMatrix.setValue(0, 1, true);
		
		assertTrue(binaryMatrix.isAntisymmetric());
		
		binaryMatrix.setValue(1, 0, true);
		assertFalse(binaryMatrix.isAntisymmetric());


	}
	
	@Test
	public void testMakeIdentityMatrix() throws Exception 
	{
		BinaryMatrix binaryMatrix = new BinaryMatrix(3);
		binaryMatrix.setValue(0, 1, true);
		binaryMatrix.makeIdentityMatrix();
		assertTrue(binaryMatrix.isReflexive());
		
		int rowIterator;
		int columnIterator;
		
		for (rowIterator = 0; rowIterator < binaryMatrix.getSizeOfMatrix(); rowIterator++)
		{
			for (columnIterator = 0; columnIterator < binaryMatrix.getSizeOfMatrix(); columnIterator++)
			{
				if (rowIterator != columnIterator)
				{
					if (binaryMatrix.getValue(rowIterator, columnIterator))
					{
						fail();
					}
				}
			}
		}
	}
	
	@Test
	public void testIsEmptyRelation() throws Exception {
		BinaryMatrix binaryMatrix = new BinaryMatrix(3);
		assertTrue(binaryMatrix.isEmptyRelation());
		binaryMatrix.setValue(1, 1, true);
		assertFalse(binaryMatrix.isEmptyRelation());
		
	}
	
	@Test
	public void testIsTransitive() throws Exception {
		
		BinaryMatrix binaryMatrix = new BinaryMatrix();
		assertTrue(binaryMatrix.isTransitive());
		
		binaryMatrix = new BinaryMatrix(2);
		binaryMatrix.setValue(0, 0, true);
		binaryMatrix.setValue(0, 1, true);
		binaryMatrix.setValue(1, 0, true);
		binaryMatrix.setValue(1, 1, true);
		assertTrue(binaryMatrix.isTransitive());
		
		binaryMatrix = new BinaryMatrix(3);
		binaryMatrix.setValue(0, 2, true);
		binaryMatrix.setValue(0, 1, true);
		binaryMatrix.setValue(1, 2, true);
		assertTrue(binaryMatrix.isTransitive());
		
	}
	
	@Test
	public void testIsEmptySet() throws Exception {
		BinaryMatrix binaryMatrix = new BinaryMatrix(3);
		assertFalse(binaryMatrix.isEmptySet());
		binaryMatrix = new BinaryMatrix(0);
		assertTrue(binaryMatrix.isEmptySet());
	}
	
	@Test
	public void testIsEquivalenceRelation() throws Exception {
		BinaryMatrix binaryMatrix = new BinaryMatrix(2);
		binaryMatrix.setValue(0, 0, true);
		binaryMatrix.setValue(0, 1, true);
		binaryMatrix.setValue(1, 0, true);
		binaryMatrix.setValue(1, 1, true);
		assertTrue(binaryMatrix.isEquivalenceRelation());
	}
	
	@Test
	public void testTransitiveClosure() throws Exception {
		BinaryMatrix binaryMatrix = new BinaryMatrix(2);
		binaryMatrix.setValue(0, 0, true);
		binaryMatrix.setValue(0, 1, true);
		binaryMatrix.setValue(1, 0, true);
		assertFalse(binaryMatrix.isTransitive());
		BinaryMatrix transitiveClosure = binaryMatrix.getTransitiveClosure();
		assertTrue(transitiveClosure.getValue(1, 1));
		assertTrue(transitiveClosure.isTransitive());

		binaryMatrix = new BinaryMatrix(8);
		binaryMatrix.setValue(0, 0, true);
		binaryMatrix.setValue(0, 3, true);
		binaryMatrix.setValue(0, 7, true);
		binaryMatrix.setValue(1, 1, true);
		binaryMatrix.setValue(1, 5, true);
		binaryMatrix.setValue(2, 2, true);
		binaryMatrix.setValue(3, 2, true);
		binaryMatrix.setValue(3, 7, true);
		binaryMatrix.setValue(4, 4, true);
		binaryMatrix.setValue(4, 6, true);
		binaryMatrix.setValue(5, 5, true);
		binaryMatrix.setValue(6, 1, true);
		binaryMatrix.setValue(6, 5, true);
		binaryMatrix.setValue(6, 6, true);
		binaryMatrix.setValue(7, 3, true);
		binaryMatrix.setValue(7, 4, true);
		binaryMatrix.setValue(7, 5, true);

		transitiveClosure = binaryMatrix.getTransitiveClosure();
		assertTrue(transitiveClosure.isTransitive());

		
	}
	
	@Test
	public void testEquivalenceClasses() throws Exception {
		BinaryMatrix binaryMatrix = new BinaryMatrix(3);
		binaryMatrix.setValue(0, 0, true);
		binaryMatrix.setValue(0, 1, true);
		binaryMatrix.setValue(1, 0, true);
		binaryMatrix.setValue(1, 1, true);
		binaryMatrix.setValue(2, 2, true);
		assertTrue(binaryMatrix.isTransitive());
		System.out.println(binaryMatrix.getEquivalenceClasses());
	}
	
}
