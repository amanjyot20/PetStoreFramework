package api.utilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import com.google.common.collect.Table.Cell;

public class XLUtility {

    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    public XLUtility(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        int rowcount = sheet.getLastRowNum();
        workbook.close();
        fi.close();
        return rowcount;
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        int cellcount = row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellcount;
    }

    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);
        DataFormatter formatter= new DataFormatter();
        String data;
        try {
            data = cell.toString();
        } catch (Exception e) {
            data = "";
        }

        workbook.close();
        fi.close();
        return data;
    }

    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
        File xlfile = new File(path);
    	if (!xlfile.exists()) { // If file not exists then create new file
    	        workbook = new XSSFWorkbook();
    	        fo = new FileOutputStream(path);
    	        workbook.write(fo);
    	        //fo.close();
    	    }

    	    fi = new FileInputStream(path);
    	    workbook = new XSSFWorkbook(fi);

    	    // If sheet does not exist then create new Sheet
    	    if (workbook.getSheetIndex(sheetName) == -1)
    	        workbook.createSheet(sheetName);

    	    sheet = workbook.getSheet(sheetName);

    	    // If row does not exist then create new Row
    	    if (sheet.getRow(rownum) == null)
    	        sheet.createRow(rownum);

    	    row = sheet.getRow(rownum);
    	    cell = row.createCell(colnum);
    	    cell.setCellValue(data);

    	    fo = new FileOutputStream(path);
    	    workbook.write(fo);
    	    workbook.close();
    	    fi.close();
    	    fo.close();
    	}
    public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
         fi = new FileInputStream("your-file-path.xlsx"); // Path to your Excel file
         workbook = new XSSFWorkbook(fi);
         sheet = workbook.getSheet(sheetName);
         row = sheet.getRow(rownum);
         cell = row.getCell(colnum);

        // Create a new style for the cell
        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex()); // Set green color
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND); // Set solid fill pattern

        // Apply the style to the cell
       // ((org.apache.poi.ss.usermodel.Cell) cell).setCellStyle(style);

        // Write the changes to a new file or the same file
       // FileOutputStream fo = new FileOutputStream("your-file-path.xlsx");
       cell.setCellStyle(style);
        workbook.write(fo);

        // Close the file streams
        fi.close();
        fo.close();
        workbook.close();
    }
    public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.createCell(colnum);
      
        style = workbook.createCellStyle();
        
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        

  // Close the input stream before writing
        cell.setCellStyle(style);
      //  fo = new FileOutputStream(path);
        workbook.write(fo);

        workbook.close();
        fi.close(); 
        fo.close();
    }
    
}