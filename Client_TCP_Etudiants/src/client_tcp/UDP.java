package client_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

import javafx.application.Platform;

public class UDP extends Thread{
    final static int TAILLE = 1024;
    static byte data[] = new byte[TAILLE];

    FXMLDocumentController fxmlCont;

    boolean marche = false;
    BufferedReader in;


    public UDP() {
    }



    public void connectUDP(String request, InetAddress server,int port) throws IOException {
        try {
            // adr contient l'@IP de la partie serveur
            InetAddress adr = InetAddress.getByName(server.getHostAddress());
            System.out.println(adr + ":" + port);
            // données à envoyer : chaîne de caractères
            byte[] data = (new String(request)).getBytes();
            // création du paquet avec les données et en précisant l'adresse du serveur
            DatagramPacket packet = new DatagramPacket(data, data.length, adr, port);
            // création d'une socket, sans la lier à un port particulier
            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(10000);
            // on envoie le paquet au serveur
            socket.send(packet);
            // attente paquet envoyé sur la socket du client
            byte[] dataReceive= new byte[512];
            DatagramPacket packetReceive = new DatagramPacket(dataReceive, dataReceive.length, adr, port);
            socket.receive(packetReceive);
            // récupération et affichage de la donnée contenue dans le paquet
            String chaine = new String(packetReceive.getData(), 0,
                    packetReceive.getLength());
            System.out.println(" recu du serveur : "+chaine);
        } catch (SocketTimeoutException ste) {
            System.out.println("Le delai pour la reponse a expire");
        } catch (IOException ex) {
        }
    }


}
