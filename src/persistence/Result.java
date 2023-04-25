package persistence;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Result {
	private int gameId;
	private int clicks;
	private int seconds;
	private Timestamp dateOfGame;
	private int score;

	public Result() {

	}

	public Result(int gameId, int clicks, int seconds, Timestamp dateOfGame, int score) {
		super();
		this.gameId = gameId;
		this.clicks = clicks;
		this.seconds = seconds;
		this.dateOfGame = dateOfGame;
		this.score = score;
	}

	public Result(int clicks, int seconds, Timestamp dateOfGame) {
		super();
		this.clicks = clicks;
		this.seconds = seconds;
		this.dateOfGame = dateOfGame;
	}

	public Result(int clicks, int seconds, Timestamp dateOfGame, int score) {
		super();
		this.clicks = clicks;
		this.seconds = seconds;
		this.dateOfGame = dateOfGame;
		this.score = score;
	}

	public int getGameId() {
		return gameId;
	}

	public int getClicks() {
		return clicks;
	}

	public int getSeconds() {
		return seconds;
	}

	public int getScore() {
		return score;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public Timestamp getDateOfGame() {
		return dateOfGame;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public void setDateOfGame(Timestamp dateOfGame) {
		this.dateOfGame = dateOfGame;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Result [gameId=" + gameId + ", clicks=" + clicks + ", seconds=" + seconds + ", dateOfGame=" + dateOfGame
				+ ", score=" + score + "]";
	}
	
	

}
