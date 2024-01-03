package TestData;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

public class align_scorecard {
	public static String inputFilePath;
	public static String outputFilePath;

	public align_scorecard(String scratchcsvFile, String alignedcsvFile) {
		inputFilePath = scratchcsvFile;
		outputFilePath = alignedcsvFile;
	}

	static Map<String, Integer> csv1HeaderPosition;
	static Map<String, Integer> csv2HeaderPosition;

	public static void main(String[] args) throws CsvValidationException {
		// Provide the paths to your input and output files

		try {
			// Read the text file
			readCSVFile(inputFilePath, outputFilePath);

			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Map<String, String[]> readCSVFile(String filename1, String filename2)
			throws IOException, CsvValidationException {
		CSVWriter writer = new CSVWriter(new FileWriter(filename2));

		Map<String, String[]> data = new HashMap<>();
		List<String> list = null;
		CSVReader reader = new CSVReader(new FileReader(filename1));

		try {
			String[] header = reader.readNext();
			list = new ArrayList<String>(Arrays.asList(header));

			list.remove("SCORE_DATE");
			list.set(0, "unique_key");
			list.set(1, "Entity");
			writer.writeNext(list.toArray(new String[list.size()]));

			List<String> rowArray = null;
			String[] nextLine;
			rowArray = null;
			while ((nextLine = reader.readNext()) != null) {
				rowArray = new ArrayList<String>();
				for (int j = 1; j < list.size() + 1; j++) {

					rowArray.add(nextLine[j].toString());

				}

				writer.writeNext(rowArray.toArray(new String[rowArray.size()]));
			}

			writer.close();

		} catch (Exception e) {
         System.out.println(e);
		}
		System.out.println("Scorecard has been aligned successfully.");
		return data;
	}
}
