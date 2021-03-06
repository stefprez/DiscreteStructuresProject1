/**
 * Stefano Prezioso
 * COSC 314 Project 1
 */
package project;

import java.util.ArrayList;

public class BinaryMatrix {
	boolean[][] matrix;
	int sizeOfMatrix;
	ArrayList<ArrayList<Integer>> equivalenceClasses;

	public BinaryMatrix(int sizeOfMatrix) {
		this.sizeOfMatrix = sizeOfMatrix;
		matrix = new boolean[sizeOfMatrix][sizeOfMatrix];
		equivalenceClasses = null;
	}
	
	public BinaryMatrix() {
		this(0);
	}
	
	public BinaryMatrix(BinaryMatrix binaryMatrixToCopy) {
		this.sizeOfMatrix = binaryMatrixToCopy.getSizeOfMatrix();
		this.matrix = binaryMatrixToCopy.getMatrix();
		this.equivalenceClasses = binaryMatrixToCopy.getEquivalenceClasses();
	}

	public ArrayList<ArrayList<Integer>> getEquivalenceClasses() {
		return equivalenceClasses;
	}

	public boolean[][] getMatrix() {
		return matrix;
	}

	public void setValue(int rowToSet, int columnToSet, boolean valueToSet) {
		matrix[rowToSet][columnToSet] = valueToSet;
	}

	public boolean getValue(int rowToReturn, int columnToReturn) {
		return matrix[rowToReturn][columnToReturn];
	}

	public int getSizeOfMatrix() {
		return sizeOfMatrix;
	}

	public void printBinaryMatrix() {
		boolean[][] matrixToPrint = this.getMatrix();
		
		for (boolean[] rowOfMatrix : matrixToPrint) {
			for (boolean columnOfMatrix : rowOfMatrix) {
				if (columnOfMatrix)
					System.out.print("1 ");
				else
					System.out.print("0 ");
			}
			// New row
			System.out.println();
		}
	}

	public void printBooleanMatrix() {
		boolean[][] matrixToPrint = this.getMatrix();
		
		for (boolean[] rowOfMatrix : matrixToPrint) {
			for (boolean columnOfMatrix : rowOfMatrix) {
				System.out.print(columnOfMatrix + " ");
			}
			// New row
			System.out.println();
		}
	}

	public boolean isEmptySet() {
		return (sizeOfMatrix == 0);
	}

	public boolean isReflexive() {
		
		if (this.isEmptySet()) 
		{
			return true;
		} 
		
		else 
		{
			for (int iterator = 0; iterator < sizeOfMatrix; iterator++) 
			{
				if (!this.getValue(iterator, iterator))
					return false;
			}
			return true;
		}
	}

	public boolean isSymmetric() {
		if (this.isEmptySet()) 
		{
			return true;
		} 
		else 
		{
			for (int rowIterator = 0; rowIterator < sizeOfMatrix; rowIterator++) {
				for (int columnIterator = 0; columnIterator < sizeOfMatrix; columnIterator++) 
				{
					//If rowIterator is related to columnIterator
					if (this.getValue(rowIterator, columnIterator)) 
					{
						//If columnIterator is not related to rowIterator
						if (!this.getValue(columnIterator, rowIterator)) {
							return false;
						}
					}
				}
			}
			return true;
		}
	}

	public boolean isAntisymmetric() {
		for (int rowIterator = 0; rowIterator < sizeOfMatrix; rowIterator++) {
			for (int columnIterator = 0; columnIterator < sizeOfMatrix; columnIterator++) 
			{
				//If rowIterator is related to columnIterator and they're not equal
				if (this.getValue(rowIterator, columnIterator)
						&& (rowIterator != columnIterator)) 
				{
					//If columnIterator is related to rowIterator
					if (this.getValue(columnIterator, rowIterator)) 
						return false;
				}
			}
		}
		return true;
	}

	/**
	 * Helper method used for testing. Turns the BinaryMatrix into
	 * an Identity Matrix
	 */
	public void makeIdentityMatrix() {
		for (int rowIterator = 0; rowIterator < sizeOfMatrix; rowIterator++) {
			for (int columnIterator = 0; columnIterator < sizeOfMatrix; columnIterator++) 
			{
				if (rowIterator == columnIterator)
					this.setValue(rowIterator, columnIterator, true);
				else 
					this.setValue(rowIterator, columnIterator, false);
			}
		}
	}

	public boolean isEmptyRelation() {
		for (int rowIterator = 0; rowIterator < sizeOfMatrix; rowIterator++) {
			for (int columnIterator = 0; columnIterator < sizeOfMatrix; columnIterator++) {
				if (this.getValue(rowIterator, columnIterator))
					return false;
			}
		}
		return true;
	}

