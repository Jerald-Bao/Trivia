package trivia.web;
import trivia.domain.*;

public interface GameService {
	returnMessage handleMessage(Message msg,gameRoom gr);
}
