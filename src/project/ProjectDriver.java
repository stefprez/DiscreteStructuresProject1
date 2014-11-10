/**
 * Stefano Prezioso
 * COSC 314 Project 1
 */
package project;

public class ProjectDriver {

	public static void main(String[] args) {
		String fileName;
		BinaryMatrix binaryMatrix;
		
		for (int i = 1; i <= 6; i++)
		{
			fileName = "relation" + i + ".txt";
			binaryMatrix = BinaryMatrixFileReader.readAndConvertFileInputToBinaryMatrix(fileName);
			binaryMatrix.printReport();
		}
	}

}
