
package client_tcp;

import java.net.*;
import java.util.*;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import static javafx.scene.paint.Color.RED;
import javafx.scene.shape.Circle;

/**
 *
 * @author Michael
 */
public class FXMLDocumentController implements Initializable {

    public TextArea textAreaReponses;
    public TextField textFieldIP;
    public TextField textFieldPort;
    public TextField textFieldRequette;
    public Label label1;
    public Circle voyant;
    public Button connecter;
    public Button deconnecter;
    public Button button;
    static boolean enRun=false;


    TCP tcp=new TCP();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        voyant.setFill(RED);
        connecter.setOnAction((e)->Connecter());
        //...
    }

    private void Connecter() {
        //...


    }

    //...
    
}
