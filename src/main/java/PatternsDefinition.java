public class PatternsDefinition {

    //Defining patterns...
    static String pattern1 = "POLICY PREMIUM\\s*\\$\\s*\\d*\\,*\\d+\\.*\\d*";
    static String pattern2 = "COMMERCIAL PROPERTY\\s*\\$\\s*\\d*\\,*\\d*";
    static String pattern3 = "COMMERCIAL GENERAL LIABILITY\\s*\\$\\s*\\d*\\,*\\d*";
    static String pattern4 = "COMMERCIAL CRIME AND FIDELITY\\s*\\$\\s*\\d*\\,*\\d*";
    static String pattern5 = "COMMERCIAL INLAND MARINE\\s*\\$\\s*\\d*\\,*\\d*";
    static String pattern6 = "COMMERCIAL AUTO\\s*\\$\\s*\\d*\\,*\\d*";
    static String pattern7 = "COMMERCIAL UMBRELLA\\s*\\$\\s*\\d*\\,*\\d*";
    static String patternMN="ADDITIONAL MINNESOTA FIRE SAFETY SURCHARGE\\s*\\$\\s*\\d*\\,*\\d*\\.*\\d*";
    static String pattern8="TOTAL PREMIUM\\s*\\$\\s*\\d*\\,*\\d*\\.*\\d*";
    static String pattern9="BALANCE TO MEET MINIMUM PREMIUM\\s*\\$\\s*\\d*\\,*\\d*\\.*\\d*";
    static String pattern10 = "COMMERCIAL CRIME\\s*\\$\\s*\\d*\\,*\\d*";

    static String patternForms="[A-Z]+[0-9]+\s*\\d+\\-\\d+.{1,1000}";

    //Worksheet
    static String locationPattern="LOCATION#\\s*\\d+\\s*BUILDING#\\s*\\d+";
    static String pkgModPattern="PKG MOD FTR\\s*\\=\\s*\\d*\\.*\\d*";
    static String lcmPattern="LCM\\s*\\=\\s*\\d*\\.*\\d*";
    static String defFactorPattern="DED FTR\\s*\\=\\s*\\d*\\.*\\d*";
    static String lcPattern="LC\\s*\\=\\s*\\d*\\.*\\d*";

    //Storing patterns..
    public static String[] patterns=new String[]{
            pattern1,pattern2,pattern3,pattern4,pattern5,pattern6,pattern7
            ,pattern8,patternMN,pattern9,pattern10
            //,pattern10,pattern11,pattern12,pattern13
    };

    public static String[] worksheetPatternsCP=new String[]{
            locationPattern,pkgModPattern,lcmPattern,defFactorPattern,lcPattern
    };

}
