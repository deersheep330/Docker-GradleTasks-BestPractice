package automation.preprocess;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import automation.utilities.Utilities;

public class Preprocess {

    public static void generateBuildNumber() {

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        Date date = new Date();
        String env = Utilities.getProperty("environment","env");

        String buildNumber = env + "-" + dateFormat.format(date);
        Utilities.storeProperty("buildNum", "build", buildNumber);

        System.out.println("==> Generate Build Number: " + buildNumber);
    }

    public static void main(String[] args) {
        generateBuildNumber();
    }

}
