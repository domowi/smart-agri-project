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


def fileDir = "C:\\Users\\odhiambod\\SmartAgri\\ExcelToCsvGroovy\\xls\\"
def outputFileDir = "C:\\Users\\odhiambod\\SmartAgri\\ExcelToCsvGroovy\\csv\\"

// fetch all the files in the directory recursively
new File(fileDir).eachFileRecurse() {

    file -> file.getAbsolutePath()
    //println "File Path:  " + file.getAbsolutePath() + "  ::: File Dir:  " + file.getParent() + " ::: File: " + file.getPath()

    //File file = new File(path)
    def filename = file.getName()

    def fileExt = filename - ~/.*(?<=\.)/

    ArrayList<String> extList = ["xls"]
    if(extList.contains(fileExt)){

        def filenameDir = file.getParent() + "\\"
        //println "Files:  " + filename + " ::: " + filenameDir
        processCsv(filenameDir, filename, outputFileDir)
        // processCsv(fileDir, filename, outputFileDir)

    }

}

// processCsv()

/**
 * Convert Excel (.xls) To CSV (.csv)
 * @param fileDir
 * @param filename
 * @param outputFileDir
 * @return
 */
def processCsv(String fileDir, String filename, String outputFileDir){

    String variety = null, varieties = null, commodity = null, unit = null, kg = null
    String avg = "Average", ave = "Ave", max = "Max", max2 = "Max.", min = "Min", min2 = "Min.", code = "code", kgColHeader = "Kg", commodityHeader = "COMMODITY"

    double tempAmt = 0
    double numericVal = 0
    int minColumn = 0
    int startColumn = 0
    int startRow = 9
    int endRow = 51
    int codeColumn = 4
    int eggTrayKg = 5
    int columnIndex = 0

    def rowMapping = [
            7: [startRow: 7, endRow: 49], // Row ID 8 on the excel
            8: [startRow: 8, endRow: 50],  // Row ID 9 on the excel
            9: [startRow: 9, endRow: 51]  // Row ID 10 on the excel
    ]

    Map<String, List> map = new HashMap<String, List>()
    List<String> cropsList = new ArrayList<String>()

    List<String> cerealsList = ["Dry Maize", "Green Maize", "Finger Millet", "Sorghum", "Wheat"]
    List<String> legumesList = ["Beans Canadian", "Beans Rosecoco", "Beans Mwitemania", "Mwezi Moja", "Dolichos (Njahi)", "Green Gram", "Cowpeas", "Fresh Peas", "Groundnuts"]
    List<String> rootsTubersList = ["Red Irish Potatoes", "White Irish Potatoes", "Cassava Fresh", "Sweet Potatoes"]
    List<String> horticultureList = ["Cabbages", "Cooking Bananas", "Ripe Bananas", "Carrots", "Tomatoes", "Onions Dry", "Spring Onions", "Chillies", "Cucumber", "Capsicums", "Brinjals",
            "Cauliflower", "Lettuce", "Passion Fruits", "Oranges", "Lemons", "Mangoes Local", "Mangoes Ngowe", "Limes", "Pineapples", "Pawpaw", "Avocado", "Kales"]
    List<String> varietyList = ["CEREAL", "LEGUMES", "ROOTS & TUBERS", "HORTICULTURE", "OTHERS"]

    Map<Integer, Map> metricListMap = new HashMap<>()
    int avgColumnIndex = 0
    int minColumnIndex = 0
    int maxColumnIndex = 0
    int kgColumnIndex = 0
    int commodityColumnIndex = 1

    String cropVal = ""
    Boolean isCodeColumnSet = true

    // metrics computation
    double sumAmount = 0.00, avgAmount = 0.00
    Integer avgCount = 0
    double maxVal = 0.00
    double minVal = 0.00

//    def fileDir = "C:\\Users\\odhiambod\\SmartAgri\\ExcelToCsvGroovy\\xls\\"

    def path = fileDir + filename // fileDir + "Copy-of-22-10-2018.xls" // "27-3-2018.xls"
    InputStream inputStream = new FileInputStream(path)

////    File file = new File(path)
////    def filename = file.getName()
////    println "File Name: " + file.getAbsoluteFile() + " ::: " + filename

    outputPath = outputFileDir + filename.replaceAll(".xls",".csv").replaceAll(".xlsx",".csv")  // + ".csv"
    OutputStream outputStream = new FileOutputStream(outputPath)

    // processing time, inserted as last column
    def date = new Date()
    def tstamp = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(date) //  new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS").format(date)

    //Workbook workbook = new XSSFWorkbook(inputStream) // for .xlsx files
    Workbook workbook = new HSSFWorkbook(inputStream)   // for .xls files

    Sheet firstSheet = workbook.getSheetAt(0)

    // loop through and get header row
    Iterator<Row> rowIter = firstSheet.iterator()
    while (rowIter.hasNext()) {

        Row nextIterRow = rowIter.next()
        Iterator<Cell> cellIter = nextIterRow.cellIterator()

        int rowNum = nextIterRow.getRowNum()

        while (cellIter.hasNext()) {

            Cell cell = cellIter.next()

            columnIndex = cell.getColumnIndex()

            if ((columnIndex == commodityColumnIndex) && (cell.getCellType() == CellType.STRING)) {

                String colVal = cell.getStringCellValue().trim().replaceAll(",", "")

                // get the startRow and endRow for this excel file
                if (colVal.equalsIgnoreCase(commodityHeader)) {

                    println "File: " + path + " ::: Row: " + rowNum + " ::: commodityHeader - " + commodityHeader

                    def comMap = rowMapping[rowNum]
                    startRow = comMap.startRow
                    endRow = comMap.endRow
                    println "Map: " + comMap + " ::: startRow - " + startRow + " ::: endRow - " + endRow
                }

            }

        }

    }

    // loop through to define and set important values
    Iterator<Row> iterator = firstSheet.iterator()

    // set variables
    while (iterator.hasNext()) {
        Row nextRow = iterator.next()
        Iterator<Cell> cellIterator = nextRow.cellIterator()

        List<String> minMaxValList = new ArrayList<>()
        List<String> sortedList = null
        Map<String, Double> cumulativeMap = new HashMap<>()

        sumAmount = 0
        avgCount = 0
        avgAmount = 0.00

        int rowNum = nextRow.getRowNum()
        if ((rowNum < startRow) || (rowNum > endRow)) {
            continue
        }

        while (cellIterator.hasNext()) {

            Cell cell = cellIterator.next()

            columnIndex = cell.getColumnIndex()

            if (cell.getCellType() == CellType.STRING) {

                String colVal = cell.getStringCellValue().trim().replaceAll(",", "")

                if (rowNum < startRow) {
                    continue
                }
                else if (rowNum == startRow) {

                    if((columnIndex == codeColumn) && (!colVal.equalsIgnoreCase(code) )){
                        isCodeColumnSet = false
                    }

                    if ((colVal.equalsIgnoreCase(avg)) || (colVal.equalsIgnoreCase(ave))) {
                        minColumn = columnIndex
                        avgColumnIndex = columnIndex
                    } else if ((colVal.equalsIgnoreCase(max)) || (colVal.equalsIgnoreCase(max2))) {
                        maxColumnIndex = columnIndex
                    } else if ((colVal.equalsIgnoreCase(min)) || (colVal.equalsIgnoreCase(min2))) {
                        minColumnIndex = columnIndex
                    } else if (colVal.equalsIgnoreCase(kgColHeader)) {
                        kgColumnIndex = columnIndex
                        startColumn = columnIndex    // if kg is last header value (code header doesnt exist)
                    } else if (colVal.equalsIgnoreCase(code)) {
                        startColumn = columnIndex    // if code is last header value
                    }
                    else {
                        continue
                    }

                } else {
                    continue
                }

            }


            if (cell.getCellType() == CellType.NUMERIC) {

                double colVal = cell.getNumericCellValue()

                if ((columnIndex > startColumn) && (columnIndex < avgColumnIndex)) {

                    // average amount calculation
                    sumAmount += colVal
                    avgCount++ 

                    // minimum and maximum value computation
                    minMaxValList.add(String.valueOf(colVal))
                    if (columnIndex == (avgColumnIndex - 1)) {

                        if (minMaxValList.isEmpty()) {
                            continue
                        } else {
                            avgAmount = Math.round(sumAmount / avgCount)
                            cumulativeMap.put("AVG", avgAmount)

                            // sort the list and extract the minimum and maximum values
                            sortedList = minMaxValList.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList())
                            maxVal = Double.valueOf(sortedList.get(sortedList.size() - 1))
                            minVal = Double.valueOf(sortedList.get(0))

                            cumulativeMap.put("MAX", maxVal)
                            cumulativeMap.put("MIN", minVal)

                            metricListMap.put(rowNum, cumulativeMap)
                        }

                    }

                }

            } // END NUMERIC


            if (cell.getCellType() == CellType.BLANK) {

                if ((columnIndex > startColumn) && (columnIndex < avgColumnIndex)) {

                    // minimum and maximum value computation
                    if (columnIndex == (avgColumnIndex - 1)) {

                        avgAmount = Math.round(sumAmount / avgCount)
                        cumulativeMap.put("AVG", avgAmount)

                        if (minMaxValList.isEmpty()) {
                            continue
                        } else {

                            // sort the list and extract the minimum and maximum values
                            sortedList = minMaxValList.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList())
                            maxVal = Double.valueOf(sortedList.get(sortedList.size() - 1))
                            minVal = Double.valueOf(sortedList.get(0))

                            cumulativeMap.put("MAX", maxVal)
                            cumulativeMap.put("MIN", minVal)

                            metricListMap.put(rowNum, cumulativeMap)

                        }

                    }

                }

            } // END BLANK

        }

    }

    Iterator<Row> iterator2 = firstSheet.iterator()
    int rowNum = 0

    List<Object> rowList = null
    def record = ''

    while (iterator2.hasNext()) {
        Row nextRow2 = iterator2.next()
        Iterator<Cell> cellIterator2 = nextRow2.cellIterator()

        rowList = new ArrayList<Object>()

        rowNum = nextRow2.getRowNum()
        if ((rowNum < startRow) || (rowNum > endRow)) {
            continue
        }

        while (cellIterator2.hasNext()) {

            Cell cell = cellIterator2.next()

            columnIndex = cell.getColumnIndex()

            switch (cell.getCellType()) {

                case CellType.STRING:

                    String colVal = cell.getStringCellValue().trim().replaceAll(",", "").replaceAll("\\/pc","")
                                    .replaceAll("Eld","Eldoret").replaceAll("Loit","Loitktk").replaceAll("Ktl","Kitale")
                                    .replaceAll("Tvt","Taveta").replaceAll("Nyahu","Nyahururu").replaceAll("Garisa","Garissa")
                    String colZeroVal = ""

                    if (columnIndex >= minColumn) {
                        continue
                    } else {
                        if ((columnIndex == 0)) {
                            // remove column 0
                            continue
                        } else {

                            if ((columnIndex == 1) && (rowNum == startRow)) {
                                cropsList.add("VARIETIES")
                                colZeroVal = "VARIETY"
                                rowList.add(colZeroVal.trim())
                            } else if (columnIndex == 1) {

                                if (cerealsList.contains(colVal)) {
                                    colZeroVal = "CEREAL"
                                } else if (legumesList.contains(colVal)) {
                                    colZeroVal = "LEGUMES"
                                } else if (rootsTubersList.contains(colVal)) {
                                    colZeroVal = "ROOTS & TUBERS"
                                } else if (horticultureList.contains(colVal)) {
                                    colZeroVal = "HORTICULTURE"
                                } else {
                                    colZeroVal = "OTHERS"
                                }
                                rowList.add(colZeroVal.trim())

                                // set the crop name to be used to get the crop-code mapping
                                if((rowNum > startRow) && (rowNum < (endRow + 1))){
                                    cropVal = colVal.trim()
                                }

                            }

                            // Modify the Tray word value on the Eggs Tray Kg row to Tray Kg (e.g. 5)
                            if((rowNum == endRow) && (columnIndex == kgColumnIndex)){
                                colVal = eggTrayKg
                            }

                        }
                        //println "filename - " + filename + " ::: rowNum - " + rowNum  + " ::: columnIndex - " + columnIndex + " ::: AVG  - " + metricListMap.get(rowNum).get("AVG") +  " ::: startRow - " + startRow + " ::: endRow - " + endRow
                        rowList.add(colVal.trim())

                    }
                    break
                case CellType.BOOLEAN:
                    if (columnIndex >= minColumn) {
                        continue
                    } else {
                        rowList.add(cell.getBooleanCellValue())
                    }
                    break
                case CellType.NUMERIC:
                    if (columnIndex >= minColumn) {
                        continue
                    } else {
                        numericVal = cell.getNumericCellValue()
                        if((columnIndex == codeColumn) && !(isCodeColumnSet)){
                            String codeVal = getCropCodeMapping(cropVal)
                            rowList.add(codeVal)
                        }
                        println "filename - " + filename + " ::: rowNum - " + rowNum  + " ::: columnIndex - " + columnIndex + " ::: AVG  - " + metricListMap.get(rowNum).get("AVG") +  " ::: startRow - " + startRow + " ::: endRow - " + endRow
                        rowList.add(numericVal)
                    }
                    break
                case CellType.BLANK:
                    if (columnIndex >= minColumn) {
                        continue
                    } else {

                        if (columnIndex > startColumn) {

                            // set the crop code for excels with no code (crop code) set
                            if((columnIndex == codeColumn) && !(isCodeColumnSet)){
                                String codeVal = getCropCodeMapping(cropVal)
                                rowList.add(codeVal)
                            }
                            println "filename - " + filename + " ::: rowNum - " + rowNum  + " ::: columnIndex - " + columnIndex + " ::: AVG  - " + metricListMap.get(rowNum).get("AVG") +  " ::: startRow - " + startRow + " ::: endRow - " + endRow
                            rowList.add(metricListMap.get(rowNum).get("AVG"))

                        }

                        // Add the Tray word value on the Eggs Tray Kg row
                        if((rowNum == endRow) && (columnIndex == kgColumnIndex)){
                            rowList.add(eggTrayKg)
                        }

                    }
                    break

                case CellType.FORMULA:
                    if (columnIndex >= minColumn) {
                        continue
                    } else {
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

            }

        }

        // Append the Average, Max, Min to the end of row 9
        if (rowNum == startRow) {
            rowList.add("Average")
            rowList.add("Max")
            rowList.add("Min")
            rowList.add("FileName")
            rowList.add("DateOfUpload")

            if(!isCodeColumnSet){
                rowList.add(codeColumn,"Code")
            }

        }

        // Append the Average, Max, Min values to the other rows
        if (metricListMap.containsKey(rowNum)) {
            rowList.add(metricListMap.get(rowNum).get("AVG"))
            rowList.add(metricListMap.get(rowNum).get("MAX"))
            rowList.add(metricListMap.get(rowNum).get("MIN"))
        }

        record = String.join(",", String.valueOf(rowList))
        //println(record)

        if(rowNum == startRow){
            record = "RowNo" + ', ' + record[0..-2].replaceAll("\\[","")
                    .replace("COMMODITY", "Commodity").replace("VARIETIES", "Varieties") + '\n'
        } else {
            record = rowNum + ', ' + record[0..-2].replaceAll("\\[","") + ', ' + filename + ', ' + tstamp + '\n'
        }
        //record = rowNum + ',' + record[0..-2].replaceAll("\\[","") + ',' + filename + ',' + tstamp + '\n'
        outputStream.write(record.getBytes(StandardCharsets.UTF_8))

        record = ''

    }

    workbook.close()
    inputStream.close()

}

