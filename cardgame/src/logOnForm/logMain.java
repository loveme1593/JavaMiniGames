package logOnForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import net.miginfocom.swing.*;
import com.jgoodies.forms.layout.*;
import javax.swing.GroupLayout.*;

//회원가입 메인
public class logMain extends JFrame {
	// id
	private JTextField textField;
	// 비번
	private JPasswordField passwordField;
	// 비번확인
	private JPasswordField passwordField_1;
	// 비밀번호 찾기 질문
	private JComboBox comboBox;
	// 비밀번호 찾기 답변
	private JTextField textField_4;
	// 확인 버튼
	private JButton btnNewButton;
	// 취소 버튼
	private JButton btnNewButton_1;
	// 아이디 중복 확인 버튼
	private JButton btnD;
	// Connection값
	private Connection con;
	// 아이디 중복검사 위한 arrayList
	private ArrayList<String> idList;
	// 아이디 중복확인해서 중복된게 아닌지 확인하기 위한 flag
	private boolean flag = false;
	private JTextField textField_1;

	public logMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("회원가입창");
		setSize(500, 500);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[432px]", "[511px]"));

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(new MigLayout("", "[56px][116px,grow]", "[][][][][][24px][]"));

		JLabel lblNewLabel_2 = new JLabel("\uC544\uC774\uB514(\uC911\uBCF5 \uBD88\uAC00)");
		panel_2.add(lblNewLabel_2, "cell 0 0,alignx trailing");

		textField = new JTextField();
		panel_2.add(textField, "cell 1 0,growx");
		textField.setColumns(10);

		btnD = new JButton("\uC911\uBCF5\uD655\uC778");
		btnD.addActionListener(new listenerAction());
		panel_2.add(btnD, "cell 1 1");

		JLabel label = new JLabel("\uB2C9\uB124\uC784");
		panel_2.add(label, "cell 0 2,alignx trailing");

		textField_1 = new JTextField();
		panel_2.add(textField_1, "cell 1 2,growx");
		textField_1.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		panel_2.add(lblNewLabel_3, "cell 0 3,alignx trailing");

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		panel_2.add(passwordField, "cell 1 3,growx");

		JLabel lblNewLabel_1 = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778");
		panel_2.add(lblNewLabel_1, "cell 0 4,alignx trailing");

		passwordField_1 = new JPasswordField();
		passwordField.setEchoChar('*');
		panel_2.add(passwordField_1, "cell 1 4,growx");

		JLabel lblNewLabel_4 = new JLabel("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30 \uC9C8\uBB38");
		panel_2.add(lblNewLabel_4, "cell 0 5,alignx trailing");

		// 비번 찾기 질문들
		String[] questions = { "선택", "당신이 가장 좋아하는 사람은?", "당신이 가장 좋아하는 장소는?", "가장 기억에 남는 물건은?" };
		comboBox = new JComboBox(questions);
		comboBox.addActionListener(new listenerAction());
		panel_2.add(comboBox, "cell 1 5,growx");

		JLabel lblNewLabel_5 = new JLabel("\uB2F5\uBCC0");
		panel_2.add(lblNewLabel_5, "cell 0 6,alignx trailing");

