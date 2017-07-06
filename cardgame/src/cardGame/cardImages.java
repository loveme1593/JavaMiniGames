package cardGame;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//ī���ư ����
public class cardImages {
	// �̹����� ������ ��̸���Ʈ
	private ArrayList<ImageIcon> iconList = new ArrayList();
	// ������ ������ ��̸���Ʈ
	private ArrayList<Integer> ranList;
	private int ranNum;
	private ArrayList<cardManager> btnMList;

	// iconList�����
	public ArrayList<ImageIcon> makeList() {
		iconList.add(new ImageIcon("image/뒷면.jpg"));
		iconList.add(new ImageIcon("image/1.jpg"));
		iconList.add(new ImageIcon("image/2.jpg"));
		iconList.add(new ImageIcon("image/3.jpg"));
		iconList.add(new ImageIcon("image/4.jpg"));
		iconList.add(new ImageIcon("image/5.jpg"));
		iconList.add(new ImageIcon("image/6.jpg"));
		iconList.add(new ImageIcon("image/7.jpg"));
		iconList.add(new ImageIcon("image/8.jpg"));
		iconList.add(new ImageIcon("image/9.jpg"));
		iconList.add(new ImageIcon("image/10.jpg"));
		iconList.add(new ImageIcon("image/11.jpg"));
		iconList.add(new ImageIcon("image/12.jpg"));
		return iconList;
	}

	// // ��ư ������ �� ��Ÿ�� �̹��� ����
	// public ImageIcon makeRealUseImage(int choice, ArrayList<JButton> btnList)
	// {
	// iconList = makeList();
	// for (int a = 0; a < iconList.size(); a++) {
	// Image img = iconList.get(a).getImage();
	// Image newImg = img.getScaledInstance(btnList.get(1).getWidth(),
	// btnList.get(1).getHeight(),
	// java.awt.Image.SCALE_SMOOTH);
	// iconList.set(a, new ImageIcon(newImg));
	// }
	// for (int a = 0; a < iconList.size(); a++) {
	// if (choice == a) {
	// return iconList.get(a);
	// }
	// }
	// return null;
	// }

	// �������� �迭 �����
	public ArrayList<Integer> makeRanNum(int menuChoice) {
		ranList = new ArrayList();
		boolean flag = true;
		// �ʱ�: 1���� 6���� ���� ����, �߱�: 1���� 8����, ���: 1���� 12����
		int num = 0;
		if (menuChoice == 0) {
			num = 6;
		} else if (menuChoice == 1) {
			num = 8;
		} else if (menuChoice == 2) {
			num = 12;
		}
		while (ranList.size() != num) {
			ranNum = (int) ((Math.random()) * num) + 1;
			// �ߺ����� �ʴ� ���� ������ �������� ��̸���Ʈ�� ����
			if (checkNum(ranNum) == true) {
				ranList.add(ranNum);
			}
		}
		ranList.add(1);
		ranList.add(2);
		ranList.add(3);
		ranList.add(4);
		ranList.add(5);
		ranList.add(6);
		if (menuChoice != 0) {
			ranList.add(7);
			ranList.add(8);
		}
		if (menuChoice == 2) {
			ranList.add(9);
			ranList.add(10);
			ranList.add(11);
			ranList.add(12);
		}
		return ranList;
	}

	// �ߺ��� �˻�
	public boolean checkNum(int ranNum) {
		for (int a = 0; a < ranList.size(); a++) {
			if (ranList.get(a) == ranNum) {
				return false;
			}
		}
		return true;
	}

	// // ī�尪 ����
	// public ArrayList<cardManager> makeReset(ArrayList<Integer> ranList, int
	// menuChoice) {
	// btnMList = new ArrayList();
	// ranList = makeRanNum(menuChoice);
	// for (int a = 0; a < btnListSize; a++) {
	// btnMList.add(new cardManager(makeImage(0), makeImage(ranList.get(a)),
	// ranList.get(a)));
	// }
	// return btnMList;
	// }
}
