package automation.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Utilities {

    public static void storeProperty(String file, String key, String value) {

        Properties props = new Properties();

        file = file + ".properties";
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path filePath = Paths.get(currentPath.toString(), "src", "main", "resources", file);

        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(filePath.toString());
            props.load(in);
            in.close();

            out = new FileOutputStream(filePath.toString());
            props.setProperty(key, value);
            props.store(out, null);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String file, String key) {

        Properties prop = new Properties();

        file = file + ".properties";
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path filePath = Paths.get(currentPath.toString(), "src", "main", "resources", file);

        InputStream in= null;
        try {
            in = new FileInputStream(filePath.toString());
            prop.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop.getProperty(key);
    }

}
