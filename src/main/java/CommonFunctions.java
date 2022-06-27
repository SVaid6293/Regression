import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonFunctions {



    static String pdfToText(PDDocument pd, int startPage, int endPage) throws IOException {

        PDFTextStripper pdfTextStripper=new PDFTextStripper();
        pdfTextStripper.setStartPage(startPage);
        pdfTextStripper.setEndPage(endPage);

        return pdfTextStripper.getText(pd);

    }


    static ArrayList getPageNumbersFor(PDDocument pd,String pattern1,String pattern2) throws IOException {
        ArrayList<Integer> pageNumbers = new ArrayList<Integer>();
        PDFTextStripper pdfTextStripper=new PDFTextStripper();
        int pages=pd.getNumberOfPages();

        Pattern p1 = Pattern.compile(pattern1);
        Pattern p2 = Pattern.compile(pattern2);

        for(int i=1;i<=pages;i++){
            pdfTextStripper.setStartPage(i);
            pdfTextStripper.setEndPage(i);
            String data=pdfTextStripper.getText(pd);

            Matcher m1 = p1.matcher(data);
            Matcher m2 = p2.matcher(data);

            if(m1.find() && m2.find()){
                pageNumbers.add(i);
            }

        }
        return pageNumbers;
    }


}
