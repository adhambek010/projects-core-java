package com.friendhub.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server implements Runnable {

	public static final String fileLocation = "C:\\CoreJavaProjects\\FriendHub\\UserData\\";
	private ServerSocket serverSocket;
	private Socket socket;
	private ArrayList<ConnectionHandler> connectionList;
	private volatile boolean done;

	public Server() {
		connectionList = new ArrayList<>(5);
		done = false;
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.run();
	}

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(2301);
			System.out.println("Server is started");
			ExecutorService executorService = Executors.newCachedThreadPool();
			while (!done) {
				System.out.println("Server is waiting for client request");
				socket = serverSocket.accept();
				System.out.println("Client is connected");
				ConnectionHandler connectionHandler = new ConnectionHandler(socket);
				connectionList.add(connectionHandler);
				executorService.execute(connectionHandler);
			}
		} catch (IOException e) {
			e.printStackTrace();
			shutDown();
		}
	}

	/**
	 * Broadcast the certain type of message through all users.
	 *
	 * @param message which should send through the platform.
	 */
	/**
	 * Broadcast the certain type of message through all users, excluding the sender.
	 *
	 * @param sender   the ConnectionHandler of the client sending the message.
	 * @param message  the message to be broadcasted.
	 */
	public void broadcast(ConnectionHandler sender, String message) {
	    try {
	        for (ConnectionHandler c : connectionList) {
	            if (c != null && c != sender) {
	                c.send(message);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        shutDown();
	    }
	}


	public void shutDown() {
		try {
			this.done = true;
			if (serverSocket != null && !(serverSocket.isClosed()))
				this.serverSocket.close();
			if (socket != null)
				this.socket.close();
			for (ConnectionHandler c : connectionList) {
				c.shutDown();
			}
		} catch (IOException e) {
			shutDown();
		}
	}

	class ConnectionHandler implements Runnable {

		private Socket socket;
		private PrintWriter writer;
		private BufferedReader reader;

		ConnectionHandler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				writer = new PrintWriter(socket.getOutputStream(), true);
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				do {
					try {
						String message = reader.readLine();
						if (message != null) {
							broadcast(this, message);
						}

					} catch (IOException e) {
						e.printStackTrace();
						shutDown();
					}
				} while (true);
			} catch (Exception e) {
				System.err.println(e.toString());
				e.printStackTrace();
				shutDown();
			}
		}

		public void send(String message) {
			writer.println(message);
			writer.flush();
		}

		public void shutDown() {
			try {
				if (socket != null && !socket.isClosed())
					this.socket.close();
				if (writer != null)
					this.writer.close();
				if (reader != null)
					this.reader.close();

			} catch (IOException e) {
				System.err.println(e.toString());
				e.printStackTrace();
			}
		}
	}
}
