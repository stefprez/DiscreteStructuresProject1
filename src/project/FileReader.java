package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

	static final BinaryMatrix readFileAndReturnBinaryMatrix(String filePath) {
		return convertFileInputToBinaryMatrix(filePath);
	}

	static final void printCodeForInitializingBinaryMatrix(String filePath) {
		BinaryMatrix binaryMatrixToInitialize = readFileAndReturnBinaryMatrix(filePath);
		int sizeOfMatrix = binaryMatrixToInitialize.getSizeOfMatrix();
		System.out.println("BinaryMatrix binaryMatrix = new BinaryMatrix("
				+ sizeOfMatrix + ");");

		for (int row = 0; row < binaryMatrixToInitialize.getSizeOfMatrix(); row++) {
			for (int column = 0; column < binaryMatrixToInitialize
					.getSizeOfMatrix(); column++)
			{
				if(binaryMatrixToInitialize.getValue(row, column))
				{
					System.out.println("binaryMatrix.setValue(" + row + ", " + column + ", true);");
				}
			}
		}
	}

	private static Scanner makeScannerToReadFromTempFile(String filePath) {
		Scanner scannerToReturn = null;
		try {
			scannerToReturn = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return scannerToReturn;
	}

	private static BinaryMatrix convertFileInputToBinaryMatrix(String filePath) {

		Scanner sizeDeterminer = makeScannerToReadFromTempFile(filePath);

		int sizeOfMatrix = 0;

		while (sizeDeterminer.hasNextLine()) {
			sizeOfMatrix++;
			sizeDeterminer.nextLine();
		}

		sizeDeterminer.close();

		Scanner file = makeScannerToReadFromTempFile(filePath);

		BinaryMatrix binaryMatrixToReturn = new BinaryMatrix(sizeOfMatrix);

		for (int row = 0; row < binaryMatrixToReturn.getSizeOfMatrix(); row++) {

			for (int column = 0; column < binaryMatrixToReturn
					.getSizeOfMatrix(); column++) {
				int digit = file.nextInt();

				if (digit == 1) {
					binaryMatrixToReturn.setValue(row, column, true);
				} else if (digit == 0) {
					binaryMatrixToReturn.setValue(row, column, false);
				} else {
					System.err
							.println("Invalid Input. Check temp.txt for proper formatting.");
					System.exit(0);
				}
			}
		}
		return binaryMatrixToReturn;
	}
}
