package com.sample.tcp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Date;

import com.sample.tcp.server.handler.BasicCalculatorBusinessLogicHandler;

public class TCPServer {

	public static BasicCalculatorBusinessLogicHandler handler = new BasicCalculatorBusinessLogicHandler();
	static int port = 5050;

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, IOException, InterruptedException {

		int portMain;
		if (args.length < 1) {
			portMain = port;
		} else {
			portMain = Integer.parseInt(args[0]);
		}

		try (ServerSocket serverSocket = new ServerSocket(portMain)) {
			System.out.println("Server is listening on port " + portMain);

			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("New client connected");

				InputStream input = socket.getInputStream();
				BufferedReader incomingRequest = new BufferedReader(new InputStreamReader(input));

				OutputStream output = socket.getOutputStream();
				PrintWriter outgoingResponse = new PrintWriter(output, false);

				while(true) {
					String text = incomingRequest.readLine();
					if (text != null) {
						String[] arr = text.split(" ");
						Method handlerMethod = handler.getMethod(arr[0]);
						Object result = handler.invokeMethod(handlerMethod, Arrays.copyOfRange(arr, 1, arr.length));						
						outgoingResponse.append("Start Handling...\n");
						outgoingResponse.append(new Date().toString() + "\n");
						outgoingResponse.println(result.toString());
						outgoingResponse.flush();
					}
				}
			}
		} catch (ConnectException ex) {
			System.out.println("Server exception: " + ex.getMessage());
			System.exit(1);
		}
	}
}
