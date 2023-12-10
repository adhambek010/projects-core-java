package com.friendhub.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class User implements Runnable {
	private String userFirstName;
	private String userLastName;
	private String uuid;
	private byte[] password;
	private Socket socket;
	private PrintWriter writer;
	private Scanner reader;
	private Scanner sc;

	/**
	 * Creates a new User object.
	 *
	 * @param userFirstName the first name of the user.
	 * @param userLastName  the last name of the user.
	 * @param uuid          the unique user id of the user.
	 * @param password      the password of the user.
	 */
	public User(String userFirstName, String userLastName, String uuid, byte[] password, Socket socket) {
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.uuid = uuid;
		this.password = hashPassword(password);
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			writer = new PrintWriter(socket.getOutputStream());
			reader = new Scanner(socket.getInputStream());
			sc = new Scanner(System.in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private byte[] hashPassword(byte[] password) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			System.err.println(e.toString());
		}
		return digest.digest(password);
	}

	public void connectToServer() {

	}

	@Override
	public String toString() {
		return String.format("UserFirstName='%s', UserLastName='%s', UUID='%s\n", userFirstName, userLastName, uuid);
	}

	/**
	 * @return the userFirstName
	 */
	public String getUserFirstName() {
		return userFirstName;
	}

	/**
	 * @param userFirstName the userFirstName to set
	 */

	public String getUserLastName() {
		return userLastName;
	}

	/**
	 * @param userLastName the userLastName to set
	 */
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @return the password
	 */
	public byte[] getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(byte[] password) {
		this.password = password;
	}

	/**
	 * @return the socket
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * @param socket the socket to set
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	/**
	 * @return the writer
	 */
	public PrintWriter getWriter() {
		return writer;
	}

	/**
	 * @param writer the writer to set
	 */
	public void setWriter(PrintWriter writer) {
		this.writer = writer;
	}

	/**
	 * @return the reader
	 */
	public Scanner getReader() {
		return reader;
	}

	/**
	 * @param reader the reader to set
	 */
	public void setReader(Scanner reader) {
		this.reader = reader;
	}

	/**
	 * @return the sc
	 */
	public Scanner getSc() {
		return sc;
	}

	/**
	 * @param sc the sc to set
	 */
	public void setSc(Scanner sc) {
		this.sc = sc;
	}

}