/**
 * Convert Map to Map String
 * @param map
 * @return
 */
def convertWithStream(Map<Integer, ?> map) {

    String mapAsString = map.keySet().stream()
            .map{key -> key + "=" + map.get(key)}
            .collect(Collectors.joining(", ", "{", "}"))
    return mapAsString

}

/**
 * Get the crop code for replacement
 * @param cropVal
 * @return
 */
def getCropCodeMapping(String cropVal){

    String cropCode = 0

    def codeMap = [
            1 : "Avocado", 2 : "Cooking Bananas", 3 : "Ripe Bananas", 4 : "Beans Canadian", 5 : "Beans Rosecoco", 6 : "Mwezi Moja", 7 : "Beans Mwitemania", 8 : "Brinjals", 9 : "Cabbages", 10 : "Cauliflower",
            11 : "Chillies", 12 : "Capsicums", 13 : "Carrots", 14 : "Cucumber", 15 : "Kales", 16 : "Lemons", 17 : "Lettuce", 18 : "Limes", 19 : "Green Maize", 20 : "Dry Maize",
            21 : "Mangoes Local", 22 : "Onions Dry", 23 : "Spring Onions", 24 : "Oranges", 25 : "Passion Fruits", 26 : "Pawpaw", 27 : "Fresh Peas", 28 : "Pineapples", 29 : "Red Irish Potatoes", 30 : "White Irish Potatoes",
            31 : "Sweet Potatoes", 32 : "Tomatoes", 33 : "", 34 : "", 35 : "Dolichos (Njahi)", 36 : "Mangoes Ngowe", 37 : "", 38 : "", 39 : "", 40 : "",
            41 : "Finger Millet", 42 : "Sorghum", 43 : "Cassava Fresh", 44 : "Groundnuts", 45 : "Cowpeas", 46 : "Green Gram", 47 : "Eggs", 48 : "Wheat"
    ]

    codeMap.each { entry ->
        if(entry.value.trim() == cropVal.trim()){
            cropCode = entry.key
        }

    }
    return cropCode

}