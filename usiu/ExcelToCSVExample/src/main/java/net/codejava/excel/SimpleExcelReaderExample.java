package net.codejava.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

import com.sun.deploy.util.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static org.apache.poi.ss.usermodel.CellType.STRING;

/**
 * A dirty simple program that reads an Excel file.
 *
 * @author www.codejava.net
 */
public class SimpleExcelReaderExample {

    public static void main(String[] args) throws IOException {

        SimpleExcelReaderExample simpleExcelReaderExample = new SimpleExcelReaderExample();
        simpleExcelReaderExample.process();
        //simpleExcelReaderExample.sortList();

    }

    public void process() {

        String variety = null, varieties = null, commodity = null, unit = null, kg = null, nairobi = null, mombasa = null, kisumu = null, nakuru = null, eldoret = null,
                busia = null, kisii = null, loitktk = null, isiolo = null, kitale = null;

        String avg = "Average", max = "Max", min = "Min", code = "code", kgColHeader = "Kg";

        Double tempAmt = null;
        Double numericVal = null;
        int minColumn = 0;
        int startColumn = 0;
        int startRow = 9;
        int endRow = 51;
        int columnIndex = 0;
        int kgColumnIndex = 0;

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

        Map<Integer, Map> metricListMap = new HashMap<>();
        int avgColumnIndex = 0;
        int minColumnIndex = 0;
        int maxColumnIndex = 0;

        // metrics computation
        double sumAmount = 0.00, avgAmount = 0.00;
        Integer avgCount = 0;

        double maxVal = 0.00;
        double minVal = 0.00;
        //List<String> minMaxValList = null;
        //List<String> sortedList = null;


        String excelFilePath = "Copy-of-22-10-2018.xls";//"9-3-2018.xls";

        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

            //Workbook workbook = new XSSFWorkbook(inputStream); // for .xlsx files
            Workbook workbook = new HSSFWorkbook(inputStream);   // for .xls files

            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = firstSheet.iterator();

            // set variables
            while (iterator.hasNext()) {
                Row nextRow = iterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                List<String> minMaxValList = new ArrayList<>();
                List<String> sortedList = null;
                Map<String, Double> cumulativeMap = new HashMap<>();

                sumAmount = 0;
                avgCount = 0;
                avgAmount = 0.00;

                int rowNum = nextRow.getRowNum();
                //System.out.print("Row No: " + rowNum + " - ");
                if ((rowNum < startRow) || (rowNum > endRow)) {
                    continue;
                }

                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();

                    columnIndex = cell.getColumnIndex();
                    //System.out.print("Index No: " + columnIndex + " - ");

                    if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                        String colVal = cell.getStringCellValue().trim().replaceAll(",", "");

                        if (rowNum < startRow) {
                            continue;
                        }
                        //else if ((rowNum == 9) && (colVal.equalsIgnoreCase(avg))) {
                        else if (rowNum == startRow) {

                            if (colVal.equalsIgnoreCase(avg)) {
                                minColumn = columnIndex;
                                //System.out.print(">>>>>> Min Column: " + minColumn + " :::  ");
                                avgColumnIndex = columnIndex;
                            } else if (colVal.equalsIgnoreCase(max)) {
                                maxColumnIndex = columnIndex;
                            } else if (colVal.equalsIgnoreCase(min)) {
                                minColumnIndex = columnIndex;
                            } else if (colVal.equalsIgnoreCase(code)) {
                                startColumn = columnIndex;
                            } else if (colVal.equalsIgnoreCase(kgColHeader)) {
                                kgColumnIndex = columnIndex;
                            }
                            else {
                                continue;
                            }

                        } else {
                            continue;
                        }
                    }




                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

                        double colVal = cell.getNumericCellValue();

                        //System.out.print(">>>>>> RowNum: " + rowNum + " :>>>>>> columnIndex.......: " + columnIndex + " = " + colVal + " ::: \n");

                        if ((columnIndex > startColumn) && (columnIndex < avgColumnIndex)) {

                            //minMaxValList = new ArrayList<>();

                            //System.out.print(">>>>>> columnIndex.......: " + columnIndex + " = " + colVal + " ::: \n");

                            System.out.print(">>>>>> RowNum: " + rowNum + " :  columnIndex.......: " + columnIndex + " : colVal: " + colVal + " ::: \n");
                            System.out.print(">>>>>> sumAmount: " + sumAmount + " :  avgCount.......: " + avgCount + " ::: \n");
                            System.out.print(">>>>>>>>>>>>>>>>>>>>>>> avgColumnIndex: " + avgColumnIndex + " ::: \n\n");

                            // average amount calculation
                            sumAmount += colVal;
                            avgCount++; // = avgCount + 1;

                            // minimum and maximum value computation
                            minMaxValList.add(String.valueOf(colVal).trim());
                            if (columnIndex == (avgColumnIndex - 1)) {

                                //cumulativeMap = new HashMap<>();

                                if (minMaxValList.isEmpty()) {
                                    continue;
                                } else {
                                    avgAmount = Math.round(sumAmount / avgCount);
                                    System.out.print(">>>>>> RowNum: " + rowNum + ">>>>>> columnIndex.......: " + columnIndex + " :: average " + avgAmount + " :: sumAmount " + sumAmount + " :: avgCount " + avgCount + "\n\n");
                                    cumulativeMap.put("AVG", avgAmount);

                                    // sort the list and extract the minimum and maximum values
                                    sortedList = minMaxValList.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
                                    maxVal = Double.valueOf(sortedList.get(sortedList.size() - 1));
                                    minVal = Double.valueOf(sortedList.get(0));
                                    //System.out.println("\n minVal = " + minVal + " :: maxVal = " + maxVal);

                                    cumulativeMap.put("MAX", maxVal);
                                    cumulativeMap.put("MIN", minVal);

                                    metricListMap.put(rowNum, cumulativeMap);
                                }

                            }

                        }

                    }

