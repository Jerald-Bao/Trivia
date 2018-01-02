package trivia.web;
import trivia.domain.*;

public interface gameService {
	returnMessage handleMessage(Message msg,gameRoom gr);
}
