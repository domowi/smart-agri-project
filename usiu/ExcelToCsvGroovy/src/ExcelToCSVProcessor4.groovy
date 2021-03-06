//println "Hello Groovy"


import com.thoughtworks.xstream.core.util.CustomObjectInputStream
import org.apache.commons.io.IOUtils
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.CellType

import java.nio.charset.*
import java.text.SimpleDateFormat

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory

import java.util.stream.Collectors

def flowFile = new File("C:\\Users\\odhiambod\\SmartAgri\\ExcelToCsvGroovy\\27-3-2018.xls")
if(!flowFile)
    return

println "The file ${flowFile.absolutePath} has ${flowFile.length()} bytes"

//// fetch all the files in the directory recursively
//new File("C:\\Users\\odhiambod\\SmartAgri\\ExcelToCsvGroovy\\").eachFileRecurse() {
//    file -> println file.getAbsolutePath()
//}

def date = new Date()

//readData()
//readDataFlowFile()

processCsv()

def processCsv(){

    String variety = null, varieties = null, commodity = null, unit = null, kg = null, nairobi = null, mombasa = null, kisumu = null, nakuru = null, eldoret = null,
           busia = null, kisii = null, loitktk = null, isiolo = null, kitale = null

    String avg = "Average", max = "Max", min = "Min", code = "code"

    double tempAmt = 0
    double numericVal = 0
    int minColumn = 0;
    int startColumn = 0;
    int startRow = 9;
    int endRow = 51;
    int columnIndex = 0;

    Map<String, List> map = new HashMap<String, List>()
    List<String> cropsList = new ArrayList<String>()

    List<String> cerealsList = ["Dry Maize", "Green Maize", "Finger Millet", "Sorghum", "Wheat"]

    List<String> legumesList = ["Beans Canadian", "Beans Rosecoco", "Beans Mwitemania", "Mwezi Moja", "Dolichos (Njahi)", "Green Gram", "Cowpeas", "Fresh Peas", "Groundnuts"]
    List<String> rootsTubersList = ["Red Irish Potatoes", "White Irish Potatoes", "Cassava Fresh", "Sweet Potatoes"]
    List<String> horticultureList = ["Cabbages", "Cooking Bananas", "Ripe Bananas", "Carrots", "Tomatoes", "Onions Dry", "Spring Onions", "Chillies", "Cucumber", "Capsicums", "Brinjals",
            "Cauliflower", "Lettuce", "Passion Fruits", "Oranges", "Lemons", "Mangoes Local", "Mangoes Ngowe", "Limes", "Pineapples", "Pawpaw", "Avocado", "Kales"]
    List<String> varietyList = ["CEREAL", "LEGUMES", "ROOTS & TUBERS", "HORTICULTURE", "OTHERS"]
    //List<String> varietyList = Arrays.asList("CEREAL", "LEGUMES", "ROOTS & TUBERS", "HORTICULTURE", "OTHERS")

    //println "List ${varietyList}"

    Map<Integer, Map> metricListMap = new HashMap<>()
    int avgColumnIndex = 0
    int minColumnIndex = 0
    int maxColumnIndex = 0

    // metrics computation
    int avgMath = 0
    double sumAmount = 0.00, avgAmount = 0.00
    Integer avgCount = 0

    double maxVal = 0.00
    double minVal = 0.00
    List<String> minMaxValList = new ArrayList<>()
    List<String> sortedList = null

    def path = "C:\\Users\\odhiambod\\SmartAgri\\ExcelToCsvGroovy\\27-3-2018.xls"
    InputStream inputStream = new FileInputStream(path)

    def path2 = "C:\\Users\\odhiambod\\SmartAgri\\ExcelToCsvGroovy\\27-3-2018_3.csv"
    OutputStream outputStream = new FileOutputStream(path2)

    // processing time, inserted as first column
    def date = new Date()
    def tstamp = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS").format(date)

    //Workbook workbook = new XSSFWorkbook(inputStream) // for .xlsx files
    Workbook workbook = new HSSFWorkbook(inputStream)   // for .xls files

    Sheet firstSheet = workbook.getSheetAt(0)
    Iterator<Row> iterator = firstSheet.iterator()


    // set variables
    while (iterator.hasNext()) {
        Row nextRow = iterator.next()
        Iterator<Cell> cellIterator = nextRow.cellIterator()

        int rowNum = nextRow.getRowNum()
        //println("Row No: " + rowNum + " - ")
        if ((rowNum < 9) || (rowNum > 51)) {
            continue
        }

        while (cellIterator.hasNext()) {

            Cell cell = cellIterator.next()

            columnIndex = cell.getColumnIndex()
            //println("Index No: " + columnIndex + " - ")

            if (cell.getCellType() == CellType.STRING) {
                String colVal = cell.getStringCellValue().trim().replaceAll(",", "")

                if (rowNum < 9) {
                    continue
                }
                //else if ((rowNum == 9) && (colVal.equalsIgnoreCase(avg))) {
                else if (rowNum == 9) {

                    if (colVal.equalsIgnoreCase(avg)) {
                        minColumn = columnIndex
                        //println(">>>>>> Min Column: " + minColumn + " :::  ")
                        avgColumnIndex = columnIndex
                    } else if (colVal.equalsIgnoreCase(max)) {
                        maxColumnIndex = columnIndex
                    } else if (colVal.equalsIgnoreCase(min)) {
                        minColumnIndex = columnIndex
                    } else {
                        continue
                    }

                } else {
                    continue
                }
            }


            if (cell.getCellType() == CellType.NUMERIC) {

                double colVal = cell.getNumericCellValue()

                //println(">>>>>> columnIndex.......: " + columnIndex + " = " + colVal + " ::: \n\n ")

                if ((columnIndex > 3) && (columnIndex < avgColumnIndex)) {

                    //println(">>>>>> columnIndex.......: " + columnIndex + " = " + colVal + " ::: \n")

                    // average amount calculation
                    sumAmount += colVal
                    avgCount++ 

                    // minimum and maximum value computation
                    minMaxValList.add(String.valueOf(colVal))
                    if (columnIndex == (avgColumnIndex - 1)) {

                        Map<String, Double> cumulativeMap = new HashMap<>()

                        avgAmount = Math.round(sumAmount / avgCount)
                        //println(">>>>>> columnIndex.......: average " + avgAmount + " :: sumAmount " + sumAmount + " :: avgCount " + avgCount + "\n\n")
                        cumulativeMap.put("AVG", avgAmount)

                        // sort the list and extract the minimum and maximum values
                        sortedList = minMaxValList.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList())
                        maxVal = Double.valueOf(sortedList.get(sortedList.size() - 1))
                        minVal = Double.valueOf(sortedList.get(0))
                        //printlnln("\n minVal = " + minVal + " :: maxVal = " + maxVal)

                        cumulativeMap.put("MAX", maxVal)
                        cumulativeMap.put("MIN", minVal)

                        metricListMap.put(rowNum, cumulativeMap)

                        // remove values for the next iteration
                        minMaxValList.clear()
                        sumAmount = 0
                        avgCount = 0
                        avgAmount = 0.00
                        maxVal = 0.00
                        minVal = 0.00

                    }

                }

            }

        }

    }

    println ">>>>>> Min Column.......: " + minColumn + " ::: \n"
    println(">>>>>> Metrics .......: " + convertWithStream(metricListMap) + " ::: \n")
    println ">>>>>> columnIndex.......: avgColumnIndex " + avgColumnIndex + " :: maxColumnIndex " + maxColumnIndex + " : minColumnIndex " + minColumnIndex + " ::: \n"

    Iterator<Row> iterator2 = firstSheet.iterator()
    int rowNum = 0

    List<Object> rowList = null
    def record = ''

    while (iterator2.hasNext()) {
        Row nextRow2 = iterator2.next()
        Iterator<Cell> cellIterator2 = nextRow2.cellIterator()

        rowList = new ArrayList<Object>()
        // List<Object> rowList = new ArrayList<Object>()

        rowNum = nextRow2.getRowNum()
        //println("Row No: " + rowNum + " - ")
        if ((rowNum < 9) || (rowNum > 51)) {
            continue
        }
        println("Row No: " + rowNum + " - ")

        while (cellIterator2.hasNext()) {

            Cell cell = cellIterator2.next()

            columnIndex = cell.getColumnIndex()
            //println("Index No: " + columnIndex + " - ")

            switch (cell.getCellType()) {

                case CellType.STRING:

                    String colVal = cell.getStringCellValue().trim().replaceAll(",", "")
                    String colZeroVal = ""

                    if (columnIndex >= minColumn) {
                        continue
                    } else {
                        if ((columnIndex == 0)) {
                            // remove column 0
                            continue
                        } else {

                            if ((columnIndex == 1) && (rowNum == 9)) {
                                cropsList.add("VARIETIES")
                                colZeroVal = "VARIETIES"
                                rowList.add(colZeroVal.trim())
                            } else if (columnIndex == 1) {

                                if (cerealsList.contains(colVal)) {
                                    //colVal = "CEREAL" + ", " + colVal
                                    colZeroVal = "CEREAL"
                                } else if (legumesList.contains(colVal)) {
                                    //colVal = "LEGUMES" + ", " + colVal
                                    colZeroVal = "LEGUMES"
                                } else if (rootsTubersList.contains(colVal)) {
                                    //colVal = "ROOTS & TUBERS" + ", " + colVal
                                    colZeroVal = "ROOTS & TUBERS"
                                } else if (horticultureList.contains(colVal)) {
                                    //colVal = "HORTICULTURE" + ", " + colVal
                                    colZeroVal = "HORTICULTURE"
                                } else {
                                    //colVal = "OTHERS" + ", " + colVal
                                    colZeroVal = "OTHERS"
                                }

                                rowList.add(colZeroVal.trim())

                            }
                        }
                        //println(String.join(",", cropsList))
                        // rowList.add(colZeroVal.trim())
                        rowList.add(colVal.trim())
                    }
                    break
                case CellType.BOOLEAN:
                    if (columnIndex >= minColumn) {
                        continue
                    } else {
                        //println(cell.getBooleanCellValue())
                        rowList.add(cell.getBooleanCellValue())
                    }
                    break
                case CellType.NUMERIC:
                    if (columnIndex >= minColumn) {
                        continue
                    } else {
                        numericVal = cell.getNumericCellValue()
                        tempAmt = numericVal
                        //println(numericVal)
                        rowList.add(numericVal)
                    }
                    break
                case CellType.FORMULA:
                    if (columnIndex >= minColumn) {
                        continue
                    } else {
                        //println("$$$$" + cell.getCellFormula())
                        rowList.add(cell.getCellFormula())
                    }
                    break
                case CellType.ERROR:
                    if (columnIndex >= minColumn) {
                        continue
                    } else {
                        println("%%%%" + cell.getErrorCellValue())
                    }
                    break
                case CellType.BLANK:
                    if (columnIndex >= minColumn) {
                        continue
                    } else {
                        if (columnIndex > 3) {
                            //println(" -- BLANK -- " + tempAmt)
                            rowList.add(tempAmt)
                        }
                    }
                    break
            }
            //println(",")

        }

        // Append the Average, Max, Min to the end of row 9
        if (rowNum == 9) {
            rowList.add("Average")
            rowList.add("Max")
            rowList.add("Min")
        }

        // Append the Average, Max, Min values to the other rows
        if (metricListMap.containsKey(rowNum)) {
            //println("Val: AVG " + metricListMap.get(rowNum).get("AVG") + "Val: MIN " + metricListMap.get(rowNum).get("MIN") + "Val: MAX " + metricListMap.get(rowNum).get("MAX"))
            rowList.add(metricListMap.get(rowNum).get("AVG"))
            rowList.add(metricListMap.get(rowNum).get("MAX"))
            rowList.add(metricListMap.get(rowNum).get("MIN"))
        }
        //println(String.join(",", String.valueOf(rowList))) //.replaceAll("\\[","").replaceAll("]","")))
        //println()

        record = String.join(",", String.valueOf(rowList))
        println(record)

        record = tstamp + ',' + rowNum + ',' + record[0..-2] + '\n'
        outputStream.write(record.getBytes(StandardCharsets.UTF_8))

        record = ''

    }

    workbook.close()
    inputStream.close()

}

