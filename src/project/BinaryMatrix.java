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
	
	public void printBinary()
	{
		boolean[][] matrixToPrint = this.getMatrix();
		for (boolean[] rowOfMatrix : matrixToPrint) {
			for (boolean columnOfMatrix : rowOfMatrix) {
				if (columnOfMatrix)
					System.out.print("1 ");
				else
					System.out.print("0 ");
			}
			//New row
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
						//Iterate through matrix to find possible relations of B
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
}
