
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegressionVerification {

    public static File folder = new File("C:\\Users\\sarth\\OneDrive\\Desktop\\MyPyProject\\TC_03\\");
    public static File[] listOfFiles = folder.listFiles();

    public static void main(String[] args) throws IOException {

        //String filePath="C:\\Users\\sarth\\OneDrive\\Desktop\\MyPyProject\\44-CL-000066650-0_PolicyAfter.pdf";
        //String filePath="C:\\Users\\sarth\\OneDrive\\Desktop\\MyPyProject\\TC_01\\44-CL-000066911-0_PolicyAfter.pdf";
        //String filePath="C:\\Users\\sarth\\OneDrive\\Desktop\\MyPyProject\\TC_02\\44-CL-000065630-0_PolicyAfter.pdf";


        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().split("\\.")[1].matches("pdf")) {
                //Get filen
                // ame of the pdf file...
                PrintWriter writer;

                String fileName = file.getName().split("\\.")[0];//Get filename without extension
                writer = new PrintWriter(folder.getAbsoluteFile() + "\\" + fileName + ".txt", "UTF-8");


                //Loading the PDF file...
                InputStream inputStream = new FileInputStream(file);
                PDDocument pdDocument = PDDocument.load(inputStream);
                int pages = pdDocument.getNumberOfPages();
                //System.out.println(pages);


                //Calling PDF2Text function...
                String decData = CommonFunctions.pdfToText(pdDocument, 1, 1);
                //System.out.println(decData);
                writer.println("------------DECLARATION------------\n");

                getDecData(decData, writer);

                writer.println("\n------------FORMS------------\n");

                getFormsData(pdDocument, writer);

                writer.println("\n------------WORKSHEETS------------\n");

                getWorksheetData(pdDocument, writer);
                writer.close();
            }


        }


    }
    //For Declaration Page of the policy...

    static void getDecData(String str, PrintWriter printWriter) {
        //Print matching text for given patters.
        for (String pattern : PatternsDefinition.patterns) {

            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            while (m.find()) {
                printWriter.println(m.group());
            }
        }

    }

    static void getFormsData(PDDocument pd, PrintWriter printWriter) throws IOException {
        ArrayList<Integer> formList = CommonFunctions.getPageNumbersFor(pd, "FORMS SCHEDULE", "FORMS");
        int count = 1;
        String formData;
        for (Integer formNum : formList
        ) {
            formData = CommonFunctions.pdfToText(pd, formNum.intValue(), formNum.intValue());
            Pattern p1 = Pattern.compile(PatternsDefinition.patternForms);
            Matcher matcher = p1.matcher(formData);

            while (matcher.find()) {

                printWriter.println((count) + "." + matcher.group());
                count++;
            }
        }
    }

    static void getWorksheetData(PDDocument pd, PrintWriter printWriter) throws IOException {
        ArrayList<Integer> cpdData = CommonFunctions.getPageNumbersFor(pd, "COMMON POLICY DECLARATIONS", "WORKSHEET");
        int count = 1;
        String worksheetData;
        for (Integer wNum : cpdData
        ) {
            worksheetData = CommonFunctions.pdfToText(pd, wNum.intValue(), wNum.intValue());
            getDecData(worksheetData, printWriter);
        }

        printWriter.println("\n--Commercial Property Worksheet Data--\n");
        ArrayList<Integer> cpData = CommonFunctions.getPageNumbersFor(pd, "COMMERCIAL PROPERTY", "WORKSHEET");
        String worksheetDataCP;
        for (String pattern : PatternsDefinition.worksheetPatternsCP) {

            for (Integer wNum : cpData) {
                worksheetDataCP = CommonFunctions.pdfToText(pd, wNum.intValue(), wNum.intValue());
                Pattern p = Pattern.compile(pattern);
                Matcher m = p.matcher(worksheetDataCP);
                while (m.find()) {
                    printWriter.println(count + ":" + m.group());
                    count++;

                }

            }
        }
    }
}


