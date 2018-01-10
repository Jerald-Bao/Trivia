//RoomList
package trivia.domain;

import java.util.List;

public class rlMessage extends returnMessage{
	private List<GameRoom> RoomList;
	
	public List<GameRoom> getRoomList() {
		return RoomList;
	}

	public void setRoomList(List<GameRoom> roomList) {
		RoomList = roomList;
	}
	

}
