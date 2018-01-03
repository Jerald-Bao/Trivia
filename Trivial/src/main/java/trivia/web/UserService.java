package trivia.web;
import trivia.domain.*;

public interface UserService {

	User userValidate(String username,String password);
}
