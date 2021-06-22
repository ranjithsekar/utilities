package jbr.utils.opencsv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVWriter;

public class CsvConverterOpenCsv {

  public static void main(String[] args) throws Exception {
    generateOnlyData();
  }

  public static void generateOnlyData() throws Exception {
    List<String[]> fruitDataStringArray = new ArrayList<>();
    getFruits().forEach(fruit ->
      {
        String[] data = { fruit.getName(), fruit.getColor(), fruit.getOrigin() };
        fruitDataStringArray.add(data);
      });

    try (CSVWriter writer = new CSVWriter(new FileWriter("fruit-data-only.csv"))) {
      writer.writeAll(fruitDataStringArray);
      System.out.println("Csv generated successfuly.");
    } catch (Exception e) {
      System.out.println("Csv generation failed: " + e.getMessage());
    }
  }

  public static List<Fruit> getFruits() throws IOException {
    return Arrays.asList(new Fruit("apple", "red", "america"), new Fruit("orange", "yellow", "india"));
  }
}
