package cardGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Timer;

import net.miginfocom.swing.*;
import com.jgoodies.forms.layout.*;
import com.sun.jmx.snmp.tasks.ThreadService;

public class cardGameGUI extends JFrame {
	private JLabel label;
	private JButton btnNewButton_1;
	private JButton btnNewButton_4;
	private makeTimer timer;
	// 버튼 너무 많으니 관리 쉽게 어레이리스트에 넣음
	private ArrayList<JButton> btnList = new ArrayList();
	private cardImages images = new cardImages();
	private ArrayList<Integer> ranList = new ArrayList();
	private ArrayList<cardManager> btnMList;
	private ArrayList<ImageIcon> iconList = new ArrayList();
	// 카드 뒤집기에 필요한 요소들(리스너 안에)
	private int[] con = new int[2];
	private int[] nums = new int[2];
	private int count = 0;// 카드 뒤집는 횟수
	private int wins = 0; // 이길때 횟수
	// 초급,중급,고급 선택할 변수
	private int menuChoice;
	// 난이도에 따른 승리 횟수 설정
	private int winNum;
	private JPanel panel_5;

	// 채팅+통신
	private JTextField textField;
	private JTextArea textArea;
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private int winOther = 0; // 0: 시간제한스레드 그냥 돌아감, 1: 내가 승리
	private boolean winOtherFlag = true; // 내가 승리할 경우 false

	private String nickname = "";

	public cardGameGUI(int menuChoice, String nickname) {
		this.nickname = nickname;
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				Object[] obj = { "message", "상대방이 종료하였습니다.\n" };
				try {
					oos.writeObject(obj);
					System.exit(0);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});

		// choice=0: 초급, 1:중급, 2: 고급
		this.menuChoice = menuChoice;
		if (menuChoice == 0) {
			winNum = 6;
		} else if (menuChoice == 1) {
			winNum = 8;
		} else if (menuChoice == 2) {
			winNum = 12;
		}
		setSize(816, 687);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblNewLabel = new JLabel("\uCE74\uB4DC\uAC8C\uC784");
		panel.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("\uC81C\uD55C \uC2DC\uAC04:");
		panel.add(lblNewLabel_2);

