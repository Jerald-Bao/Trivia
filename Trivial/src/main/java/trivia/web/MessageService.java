package trivia.web;
import trivia.domain.*;

public interface MessageService {
	returnMessage handleMessage(Message msg,int uid);
}
