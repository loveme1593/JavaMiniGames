package searchPW;

import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

import logOnForm.DBConnection;
import logOnForm.Human;

import java.awt.*;
import net.miginfocom.swing.MigLayout;

//비밀번호 찾기 메인(gui도 여기서 뜸)
public class pwMain extends JFrame {
	// 아이디
	private JTextField textField;
	// 비번
	private JTextField textField_1;
	// 콤보박스
	private JComboBox comboBox;
	// 아이디 확인을 위한 플래그
	private boolean flag = false;
	// 디비와의 커넥션을 위한
	private Connection con;
	private pwManager pw;

	public pwMain() {
		pw = new pwManager();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("비밀번호 찾기");
		setSize(382, 251);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 15));
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new MigLayout("", "[282px,grow]", "[][][][][][][]"));

		JLabel lblNewLabel_1 = new JLabel("\uCC3E\uACE0\uC790 \uD558\uB294 \uC544\uC774\uB514");
		panel_1.add(lblNewLabel_1, "flowx,cell 0 0");

		textField = new JTextField();
		panel_1.add(textField, "cell 0 0");
		textField.setColumns(10);

		JButton btnNewButton = new JButton("\uC544\uC774\uB514 \uD655\uC778");
		btnNewButton.addActionListener(new listenerAction());
		panel_1.add(btnNewButton, "cell 0 1,alignx center");

		JLabel lblNewLabel_2 = new JLabel("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30 \uC9C8\uBB38");
		panel_1.add(lblNewLabel_2, "flowx,cell 0 2");

		String[] questions = { "선택", "당신이 가장 좋아하는 사람은?", "당신이 가장 좋아하는 장소는?", "가장 기억에 남는 물건은?" };
		comboBox = new JComboBox(questions);
		comboBox.addActionListener(new listenerAction());
		panel_1.add(comboBox, "cell 0 2,alignx right");

		JLabel label = new JLabel("\uBE44\uBC00\uBC88\uD638 \uCC3E\uAE30 \uB2F5\uBCC0");
		panel_1.add(label, "flowx,cell 0 3");

		textField_1 = new JTextField();
		panel_1.add(textField_1, "cell 0 3");
		textField_1.setColumns(10);

		JButton btnNewButton_1 = new JButton("\uD655\uC778");
		btnNewButton_1.addActionListener(new listenerAction());
		panel_1.add(btnNewButton_1, "flowx,cell 0 5,alignx center");

		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new pwMain();
	}

	class listenerAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String id = textField.getText();
			int choice = comboBox.getSelectedIndex();
			String answer = textField_1.getText();
			if (e.getActionCommand().equals("아이디 확인")) {
				if (pw.checkID(id) != null) {
					JOptionPane.showMessageDialog(null, id + " 로의 비밀 번호 찾기가 가능합니다.");
					// 아이디 체크가 정상적으로 이루어질 경우 플래그는 트루
					flag = true;
				} else {
					JOptionPane.showMessageDialog(null, "해당 아이디가 존재하지 않습니다.");
					textField.setText("");
					textField_1.setText("");
					comboBox.setSelectedIndex(0);
				}
			} else if (e.getActionCommand().equals("확인")) {
				// 아이디 체크가 정상적으로 이루어진 경우에만 찾기 진행(아이디가 존재하는 경우)
				if (flag) {
					Human h = new Human(id, choice, answer);
					String password = pw.findPW(h);
					if (password != null) {
						JOptionPane.showMessageDialog(null, "비밀번호는 " + password + " 입니다.");
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호 질문과 답변을 확인해주세요.");
						textField_1.setText("");
						comboBox.setSelectedIndex(0);
					}
				} else {
					JOptionPane.showMessageDialog(null, "아이디 확인부터 해주세요.");
					textField.setText("");
					textField_1.setText("");
					comboBox.setSelectedIndex(0);
				}
			}
		}

	}

}
