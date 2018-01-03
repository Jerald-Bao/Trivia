package trivia.web;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import trivia.dao.*;
import trivia.domain.*;
import trivia.domain.returnMessage;

@Service
public class GameServiceImpl implements GameService{
	
	@Autowired
    private QuestionDao quesDao;
	
	@Override
	public returnMessage handleMessage(Message msg,gameRoom gr)
	{
		return new returnMessage();
	}

}