	public boolean isTransitive() {
		if (this.isEmptySet())
			return true;
		
		else 
		{
			// Iterate through matrix
			for (int rowIterator = 0; rowIterator < sizeOfMatrix; rowIterator++) {
				for (int columnIterator = 0; columnIterator < sizeOfMatrix; columnIterator++) 
				{
					// If A is related to B
					if (this.getValue(rowIterator, columnIterator)) 
					{
						// Iterate through matrix to find possible relations of B
						for (int secondColumnIterator = 0; secondColumnIterator < sizeOfMatrix; secondColumnIterator++) 
						{
							if (this.getValue(columnIterator, secondColumnIterator)) 
							{
								// If B is not related to C
								if (!this.getValue(rowIterator, secondColumnIterator)) 
									return false;
							}
						}
					}
				}
			}
			return true;
		}
	}

	private ArrayList<ArrayList<Integer>> calculateEquivalenceClasses() {
		ArrayList<ArrayList<Integer>> equivalenceClassesToReturn = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> listOfElementsAccountedFor = new ArrayList<Integer>();
		int equivalenceClassCounter = 0;
		
		equivalenceClassesToReturn.add(new ArrayList<Integer>());
		
		// Iterate through matrix
		for (int rowIterator = 0; rowIterator < sizeOfMatrix; rowIterator++) {

			if (!listOfElementsAccountedFor.contains(rowIterator))
			{
				for (int columnIterator = 0; columnIterator < sizeOfMatrix; columnIterator++) 
				{
					// If A is related to B
					if (this.getValue(rowIterator, columnIterator)) 
					{
						//Add columnIterator to the current equivalence class and listOfElementsAccountedFor
						equivalenceClassesToReturn.get(equivalenceClassCounter).add(columnIterator);
						listOfElementsAccountedFor.add(columnIterator);
					}
				}
				equivalenceClassCounter++;
				//If we have not gone through all of the elements yet, make a new Equivalence Class
				if(listOfElementsAccountedFor.size() != sizeOfMatrix)
					equivalenceClassesToReturn.add(new ArrayList<Integer>());
			}
		}
		return equivalenceClassesToReturn;
	}

	public boolean isEquivalenceRelation() {
		if (this.isReflexive() && this.isSymmetric() && this.isTransitive())
		{
			equivalenceClasses = this.calculateEquivalenceClasses();
			return true;
		}
		else
			return false;
	}

	public BinaryMatrix getTransitiveClosure() {
		if (this.isTransitive())
			return this;
		
		else 
		{
			BinaryMatrix transitiveClosure = new BinaryMatrix(this);

			while (!transitiveClosure.isTransitive()) 
			{
				// Iterate through matrix
				for (int rowIterator = 0; rowIterator < sizeOfMatrix; rowIterator++) {
					for (int columnIterator = 0; columnIterator < sizeOfMatrix; columnIterator++) 
					{
						// If A is related to B
						if (transitiveClosure.getValue(rowIterator, columnIterator)) 
						{
							// Iterate through matrix to find possible relations of B
							for (int secondColumnIterator = 0; secondColumnIterator < sizeOfMatrix; secondColumnIterator++) 
							{
								if (transitiveClosure.getValue(columnIterator, secondColumnIterator)) 
								{
									// If B is not related to C, make B related to C
									if (!transitiveClosure.getValue(rowIterator, secondColumnIterator)) 
										transitiveClosure.setValue(rowIterator, secondColumnIterator, true);
								}
							}
						}
					}
				}
			}
			return transitiveClosure;
		}
	}
	/**
 	* Formats the Equivalence Classes nicely when printing.
 	*/
	public void printEquivalenceClasses()
	{
		if (!this.isEquivalenceRelation())
			return;
		
		else
		{
			String stringToPrint = "";
			for (ArrayList<Integer> arrayList : equivalenceClasses) {
				stringToPrint += "{";
				for (Integer integer : arrayList) {
					stringToPrint += (integer + 1) + ", ";
				}
				stringToPrint = stringToPrint.substring(0, stringToPrint.length() - 2) + "}, ";
			}
			stringToPrint = stringToPrint.substring(0, stringToPrint.length() - 2);
			
			System.out.println(stringToPrint);
		}
	}
	
	public void printReport() {
		System.out.println("The relation with the matrix:\n");
		this.printBinaryMatrix();
		System.out.println();

		if (this.isReflexive())
			System.out.println("is reflexive,");
		else
			System.out.println("is not reflexive,");

		if (this.isSymmetric())
			System.out.println("is symmetric,");
		else
			System.out.println("is not symmetric,");

		if (this.isTransitive())
			System.out.println("is transitive,");
		else
			System.out.println("is not transitive,");

		if (this.isAntisymmetric())
			System.out.println("is antisymmetric,");
		else
			System.out.println("is not antisymmetric,");

		if (this.isEquivalenceRelation())
		{
			System.out
			.println("and is an equivalence relation with equivalence classes");
			this.printEquivalenceClasses();
		}
		else
			System.out.println("and is not an equivalence relation.");

		if (!this.isTransitive()) {
			System.out.println("The matrix of its transitive closure is:\n");
			this.getTransitiveClosure().printBinaryMatrix();
		}

		System.out.println();

	}
}
