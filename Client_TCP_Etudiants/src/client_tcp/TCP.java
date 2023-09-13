/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_tcp;


import java.io.*;
import java.net.*;
import java.util.*;

import javafx.application.Platform;

/**
 *
 * @author Michael
 */
public class TCP extends Thread {
    int port;
    InetAddress serveur;     
    Socket socket;
    boolean marche=false;
    boolean connection=false;
    PrintStream out;
    BufferedReader in;
    
    FXMLDocumentController fxmlCont;
  
    public TCP() {
    }

    public TCP(InetAddress serveur,int port, FXMLDocumentController fxmlCont ) {
        this.port = port;
        this.serveur = serveur;  
        this.fxmlCont=fxmlCont;
        System.out.println("@ serveur: "+serveur+" port: "+port);
    }
       
    public ArrayList<String> listeDesAdresses() throws UnknownHostException{
        ArrayList<String> laListeDesAdresses = new ArrayList<>();
        InetAddress adrLB = InetAddress.getLoopbackAddress();
        InetAddress adrLH = InetAddress.getLocalHost();
        laListeDesAdresses.add(adrLB.getHostAddress());
        laListeDesAdresses.add(adrLH.getHostAddress());
        return laListeDesAdresses;    
    }
    
    public void connection() throws IOException {
        this.socket=new Socket(this.serveur,this.port);
        out=new PrintStream(socket.getOutputStream());
    }
    
    public void deconnection() throws InterruptedException   {
        //...
        
    }
        
    public void requette(String laRequette) throws IOException  {
        out.print(laRequette);  // envoi reseau
        System.out.println("la requette "+laRequette);
    }   
    
    public void run() {
    
        while(marche) {
           //...

            
        }
        
    }
    


    protected void updateMessage(String message) {
        Platform.runLater(() -> fxmlCont.textAreaReponses.appendText("    MESSAGE SERVEUR >  \n      " + message + "\n"));
    }
    
    
}