def convertWithStream(Map<Integer, ?> map) {

    String mapAsString = map.keySet().stream()
            .map{key -> key + "=" + map.get(key)}
            .collect(Collectors.joining(", ", "{", "}"))
    return mapAsString

}

def readData(){

    def path = "C:\\Users\\odhiambod\\SmartAgri\\ExcelToCsvGroovy\\27-3-2018.xls"

    InputStream inputStream = new FileInputStream(path)

    Workbook workbook = WorkbookFactory.create(inputStream)

    Sheet sheet = workbook.getSheetAt(0)

    Iterator rowIterator = sheet.rowIterator()
    rowIterator.next()
    Row row
    def rowsData = []

    while(rowIterator.hasNext()){
        row = rowIterator.next()
        def rowIndex = row.getRowNum()
        println "The row index is ${rowIndex}"

        def colIndex
        def rowData = []
        for(Cell cell : row){

            colIndex = cell.getColumnIndex()
            switch (cell.getCellType()){

               case CellType.STRING:
                   rowData[colIndex] = cell.getRichStringCellValue().getString()
                   println "The string cell value for ${colIndex} is ${rowData[colIndex]}"
                   break

               case CellType.NUMERIC:
                   rowData[colIndex] = cell.getNumericCellValue().toString()
                   println "The numeric cell value for ${colIndex} is ${rowData[colIndex]}"
                   break

               default:
                   break

            }

            //rowData[colIndex] = cell.getNumericCellValue().getString()
            // rowData[colIndex] = cell.getRichStringCellValue().getString()
        }
        rowsData << rowData
    }
    rowsData

}

