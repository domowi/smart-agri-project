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

//from org.apache.nifi.processor.io
//import StreamCallback

//import org.apache.nifi.processor.io.StreamCallback

import java.util.stream.Collectors

String variety = null, varieties = null, commodity = null, unit = null, kg = null
String avg = "Average", max = "Max", min = "Min", code = "code", kgColHeader = "Kg"

double tempAmt = 0
double numericVal = 0
int minColumn = 0
int startColumn = 0
int startRow = 9
int endRow = 51
int codeColumn = 4
int eggTrayKg = 5
int columnIndex = 0

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

String cropVal = ""
Boolean isCodeColumnSet = true

// metrics computation
double sumAmount = 0.00, avgAmount = 0.00
Integer avgCount = 0
double maxVal = 0.00
double minVal = 0.00

def flowFile = session.get()
if(!flowFile)
    return

def this_filename = flowFile.getAttribute('filename').split('\\.')[0]

// processing time, inserted as last column
def date = new Date()
def tstamp = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(date)

// process flow file
flowFile = session.write(flowFile, {inputStream, outputStream ->
    try {
        Workbook wb = WorkbookFactory.create(inputStream,)
        Sheet firstSheet = wb.getSheetAt(0)
        def record = ''

        Iterator<Row> rowIter = firstSheet.rowIterator()
        def rowNum = 0
        while (rowIter.hasNext()) {

            Row nextRow = rowIter.next()
            Iterator<Cell> cellIterator = nextRow.cellIterator()

            List<String> minMaxValList = new ArrayList<>()
            List<String> sortedList = null
            Map<String, Double> cumulativeMap = new HashMap<>()

            sumAmount = 0
            avgCount = 0
            avgAmount = 0.00

            rowNum = nextRow.getRowNum()
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

                        if (colVal.equalsIgnoreCase(avg)) {
                            minColumn = columnIndex
                            avgColumnIndex = columnIndex
                        } else if (colVal.equalsIgnoreCase(max)) {
                            maxColumnIndex = columnIndex
                        } else if (colVal.equalsIgnoreCase(min)) {
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


            // Start of Processing
            // Start of Processing
            Iterator<Row> iterator2 = firstSheet.iterator()
            //int rowNum = 0

            List<Object> rowList = null
            //def record = ''

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

                            String colVal = cell.getStringCellValue().trim().replaceAll(",", "")
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
                                rowList.add(colVal.trim())

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
                                rowList.add(numericVal)
                            }
                            break

                        case CellType.BOOLEAN:
                            if (columnIndex >= minColumn) {
                                continue
                            } else {
                                rowList.add(cell.getBooleanCellValue())
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
                    record = rowNum + ', ' + record[0..-2].replaceAll("\\[","") + ', ' + this_filename + ', ' + tstamp + '\n'
                }
                //record = rowNum + ',' + record[0..-2].replaceAll("\\[","") + ',' + this_filename + ',' + tstamp + '\n'
                outputStream.write(record.getBytes(StandardCharsets.UTF_8))

                record = ''

            }




//            rowNum++
//            Row nextRow = rowIter.next()
//            Iterator<Cell> cellIterator = nextRow.cellIterator()
//
//            while (cellIterator.hasNext()) {
//                Cell cell = cellIterator.next()
//                record = record + cell.toString() + ','
//            }
//
//            if(rowNum > 1){
//                // insert tstamp, row num, drop last comma and add end line.
//                // Note: tstamp + row num are composite key
//                record = tstamp + ',' + rowNum + ',' + record[0..-2] + '\n'
//                outputStream.write(record.getBytes(StandardCharsets.UTF_8))
//            }
//            record = ''
        }



    }
    catch(e) {
        log.error("Error during processing of spreadsheet name = xx, sheet = xx", e)
        //session.transfer(inputStream, REL_FAILURE)
    }
} ) //as StreamCallback)

def filename = flowFile.getAttribute('filename').split('\\.')[0] + '_' + new SimpleDateFormat("YYYYMMdd-HHmmss").format(date)+'.csv'
flowFile = session.putAttribute(flowFile, 'filename', filename)

session.transfer(flowFile, REL_SUCCESS)



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