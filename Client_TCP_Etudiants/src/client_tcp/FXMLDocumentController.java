package client_tcp;

import java.io.BufferedReader;
import java.io.IOException;
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
    public CheckBox udp_toggle;
    public CheckBox tcp_toggle;

    TCP tcp=new TCP();
    UDP udp=new UDP();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        voyant.setFill(RED);
        deconnecter.setDisable(true);
        button.setDisable(true);
        connecter.setOnAction((e)-> {
            try {
                Connecter();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        deconnecter.setOnAction((e)->Deconnecter());
        button.setOnAction((e)-> {
            try {
                Requette();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        udp_toggle.setOnAction((e)-> {
            try {
                udp_toggle();
            } catch (UnknownHostException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        udp_toggle.setSelected(false);
        udp_toggle.setDisable(false);
        tcp_toggle.setOnAction((e)->tcp_toggle());
        tcp_toggle.setSelected(true);
        tcp_toggle.setDisable(true);
        //...
    }

    public void udp_toggle() throws IOException {
        if (udp_toggle.isSelected()) {
            udp_toggle.setDisable(true);
            tcp_toggle.setDisable(false);
            udp_toggle.setSelected(true);
            tcp_toggle.setSelected(false);
            udp_toggle.setDisable(true);
            tcp_toggle.setDisable(false);
            button.setDisable(false);
            connecter.setDisable(true);
            deconnecter.setDisable(true);

            String adresseServeur = textFieldIP.getText();
            int port = Integer.parseInt(textFieldPort.getText());
            udp.connectUDP(textFieldRequette.getText(), InetAddress.getByName(adresseServeur), port);
        }
    }


    public void tcp_toggle(){
        if(tcp_toggle.isSelected()){
            udp_toggle.setDisable(false);
            tcp_toggle.setDisable(true);
            udp_toggle.setSelected(false);
            tcp_toggle.setSelected(true);
            udp_toggle.setDisable(false);
            tcp_toggle.setDisable(true);
            button.setDisable(false);
            connecter.setDisable(false);
            deconnecter.setDisable(true);
        }
    }



    private void Connecter() throws IOException {
        //...
        String adresseServeur = textFieldIP.getText();
        int port = Integer.parseInt(textFieldPort.getText());
        if (tcp_toggle.isSelected()) {
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

    private void Requette() throws IOException {
        //...


        String requette = textFieldRequette.getText() + "\n" + "\r";
        tcp.requette(requette);
        //String reponse = bufferedReaderToString(tcp.in);
        //tcp.updateMessage(reponse);
        //textAreaReponses.setText(reponse);

    }

    public String bufferedReaderToString(BufferedReader br) throws IOException {
        String reponse = br.readLine();
        tcp.updateMessage(reponse);
        System.out.println("la reponse "+reponse);
        return reponse;
    }
    //...
}

