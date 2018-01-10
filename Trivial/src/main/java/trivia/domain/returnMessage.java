package trivia.domain;

import java.util.ArrayList;

public class returnMessage {

	private int fromId;
    private ArrayList<Integer> toId=new ArrayList<Integer>();
    private boolean result;
    private String type;
    
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public returnMessage(){
		
	}
	public int getFromId() {
		return fromId;
	}
	public void setFromId(int fromId) {
		this.fromId = fromId;
	}
	public ArrayList<Integer> getToId() {
		return toId;
	}
	public void setToId(ArrayList<Integer> toId) {
		this.toId = toId;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
}
