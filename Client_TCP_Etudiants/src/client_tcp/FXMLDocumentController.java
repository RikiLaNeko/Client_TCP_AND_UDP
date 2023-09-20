package client_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;

import javafx.fxml.Initializable;
import javafx.scene.control.*;

import static javafx.scene.paint.Color.RED;
import javafx.scene.shape.Circle;

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
        deconnecter.setDisable(true);
        button.setDisable(true);
        connecter.setOnAction((e)->Connecter());
        deconnecter.setOnAction((e)->Deconnecter());
        button.setOnAction((e)->Requette());
        //...
    }

    private void Connecter() {
        //...
        String adresseServeur = textFieldIP.getText();
        int port = Integer.parseInt(textFieldPort.getText());
        try {
            tcp = new TCP(InetAddress.getByName(adresseServeur), port, this);
            tcp.connection();
            if (tcp.socket.isConnected()) {
                voyant.setFill(javafx.scene.paint.Color.GREEN);
                connecter.setDisable(true);
                deconnecter.setDisable(false);
                button.setDisable(false);
                tcp.start();
                tcp.marche = true;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void Deconnecter() {
        //...
        try {
            tcp.deconnection();
            voyant.setFill(RED);
            connecter.setDisable(false);
            deconnecter.setDisable(true);
            button.setDisable(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void Requette() {
        //...
        String requette = textFieldRequette.getText() + "\n" + "\r";
        try {
            tcp.requette(requette);
            //String reponse = bufferedReaderToString(tcp.in);
            //tcp.updateMessage(reponse);
            //textAreaReponses.setText(reponse);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String bufferedReaderToString(BufferedReader br) throws IOException {
        String reponse = br.readLine();
        tcp.updateMessage(reponse);
        System.out.println("la reponse "+reponse);
        return reponse;
    }
    //...
}
