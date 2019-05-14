package model;

public class Toon {
	public String toonName;
	public String toonId;
	public String url;
	
	public Toon(String name, String toonId, String url) {
		this.toonName = name;
		this.toonId = toonId;
		this.url = url;
	}
	
	public boolean checkContain(String value) {
		if(toonName.contains(value)) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public String toString() {
		return toonName + " : " + toonId;
	}
}