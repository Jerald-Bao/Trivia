//ExitMessage
package trivia.domain;

import java.util.List;

public class exMessage extends returnMessage{
	private int exitUserId;
	private Player host;
	
	public int getExitUserId() {
		return exitUserId;
	}
	public void setExitUserId(int exitUserId) {
		this.exitUserId = exitUserId;
	}
	public Player getHost() {
		return host;
	}
	public void setHost(Player host) {
		this.host = host;
	}
	
}
