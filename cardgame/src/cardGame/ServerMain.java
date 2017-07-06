package cardGame;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerMain {

	private ServerSocket ss;
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private ArrayList<ObjectOutputStream> oosList = new ArrayList();
	private ServerThread st;
	private ArrayList<String> startList = new ArrayList();

	public ServerMain() {
		try {
			ss = new ServerSocket(9040);
			while (true) {
				System.out.println("waiting");
				socket = ss.accept();
				System.out.println("connected");
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
				oosList.add(oos);
				st = new ServerThread(ois, oos, oosList, startList);
				Thread t = new Thread(st);
				t.start();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ServerMain();
	}

}
