package discordbot;

import util.TimeTable;

public class TimeTableTest {
	public static void main(String[] args) {
		TimeTable tt = new TimeTable();
		
		String result = tt.getTime("20190920", "1");
		
		System.out.println(result);
	}
}
