package discordbot2;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import receiver.LunchReceiver;

public class Main {
	public static void main(String[] args) {
		JDABuilder builder = new JDABuilder(AccountType.BOT);
		String token = "NjI0MTQyOTk3OTgyMDE5NTg0.XYMtdw.mBsJTlQ7Gs3l7SwBW2up9iZNdyc";
		builder.setToken(token);
		try {
			builder.addEventListeners(new LunchReceiver());
			builder.build();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
}