def readDataFlowFile() {

    def flowFile = new File("C:\\Users\\odhiambod\\SmartAgri\\ExcelToCsvGroovy\\27-3-2018.xls")
    if(!flowFile) return

    def date = new Date()

        try {
            def path = "C:\\Users\\odhiambod\\SmartAgri\\ExcelToCsvGroovy\\27-3-2018.xls"
            InputStream inputStream = new FileInputStream(path)

            def path2 = "C:\\Users\\odhiambod\\SmartAgri\\ExcelToCsvGroovy\\27-3-2018_2.csv"
            OutputStream outputStream = new FileOutputStream(path2)

            Workbook wb = WorkbookFactory.create(inputStream)
            Sheet mySheet = wb.getSheetAt(0)
            def record = ''

            // processing time, inserted as first column
            def tstamp = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS").format(date)

            Iterator<Row> rowIter = mySheet.rowIterator()
            def rowNum = 0
            while (rowIter.hasNext()) {
                rowNum++
                Row nextRow = rowIter.next()
                Iterator<Cell> cellIterator = nextRow.cellIterator()

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next()
                    record = record + cell.toString() + ','
                }

                if(rowNum > 1){
                    // insert tstamp, row num, drop last comma and add end line.
                    // Note: tstamp + row num are composite key
                    record = tstamp + ',' + rowNum + ',' + record[0..-2] + '\n'
                    outputStream.write(record.getBytes(StandardCharsets.UTF_8))
                }
                record = ''
            }

        }
        catch(e) {
            println "Error during processing of spreadsheet name = xx, sheet = xx ${e}"
            //session.transfer(inputStream, REL_FAILURE)
        }


}

