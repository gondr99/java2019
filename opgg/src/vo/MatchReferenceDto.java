package vo;

public class MatchReferenceDto {
	private String lane;
	private Long gameId;
	private Integer champion;
	private String platformId;
	private Integer season;
	private Integer queue;
	private String role;
	private Long timestamp;
	
	private String champName;
	private String champImage;

	public String getChampName() {
		return champName;
	}
	public void setChampName(String champName) {
		this.champName = champName;
	}
	public String getChampImage() {
		return champImage;
	}
	public void setChampImage(String champImage) {
		this.champImage = champImage;
	}

	
	public String getLane() {
		return lane;
	}
	public void setLane(String lane) {
		this.lane = lane;
	}
	public Long getGameId() {
		return gameId;
	}
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	public Integer getChampion() {
		return champion;
	}
	public void setChampion(Integer champion) {
		this.champion = champion;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public Integer getSeason() {
		return season;
	}
	public void setSeason(Integer season) {
		this.season = season;
	}
	public Integer getQueue() {
		return queue;
	}
	public void setQueue(Integer queue) {
		this.queue = queue;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}
