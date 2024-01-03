package TestData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class RowToColumnConverter {
    public static void main(String[] args) {
        // Provide the paths to your input and output files
        String inputFilePath = "C:\\Users\\Sheikumarali\\Downloads\\mytestrun_16112023.txt";
        String outputFilePath = "C:\\\\Users\\\\Sheikumarali\\\\Downloads\\\\convert_16112023.csv";

        try {
            // Read the text file and write to CSV
            convertRowToColumn(inputFilePath, outputFilePath);
            
            System.out.println("Conversion completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void convertRowToColumn(String inputFilePath, String outputFilePath) throws IOException {
        Map<String, String> keyValueMap = new LinkedHashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split each line into key-value pairs using colons
                String[] pairs = line.split(":");
                for (String pair : pairs) {
                    // Split each pair into key and value
                    String[] keyValue = pair.trim().split("\\s*,\\s*", 2);
                    if (keyValue.length == 2) {
                        // Put key-value pairs into the map
                        keyValueMap.put(keyValue[0], keyValue[1]);
                    }
                }
            }
        }

        // Write key-value pairs to the CSV file
        try (FileWriter writer = new FileWriter(outputFilePath)) {
            for (Map.Entry<String, String> entry : keyValueMap.entrySet()) {
                writer.append(entry.getKey());
                writer.append(",");
                writer.append(entry.getValue());
                writer.append("\n");
            }
        }
        
    }
}
