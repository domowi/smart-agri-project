package com.amudabadmus.awfa;

import net.sourceforge.tess4j.*;
import java.io.*;

public class AppImageTextExtractor {

    public String getImgText(String imageLocation) {
        ITesseract instance = new Tesseract();
        try
        {
            String imgText = instance.doOCR(new File(imageLocation));
            return imgText;
        }
        catch (TesseractException e)
        {
            e.getMessage();
            return "Error while reading image";
        }
    }
    public static void main ( String[] args)
    {
        AppImageTextExtractor app = new AppImageTextExtractor();
        //System.out.println(app.getImgText("C:\\Users\\User\\Pictures\\img.png"));
        //System.out.println(app.getImgText("C:\\Users\\odhiambod\\SmartAgri\\ExcelToCsvGroovy\\xls\\uploads\\2019\\05\\14.05.2019-768x607.pdf"));
        //System.out.println(app.getImgText("C:\\Users\\odhiambod\\SmartAgri\\ExcelToCsvGroovy\\xls\\uploads\\2019\\05\\imageonline-jpgtopdf6197079.pdf"));
        System.out.println(app.getImgText("C:\\Users\\odhiambod\\SmartAgri\\ExcelToCsvGroovy\\xls\\uploads\\2019\\05\\imageonline-pngtopdf6533376.pdf"));

    }

}
