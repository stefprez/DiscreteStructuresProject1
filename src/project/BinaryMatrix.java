package project;

public class BinaryMatrix {
	boolean[][] matrix;
	int sizeOfMatrix;

	public BinaryMatrix(int sizeOfMatrix)
	{
		this.sizeOfMatrix = sizeOfMatrix;
		matrix = new boolean[sizeOfMatrix][sizeOfMatrix];
	}

	public BinaryMatrix()
	{
		this(0);
	}

	public boolean[][] getMatrix() {
		return matrix;
	}

	public void setValue(int rowToSet, int columnToSet, boolean valueToSet)
	{
		matrix[rowToSet][columnToSet] = valueToSet;
	}

	public boolean getValue(int rowToReturn, int columnToReturn)
	{
		return matrix[rowToReturn][columnToReturn];
	}

	public int getSizeOfMatrix() {
		return sizeOfMatrix;
	}

	public void print()
	{
		boolean[][] matrixToPrint = this.getMatrix();
		for (boolean[] rowOfMatrix : matrixToPrint) {
			for (boolean columnOfMatrix : rowOfMatrix) {
				System.out.print(columnOfMatrix + " ");
			}
			//New row
			System.out.println();
		}
	}
	
	public boolean isEmptySet()
	{
		return (sizeOfMatrix == 0);
	}
	
	public boolean isReflexive()
	{
		if (this.isEmptySet())
		{
			return true;
		}
		else
		{
			int iterator;

			for (iterator = 0; iterator < sizeOfMatrix; iterator++)
			{
				if (!this.getValue(iterator, iterator))
				{
					return false;
				}
			}

			return true;
		}
	}

	public boolean isSymmetric()
	{
		if (this.isEmptySet())
		{
			return true;
		}
		else
		{
			int rowIterator;
			int columnIterator;

			for (rowIterator = 0; rowIterator < sizeOfMatrix; rowIterator++)
			{
				for (columnIterator = 0; columnIterator < sizeOfMatrix; columnIterator++)
				{
					if (this.getValue(rowIterator, columnIterator))
					{
						if (!this.getValue(columnIterator, rowIterator))
						{
							return false;
						}
					}
				}
			}

			return true;
		}
	}

	public boolean isAntisymmetric()
	{
		int rowIterator;
		int columnIterator;

		for (rowIterator = 0; rowIterator < sizeOfMatrix; rowIterator++)
		{
			for (columnIterator = 0; columnIterator < sizeOfMatrix; columnIterator++)
			{
				if (this.getValue(rowIterator, columnIterator) && (rowIterator != columnIterator))
				{
					if (this.getValue(columnIterator, rowIterator))
					{
						return false;
					}
				}
			}
		}

		return true;
	}

	public void makeIdentityMatrix()
	{
		int rowIterator;
		int columnIterator;

		for (rowIterator = 0; rowIterator < sizeOfMatrix; rowIterator++)
		{
			for (columnIterator = 0; columnIterator < sizeOfMatrix; columnIterator++)
			{
				if (rowIterator == columnIterator)
				{
					this.setValue(rowIterator, columnIterator, true);
				}
				else
				{
					this.setValue(rowIterator, columnIterator, false);
				}
			}
		}
	}

	public boolean isEmptyRelation()
	{
		int rowIterator;
		int columnIterator;

		for (rowIterator = 0; rowIterator < sizeOfMatrix; rowIterator++)
		{
			for (columnIterator = 0; columnIterator < sizeOfMatrix; columnIterator++)
			{
				if (this.getValue(rowIterator, columnIterator))
				{
					return false;
				}
			}
		}

		return true;
	}

	public static void main(String[] args) {
		new BinaryMatrix(6).print();
	}

	public boolean isTransitive() {
		// TODO Auto-generated method stub
		return false;
	}
}
