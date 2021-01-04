//println "Hello Groovy"

import org.apache.commons.io.IOUtils
import org.apache.poi.ss.usermodel.CellType

import java.nio.charset.*
import java.text.SimpleDateFormat

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory

def flowFile = new File("C:\\Users\\odhiambod\\SmartAgri\\ExcelToCsvGroovy\\27-3-2018.xls")
if(!flowFile)
    return

println "The file ${flowFile.absolutePath} has ${flowFile.length()} bytes"

//// fetch all the files in the directory recursively
//new File("C:\\Users\\odhiambod\\SmartAgri\\ExcelToCsvGroovy\\").eachFileRecurse() {
//    file -> println file.getAbsolutePath()
//}

def date = new Date()

readData();

def readData(){

    def path = "C:\\Users\\odhiambod\\SmartAgri\\ExcelToCsvGroovy\\27-3-2018.xls";

    InputStream inputStream = new FileInputStream(path);

    Workbook workbook = WorkbookFactory.create(inputStream);

    Sheet sheet = workbook.getSheetAt(0);

    Iterator rowIterator = sheet.rowIterator();
    rowIterator.next()
    Row row;
    def rowsData = []

    while(rowIterator.hasNext()){
        row = rowIterator.next()
        def rowIndex = row.getRowNum()
        println "The row index is ${rowIndex}"

        def colIndex;
        def rowData = []
        for(Cell cell : row){

            colIndex = cell.getColumnIndex()
            switch (cell.getCellType()){

               case CellType.STRING:
                   rowData[colIndex] = cell.getRichStringCellValue().getString();
                   println "The string cell value for ${colIndex} is ${rowData[colIndex]}"
                   break

               case CellType.NUMERIC:
                   rowData[colIndex] = cell.getNumericCellValue().toString();
                   println "The numeric cell value for ${colIndex} is ${rowData[colIndex]}"
                   break

               default:
                   break

            }

            //rowData[colIndex] = cell.getNumericCellValue().getString();
            // rowData[colIndex] = cell.getRichStringCellValue().getString();
        }
        rowsData << rowData
    }
    rowsData

}