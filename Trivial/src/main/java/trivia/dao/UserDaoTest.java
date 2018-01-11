package trivia.dao;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import trivia.domain.User;
import trivia.BaseTest;

public class UserDaoTest extends BaseTest{

	@Autowired
	private UserDao userDao;
	
    @Before
    public void prepare(){
        System.out.println(" before ：所有的测试方法之前都先执行这个方法");
    }
    
    @Test
	public void testQueryById() throws Exception {
		int userid=1;
		User user=userDao.findByUserid(userid);
		System.out.println(user);
	}
}
