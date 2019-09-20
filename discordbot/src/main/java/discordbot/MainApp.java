package discordbot;

import listener.LunchListener;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;

public class MainApp {
	public static void main(String[] args) {
		JDABuilder builder = new JDABuilder(AccountType.BOT);
		String token = "NjI0MTQyOTk3OTgyMDE5NTg0.XYRZEw.I_tzySH23G1pxic11kAnwU_-FXw"; //토큰은 여러분의 디스코드 앱의 봇에서 가져옵니다.
		builder.setToken(token);
		try {
			builder.addEventListeners(new LunchListener());
			builder.build();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
