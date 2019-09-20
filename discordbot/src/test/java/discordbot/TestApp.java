package discordbot;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class TestApp {
	public static void main(String[] args) {
		LocalDate date = LocalDate.of(2019, 9, 1);
		
		DayOfWeek day = date.getDayOfWeek();
		System.out.println(day.getValue());
	}
}
