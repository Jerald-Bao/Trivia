package trivia.domain;

public class paMessage extends returnMessage{
	private static String type="PlayerAnswer";
	private int ansUserId;
	private int point;
	private boolean gameOver;
	
	public static String getType() {
		return type;
	}

	public static void setType(String type) {
		paMessage.type = type;
	}

	public int getAnsUserId() {
		return ansUserId;
	}

	public void setAnsUserId(int ansUserId) {
		this.ansUserId = ansUserId;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
}
