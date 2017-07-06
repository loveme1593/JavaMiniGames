package searchPW;

import java.sql.*;
import java.util.*;
import logOnForm.*;

public class pwManager {

	private Connection con;
	private ArrayList<Human> hList;

	public String findPW(Human h) {
		hList = getList();
		Human hCon = null;
		con = DBConnection.getConnection();
		for (int a = 0; a < hList.size(); a++) {
			if (hList.get(a).getId().equals(h.getId())) {
				hCon = hList.get(a);
			}
		}
		if (hCon != null) {
			if (hCon.getNum() == h.getNum() && hCon.getAnswer().equals(h.getAnswer())) {
				return hCon.getPw();
			}
		}
		return null;
	}

	public ArrayList<Human> getList() {
		con = DBConnection.getConnection();
		hList = new ArrayList();
		Human h;
		try {
			String sql = "select* from logmain";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String id = rs.getString(1);
				String nickname = rs.getString(2);
				String pw = rs.getString(3);
				int question = rs.getInt(4);
				String answer = rs.getString(5);
				h = new Human(id, nickname, pw, question, answer);
				hList.add(h);
			}
			DBConnection.closeConnection(con);
			return hList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBConnection.closeConnection(con);
		return null;
	}

	// 해당하는 아이디가 있는지 찾기
	public Human checkID(String id) {
		hList = getList();
		for (int a = 0; a < hList.size(); a++) {
			if (hList.get(a).getId().equals(id)) {
				return hList.get(a);
			}
		}
		return null;
	}
}
