package com.sample.tcp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {

	public static void main(String[] args) throws InterruptedException {
		if (args.length < 2) {
			args = new String[] { "localhost", "5050" };
		}

		String hostname = args[0];
		int port = Integer.parseInt(args[1]);
		String add = "add 5.0 6.0";
		try (Socket socket = new Socket(hostname, port)) {

			OutputStream output = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output, true);
			writer.println(add);
			
			Thread.sleep(2000);
			
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String response = reader.readLine();
			System.out.println(response);
			socket.close();
		} catch (UnknownHostException ex) {
			System.out.println("Server not found: " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("I/O error: " + ex.getMessage());
		}
	}
}
