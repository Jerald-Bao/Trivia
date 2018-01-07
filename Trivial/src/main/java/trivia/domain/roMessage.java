package trivia.domain;

public class roMessage extends returnMessage{
	private static String type="Roll";
	private int rollUserId;
	private int rollNum;
	private Question question;
	private int location;
	
	
	public static String getType() {
		return type;
	}
	public static void setType(String type) {
		roMessage.type = type;
	}
	public int getRollUserId() {
		return rollUserId;
	}
	public void setRollUserId(int rollUserId) {
		this.rollUserId = rollUserId;
	}
	public int getRollNum() {
		return rollNum;
	}
	public void setRollNum(int rollNum) {
		this.rollNum = rollNum;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}	
	
}
