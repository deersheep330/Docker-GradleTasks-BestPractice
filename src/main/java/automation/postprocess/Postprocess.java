package automation.postprocess;

import automation.utilities.Utilities;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Postprocess {

    public static void archiveTestResults() {

        // make archive folder
        File archiveFolder = new File(Paths.get(System.getProperty("user.dir"), "archive", Utilities.getProperty("buildNum","build")).toString());
        if (!archiveFolder.exists()) {
            archiveFolder.mkdirs();
        }

        // copy latest test-results folder to archive
        Path testResultsFolder = Paths.get(System.getProperty("user.dir"), "build", "test-results");
        File tempFolder = new File(Paths.get(System.getProperty("user.dir"), "archive", "test-results").toString());

        try {
            if (tempFolder.exists()) {
                FileUtils.deleteDirectory(tempFolder);
            }
            FileUtils.copyDirectory(new File(testResultsFolder.toString()), tempFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // copy junit & cucumber reports to archive/build-num
        Path junitReportFolder = Paths.get(System.getProperty("user.dir"), "build", "test-results", "junit");
        Path cucumberReportFolder = Paths.get(System.getProperty("user.dir"), "build", "test-results", "cucumber");

        try {
            FileUtils.copyDirectory(new File(junitReportFolder.toString()), archiveFolder);
            FileUtils.copyDirectory(new File(cucumberReportFolder.toString()), archiveFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        archiveTestResults();
    }

}
