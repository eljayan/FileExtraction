import java.io.File;
import java.io.FileFilter;
import java.nio.file.FileSystem;
import java.nio.file.FileVisitor;
import java.util.Iterator;
import java.util.List;

/**
 * Created by R00715649 on 30-Oct-16.
 */
public class FileExtraction {
    public static void main(String[] args) {

        //select the pst file folder
        String folder = "D:/Email/";

        //loop pst files in folder
        File fileDirectory = new File(folder);
        File[] fileList = fileDirectory.listFiles();

        //validate if it is a pst file
        for (File file:fileList){
            String fileName = file.getName();
            if (fileName.endsWith(".pst")){
                System.out.println(fileName);
            }
        }

        //parse the pst file

        //save the file to a folder

    }

}
