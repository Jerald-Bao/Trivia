package trivia.domain;
import java.sql.Timestamp;

public class Message {
	private int roomId;
    private int fromId;
    private String fromName;
    private int[] toId= {0,0,0,0};
    private String operation;//roll,chooseAnswer,getHelp..
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

    public int[] getToId() {
        return toId;
    }

    public void setToId(int[] toId) {
        this.toId = toId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
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
                ", operation='" + operation + '\'' +
                ", messageDate=" + messageDate +
                '}';
    }

}
