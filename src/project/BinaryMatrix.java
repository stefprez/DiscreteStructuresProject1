package project;

public class BinaryMatrix {
	boolean[][] matrix;
	int sizeOfMatrix;

	public BinaryMatrix(int sizeOfMatrix) {
		this.sizeOfMatrix = sizeOfMatrix;
		matrix = new boolean[sizeOfMatrix][sizeOfMatrix];
	}

	public BinaryMatrix() {
		this(0);
	}

	public BinaryMatrix(BinaryMatrix binaryMatrixToCopy) {
		this.sizeOfMatrix = binaryMatrixToCopy.getSizeOfMatrix();
		this.matrix = binaryMatrixToCopy.getMatrix();
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

	public void printBinary() {
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

	public void printBoolean() {
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
		if (this.isEmptySet()) {
			return true;
		} else {
			int iterator;

			for (iterator = 0; iterator < sizeOfMatrix; iterator++) {
				if (!this.getValue(iterator, iterator)) {
					return false;
				}
			}

			return true;
		}
	}

	public boolean isSymmetric() {
		if (this.isEmptySet()) {
			return true;
		} else {
			int rowIterator;
			int columnIterator;

			for (rowIterator = 0; rowIterator < sizeOfMatrix; rowIterator++) {
				for (columnIterator = 0; columnIterator < sizeOfMatrix; columnIterator++) {
					if (this.getValue(rowIterator, columnIterator)) {
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
		int rowIterator;
		int columnIterator;

		for (rowIterator = 0; rowIterator < sizeOfMatrix; rowIterator++) {
			for (columnIterator = 0; columnIterator < sizeOfMatrix; columnIterator++) {
				if (this.getValue(rowIterator, columnIterator)
						&& (rowIterator != columnIterator)) {
					if (this.getValue(columnIterator, rowIterator)) {
						return false;
					}
				}
			}
		}

		return true;
	}

	public void makeIdentityMatrix() {
		int rowIterator;
		int columnIterator;

		for (rowIterator = 0; rowIterator < sizeOfMatrix; rowIterator++) {
			for (columnIterator = 0; columnIterator < sizeOfMatrix; columnIterator++) {
				if (rowIterator == columnIterator) {
					this.setValue(rowIterator, columnIterator, true);
				} else {
					this.setValue(rowIterator, columnIterator, false);
				}
			}
		}
	}

	public boolean isEmptyRelation() {
		int rowIterator;
		int columnIterator;

		for (rowIterator = 0; rowIterator < sizeOfMatrix; rowIterator++) {
			for (columnIterator = 0; columnIterator < sizeOfMatrix; columnIterator++) {
				if (this.getValue(rowIterator, columnIterator)) {
					return false;
				}
			}
		}

		return true;
	}

	public boolean isTransitive() {
		if (this.isEmptySet())
			return true;
		else {

			// Iterate through matrix
			for (int rowIterator = 0; rowIterator < sizeOfMatrix; rowIterator++) {
				for (int columnIterator = 0; columnIterator < sizeOfMatrix; columnIterator++) {
					// If A is related to B
					if (this.getValue(rowIterator, columnIterator)) {
						// Iterate through matrix to find possible relations of
						// B
						for (int secondColumnIterator = 0; secondColumnIterator < sizeOfMatrix; secondColumnIterator++) {
							if (this.getValue(columnIterator,
									secondColumnIterator)) {
								// If B is not related to C
								if (!this.getValue(rowIterator,
										secondColumnIterator)) {
									return false;
								}
							}
						}
					}
				}
			}

			return true;
		}
	}

	public boolean isEquivalenceRelation() {
		return (this.isReflexive() && this.isSymmetric() && this.isTransitive());
	}

	public BinaryMatrix getTransitiveClosure() {
		if (this.isTransitive()) {
			return this;
		} else {
			BinaryMatrix transitiveClosure = new BinaryMatrix(this);

			while (!transitiveClosure.isTransitive()) {
				// Iterate through matrix
				for (int rowIterator = 0; rowIterator < sizeOfMatrix; rowIterator++) {
					for (int columnIterator = 0; columnIterator < sizeOfMatrix; columnIterator++) {
						// If A is related to B
						if (transitiveClosure.getValue(rowIterator,
								columnIterator)) {
							// Iterate through matrix to find possible relations
							// of B
							for (int secondColumnIterator = 0; secondColumnIterator < sizeOfMatrix; secondColumnIterator++) {
								if (transitiveClosure.getValue(columnIterator,
										secondColumnIterator)) {
									// If B is not related to C, make B related
									// to C
									if (!transitiveClosure.getValue(
											rowIterator, secondColumnIterator)) {
										transitiveClosure.setValue(rowIterator,
												secondColumnIterator, true);
									}
								}
							}
						}
					}
				}
			}
			return transitiveClosure;
		}
	}
	
	public void printReport()
	{
		System.out.println("The relation with the matrix:\n");
		this.printBinary();
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
			System.out.println("and is an equivalence relation with equivalence classes");
//			this.printEquivalenceClasses();
		else
			System.out.println("and is not an equivalence relation.");
		
		if (!this.isTransitive())
		{
			System.out.println("The matrix of its transitive closure is:\n");
			this.getTransitiveClosure().printBinary();
		}
		
		System.out.println();
	}
}
