/**
 * Stefano Prezioso
 * COSC 314 Project 1
 */
package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BinaryMatrixFileReader {

	/**
	 * This is used for assisting with writing unit tests. It prints out the
	 * Java code necessary to initialize a BinaryMatrix that was read from a file.
	 * After placing the code into the unit test, I could delete the temporary file
	 * used to import the matrix.
	 * @param filePath File path of the file to read in.
	 */
	public static final void printCodeForInitializingBinaryMatrix(String filePath) {
		BinaryMatrix binaryMatrixToInitialize = readAndConvertFileInputToBinaryMatrix(filePath);
		int sizeOfMatrix = binaryMatrixToInitialize.getSizeOfMatrix();
		
		//Print out the code to instantiate the new empty BinaryMatrix
		System.out.println("BinaryMatrix binaryMatrix = new BinaryMatrix("
				+ sizeOfMatrix + ");");

		//Iterate through the file to find which positions are true
		for (int row = 0; row < binaryMatrixToInitialize.getSizeOfMatrix(); row++) {
			for (int column = 0; column < binaryMatrixToInitialize
					.getSizeOfMatrix(); column++)
			{
				if(binaryMatrixToInitialize.getValue(row, column))
				{
					//Print the code to set the appropriate values in the matrix to true
					System.out.println("binaryMatrix.setValue(" + row + ", " + column + ", true);");
				}
			}
		}
	}

	public static BinaryMatrix readAndConvertFileInputToBinaryMatrix(String filePath) {
		Scanner sizeDeterminer = makeScannerToReadFromTempFile(filePath);
		int sizeOfMatrix = 0;

		while (sizeDeterminer.hasNextLine()) {
			sizeOfMatrix++;
			sizeDeterminer.nextLine();
		}
		sizeDeterminer.close();

		Scanner file = makeScannerToReadFromTempFile(filePath);
		BinaryMatrix binaryMatrixToReturn = new BinaryMatrix(sizeOfMatrix);

		//Iterate through the binary matrix
		for (int row = 0; row < binaryMatrixToReturn.getSizeOfMatrix(); row++) {
			for (int column = 0; column < binaryMatrixToReturn
					.getSizeOfMatrix(); column++) {
				
				int digit = file.nextInt();

				if (digit == 1) 
				{
					binaryMatrixToReturn.setValue(row, column, true);
				} 
				else if (digit == 0) 
				{
					binaryMatrixToReturn.setValue(row, column, false);
				} 
				else 
				{
					System.err.println("Invalid Input. Check input file for proper formatting.");
					System.exit(0);
				}
			}
		}
		
		return binaryMatrixToReturn;
	}
	
	private static Scanner makeScannerToReadFromTempFile(String filePath) {
		Scanner scannerToReturn = null;
		
		try 
		{
			scannerToReturn = new Scanner(new File(filePath));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		return scannerToReturn;
	}
}
