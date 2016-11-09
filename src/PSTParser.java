import com.pff.*;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by R00715649 on 30-Oct-16.
 */
public class PSTParser implements Runnable {

    public String saveFolder = "D:/importDocs/";
    public int fileCount =0;
    public String pstFileName;

    //constructor
    PSTParser(String pstFileName){
        this.pstFileName = pstFileName;
    }


    @Override
    public void run(){
        try{
            parse();
        }catch (Exception e){

        };
    }


    //open the pst file
    public void parse()throws Exception{
        PSTFile pstFile = new PSTFile(pstFileName);

        //scan all the folders
        PSTFolder rootFolder = pstFile.getRootFolder();
        processFolder(rootFolder);

    }

    public void processFolder(PSTFolder folder) throws PSTException, IOException{
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

                //check if there is a pl number in the subject
                String subject = message.getSubject();
                String plNumber = extractPL(subject);

                if (plNumber != null){
                    File plFolder = new File(saveFolder + plNumber + "/");
                    if (!plFolder.exists()){
                        plFolder.mkdirs();
                    }
                    saveAttachments(message, plFolder.getPath());
                    System.out.println(plNumber);
                }
                message = (PSTMessage) folder.getNextChild();
            }
        }

    }


    //get the items


    public String extractPL(String text){

        Pattern pattern1 = Pattern.compile("0[\\d\\w][\\d\\w]\\d{9}[\\d\\w]\\w{4}\\d[\\d\\w]\\w[\\w\\d]?[\\w\\d]?[\\w\\d]?", Pattern.CASE_INSENSITIVE);
        Pattern pattern2 = Pattern.compile("00P\\d{10}\\w\\d?", Pattern.CASE_INSENSITIVE);
        Pattern pattern3 = Pattern.compile("WT\\d{9}\\w", Pattern.CASE_INSENSITIVE);
        Pattern pattern4 = Pattern.compile("\\d{12}\\w{2}\\d\\d\\w\\d\\d", Pattern.CASE_INSENSITIVE);
        List<Pattern>patterns = Arrays.asList(pattern1, pattern2, pattern3, pattern4);

        for (Pattern p:patterns){
            Matcher matcher = p.matcher(text);
            if (matcher.find()){
                return matcher.group();
            }
        }
        return null;
    }

    public void saveAttachments(PSTMessage message, String saveFolder) throws PSTException, IOException{
        if (message.hasAttachments()){
            int attachmentCount = message.getNumberOfAttachments();
            for (int i = 0; i < attachmentCount; i++){
                PSTAttachment attachment = message.getAttachment(i);
                InputStream fileInputStream = attachment.getFileInputStream();
                File outputFile = new File(saveFolder + "/" + attachment.getFilename());
                //System.out.println(outputFile.getAbsolutePath());


                try {
                    outputFile.createNewFile();
                }catch (IOException error){
                    System.out.println(error.getMessage());
                    continue;
                }

                try{
                    FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
                    int fileDataByte = fileInputStream.read();
                    while (fileDataByte != -1){
                        fileOutputStream.write(fileDataByte);
                        fileDataByte = fileInputStream.read();
                    }
                    fileOutputStream.close();
                    System.out.println(attachment.getDisplayName());

                }catch (FileNotFoundException err){
                    continue;
                }

            }
        }
    }



//    public static void main(String[] args) throws Exception{
//        parse("D://Email//Archive.pst");
//        System.out.println(fileCount);
//    }
}
