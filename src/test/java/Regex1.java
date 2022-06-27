import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex1 {
    static String locationPattern="LOCATION#\\s*\\d+\\s*BUILDING#\\s*\\d+";
    static String pkgModPattern="PKG MOD FTR\\s*\\=\\s*\\d*\\.*\\d*";
    static String lcmPattern="LCM\\s*\\=\\s*\\d*\\.*\\d*";
    static String defFactorPattern="DED FTR\\s*\\=\\s*\\d*\\.*\\d*";
    static String lcPattern="LC\\s*\\=\\s*\\d*\\.*\\d*";

    public static String[] worksheetPatterns=new String[]{
            locationPattern,pkgModPattern,lcmPattern,defFactorPattern,lcPattern
    };
    public static void main(String[]args) throws IOException {

        //System.out.println(System.getProperty("user.dir"));
        File file=new File("C:\\Users\\sarth\\OneDrive\\Desktop\\MyPyProject\\TC_01\\44-CL-000066911-0_PolicyAfter.pdf");
        InputStream inputStream=new FileInputStream(file);
        PDDocument pdDocument= PDDocument.load(inputStream);
        PDFTextStripper pdfTextStripper=new PDFTextStripper();

        for (String pattern:worksheetPatterns){

            for(int i=93;i<=105;i++) {
                pdfTextStripper.setStartPage(i);
                pdfTextStripper.setEndPage(i);
                String data = pdfTextStripper.getText(pdDocument);
                Pattern p = Pattern.compile(pattern);
                Matcher m = p.matcher(data);
                while (m.find()) {
                    System.out.println(m.group());

                }

            }
        }
    }
}
