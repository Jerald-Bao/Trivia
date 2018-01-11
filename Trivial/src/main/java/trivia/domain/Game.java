package trivia.domain;

import java.util.List;
import java.util.Random;

public class Game {
	private List<Question> popQuestions;
	private List<Question> scienceQuestions;
	private List<Question> sportsQuestions;
	private List<Question> rockQuestions;
	private List<Player> players;
	private Question currengQuestion;
	private int[] location= {0,0,0,0};
	private int[] point= {0,0,0,0};
	private int[] lock= {0,0,0,0};

	public static final int MAX_NUMBER_OF_PLACE = 12;
	public static final int CATEGORY_POP_1 = 0;
	public static final int CATEGORY_POP_2 = 4;
	public static final int CATEGORY_POP_3 = 8;
	public static final int CATEGORY_SCIENCE_1 = 1;
	public static final int CATEGORY_SCIENCE_2 = 5;
	public static final int CATEGORY_SCIENCE_3 = 9;
	public static final int CATEGORY_SPORTS_1 = 2;
	public static final int CATEGORY_SPORTS_2 = 6;
	public static final int CATEGORY_SPORTS_3 = 10;
    public static final String POP = "Pop";
    public static final String SCIENCE = "Science";
    public static final String SPORTS = "Sports";
    public static final String ROCK = "Rock";
    
	public Game(List<Player> players) {
		super();
		this.players = players;
	}
	
	public int roll() {
		Random rand = new Random();
		return rand.nextInt(5) + 1;
	}
	
	public int move(Player p,int rollNum) {
		int index=p.getPosition();
		int location=this.getLocation()[index];
		location+=rollNum;
		this.getLocation()[index]=location;
		return location % MAX_NUMBER_OF_PLACE;
	}
	
	public int addPoint(Player p,int winPoint) {
		int index=p.getPosition();
	    this.getPoint()[index]+=winPoint;
	    return this.getPoint()[index];
	}
	
	public String getCurrentCategory(int location) {
	    if (location == CATEGORY_POP_1) return POP;
	    if (location == CATEGORY_POP_2) return POP;
	    if (location== CATEGORY_POP_3) return POP;
	    if (location == CATEGORY_SCIENCE_1) return SCIENCE;
	    if (location == CATEGORY_SCIENCE_2) return SCIENCE;
	    if (location == CATEGORY_SCIENCE_3) return SCIENCE;
	    if (location == CATEGORY_SPORTS_1) return SPORTS;
	    if (location == CATEGORY_SPORTS_2) return SPORTS;
	    if (location == CATEGORY_SPORTS_3) return SPORTS;
	    return ROCK;
	}
	 
	public Question getQuestion(String category) {
		Question question=new Question();
		switch(category) {
		case "Pop":
			question=this.getPopQuestions().get(0);
			this.getPopQuestions().remove(0);
			break;
		case "Science":
			question=this.getScienceQuestions().get(0);
			this.getScienceQuestions().remove(0);
			break;
		case "Sports":
			question=this.getSportsQuestions().get(0);
			this.getSportsQuestions().remove(0);
			break;
		case "Rock":
			question=this.getRockQuestions().get(0);
			this.getRockQuestions().remove(0);
			break;
		default:
			break;
		}
		return question;
	}
	
	public List<Question> getPopQuestions() {
		return popQuestions;
	}


	public void setPopQuestions(List<Question> popQuestions) {
		this.popQuestions = popQuestions;
	}


	public List<Question> getScienceQuestions() {
		return scienceQuestions;
	}


	public void setScienceQuestions(List<Question> scienceQuestions) {
		this.scienceQuestions = scienceQuestions;
	}


	public List<Question> getRockQuestions() {
		return rockQuestions;
	}


	public void setRockQuestions(List<Question> rockQuestions) {
		this.rockQuestions = rockQuestions;
	}


	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public Question getCurrengQuestion() {
		return currengQuestion;
	}

	public void setCurrengQuestion(Question currengQuestion) {
		this.currengQuestion = currengQuestion;
	}

	public int[] getLocation() {
		return location;
	}

	public void setLocation(int[] location) {
		this.location = location;
	}
	public void setLocation(int location,int index) {
		this.location[index] = location;
	}

	public int[] getPoint() {
		return point;
	}

	public void setPoint(int[] point) {
		this.point = point;
	}

	public int[] getLock() {
		return lock;
	}

	public void setLock(int[] lock) {
		this.lock = lock;
	}

	public List<Question> getSportsQuestions() {
		return sportsQuestions;
	}

	public void setSportsQuestions(List<Question> sportsQuestions) {
		this.sportsQuestions = sportsQuestions;
	}
	
}
