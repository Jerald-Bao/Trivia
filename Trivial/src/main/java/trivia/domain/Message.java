package trivia.domain;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class Message {
	private int roomId;
    private int fromId;
    private String fromName;
    private ArrayList<Integer> toId;
    private String request;//roll,chooseAnswer,getHelp..
    private int from;//房间列表起始位置
    private int to;
    private String category;
    private int playersAnswer;
    private Timestamp messageDate;
    
    public Message() {
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int messageId) {
        this.roomId = messageId;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }
    public ArrayList<Integer> getToId() {
        return toId;
    }
	public void setToId(ArrayList<Integer> toId) {
		this.toId = toId;
	}
    public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public Timestamp getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Timestamp messageDate) {
        this.messageDate = messageDate;
    }

    @Override
    public String toString() {
        return "Message{" +
                "roomId=" + roomId +
                ", fromId=" + fromId +
                ", fromName='" + fromName + '\'' +
                ", toId=" + toId +
                ", request='" + request + '\'' +
                ", messageDate=" + messageDate +
                '}';
    }

	public int getPlayersAnswer() {
		return playersAnswer;
	}

	public void setPlayersAnswer(int playersAnswer) {
		this.playersAnswer = playersAnswer;
	}

}
