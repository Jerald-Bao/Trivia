package trivia.domain;

public class paMessage extends returnMessage{
	private int ansUserPos;
	private int playersAnswer;
	private int correctAnswer;
	private int point;
	private boolean gameOver;

	public int getAnsUserPos() {
		return ansUserPos;
	}

	public void setAnsUserPos(int ansUserPos) {
		this.ansUserPos = ansUserPos;
	}

	public int getPlayersAnswer() {
		return playersAnswer;
	}

	public void setPlayersAnswer(int playersAnswer) {
		this.playersAnswer = playersAnswer;
	}

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
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