		textField_4 = new JTextField();
		panel_2.add(textField_4, "cell 1 6,growx");
		textField_4.setColumns(10);
		panel.add(panel_2, "cell 0 0,grow");

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);

		btnNewButton = new JButton("\uD655\uC778");
		btnNewButton.addActionListener(new listenerAction());
		panel_1.add(btnNewButton);

		btnNewButton_1 = new JButton("\uCDE8\uC18C");
		btnNewButton_1.addActionListener(new listenerAction());
		panel_1.add(btnNewButton_1);

		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		panel_3.add(lblNewLabel);
		// setDB(); ->디비에 테이블만들기용...
		setVisible(true);
	}

	// db연결, (기존 테이블 존재하는지 여부 확인 후 없으면 만들고 있으면 없애고 새로 만듬)->나중에 수정
	public void setDB() {
		// 기존 테이블이 존재하는 경우
		con = DBConnection.getConnection();
		String sql = "";
		Statement st;
		try {
			sql = "drop table logmain";
			st = con.createStatement();
			st.executeUpdate(sql);
			sql = "create table logmain (id varchar(10) primary key,nickname varchar(10) not null ,pw varchar(10) not null, question number(10) not null, answer varchar(10) not null)";
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// 테이블 없는 경우
			sql = "create table logmain (id varchar(10) primary key,nickname varchar(10), pw varchar(10) not null, question number(10) not null, answer varchar(10) not null)";
			try {
				st = con.createStatement();
				st.executeUpdate(sql);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		DBConnection.closeConnection(con);
	}

	public boolean insertHuman(Human h) {
		con = DBConnection.getConnection();
		try {
			String sql = "insert into logmain values(?,?,?,?,?)";
			PreparedStatement pps = con.prepareStatement(sql);
			pps.setString(1, h.getId());
			pps.setString(2, h.getNickName());
			pps.setString(3, h.getPw());
			pps.setInt(4, h.getNum());
			pps.setString(5, h.getAnswer());
			pps.executeUpdate();
			DBConnection.closeConnection(con);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DBConnection.closeConnection(con);
			return false;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new logMain();
	}

	public boolean checkId(String id) {
		con = DBConnection.getConnection();
		idList = new ArrayList();
		String sql = "select id from logmain";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				idList.add(rs.getString(1));
			}
			for (int a = 0; a < idList.size(); a++) {
				if (idList.get(a).equals(id)) {
					DBConnection.closeConnection(con);
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBConnection.closeConnection(con);
		return true;
	}

	class listenerAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Human h;
			String id = textField.getText();
			String nickname = textField_1.getText();
			String pw = passwordField.getText();
			String pwConfirm = passwordField_1.getText(); // 비밀번호 확인
			int choice = comboBox.getSelectedIndex();
			String answer = textField_4.getText();
			// 중복 검사
			if (e.getActionCommand().equals("중복확인")) {
				if (id.equals("")) {
					// 아이디 란이 공란인 경우
					JOptionPane.showMessageDialog(null, "빈칸없이 아이디를 작성해주세요");
				} else {
					// 아이디 란이 공란이 아닌 경우 중복값 검사
					if (checkId(id)) {
						JOptionPane.showMessageDialog(null, "해당 아이디로 회원 가입이 가능합니다.");
						flag = true;
					} else {
						JOptionPane.showMessageDialog(null, "아이디가 중복되었습니다.");
						textField.setText("");
					}
				}
			} else if (e.getActionCommand().equals("확인")) {
				if (flag) {
					// flag-> 중복확인이 제대로 안됐으면 false
					if (choice == 0) {
						// 콤보박스서 공란 선택한경우
						JOptionPane.showMessageDialog(null, "비밀번호 찾기 답변을 선택해주세요!");
					} else {
						if (!pw.equals(pwConfirm)) {
							// 비밀번호와 확인 값이 일치하지 않는 경우
							JOptionPane.showMessageDialog(null, "비밀번호와 비밀번호 확인 값이 일치하지 않습니다.");
							passwordField.setText("");
							passwordField_1.setText("");
						} else {
							h = new Human(id, nickname, pw, choice, answer);
							if (insertHuman(h)) {
								JOptionPane.showMessageDialog(null, h.getId() + " 님 회원 가입 성공!");
								System.exit(0);
							} else {
								JOptionPane.showMessageDialog(null, "빠짐 없이 작성해주세요!");
							}
						}
					}
				} else {
					// flag=false; 인 경우
					JOptionPane.showMessageDialog(null, "아이디 중복 확인을 해주세요");
				}
			} else if (e.getActionCommand().equals("취소")) {
				JOptionPane.showMessageDialog(null, "회원 가입을 취소합니다.");
				System.exit(0);
			}
		}
	}

}
