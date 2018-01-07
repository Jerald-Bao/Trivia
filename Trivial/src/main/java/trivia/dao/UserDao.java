package trivia.dao;

import trivia.domain.User;

public interface UserDao {
    public abstract User findByUsername(String username);
    public abstract User findByUserid(int userid);
    public abstract boolean winnerUpdateById(int winnerid);
    public abstract boolean loserUpdateById(int loserid);
}
