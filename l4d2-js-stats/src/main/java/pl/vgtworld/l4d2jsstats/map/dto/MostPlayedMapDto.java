package pl.vgtworld.l4d2jsstats.map.dto;

public class MostPlayedMapDto {
	
	private int mapId;
	
	private String mapName;
	
	private long gamesCount;
	
	public MostPlayedMapDto(int mapId, String mapName, long gamesCount) {
		this.mapId = mapId;
		this.mapName = mapName;
		this.gamesCount = gamesCount;
	}

	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public long getGamesCount() {
		return gamesCount;
	}

	public void setGamesCount(long gamesCount) {
		this.gamesCount = gamesCount;
	}
	
}
