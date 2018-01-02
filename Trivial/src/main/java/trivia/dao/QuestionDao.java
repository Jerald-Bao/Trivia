package trivia.dao;
import org.apache.ibatis.annotations.Param;
import trivia.domain.Question;

public interface QuestionDao {
	Question findByCategory(String category);
       /**
	     * @param category
	     * @param description
	     * @return
	     */
	boolean addQuestion(@Param("category")String category,@Param("description")String description,@Param("choiceA")String A,@Param("choiceB")String B,
			@Param("choiceC")String C,@Param("choiceD")String D,@Param("answer")String answer,@Param("difficulty")String difficluty);
}
