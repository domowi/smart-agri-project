package net.codejava.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * A dirty simple program that reads an Excel file.
 *
 * @author www.codejava.net
 */
public class SimpleExcelReaderExample_Working1 {

    public static void main(String[] args) throws IOException {

        String variety = null, varieties = null, commodity = null, unit = null, kg = null, nairobi = null, mombasa = null, kisumu = null, nakuru = null, eldoret = null,
                busia = null, kisii = null, loitktk = null, isiolo = null, kitale = null;

        String avg = "Average", max = "Max", min = "Min";

        Double tempAmt = null;
        Double numericVal = null;
        int minColumn = 0;
        int columnIndex = 0;

        Map<String, List> map = new HashMap<String, List>();
        List<String> cropsList = new ArrayList<String>();

        List<String> cerealsList = Arrays.asList("Dry Maize", "Green Maize", "Finger Millet", "Sorghum", "Wheat");
        //map.put("CEREAL", cerealsList);

        List<String> legumesList = Arrays.asList("Beans Canadian", "Beans Rosecoco", "Beans Mwitemania", "Mwezi Moja", "Dolichos (Njahi)", "Green Gram", "Cowpeas", "Fresh Peas", "Groundnuts");
        List<String> rootsTubersList = Arrays.asList("Red Irish Potatoes", "White Irish Potatoes", "Cassava Fresh", "Sweet Potatoes");
        List<String> horticultureList = Arrays.asList("Cabbages", "Cooking Bananas", "Ripe Bananas", "Carrots", "Tomatoes", "Onions Dry", "Spring Onions", "Chillies", "Cucumber", "Capsicums", "Brinjals",
                "Cauliflower", "Lettuce", "Passion Fruits", "Oranges", "Lemons", "Mangoes Local", "Mangoes Ngowe", "Limes", "Pineapples", "Pawpaw", "Avocado", "Kales");
        List<String> varietyList = Arrays.asList("CEREAL", "LEGUMES", "ROOTS & TUBERS", "HORTICULTURE", "OTHERS");


//        List<String> legumesList = Arrays.asList("Beans Canadian","","","","","","","","","");
//        map.put("LEGUMES", legumesList);
//
//        List<String> rootsTubersList = Arrays.asList("","","","","");
//        map.put("ROOTS & TUBERS", rootsTubersList);
//
//        List<String> horticultureList = Arrays.asList("","","","","","","","","","","","","","","","","","","","");
//        map.put("HORTICULTURE", horticultureList);
//
//        List<String> othersList = Arrays.asList("","","","","");
//        map.put("OTHERS", cerealsList);

        String excelFilePath = "27-3-2018.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();

        // set variables
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            int rowNum = nextRow.getRowNum();
            //System.out.print("Row No: " + rowNum + " - ");
            if ((rowNum < 9) || (rowNum > 51)) {
                continue;
            }

            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next();

                columnIndex = cell.getColumnIndex();
                //System.out.print("Index No: " + columnIndex + " - ");

                if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    String colVal = cell.getStringCellValue().trim().replaceAll(",", "");

                    if ((rowNum == 9) && (colVal.equalsIgnoreCase(avg))) {
                        minColumn = columnIndex;
                        //System.out.print(">>>>>> Min Column: " + minColumn + " :::  ");
                    }
                }
            }

        }

        System.out.print(">>>>>> Min Column.......: " + minColumn + " ::: \n\n ");

        Iterator<Row> iterator2 = firstSheet.iterator();

        while (iterator2.hasNext()) {
            Row nextRow2 = iterator2.next();
            Iterator<Cell> cellIterator2 = nextRow2.cellIterator();

            List<Object> rowList = new ArrayList<Object>();

            int rowNum = nextRow2.getRowNum();
            //System.out.print("Row No: " + rowNum + " - ");
            if ((rowNum < 9) || (rowNum > 51)) {
                continue;
            }
            System.out.print("Row No: " + rowNum + " - ");

            while (cellIterator2.hasNext()) {

                Cell cell = cellIterator2.next();

                columnIndex = cell.getColumnIndex();
                //System.out.print("Index No: " + columnIndex + " - ");

                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        String colVal = cell.getStringCellValue().trim().replaceAll(",", "");

                        if (columnIndex >= minColumn) {
                            continue;
                        } else {
                            if ((columnIndex == 0) && (varietyList.contains(colVal))) {
                                // remove column 0
                                continue;
                            } else {

                                if ((columnIndex == 1) && (rowNum == 9)) {
                                    cropsList.add("VARIETIES");
                                    //System.out.print("VARIETIES,");
                                } else if (columnIndex == 1) {
                                    if (cerealsList.contains(colVal)) {
                                        //System.out.print("CEREAL,");
                                        colVal = "CEREAL" + ", " + colVal;
                                    } else if (legumesList.contains(colVal)) {
                                        //System.out.print("LEGUMES,");
                                        colVal = "LEGUMES" + ", " + colVal;
                                        colVal = colVal.replaceAll(", LEGUMES", "LEGUMES");
                                    } else if (rootsTubersList.contains(colVal)) {
                                        //System.out.print("ROOTS & TUBERS,");
                                        colVal = "ROOTS & TUBERS" + ", " + colVal;
                                    } else if (horticultureList.contains(colVal)) {
                                        //System.out.print("HORTICULTURE,");
                                        colVal = "HORTICULTURE" + ", " + colVal;
                                    } else {
                                        //System.out.print("OTHERS,");
                                        colVal = "OTHERS" + ", " + colVal;
                                    }
                                }
                            }
                            //System.out.print(colVal);
                            //System.out.print(String.join(",", cropsList));
                            rowList.add(colVal.trim());
                        }
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        if (columnIndex >= minColumn) {
                            continue;
                        } else {
                            //System.out.print(cell.getBooleanCellValue());
                            rowList.add(cell.getBooleanCellValue());
                        }
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        if (columnIndex >= minColumn) {
                            continue;
                        } else {
                            numericVal = cell.getNumericCellValue();
                            tempAmt = numericVal;
                            //System.out.print(numericVal);
                            rowList.add(numericVal);
                        }
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        if (columnIndex >= minColumn) {
                            continue;
                        } else {
                            //System.out.print("$$$$" + cell.getCellFormula());
                            rowList.add(cell.getCellFormula());
                        }
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        if (columnIndex >= minColumn) {
                            continue;
                        } else {
                            System.out.print("%%%%" + cell.getErrorCellValue());
                        }
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        if (columnIndex >= minColumn) {
                            continue;
                        } else {
                            if (columnIndex > 3) {
                                //System.out.print(" -- BLANK -- " + tempAmt);
                                rowList.add(tempAmt);
                            }
                        }
                        break;
                }
                //System.out.print(",");

            }
            System.out.print(String.join(",", String.valueOf(rowList))); //.replaceAll("\\[","").replaceAll("]","")));
            System.out.println();
        }

        workbook.close();
        inputStream.close();
    }

}