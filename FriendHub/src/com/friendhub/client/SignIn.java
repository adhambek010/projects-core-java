package com.friendhub.client;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SignIn {

	private Socket socket;
	private PrintWriter writer;
	private Scanner reader;
	private FileReader fr;
	private Scanner sc;
	private String uFName;

	public SignIn(Socket socket) {
		try {
			this.socket = socket;
			this.writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			this.reader = new Scanner(new InputStreamReader(socket.getInputStream()));
			this.sc = new Scanner(System.in);
		} catch (IOException e) {
			shutDown();
		}
	}

	public void signIn() {
		try {
			System.out.println("Enter your user id : ");
			String userID = sc.nextLine();
			System.out.println("Enter your pin : ");
			String pwd = sc.nextLine();

			String fileName = userID + ".txt";

			String fileLocationName = SignUp.fileLocation + fileName;
			fr = new FileReader(fileLocationName);
			StringBuilder data = new StringBuilder();
			int val = fr.read();
			while (val != -1) {
				data.append((char) val);
				val = fr.read();
			}
			String[] strArray = data.toString().split(",");
			String storedPwd = strArray[strArray.length - 1];
			String storedUuid = strArray[0];

			if (storedPwd != null) {
				boolean isPinValid = validatePin(pwd, storedPwd);
				if (isPinValid) {
					uFName = strArray[1];
					String uLName = strArray[2];
					byte[] pinHash = hashPassword(storedPwd);

					User user = new User(uFName, uLName, storedUuid, pinHash, socket);
					SignUp signUp = new SignUp(socket);
					signUp.getUsers().add(user);
					signUp.getUsersuuids().put(user, storedUuid);

					// TODO: Login the user. uuid, fName, lName, pwd
					if (!isPinValid) {
					    System.out.println("Your password did not match, please try again");
					}
					System.out.println("You loged in");
				}
			} else {
				System.out.println("Your password did not match, please try again");
				System.exit(1);
				this.signIn();
			}

		} catch (IOException e) {
			System.out.println("Invalid user Id or User Password\n" + "Please try again");
			shutDown();
		}
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

	/**
	 * Check whether a given pin matches the true User password.
	 *
	 * @param inputpwd The password which entered by the user.
	 * @param savedpwd The password which saved reader the file.
	 * @return Whether the password is a match or not.
	 */
	public boolean validatePin(String inputpwd, String savedpwd) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			return MessageDigest.isEqual(md.digest(inputpwd.getBytes()), (savedpwd).getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			shutDown();
		}
		return false;
	}

	private void shutDown() {
		try {
			if (reader != null)
				reader.close();
			if (fr != null)
				fr.close();
			if (writer != null)
				writer.close();
			if (socket != null)
				socket.close();
			if (sc != null)
				sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the socket
	 */
	public Socket getSocket() {
		return socket;
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
	 * @return the fr
	 */
	public FileReader getFr() {
		return fr;
	}

	/**
	 * @return the sc
	 */
	public Scanner getSc() {
		return sc;
	}

	/**
	 * @return the uFName
	 */
	public String getuFName() {
		return uFName;
	}
	
}