		label = new JLabel("");
		panel.add(label);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);

		btnNewButton_1 = new JButton("\uC2DC\uC791");
		btnNewButton_1.addActionListener(new cardListener());
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_1.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("\uC885\uB8CC");
		btnNewButton_2.addActionListener(new cardListener());
		panel_1.add(btnNewButton_2);

		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));

		panel_5 = new JPanel();
		panel_3.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnNewButton_3 = new JButton();
		panel_5.add(btnNewButton_3);
		btnNewButton_3.setActionCommand("0");

		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 0, 0, 0));

		btnNewButton_4 = new JButton();
		panel_6.add(btnNewButton_4);
		btnNewButton_4.setActionCommand("1");

		JPanel panel_7 = new JPanel();
		panel_3.add(panel_7);
		panel_7.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_5 = new JButton();
		panel_7.add(btnNewButton_5);
		btnNewButton_5.setActionCommand("2");

		JPanel panel_8 = new JPanel();
		panel_3.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_6 = new JButton();
		panel_8.add(btnNewButton_6);
		btnNewButton_6.setActionCommand("3");

		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 4, 0, 0));

		JPanel panel_9 = new JPanel();
		panel_4.add(panel_9);
		panel_9.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_7 = new JButton();
		panel_9.add(btnNewButton_7);
		btnNewButton_7.setActionCommand("4");

		JPanel panel_10 = new JPanel();
		panel_4.add(panel_10);
		panel_10.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_8 = new JButton();
		panel_10.add(btnNewButton_8);
		btnNewButton_8.setActionCommand("5");

		JPanel panel_11 = new JPanel();
		panel_4.add(panel_11);
		panel_11.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_9 = new JButton();
		panel_11.add(btnNewButton_9);
		btnNewButton_9.setActionCommand("6");

		JPanel panel_12 = new JPanel();
		panel_4.add(panel_12);
		panel_12.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_10 = new JButton();
		panel_12.add(btnNewButton_10);
		btnNewButton_10.setActionCommand("7");

		JPanel panel_13 = new JPanel();
		panel_2.add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));

		JPanel panel_14 = new JPanel();
		panel_13.add(panel_14, BorderLayout.CENTER);
		panel_14.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_15 = new JPanel();
		panel_14.add(panel_15);
		panel_15.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_17 = new JPanel();
		panel_15.add(panel_17);
		panel_17.setLayout(new GridLayout(0, 1, 0, 0));

		JButton btnNewButton = new JButton();
		panel_17.add(btnNewButton);
		btnNewButton.setActionCommand("8");

		JPanel panel_18 = new JPanel();
		panel_15.add(panel_18);
		panel_18.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_11 = new JButton();
		panel_18.add(btnNewButton_11);
		btnNewButton_11.setActionCommand("9");

		JPanel panel_16 = new JPanel();
		panel_14.add(panel_16);
		panel_16.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_19 = new JPanel();
		panel_16.add(panel_19);
		panel_19.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_12 = new JButton();
		panel_19.add(btnNewButton_12);
		btnNewButton_12.setActionCommand("10");

		JPanel panel_20 = new JPanel();
		panel_16.add(panel_20);
		panel_20.setLayout(new GridLayout(1, 0, 0, 0));

		JButton btnNewButton_13 = new JButton();
		panel_20.add(btnNewButton_13);
		btnNewButton_13.setActionCommand("11");
		timer = new makeTimer();

		// ArrayList에 버튼 넣기
		btnList.add(btnNewButton_3);
		btnList.add(btnNewButton_4);
		btnList.add(btnNewButton_5);
		btnList.add(btnNewButton_6);
		btnList.add(btnNewButton_7);
		btnList.add(btnNewButton_8);
		btnList.add(btnNewButton_9);
		btnList.add(btnNewButton_10);
		btnList.add(btnNewButton);
		btnList.add(btnNewButton_11);
		btnList.add(btnNewButton_12);
		btnList.add(btnNewButton_13);

		// 초급(기본)

		if (menuChoice != 0) {
			JPanel panel_21 = new JPanel();
			panel_2.add(panel_21);
			panel_21.setLayout(new GridLayout(0, 2, 0, 0));

			JPanel panel_24 = new JPanel();
			panel_21.add(panel_24);
			panel_24.setLayout(new GridLayout(1, 0, 0, 0));

			JPanel panel_26 = new JPanel();
			panel_24.add(panel_26);
			panel_26.setLayout(new GridLayout(1, 0, 0, 0));

			JButton btnNewButton_14 = new JButton();
			panel_26.add(btnNewButton_14);
			btnNewButton_14.setActionCommand("12");

			JPanel panel_27 = new JPanel();
			panel_24.add(panel_27);
			panel_27.setLayout(new GridLayout(1, 0, 0, 0));

			JButton btnNewButton_15 = new JButton();
			panel_27.add(btnNewButton_15);
			btnNewButton_15.setActionCommand("13");

			JPanel panel_25 = new JPanel();
			panel_21.add(panel_25);
			panel_25.setLayout(new GridLayout(1, 0, 0, 0));

			JPanel panel_28 = new JPanel();
			panel_25.add(panel_28);
			panel_28.setLayout(new GridLayout(1, 0, 0, 0));

			JButton btnNewButton_16 = new JButton();
			panel_28.add(btnNewButton_16);
			btnNewButton_16.setActionCommand("14");

			JPanel panel_29 = new JPanel();
			panel_25.add(panel_29);
			panel_29.setLayout(new GridLayout(1, 0, 0, 0));

			JButton btnNewButton_17 = new JButton();
			panel_29.add(btnNewButton_17);
			btnNewButton_17.setActionCommand("15");

			btnList.add(btnNewButton_14);
			btnList.add(btnNewButton_15);
			btnList.add(btnNewButton_16);
			btnList.add(btnNewButton_17);
			// 중급
		}
		if (menuChoice == 2) {
			JPanel panel_22 = new JPanel();
			panel_2.add(panel_22);
			panel_22.setLayout(new GridLayout(1, 0, 0, 0));

			JPanel panel_30 = new JPanel();
			panel_22.add(panel_30);
			panel_30.setLayout(new GridLayout(1, 0, 0, 0));

			JPanel panel_32 = new JPanel();
			panel_30.add(panel_32);
			panel_32.setLayout(new GridLayout(1, 0, 0, 0));

			JButton btnNewButton_18 = new JButton();
			panel_32.add(btnNewButton_18);
			btnNewButton_18.setActionCommand("16");

			JPanel panel_33 = new JPanel();
			panel_30.add(panel_33);
			panel_33.setLayout(new GridLayout(1, 0, 0, 0));

			JButton btnNewButton_19 = new JButton();
			panel_33.add(btnNewButton_19);
			btnNewButton_19.setActionCommand("17");

			JPanel panel_31 = new JPanel();
			panel_22.add(panel_31);
			panel_31.setLayout(new GridLayout(1, 0, 0, 0));

			JPanel panel_34 = new JPanel();
			panel_31.add(panel_34);
			panel_34.setLayout(new GridLayout(1, 0, 0, 0));

			JButton btnNewButton_20 = new JButton();
			panel_34.add(btnNewButton_20);
			btnNewButton_20.setActionCommand("18");

			JPanel panel_35 = new JPanel();
			panel_31.add(panel_35);
			panel_35.setLayout(new GridLayout(1, 0, 0, 0));

			JButton btnNewButton_21 = new JButton();
			panel_35.add(btnNewButton_21);
			btnNewButton_21.setActionCommand("19");

			JPanel panel_23 = new JPanel();
			panel_2.add(panel_23);
			panel_23.setLayout(new GridLayout(1, 0, 0, 0));

			JPanel panel_36 = new JPanel();
			panel_23.add(panel_36);
			panel_36.setLayout(new GridLayout(1, 0, 0, 0));

			JPanel panel_38 = new JPanel();
			panel_36.add(panel_38);
			panel_38.setLayout(new GridLayout(1, 0, 0, 0));

			JButton btnNewButton_22 = new JButton();
			panel_38.add(btnNewButton_22);
			btnNewButton_22.setActionCommand("20");

			JPanel panel_39 = new JPanel();
			panel_36.add(panel_39);
			panel_39.setLayout(new GridLayout(1, 0, 0, 0));

			JButton btnNewButton_23 = new JButton();
			panel_39.add(btnNewButton_23);
			btnNewButton_23.setActionCommand("21");

			JPanel panel_37 = new JPanel();
			panel_23.add(panel_37);
			panel_37.setLayout(new GridLayout(1, 0, 0, 0));

			JPanel panel_40 = new JPanel();
			panel_37.add(panel_40);
			panel_40.setLayout(new GridLayout(1, 0, 0, 0));

			JButton btnNewButton_24 = new JButton();
			panel_40.add(btnNewButton_24);
			btnNewButton_24.setActionCommand("22");

			JPanel panel_41 = new JPanel();
			panel_37.add(panel_41);
			panel_41.setLayout(new GridLayout(1, 0, 0, 0));

			JButton btnNewButton_25 = new JButton();
			panel_41.add(btnNewButton_25);
			btnNewButton_25.setActionCommand("23");

			btnList.add(btnNewButton_18);
			btnList.add(btnNewButton_19);
			btnList.add(btnNewButton_20);
			btnList.add(btnNewButton_21);
			btnList.add(btnNewButton_22);
			btnList.add(btnNewButton_23);
			btnList.add(btnNewButton_24);
			btnList.add(btnNewButton_25);
		}

		// textArea, textField 생성
		JPanel panel_42 = new JPanel();
		panel_2.add(panel_42);
		panel_42.setLayout(new BorderLayout(0, 0));

		JPanel panel_43 = new JPanel();
		panel_42.add(panel_43, BorderLayout.SOUTH);
		panel_43.setLayout(new GridLayout(0, 1, 0, 0));

		textField = new JTextField();
		panel_43.add(textField);
		textField.addActionListener(new textListener());
		textField.setColumns(10);

		JPanel panel_44 = new JPanel();
		panel_42.add(panel_44, BorderLayout.CENTER);
		panel_44.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_44.add(scrollPane);

		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		// 액션리스너달기, 카드 뒷면 이미지 생성
		for (int a = 0; a < btnList.size(); a++) {
			btnList.get(a).addActionListener(new cardListener());
		}
		// 시작버튼 누르기 전에 모든 버튼 비활성화
		closeButton();
		setVisible(true);
		// iconList=> 크기 자동조절하는 ImageIcons 저
		iconList = makeRealUseImage(menuChoice);
		Connection();
		Thread t = new Thread(new chatting());
		t.start();
	}

	// 채팅 스레드
	class chatting implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			boolean truth = true;
			while (truth) {
				try {
					Object[] obj = (Object[]) ois.readObject();
					String protocol = (String) obj[0];
					switch (protocol) {
					case "message":
						String message = (String) obj[1];
						textArea.append(message);
						break;
					case "win":
						winOther = (Integer) obj[1];
						// 상대방이 승리한 경우->winOtherFlag는 true
						// 내가 승리한 경우->winOtherFlag는 false->상대방이 승리하였습니다 창 띄우지 않음
						if (winOther == 1 && winOtherFlag) {
							JOptionPane.showMessageDialog(null, "상대방이 승리하였습니다.");
							closeButton();
						}
						break;
					case "startAll":
						// 서버스레드에서 startAll이란 객체를 받아왔을때 동시시작
						JOptionPane.showMessageDialog(null, "시작합니다.");
						wins = 0;
						setBackImage();
						btnMList = makeReset(images.makeRanNum(menuChoice), menuChoice);
						openButton();
						Thread t = new Thread(new myThread());
						t.start();
						btnNewButton_1.setEnabled(false);
						break;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					truth = false;
				}
			}

		}
	}

	// 채팅 위한 커넥션만들기
	public void Connection() {
		try {
			socket = new Socket("localhost", 9040);
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			Object[] obj = { "message", nickname + " 님 이 입장하였습니다.\n" };
			oos.writeObject(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 타이머 스레드
	class myThread implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			int timeNum = 0;
			if (menuChoice == 0) {
				timeNum = 30;
			} else if (menuChoice == 1) {
				timeNum = 40;
			} else if (menuChoice == 2) {
				timeNum = 60;
			}
			String time = timer.getTime(timeNum);
			// 경과시간 알기 위한 플래그
			int flag = 0;
			for (int a = timeNum; a >= 0; a--) {
				if (checkCloseButton()) {
					break;
				} // 버튼이 모두 닫혀있다면 종료
				time = timer.getTime(a);
				flag++;
				try {
					Thread.sleep(1000); // 1초씩 진행하도록 해줌
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				label.setText(time);
			}
			if (flag >= timeNum) {
				closeButton();
				int choice = JOptionPane.showConfirmDialog(null, "제한시간 만료! 다시 시작하시겠습니까?");
				if (choice == 1 || choice == 2) {
					JOptionPane.showMessageDialog(null, "종료합니다.");
					closeButton();
					Object[] obj = { "message", nickname + " 님 이 종료하였습니다.\n" };
					try {
						oos.writeObject(obj);
						System.exit(0);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.exit(0);
				} else {
					JOptionPane.showMessageDialog(null, "시작 버튼을 다시 눌러주세요.");
					btnNewButton_1.setEnabled(true);
				}
			}
		}
	}

	// 뒷면 이미지 붙이기
	public void setBackImage() {
		for (int a = 0; a < btnList.size(); a++) {
			btnList.get(a).setIcon(iconList.get(0));
		}
	}

	// 카드 닫기
	public void closeButton() {
		for (int a = 0; a < btnList.size(); a++) {
			btnList.get(a).setEnabled(false);
		}
	}

	// 카드값 리셋
	public ArrayList<cardManager> makeReset(ArrayList<Integer> ranList, int menuChoice) {
		btnMList = new ArrayList();
		ranList = images.makeRanNum(menuChoice);
		for (int a = 0; a < btnList.size(); a++) {
			btnMList.add(new cardManager(iconList.get(0), iconList.get(ranList.get(a)), ranList.get(a)));
		}
		return btnMList;
	}

	// 버튼 눌렀을 때 나타날 이미지 설정
	public ArrayList<ImageIcon> makeRealUseImage(int choice) {
		ArrayList<ImageIcon> iconList = images.makeList();
		for (int a = 0; a < iconList.size(); a++) {
			Image img = iconList.get(a).getImage();
			Image newImg = img.getScaledInstance(btnNewButton_4.getWidth(), btnNewButton_4.getHeight(),
					java.awt.Image.SCALE_SMOOTH);
			iconList.set(a, new ImageIcon(newImg));
		}
		return iconList;
	}

	public boolean checkCloseButton() {
		int nums = 0;
		for (int a = 0; a < btnList.size(); a++) {
			if (!btnList.get(a).isEnabled())
				nums++;
		}
		if (nums == btnList.size()) {
			return true; // 모든 버튼이 닫혀있음을 의미
		}
		return false;
	}

	// 카드 열기
	public void openButton() {
		for (int a = 0; a < btnList.size(); a++) {
			btnList.get(a).setEnabled(true);
		}
	}

	// 카드 뒤집기
	public void cardGame(int count, ActionEvent e) {
		// 각 카드번호에 따라 뒤집을때 이미지를 설정
		if (e.getActionCommand().equals("0")) {
			setBackImage(0, count);
		} else if (e.getActionCommand().equals("1")) {
			setBackImage(1, count);
		} else if (e.getActionCommand().equals("2")) {
			setBackImage(2, count);
		} else if (e.getActionCommand().equals("3")) {
			setBackImage(3, count);
		} else if (e.getActionCommand().equals("4")) {
			setBackImage(4, count);
		} else if (e.getActionCommand().equals("5")) {
			setBackImage(5, count);
		} else if (e.getActionCommand().equals("6")) {
			setBackImage(6, count);
		} else if (e.getActionCommand().equals("7")) {
			setBackImage(7, count);
		} else if (e.getActionCommand().equals("8")) {
			setBackImage(8, count);
		} else if (e.getActionCommand().equals("9")) {
			setBackImage(9, count);
		} else if (e.getActionCommand().equals("10")) {
			setBackImage(10, count);
		} else if (e.getActionCommand().equals("11")) {
			setBackImage(11, count);
		} else if (e.getActionCommand().equals("12")) {
			setBackImage(12, count);
		} else if (e.getActionCommand().equals("13")) {
			setBackImage(13, count);
		} else if (e.getActionCommand().equals("14")) {
			setBackImage(14, count);
		} else if (e.getActionCommand().equals("15")) {
			setBackImage(15, count);
		} else if (e.getActionCommand().equals("16")) {
			setBackImage(16, count);
		} else if (e.getActionCommand().equals("17")) {
			setBackImage(17, count);
		} else if (e.getActionCommand().equals("18")) {
			setBackImage(18, count);
		} else if (e.getActionCommand().equals("19")) {
			setBackImage(19, count);
		} else if (e.getActionCommand().equals("20")) {
			setBackImage(20, count);
		} else if (e.getActionCommand().equals("21")) {
			setBackImage(21, count);
		} else if (e.getActionCommand().equals("22")) {
			setBackImage(22, count);
		} else if (e.getActionCommand().equals("23")) {
			setBackImage(23, count);
		}
		// 2번째 뒤집은 카드일 경우
		if (count == 2) {
			// 이미지 저장 번호가 같은 경우
			if (con[0] == con[1] && con[0] != 0) {
				wins++;
				// 카드 맞춘 횟수가 6회일 경우
				if (wins == winNum) {
					winOtherFlag = false;
					JOptionPane.showMessageDialog(null, "축하합니다. 모든 카드를 찾았습니다.");
					closeButton();
					Object[] obj = { "win", 1 };
					try {
						oos.writeObject(obj);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} else {
				// 선택된 두 카드가 다를 경우->원 상태로 복구
				// 1초 있다가 카드 뒤집기******
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						btnList.get(nums[0]).setEnabled(true);
						btnList.get(nums[1]).setEnabled(true);
						btnList.get(nums[1]).setIcon(iconList.get(0));
						btnList.get(nums[0]).setIcon(iconList.get(0));
					}
				}, 500);
			}
		}
	}

	// 각 카드별로 카드 뒷면 설정하기
	public void setBackImage(int num, int count) {
		con[count - 1] = btnMList.get(num).getNum();
		nums[count - 1] = num;
		btnMList.get(num).setImage(1, btnList.get(num));
		btnList.get(num).setEnabled(false);
	}

	// 카드게임 리스너
	class cardListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals("시작")) {
				// 시작버튼 누를 경우 start를 서버로 보냄-> start가 두개 보내질 경우 시작
				Object[] obj = { "start" };
				try {
					oos.writeObject(obj);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// 시작버튼 누를 경우 시작 버튼 자체가 비활성화
				btnNewButton_1.setEnabled(false);
			} else if (e.getActionCommand().equals("종료")) {
				// 종료버튼을 누르면 모든 카드는 선택 불가능 상태로 바꿔줌
				closeButton();
				int choice = JOptionPane.showConfirmDialog(null, "해당 게임을 종료하고 새 게임을 불러오시겠습니까?");
				if (choice == 0) {
					// 다시 시작을 누른 경우 시작버튼을 활성화
					JOptionPane.showMessageDialog(null, "시작 버튼을 다시 눌러주세요.");
					closeButton();
					btnNewButton_1.setEnabled(true);
				} else if (choice == 1) {
					// 종료 누른 경우 창 끄기
					JOptionPane.showMessageDialog(null, "해당 게임을 종료합니다.");
					Object[] obj = { "message", "상대방이 종료하였습니다.\n" };
					try {
						oos.writeObject(obj);
						System.exit(0);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.exit(0);
				}
			} else if (e.getSource() instanceof JButton) {
				count++;
				cardGame(count, e);
				// count가 2일 때 0으로 초기화시켜줌
				if (count == 2) {
					count = 0;
				}
			}

		}
	}

	// 채팅 리스너
	class textListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String message = nickname + " 님:" + textField.getText() + "\n";
			if (e.getSource() instanceof JTextField && !message.equals("")) {
				Object[] obj = { "message", message };
				try {
					oos.writeObject(obj);
					textField.setText("");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

}
