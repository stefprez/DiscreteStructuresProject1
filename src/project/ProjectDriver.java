package project;

public class ProjectDriver {

	public static void main(String[] args) {
		String file;
		BinaryMatrix binaryMatrix;
		for (int i = 1; i <= 6; i++)
		{
			file = "relation" + i + ".txt";
			binaryMatrix = FileReader.readFileAndReturnBinaryMatrix(file);
			binaryMatrix.printReport();
		}
	}

}