                    //System.out.print("CELL_TYPE_ALL >>>>>> RowNum: " + rowNum + " :>>>>>> minMaxValList 222222 .......: " + minMaxValList.toString() + " ::: \n");

                    if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {

                        //System.out.print("CELL_TYPE_BLANK >>>>>> RowNum: " + rowNum + " :>>>>>> minMaxValList 1111111 .......: " + minMaxValList.toString() + " ::: \n");

                        if ((columnIndex > startColumn) && (columnIndex < avgColumnIndex)) {

//
//                            //System.out.print(">>>>>> columnIndex.......: " + columnIndex + " = " + colVal + " ::: \n");
//
//                            System.out.print("CELL_TYPE_BLANK >>>>>> RowNum: " + rowNum + " :  columnIndex.......: " + columnIndex + " ::: \n");
//                            System.out.print("CELL_TYPE_BLANK >>>>>> sumAmount: " + sumAmount + " :  avgCount.......: " + avgCount + " ::: \n");
//                            System.out.print("CELL_TYPE_BLANK >>>>>>>>>>>>>>>>>>>>>>> avgColumnIndex: " + avgColumnIndex + " ::: \n\n");

                            // minimum and maximum value computation
                            if (columnIndex == (avgColumnIndex - 1)) {

                                //cumulativeMap = new HashMap<>();
                                avgAmount = Math.round(sumAmount / avgCount);
                                System.out.print("CELL_TYPE_BLANK >>>>>>  RowNum: " + rowNum + ">>>>>> columnIndex.......: " + columnIndex + " :: average " + avgAmount + " :: sumAmount " + sumAmount + " :: avgCount " + avgCount + "\n\n");
                                cumulativeMap.put("AVG", avgAmount);

                                System.out.print("CELL_TYPE_BLANK >>>>>> RowNum: " + rowNum + " :>>>>>> metricListMap size.......: " + metricListMap.size() + " -- " + convertWithStream(metricListMap) + " ::: \n");
                                System.out.print("CELL_TYPE_BLANK >>>>>> RowNum: " + rowNum + " :>>>>>> minMaxValList.......: " + minMaxValList.toString() + " ::: \n");

                                if (minMaxValList.isEmpty()) {
                                    continue;
                                } else {

                                    // sort the list and extract the minimum and maximum values
                                    sortedList = minMaxValList.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
                                    maxVal = Double.valueOf(sortedList.get(sortedList.size() - 1));
                                    minVal = Double.valueOf(sortedList.get(0));
                                    //System.out.println("\n minVal = " + minVal + " :: maxVal = " + maxVal);

                                    cumulativeMap.put("MAX", maxVal);
                                    cumulativeMap.put("MIN", minVal);

                                    metricListMap.put(rowNum, cumulativeMap);

                                }

                            }

                        }

                    }

                }
                //System.out.print(">>>>>> columnIndex.......: avgColumnIndex " + avgColumnIndex + " :: maxColumnIndex " + maxColumnIndex + " :; minColumnIndex " + minColumnIndex + " ::: \n\n ");
                //System.out.print(">>>>>> Metrics .......: " + convertWithStream(metricListMap) + " ::: \n\n ");

            }

            System.out.print(" >>>>>> Start Column.......: " + startColumn + " >>>>>> Min Column.......: " + minColumn + " ::: \n ");
            System.out.print(">>>>>> Metrics .......: " + convertWithStream(metricListMap) + " ::: \n\n ");

            Iterator<Row> iterator2 = firstSheet.iterator();

            List<Object> rowList = null;

            while (iterator2.hasNext()) {
                Row nextRow2 = iterator2.next();
                Iterator<Cell> cellIterator2 = nextRow2.cellIterator();

                rowList = new ArrayList<Object>();
                // List<Object> rowList = new ArrayList<Object>();

                int rowNum = nextRow2.getRowNum();
                //System.out.print("Row No: " + rowNum + " - ");
                if ((rowNum < startRow) || (rowNum > endRow)) {
                    continue;
                }
                System.out.print("Row No: " + rowNum + " - ");

                while (cellIterator2.hasNext()) {

                    Cell cell = cellIterator2.next();

                    columnIndex = cell.getColumnIndex();
                    //System.out.print("Index No: " + columnIndex + " - ");

                    switch (cell.getCellTypeEnum()) {

                        case STRING:

                            String colVal = cell.getStringCellValue().trim().replaceAll(",", "");
                            String colZeroVal = "";

                            if (columnIndex >= minColumn) {
                                continue;
                            } else {
                                if ((columnIndex == 0)) {
                                    // if ((columnIndex == 0) && (varietyList.contains(colVal))) {
                                    // remove column 0
                                    continue;
                                } else {

                                    if ((columnIndex == 1) && (rowNum == startRow)) {
                                        cropsList.add("VARIETIES");
                                        colZeroVal = "VARIETIES";
                                        rowList.add(colZeroVal.trim());
                                    } else if (columnIndex == 1) {

                                        if (cerealsList.contains(colVal)) {
                                            //colVal = "CEREAL" + ", " + colVal;
                                            colZeroVal = "CEREAL";
                                        } else if (legumesList.contains(colVal)) {
                                            //colVal = "LEGUMES" + ", " + colVal;
                                            colZeroVal = "LEGUMES";
                                        } else if (rootsTubersList.contains(colVal)) {
                                            //colVal = "ROOTS & TUBERS" + ", " + colVal;
                                            colZeroVal = "ROOTS & TUBERS";
                                        } else if (horticultureList.contains(colVal)) {
                                            //colVal = "HORTICULTURE" + ", " + colVal;
                                            colZeroVal = "HORTICULTURE";
                                        } else {
                                            //colVal = "OTHERS" + ", " + colVal;
                                            colZeroVal = "OTHERS";
                                        }

                                        rowList.add(colZeroVal.trim());

                                    }
                                }
                                //System.out.print(String.join(",", cropsList));
                                // rowList.add(colZeroVal.trim());
                                rowList.add(colVal.trim());
                            }
                            break;
                        case BOOLEAN:
                            if (columnIndex >= minColumn) {
                                continue;
                            } else {
                                //System.out.print(cell.getBooleanCellValue());
                                rowList.add(cell.getBooleanCellValue());
                            }
                            break;
                        case NUMERIC:
                            if (columnIndex >= minColumn) {
                                continue;
                            } else {
                                numericVal = cell.getNumericCellValue();
                                tempAmt = numericVal;
                                //System.out.print(numericVal);
                                rowList.add(numericVal);
                            }
                            break;
                        case FORMULA:
                            if (columnIndex >= minColumn) {
                                continue;
                            } else {
                                //System.out.print("$$$$" + cell.getCellFormula());
                                rowList.add(cell.getCellFormula());
                            }
                            break;
                        case ERROR:
                            if (columnIndex >= minColumn) {
                                continue;
                            } else {
                                System.out.print("%%%%" + cell.getErrorCellValue());
                            }
                            break;
                        case BLANK:
                            if (columnIndex >= minColumn) {
                                continue;
                            } else {
                                if (columnIndex > startColumn) {
                                    //System.out.print(" -- BLANK -- " + tempAmt);
                                    //rowList.add(tempAmt);
                                    rowList.add(metricListMap.get(rowNum).get("AVG"));

                                    //System.out.println("Val: AVG " + metricListMap.get(rowNum).get("AVG"));
                                    //System.out.println("Val: AVG " + metricListMap.get(rowNum).get("AVG"));
                                }

                                // Add the Tray word value on the Eggs Tray row
                                if((rowNum == endRow) && (columnIndex == kgColumnIndex)){
                                    rowList.add("Tray");
                                }

                            }
                            break;
                    }
                    //System.out.print(",");

                }

                // Append the Average, Max, Min to the end of row 9
                if (rowNum == startRow) {
                    rowList.add("Average");
                    rowList.add("Max");
                    rowList.add("Min");
                }

                // Append the Average, Max, Min values to the other rows
                if (metricListMap.containsKey(rowNum)) {
                    //System.out.println("Val: AVG " + metricListMap.get(rowNum).get("AVG") + "Val: MIN " + metricListMap.get(rowNum).get("MIN") + "Val: MAX " + metricListMap.get(rowNum).get("MAX"));
                    rowList.add(metricListMap.get(rowNum).get("AVG"));
                    rowList.add(metricListMap.get(rowNum).get("MAX"));
                    rowList.add(metricListMap.get(rowNum).get("MIN"));
                }

                System.out.print(String.join(",", String.valueOf(rowList))); //.replaceAll("\\[","").replaceAll("]","")));
                System.out.println();
            }

            workbook.close();
            inputStream.close();

//        File csvOutputFile = new File("20180327.csv");
//            PrintWriter pw = new PrintWriter(csvOutputFile);
//            rowlist.stream()
//            .map(this::convertToCSV)
//            .forEach(pw.println);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String convertWithStream(Map<Integer, ?> map) {
        String mapAsString = map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
        return mapAsString;
    }

    public void sortList() {

        List<String> slist = Arrays.asList("2800.0", "2600.0", "3600.0", "2500.0", "2400.0", "3150.0", "2600.0", "2000.0", "3600.0", "2300.0");
        List<String> sortedList = slist.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        sortedList.forEach(System.out::println);

        System.out.println("\n first = " + sortedList.get(0) + " :: last = " + sortedList.get(sortedList.size() - 1));

        System.out.println("--- \n");

        List<String> sortedList2 = slist.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        sortedList2.forEach(System.out::println);

        System.out.println("\n first = " + sortedList2.get(0) + " :: last = " + sortedList2.get(sortedList.size() - 1));

    }

}