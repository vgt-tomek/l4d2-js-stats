package pl.vgtworld.l4d2jsstats.match.dto;


public class MapBreakDto implements Comparable<MapBreakDto> {
	
	private int mapId;
	
	private String mapName;
	
	private Integer breakDayCount;
	
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
	
	public Integer getBreakDayCount() {
		return breakDayCount;
	}
	
	public void setBreakDayCount(Integer breakDayCount) {
		this.breakDayCount = breakDayCount;
	}

	@Override
	public int compareTo(MapBreakDto other) {
		if (breakDayCount == null && other.getBreakDayCount() == null) {
			return 0;
		}
		if (breakDayCount == null) {
			return -1;
		}
		if (other.getBreakDayCount() == null) {
			return 1;
		}
		return other.getBreakDayCount() - getBreakDayCount();
	}
	
}
