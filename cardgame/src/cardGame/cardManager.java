package cardGame;

import javax.swing.*;

public class cardManager extends JButton {

	private ImageIcon front;
	private ImageIcon back;
	private int num;

	public cardManager(ImageIcon front, ImageIcon back, int num) {
		this.front = front;
		this.back = back;
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	// 0, front->ÇÇÄ«Ãò
	public JButton setImage(int choice, JButton button) {
		if (choice == 0) {
			button.setIcon(front);
		} else if (choice == 1) {
			button.setIcon(back);
		}
		return button;
	}

}
