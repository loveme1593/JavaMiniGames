package logOnForm;

//human 객체
public class Human {

	private String id;
	private String nickName;
	private String pw;
	// 비번찾기 질문 선택 번호(combobox로 질문 받아 인트값으로 번호 저장)
	private int num;
	// 비번찾기 답변
	private String answer;

	public Human(String id, int num, String answer) {
		this.id = id;
		this.num = num;
		this.answer = answer;
	}

	public Human(String id, String nickName, String pw, int num, String answer) {
		super();
		this.id = id;
		this.nickName = nickName;
		this.pw = pw;
		this.num = num;
		this.answer = answer;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "id=" + id + ", pw=" + pw + ", num=" + num + ", answer=" + answer;
	}

}
