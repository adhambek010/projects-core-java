package com.friendhub.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client implements Runnable {
	private Socket socket;
	private Scanner sc;
	private PrintWriter writer;
	private BufferedReader reader;
	private ExecutorService exeService;
	private SignUp signUp;
	private SignIn signIn;
	private Input input;
	private volatile boolean done = false;

	public static void main(String[] args) {
		Client client = new Client();
		Thread t = new Thread(client);
		t.start();
	}

	@Override
	public void run() {
		try {
			socket = new Socket("127.0.0.1", 2301);
			System.out.println("Debug: Client is connected successfully");

			writer = new PrintWriter(socket.getOutputStream(), true);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			sc = new Scanner(System.in);
			exeService = Executors.newCachedThreadPool();

			signUp = new SignUp(socket);
			signIn = new SignIn(socket);
			input = new Input();
			System.out.println(
					"\tWelcome to FriendHub, my first Java Socket "
					+ "application!\n" + " 1) SignUp\n 2) SignIn");
			int ans;
			do {
				ans = sc.nextInt();
				sc.nextLine();

				switch (ans) {
				case 1:
					signUp.signUp();
					break;
				case 2:
					signIn.signIn();
					break;
				}
			} while (ans != 1 && ans != 2);

			Thread thread = new Thread(input);
			thread.start();
			while (socket.isConnected() && !(socket.isClosed())) {
				String inMsg = reader.readLine();
				if (inMsg != null&& !inMsg.equals("")) {
					System.out.println("Message from users : " + inMsg);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			shutDown();
		}
	}

	private void shutDown() {
		try {
			if (socket != null && !socket.isClosed())
				this.socket.close();
			if (writer != null)
				this.writer.close();
			if (exeService != null)
				this.exeService.shutdownNow();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	class Input implements Runnable {
		private Scanner sc;

		@Override
		public void run() {
			sc = new Scanner(System.in);
			while (!done) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.print("Your message : ");
				String msg = sc.nextLine();
				if (msg.equals("/quit")) {
					sc.close();
					shutDown();
				} else {
					writer.println(msg);
				}
			}
		}
	}
}
