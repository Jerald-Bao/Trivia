package trivia.domain;

public class User {
	
	private int userid;
    private String username;
    private String password;
    private String account;
    private String gender;
    private int totalGame;
    private int totalWin;

    public User() {
        super();
    }

    public User(int userid, String username, String password, String account, String gender, int totalGame,
			int totalWin) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.account = account;
		this.gender = gender;
		this.totalGame = totalGame;
		this.totalWin = totalWin;
	}

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getTotalGame() {
		return totalGame;
	}

	public void setTotalGame(int totalGame) {
		this.totalGame = totalGame;
	}

	public int getTotalWin() {
		return totalWin;
	}

	public void setTotalWin(int totalWin) {
		this.totalWin = totalWin;
	}
}