def processCsv_2(){

    String variety = null, varieties = null, commodity = null, unit = null, kg = null, nairobi = null, mombasa = null, kisumu = null, nakuru = null, eldoret = null,
           busia = null, kisii = null, loitktk = null, isiolo = null, kitale = null

    String avg = "Average", max = "Max", min = "Min"

    double tempAmt = 0
    double numericVal = 0
    int minColumn = 0
    int columnIndex = 0

    Map<String, List> map = new HashMap<String, List>()
    List<String> cropsList = new ArrayList<String>()

    List<String> cerealsList = ["Dry Maize", "Green Maize", "Finger Millet", "Sorghum", "Wheat"]

    List<String> legumesList = ["Beans Canadian", "Beans Rosecoco", "Beans Mwitemania", "Mwezi Moja", "Dolichos (Njahi)", "Green Gram", "Cowpeas", "Fresh Peas", "Groundnuts"]
    List<String> rootsTubersList = ["Red Irish Potatoes", "White Irish Potatoes", "Cassava Fresh", "Sweet Potatoes"]
    List<String> horticultureList = ["Cabbages", "Cooking Bananas", "Ripe Bananas", "Carrots", "Tomatoes", "Onions Dry", "Spring Onions", "Chillies", "Cucumber", "Capsicums", "Brinjals",
                                     "Cauliflower", "Lettuce", "Passion Fruits", "Oranges", "Lemons", "Mangoes Local", "Mangoes Ngowe", "Limes", "Pineapples", "Pawpaw", "Avocado", "Kales"]
    List<String> varietyList = ["CEREAL", "LEGUMES", "ROOTS & TUBERS", "HORTICULTURE", "OTHERS"]
    //List<String> varietyList = Arrays.asList("CEREAL", "LEGUMES", "ROOTS & TUBERS", "HORTICULTURE", "OTHERS")

    //println "List ${varietyList}"

    Map<Integer, Map> metricListMap = new HashMap<>()
    int avgColumnIndex = 0
    int minColumnIndex = 0
    int maxColumnIndex = 0

    // metrics computation
    int avgMath = 0
    double sumAmount = 0.00, avgAmount = 0.00
    Integer avgCount = 0

    double maxVal = 0.00
    double minVal = 0.00
    List<String> minMaxValList = new ArrayList<>()
    List<String> sortedList = null

    def path = "C:\\Users\\odhiambod\\SmartAgri\\ExcelToCsvGroovy\\27-3-2018.xls"
    InputStream inputStream = new FileInputStream(path)

    def path2 = "C:\\Users\\odhiambod\\SmartAgri\\ExcelToCsvGroovy\\27-3-2018_3.csv"
    OutputStream outputStream = new FileOutputStream(path2)

    // processing time, inserted as first column
    def date = new Date()
    def tstamp = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS").format(date)

    //Workbook workbook = new XSSFWorkbook(inputStream) // for .xlsx files
    Workbook workbook = new HSSFWorkbook(inputStream)   // for .xls files

    Sheet firstSheet = workbook.getSheetAt(0)
    Iterator<Row> iterator = firstSheet.iterator()


    // set variables
    while (iterator.hasNext()) {
        Row nextRow = iterator.next()
        Iterator<Cell> cellIterator = nextRow.cellIterator()

        int rowNum = nextRow.getRowNum()
        //println("Row No: " + rowNum + " - ")
        if ((rowNum < 9) || (rowNum > 51)) {
            continue
        }

        while (cellIterator.hasNext()) {

            Cell cell = cellIterator.next()

            columnIndex = cell.getColumnIndex()
            //println("Index No: " + columnIndex + " - ")

            if (cell.getCellType() == CellType.STRING) {
                String colVal = cell.getStringCellValue().trim().replaceAll(",", "")

                if (rowNum < 9) {
                    continue
                }
                //else if ((rowNum == 9) && (colVal.equalsIgnoreCase(avg))) {
                else if (rowNum == 9) {

                    if (colVal.equalsIgnoreCase(avg)) {
                        minColumn = columnIndex
                        //println(">>>>>> Min Column: " + minColumn + " :::  ")
                        avgColumnIndex = columnIndex
                    } else if (colVal.equalsIgnoreCase(max)) {
                        maxColumnIndex = columnIndex
                    } else if (colVal.equalsIgnoreCase(min)) {
                        minColumnIndex = columnIndex
                    } else {
                        continue
                    }

                } else {
                    continue
                }
            }


            if (cell.getCellType() == CellType.NUMERIC) {

                double colVal = cell.getNumericCellValue()

                //println(">>>>>> columnIndex.......: " + columnIndex + " = " + colVal + " ::: \n\n ")

                if ((columnIndex > 3) && (columnIndex < avgColumnIndex)) {

                    //println(">>>>>> columnIndex.......: " + columnIndex + " = " + colVal + " ::: \n")

                    // average amount calculation
                    sumAmount += colVal
                    avgCount++

                    // minimum and maximum value computation
                    minMaxValList.add(String.valueOf(colVal))
                    if (columnIndex == (avgColumnIndex - 1)) {

                        Map<String, Double> cumulativeMap = new HashMap<>()

                        avgAmount = Math.round(sumAmount / avgCount)
                        //println(">>>>>> columnIndex.......: average " + avgAmount + " :: sumAmount " + sumAmount + " :: avgCount " + avgCount + "\n\n")
                        cumulativeMap.put("AVG", avgAmount)

                        // sort the list and extract the minimum and maximum values
                        sortedList = minMaxValList.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList())
                        maxVal = Double.valueOf(sortedList.get(sortedList.size() - 1))
                        minVal = Double.valueOf(sortedList.get(0))
                        //printlnln("\n minVal = " + minVal + " :: maxVal = " + maxVal)

                        cumulativeMap.put("MAX", maxVal)
                        cumulativeMap.put("MIN", minVal)

                        metricListMap.put(rowNum, cumulativeMap)

                        // remove values for the next iteration
                        minMaxValList.clear()
                        sumAmount = 0
                        avgCount = 0
                        avgAmount = 0.00
                        maxVal = 0.00
                        minVal = 0.00

                    }

                }

            }

        }

    }

    println ">>>>>> Min Column.......: " + minColumn + " ::: \n"
    println(">>>>>> Metrics .......: " + convertWithStream(metricListMap) + " ::: \n")
    println ">>>>>> columnIndex.......: avgColumnIndex " + avgColumnIndex + " :: maxColumnIndex " + maxColumnIndex + " : minColumnIndex " + minColumnIndex + " ::: \n"

    Iterator<Row> iterator2 = firstSheet.iterator()
    int rowNum = 0

    List<Object> rowList = null
    def record = ''

    while (iterator2.hasNext()) {
        Row nextRow2 = iterator2.next()
        Iterator<Cell> cellIterator2 = nextRow2.cellIterator()

        rowList = new ArrayList<Object>()
        // List<Object> rowList = new ArrayList<Object>()

        rowNum = nextRow2.getRowNum()
        //println("Row No: " + rowNum + " - ")
        if ((rowNum < 9) || (rowNum > 51)) {
            continue
        }
        println("Row No: " + rowNum + " - ")

        while (cellIterator2.hasNext()) {

            Cell cell = cellIterator2.next()

            columnIndex = cell.getColumnIndex()
            //println("Index No: " + columnIndex + " - ")

            switch (cell.getCellType()) {

                case CellType.STRING:

                    String colVal = cell.getStringCellValue().trim().replaceAll(",", "")
                    String colZeroVal = ""

                    if (columnIndex >= minColumn) {
                        continue
                    } else {
                        if ((columnIndex == 0)) {
                            // remove column 0
                            continue
                        } else {

                            if ((columnIndex == 1) && (rowNum == 9)) {
                                cropsList.add("VARIETIES")
                                colZeroVal = "VARIETIES"
                                rowList.add(colZeroVal.trim())
                            } else if (columnIndex == 1) {

                                if (cerealsList.contains(colVal)) {
                                    //colVal = "CEREAL" + ", " + colVal
                                    colZeroVal = "CEREAL"
                                } else if (legumesList.contains(colVal)) {
                                    //colVal = "LEGUMES" + ", " + colVal
                                    colZeroVal = "LEGUMES"
                                } else if (rootsTubersList.contains(colVal)) {
                                    //colVal = "ROOTS & TUBERS" + ", " + colVal
                                    colZeroVal = "ROOTS & TUBERS"
                                } else if (horticultureList.contains(colVal)) {
                                    //colVal = "HORTICULTURE" + ", " + colVal
                                    colZeroVal = "HORTICULTURE"
                                } else {
                                    //colVal = "OTHERS" + ", " + colVal
                                    colZeroVal = "OTHERS"
                                }

                                rowList.add(colZeroVal.trim())

                            }
                        }
                        //println(String.join(",", cropsList))
                        // rowList.add(colZeroVal.trim())
                        rowList.add(colVal.trim())
                    }
                    break
                case CellType.BOOLEAN:
                    if (columnIndex >= minColumn) {
                        continue
                    } else {
                        //println(cell.getBooleanCellValue())
                        rowList.add(cell.getBooleanCellValue())
                    }
                    break
                case CellType.NUMERIC:
                    if (columnIndex >= minColumn) {
                        continue
                    } else {
                        numericVal = cell.getNumericCellValue()
                        tempAmt = numericVal
                        //println(numericVal)
                        rowList.add(numericVal)
                    }
                    break
                case CellType.FORMULA:
                    if (columnIndex >= minColumn) {
                        continue
                    } else {
                        //println("$$$$" + cell.getCellFormula())
                        rowList.add(cell.getCellFormula())
                    }
                    break
                case CellType.ERROR:
                    if (columnIndex >= minColumn) {
                        continue
                    } else {
                        println("%%%%" + cell.getErrorCellValue())
                    }
                    break
                case CellType.BLANK:
                    if (columnIndex >= minColumn) {
                        continue
                    } else {
                        if (columnIndex > 3) {
                            //println(" -- BLANK -- " + tempAmt)
                            rowList.add(tempAmt)
                        }
                    }
                    break
            }
            //println(",")

        }

        // Append the Average, Max, Min to the end of row 9
        if (rowNum == 9) {
            rowList.add("Average")
            rowList.add("Max")
            rowList.add("Min")
        }

        // Append the Average, Max, Min values to the other rows
        if (metricListMap.containsKey(rowNum)) {
            //println("Val: AVG " + metricListMap.get(rowNum).get("AVG") + "Val: MIN " + metricListMap.get(rowNum).get("MIN") + "Val: MAX " + metricListMap.get(rowNum).get("MAX"))
            rowList.add(metricListMap.get(rowNum).get("AVG"))
            rowList.add(metricListMap.get(rowNum).get("MAX"))
            rowList.add(metricListMap.get(rowNum).get("MIN"))
        }
        //println(String.join(",", String.valueOf(rowList))) //.replaceAll("\\[","").replaceAll("]","")))
        //println()

        record = String.join(",", String.valueOf(rowList))
        println(record)

        record = tstamp + ',' + rowNum + ',' + record[0..-2] + '\n'
        outputStream.write(record.getBytes(StandardCharsets.UTF_8))

        record = ''

    }

    workbook.close()
    inputStream.close()

}