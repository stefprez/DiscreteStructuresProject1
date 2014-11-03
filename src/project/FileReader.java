package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

	public Scanner openFileWithPrompt() {

		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("temp.txt"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return scanner;
	}

	public BinaryMatrix convertFileInputToBinaryMatrix(Scanner file) {

		int sizeOfMatrix = file.nextLine().length();
		file.close();

		try {
			file = new Scanner(new File("temp.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String line;
		BinaryMatrix binaryMatrixToReturn = new BinaryMatrix(sizeOfMatrix);

		for (int row = 0; row < binaryMatrixToReturn.getSizeOfMatrix(); row++) {
			line = file.nextLine();

			if (line.length() != sizeOfMatrix) {
				System.err
						.println("Invalid Input for convertFileInputToBinaryMatrix;");
				System.exit(0);
			}

			for (int column = 0; column < binaryMatrixToReturn
					.getSizeOfMatrix(); column++) {
				int digit = Integer
						.parseInt(line.substring(column, column + 1));

				if (digit == 0) {
					binaryMatrixToReturn.setValue(row, column, false);
				} else {
					binaryMatrixToReturn.setValue(row, column, true);
				}
			}
		}
		return binaryMatrixToReturn;
	}
}
