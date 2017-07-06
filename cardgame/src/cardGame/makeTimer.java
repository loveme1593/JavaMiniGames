package cardGame;

public class makeTimer {
	static String timerBuffer;

	// 초를 시간으로 변경해줌
	public String getTime(int secs) {
		int sec = secs % 60;
		int min = secs / 60;
		return timerBuffer = String.format("%02d:%02d", min, sec);
	}
}
