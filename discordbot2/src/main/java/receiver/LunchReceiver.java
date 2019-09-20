package receiver;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class LunchReceiver extends ListenerAdapter{
	//echo 라는 명령이 내려오면 해당 명령을 수행한다.
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		System.out.println("Received message from " + e.getAuthor().getName() + " : " + e.getMessage().getContentDisplay());
		if(e.getMessage().getContentRaw().contentEquals("!echo")) {
			e.getChannel().sendMessage("Echo !!!!!").queue();
		}
		
	}
}
