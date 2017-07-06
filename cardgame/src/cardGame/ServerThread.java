package cardGame;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class ServerThread implements Runnable {

	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private ArrayList<ObjectOutputStream> oosList;
	private ArrayList<String> startList;
	private String protocol;

	public ServerThread(ObjectInputStream ois, ObjectOutputStream oos, ArrayList<ObjectOutputStream> oosList,
			ArrayList<String> startList) {
		this.ois = ois;
		this.oos = oos;
		this.oosList = oosList;
		this.startList = startList;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean truth = true;
		while (truth) {
			try {
				Object[] obj = (Object[]) ois.readObject();
				protocol = (String) obj[0];
				if (protocol.equals("start")) {
					if (startList.size() == oosList.size()) {
						startList.remove(protocol);
						if (startList.size() == 0) {
							obj[0] = "startAll";
						}
					} else {
						startList.add(protocol);
						if (startList.size() == oosList.size()) {
							obj[0] = "startAll";
						}
					}
				}
				for (ObjectOutputStream oos : oosList) {
					oos.writeObject(obj);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				truth = false;
				oosList.remove(oos);
			}
		}
	}

}
