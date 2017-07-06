package cardGame;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;

public class cardGameMain {
//카드게임
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean flag = true; // 초급, 중급, 고급 이라는 단어 입력할때까지 계속 받음
		while (flag) {
			String nickname = JOptionPane.showInputDialog("원하는 닉네임을 입력해주세요.");
			String choice = JOptionPane.showInputDialog("초급, 중급, 고급 중 원하는 난이도를 입력해주세요");
			if (choice.equals("초급")) {
				JOptionPane.showMessageDialog(null, "초급 난이도를 선택하셨습니다(제한시간 30초)");
				new cardGameGUI(0, nickname);
				flag = false;
			} else if (choice.equals("중급")) {
				JOptionPane.showMessageDialog(null, "중급 난이도를 선택하셨습니다(제한시간 40초)");
				new cardGameGUI(1, nickname);
				flag = false;
			} else if (choice.equals("고급")) {
				JOptionPane.showMessageDialog(null, "고급 난이도를 선택하셨습니다(제한시간 60초)");
				new cardGameGUI(2, nickname);
				flag = false;
			} else {
				JOptionPane.showMessageDialog(null, "잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}

}
