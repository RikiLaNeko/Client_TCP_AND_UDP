package client_tcp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDP {
        private DatagramSocket serverSocket;
        private int port_service;
        private String request = null, response = null;
        private DatagramPacket serverPacket;
        private int taille = 2024, port_client;
        private InetAddress ip_Client;

        public UDP(int port_service) throws SocketException, IOException {
            this.port_service = port_service;
            serverSocket = new DatagramSocket(this.port_service);
        }

    public DatagramSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(DatagramSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public int getPort_service() {
        return port_service;
    }

    public void setPort_service(int port_service) {
        this.port_service = port_service;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public DatagramPacket getServerPacket() {
        return serverPacket;
    }

    public void setServerPacket(DatagramPacket serverPacket) {
        this.serverPacket = serverPacket;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getPort_client() {
        return port_client;
    }

    public void setPort_client(int port_client) {
        this.port_client = port_client;
    }

    public InetAddress getIp_Client() {
        return ip_Client;
    }

    public void setIp_Client(InetAddress ip_Client) {
        this.ip_Client = ip_Client;
    }
}
