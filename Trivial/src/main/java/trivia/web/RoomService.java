package trivia.web;
import trivia.domain.*;

public interface RoomService {
	returnMessage handleMessage(Message msg,int uid);
}
