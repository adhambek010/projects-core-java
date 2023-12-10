package com.friendhub.client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class SignUp {

	public static final String fileLocation = "C:\\CoreJavaProjects\\FriendHub\\UserData\\";
	private Socket socket;
	private HashMap<User, String> usersuuids = new HashMap<>(5, 0.99f);
	private ArrayList<String> fileNames = new ArrayList<>(5);
	private ArrayList<User> users = new ArrayList<>(5);
	private PrintWriter writer;
	private Scanner reader;
	private Scanner sc;
	private String fName;

	public SignUp(Socket socket) {
		try {
			this.socket = socket;
			this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			this.reader = new Scanner(new InputStreamReader(socket.getInputStream()));
			this.sc = new Scanner(System.in);
		} catch (IOException e) {
			e.printStackTrace();
			shutDown();
		}
	}

	public void signUp() {
		System.out.println("Enter your first name : ");
		fName = sc.nextLine();
		System.out.println("Enter your last name : ");
		String lName = sc.nextLine();
		System.out.println("Do you want an auto-generated password ?");
		System.out.println("Yes --> Y\nNo --> N : ");
		String ans = sc.nextLine();
		char c = ans.charAt(0);

		String password;
		if (c == 'Y' || c == 'y') {
			password = this.getNewAutoPassword();
		} else {
			password = inputPwd();
		}

		String uuid = uuid();
		System.out.println(String.format(
				"%s is your password. Please do not share it with anyone.\n%s is your Unique User ID. Please remember this.",
				password, uuid));
		saveUser(fName, lName, uuid, password);

		byte[] pinHash = hashPassword(password);
		User user = new User(fName, lName, uuid, pinHash, socket);
		usersuuids.put(user, uuid);
		users.add(user);
	}

	private byte[] hashPassword(String password) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			System.err.println(e.toString());
		}
		return digest.digest((password.getBytes()));
	}

	private String uuid() {
		Random rd = new Random();
		String uuid;

		do {
			uuid = "";
			for (int i = 0; i < 6; i++) {
				uuid += ((Integer) rd.nextInt(10)).toString();
			}
		} while (usersuuids.containsValue(uuid));

		return uuid;
	}

	public String getNewAutoPassword() {
		StringBuilder pwd = new StringBuilder();
		Random rd = new Random();
		String union = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm#$?1234567890";
		for (int i = 0; i < 8; i++) {
			pwd.append(union.charAt(rd.nextInt(union.length())));
		}
		return pwd.toString();
	}

	public String inputPwd() {
		String pwd;
		do {
			System.out.println("Enter your password (6 to 8 characters) : ");
			pwd = sc.nextLine().trim();
			if (pwd.length() < 6 || pwd.length() > 8) {
				System.out.println("Invalid input, password must be 6 to 8 characters long. Please try again.");
			}
		} while (pwd.length() < 6 || pwd.length() > 8);
		return pwd;
	}

	public void saveUser(String fName, String lName, String uuid, String pwd) {
		String fileName = fileLocation + uuid + ".txt";
		try (FileOutputStream fos = new FileOutputStream(fileName, false)) {
			fos.write(String.format("%s,%s,%s,%s", uuid, fName, lName, pwd).getBytes());
			fileNames.add(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void shutDown() {
		if (socket != null)
			sc.close();
		if (writer != null)
			writer.close();
		if (sc != null)
			sc.close();
		if (reader != null)
			reader.close();
	}

	/**
	 * @return the filelocation
	 */
	public static String getFilelocation() {
		return fileLocation;
	}

	/**
	 * @return the socket
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * @return the usersuuids
	 */
	public HashMap<User, String> getUsersuuids() {
		return usersuuids;
	}

	/**
	 * @return the fileNames
	 */
	public ArrayList<String> getFileNames() {
		return fileNames;
	}

	/**
	 * @return the users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * @return the writer
	 */
	public PrintWriter getWriter() {
		return writer;
	}

	/**
	 * @return the reader
	 */
	public Scanner getReader() {
		return reader;
	}

	/**
	 * @return the sc
	 */
	public Scanner getSc() {
		return sc;
	}

	/**
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}

	
	
}
