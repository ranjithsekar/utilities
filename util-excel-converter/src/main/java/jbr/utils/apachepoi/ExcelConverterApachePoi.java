package jbr.utils.apachepoi;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelConverterApachePoi {

  public static void main(String[] args) {
    // Blank workbook
    XSSFWorkbook workbook = new XSSFWorkbook();

    // Create a blank sheet
    XSSFSheet sheet = workbook.createSheet("Data");
    createHeaderRow(workbook, sheet);
    int rowCount = 0;

    for (Animal Animal : animalData()) {
      Row row = sheet.createRow(++rowCount);
      writeData(workbook, Animal, row);
    }

    try {
      // Write the workbook in file system
      FileOutputStream out = new FileOutputStream(new File("animals.xlsx"));
      workbook.write(out);
      out.close();
      workbook.close();
      System.out.println("Excel file generated successfully.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void writeData(XSSFWorkbook wb, Animal animal, Row row) {
    XSSFRichTextString rts = new XSSFRichTextString();
    XSSFFont fontBold = wb.createFont();
    fontBold.setBold(true);

    Cell cell = row.createCell(0);
    cell.setCellValue(animal.getName());

    cell = row.createCell(1);
    CellStyle cs = wb.createCellStyle();
    cs.setWrapText(true);
    cell.setCellStyle(cs);
    rts.append("Type Data:\n", fontBold);
    rts.append("Place: ");
    cell.setCellValue(rts + animal.getType());
  }

  public static String formatDate(Date date, String format) {
    if (date == null) {
      return null;
    }
    DateFormat df = new SimpleDateFormat(format);
    return df.format(date);
  }

  private static void createHeaderRow(XSSFWorkbook wb, Sheet sheet) {
    XSSFRichTextString rts = new XSSFRichTextString();
    XSSFFont fontBold = wb.createFont();
    fontBold.setBold(true);

    Row row = sheet.createRow(0);
    Cell cellTitle = row.createCell(0);
    rts.append("Name", fontBold);
    cellTitle.setCellValue(rts);

    rts = new XSSFRichTextString();
    Cell cellAuthor = row.createCell(1);
    rts.append("Color", fontBold);
    cellAuthor.setCellValue(rts);
  }

  private static List<Animal> animalData() {
    return new ArrayList<>(Arrays.asList(new Animal("Lion", "Wild Animal"), new Animal("Tiger", "Wild Animal"),
        new Animal("Dog", "Domestic Animal")));
  }
}
