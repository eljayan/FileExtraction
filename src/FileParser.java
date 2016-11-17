import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.yield;

/**
 * Created by R00715649 on 30-Oct-16.
 */
public class FileParser {

    public static List<String> pstFileNames = new ArrayList<>();

    //The method populates a list of pst files string paths
    public static List<String> parser(String rootFolder) {

        //iterate all files in folder
        File fileDirectory = new File(rootFolder);
        File[] fileList = fileDirectory.listFiles();

        for (File file:fileList){
            if (file.isDirectory()){
                parser(file.getAbsolutePath());
            }else {
                String fileName = file.getName();
                if (fileName.endsWith(".pst")) {
                    pstFileNames.add(file.getAbsolutePath());
                }
            }
        }
        return pstFileNames;
    }


}
