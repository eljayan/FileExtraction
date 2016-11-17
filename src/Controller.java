import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.util.List;

/**
 * Created by R00715649 on 04-Nov-16.
 */
public class Controller {
    @FXML
    Button selectFolder;
    @FXML
    Button start;
    @FXML
    Label folderName;
    @FXML
    Label processStatus;

    @FXML
    void loadFolder(ActionEvent event){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File folder = directoryChooser.showDialog(null);
        folderName.setText(folder.getAbsolutePath());
    }

    @FXML
    void fileExtraction(ActionEvent event)throws Exception{

            //processStatus.setText("Scanning file "+ f + " " + scannedFiles + " of " + totalFiles);
            PSTParser pstParser = new PSTParser(folderName.getText());

            processStatus.textProperty().bind(pstParser.status);

            Thread pstParserThread = new Thread(pstParser);
            pstParserThread.setDaemon(true);

            pstParserThread.start();

        }

//        PSTParser pstParser = new PSTParser(fileNames.get(2));
//        processStatus.textProperty().bind(pstParser.status);
//        Thread pstParserThread = new Thread(pstParser);
//        pstParserThread.start();

        //processStatus.setText("Scanning finished.");


}
