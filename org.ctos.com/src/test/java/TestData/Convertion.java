package TestData;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVWriter;

public class Convertion {
	public static String inputFilePath;
	public static String outputFilePath;

	Convertion(String textFile, String csvFile) throws IOException {
		inputFilePath = textFile;
		outputFilePath = csvFile;

	}

	public static void main(String[] args) {
		// Provide the paths to your input and output files

		try {
			// Read the text file
			convertTextToCSV(inputFilePath, outputFilePath);

			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void convertTextToCSV(String inputFilePath, String outputFilePath) throws IOException {
		List<String> headerList = new ArrayList<String>();

		CSVWriter writer = new CSVWriter(new FileWriter(outputFilePath));
		try {

			// Read each line from the input text file
			List<String> headerList1 = new ArrayList<String>();
			Map<String, String> keyValueMap1 = new LinkedHashMap<>();
			List<String> lines = Files.readAllLines(Paths.get(inputFilePath));
			String[] s = lines.get(0).split(",");
			//System.out.println(s);
			for (int i = 0; i < s.length; i++) {
				String[] column = s[i].split(":");
				String value = column[0].toString();
				value = value.replace("'", "");
				value = value.replace("{", "");
				value = value.replace("}", "");

				headerList1.add(value);

				// keyValueMap1.put(column[0], column[1].toString());

			}
			
			String lastElement = headerList1.remove(headerList1.size() - 1);
			headerList1.add(0, lastElement);
			headerList1.add(1, "Entity");
			headerList1.remove(" NO_OF_AF_ratio");
			//System.out.println(headerList1);
			String[] arr = new String[headerList1.size()];

			writer.writeNext(headerList1.toArray(new String[headerList1.size() - 1]));

			for (int i = 0; i < lines.size(); i++) {
				List<String> headers = new ArrayList<String>();
				String[] s1 = lines.get(i).split(",");

				for (int j = 0; j < s.length; j++) {
					Map<String, String> keyValueMap2 = new LinkedHashMap<>();
					String[] column = s1[j].split(":");
					keyValueMap2.put(column[0].trim(), column[1].trim());
					// System.out.println(keyValueMap2.containsKey("'NO_OF_AF_ratio'"));
					if (!keyValueMap2.containsKey("'NO_OF_AF_ratio'")) {
						if (keyValueMap2.containsValue("None")) {
							String value = column[1].toString().replace("None", " ");
							headers.add(value);
						} else {
							String value = column[1].toString();
							value = value.replace("'", "");
							value = value.replace("{", "");
							value = value.replace("}", "");
							headers.add(value);
						}
					} else {
						continue;
					}

				}

				String lastElement1 = headers.remove(headers.size() - 1);
				String[] split = lastElement1.split("-");
				String unique_key = split[0].replace("'", "");
				unique_key = split[0].replace("{", "");
				String Entity = split[1].replace("'", "");
				Entity = split[1].replace("}", "");
				headers.add(0, unique_key);
				headers.add(1, Entity);

				writer.writeNext(headers.toArray(new String[headers.size() - 1]));
			}

			writer.close();
			System.out.println("Conversion completed successfully.");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
