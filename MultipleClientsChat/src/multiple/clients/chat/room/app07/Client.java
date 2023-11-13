package multiple.clients.chat.room.app07;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String uName;

    public Client(Socket socket, String uName) {
        try {
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter((socket.getOutputStream())));
            this.uName = uName;
        }catch(IOException e){
            closeEverything(socket, reader, writer);
        }
    }

    public void sendMessage(){
        try{
            writer.write(uName);
            writer.newLine();
            writer.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()){
                String messageToSend = scanner.nextLine();
                writer.write(uName + " : " + messageToSend);
                writer.newLine();
                writer.flush();
            }
        }catch (IOException e){
            e.printStackTrace();
            closeEverything(socket, reader, writer);
        }
    }
    public void listenForMessages(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;
                while (socket.isConnected()) {
                    try {
                        msgFromGroupChat = reader.readLine();
                        System.out.println(msgFromGroupChat);
                    } catch (IOException e) {
                        closeEverything(socket, reader, writer);
                    }
                }

            }
        }).start();
    }
    public void closeEverything(Socket socket, BufferedReader reader, BufferedWriter writer){
        try {
            if(socket != null){
                socket.close();
            }
            if(reader != null){
                reader.close();
            }
            if (writer != null){
                writer.close();
            }
        }catch (IOException e){
            e.getMessage();
        }
    }

    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your user name for the Group Chat : ");
        String uName = scanner.nextLine();
        Socket socket = new Socket("localhost",8080);
        Client client = new Client(socket, uName);
        client.listenForMessages();
        client.sendMessage();

    }
}
