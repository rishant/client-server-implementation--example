package com.sample.udp.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UDPServer {
	
	static int port = 5060;
    private DatagramSocket socket;
    private List<String> listQuotes = new ArrayList<>();
    private Random random;
 
    public UDPServer(int port) throws SocketException {
        socket = new DatagramSocket(port);
        random = new Random();
    }
 
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Syntax: UDPServer <file> <port>");
            return;
        }
 
        String quoteFile = args[0];
        int port = Integer.parseInt(args[1]);
 
        try {
        	UDPServer server = new UDPServer(port);
            server.loadQuotesFromFile(quoteFile);
            server.service();
        } catch (SocketException ex) {
            System.out.println("Socket error: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
 
    private void service() throws IOException {
        while (true) {
            DatagramPacket request = new DatagramPacket(new byte[1], 1);
            socket.receive(request);
 
            String quote = getRandomQuote();
            byte[] buffer = quote.getBytes();
 
            InetAddress clientAddress = request.getAddress();
            int clientPort = request.getPort();
 
            DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
            socket.send(response);
        }
    }
 
	private void loadQuotesFromFile(String quoteFile) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(quoteFile))) {
			String aQuote;
			while ((aQuote = reader.readLine()) != null) {
				listQuotes.add(aQuote);
			}
		}
	}
 
    private String getRandomQuote() {
        int randomIndex = random.nextInt(listQuotes.size());
        return listQuotes.get(randomIndex);
    }
}
