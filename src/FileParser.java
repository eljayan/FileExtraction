import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.yield;

/**
 * Created by R00715649 on 30-Oct-16.
 */
public class FileParser {

    //The method yields a string path each time
    public static List<String> parser(String folder) {

        List<String> pstFileNames = new ArrayList<String>();

        //loop pst files in folder
        File fileDirectory = new File(folder);
        File[] fileList = fileDirectory.listFiles();

        //validate if it is a pst file
        for (File file:fileList){
            String fileName = file.getName();
            if (fileName.endsWith(".pst")){
                pstFileNames.add(file.getAbsolutePath());
            }
        }
        return pstFileNames;
    }

}
