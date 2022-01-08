package com.sample.udp.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class UDPClient {

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Syntax: UDPClient <hostname> <port>");
			return;
		}

		String hostname = args[0];
		int port = Integer.parseInt(args[1]);

		DatagramSocket socket = null;
		try {
			InetAddress address = InetAddress.getByName(hostname);
			socket = new DatagramSocket();

			while (true) {

				DatagramPacket request = new DatagramPacket(new byte[1], 1, address, port);
				socket.send(request);

				byte[] buffer = new byte[512];
				DatagramPacket response = new DatagramPacket(buffer, buffer.length);
				socket.receive(response);

				String quote = new String(buffer, 0, response.getLength());
				System.out.println(quote);
				System.out.println();

				Thread.sleep(10000);
			}
		} catch (SocketTimeoutException ex) {
			System.err.println("error: " + ex.getMessage());
			ex.printStackTrace();
		} catch (IOException | InterruptedException e) {
			System.err.println("error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (socket != null)
				socket.close();
		}
	}
}
