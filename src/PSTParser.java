import com.pff.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by R00715649 on 30-Oct-16.
 */
public class PSTParser {

    private String saveFolder = "D:/importDocs/";
    static int fileCount =0;

    //open the pst file
    static void parse(String fileName)throws Exception{
        PSTFile pstFile = new PSTFile(fileName);

        //scan all the folders
        PSTFolder rootFolder = pstFile.getRootFolder();
        processFolder(rootFolder);

    }

    static void processFolder(PSTFolder folder) throws PSTException, IOException{
        if (folder.hasSubfolders()){
            Vector<PSTFolder>subFolders = folder.getSubFolders();
            Iterator<PSTFolder> folderIterator = subFolders.iterator();
            while (folderIterator.hasNext()){
                processFolder(folderIterator.next());
            }

        }else {
            PSTMessage message = (PSTMessage) folder.getNextChild();
            while (message != null){
                fileCount++;
                System.out.println(message.getSubject());
                message = (PSTMessage) folder.getNextChild();

                //check if there is a pl number in the subject
                String subject = message.getSubject();
                String plNumber = extractPL(subject);

                if (plNumber != null){
                    saveAttachments(message);
                }
            }
        }

    }


    //get the items


    static String extractPL(String text){
        //#######################
    }

    static void saveAttachments(PSTMessage message) throws PSTException, IOException{
        if (message.hasAttachments()){
            int attachmentCount = message.getNumberOfAttachments();
            for (int i = 0; i < attachmentCount; i++){
                PSTAttachment attachment = message.getAttachment(i);
                System.out.println(attachment.getDisplayName());
            }
        }
    }

    //check if save folder contains folder with pl name



    //save to the save folder.


    public static void main(String[] args) throws Exception{
        parse("D://Email//archive 2009 ruben.pst");
        System.out.println(fileCount);
    }
}
