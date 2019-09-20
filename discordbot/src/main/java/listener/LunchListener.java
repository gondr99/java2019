package listener;

import domain.LunchVO;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import util.LunchParser;
import util.TimeTable;

public class LunchListener extends ListenerAdapter {
	private LunchParser lunch;
	private TimeTable tt;
	
	//생성자를 만들고
	public LunchListener() {
		//생성자에서 할당한다.
		lunch = new LunchParser();
		tt = new TimeTable();
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		String msg = e.getMessage().getContentRaw();
		
		//메시지가 !yy로 시작하면 명령어
		if(msg.startsWith("!yy")) {
			int idx = msg.indexOf(" "); //첫번째로 나오는 공백을 찾아서
			if(idx < 0) {
				sayMsg(e.getChannel(), "올바른 명령어를 입력해주세요");
				return;
			}
			
			String cmd = msg.substring(idx + 1); //공백 이후부터를 잘라내고
			int paramIdx = cmd.indexOf(" "); //다음 공백까지를 찾아낸다.
			
			String param = "";
			if(paramIdx >= 0) {
				param = cmd.substring(paramIdx + 1);
				cmd = cmd.substring(0, paramIdx);
			}
			
			switch(cmd) {
			case "echo":
				if(param.isEmpty()) {
					sayMsg(e.getChannel(), "echo는 메아리할 말을 입력해야 합니다.");
				}else {
					sayMsg(e.getChannel(), param);
				}
				break;
			case "lunch":
				LunchVO lunchVO = lunch.getMenu(param);
				if(lunchVO.getMenuString().isEmpty()) {
					sayMsg(e.getChannel(), "해당일에 메뉴가 존재하지 않거나 날짜 입력형식 오류입니다. !yy lunch 20190920 형태로 입력하세요");
				} else {
					sayMsg(e.getChannel(), lunchVO.getDate() + "일의 메뉴 : " + lunchVO.getMenuString());
				}
			case "time":
				String[] params = param.split(" ");
				if(params.length != 2) {
					sayMsg(e.getChannel(), "시간표조회 : !yy time 날짜(20190920) 반(1또는 2) 으로 입력하세요");
				}else {
					sayMsg(e.getChannel(), tt.getTime(params[0], params[1]));
				}
			}
		}
	}
	
	private void sayMsg(MessageChannel channel, String msg) {
		channel.sendMessage(msg).queue();
	}
}
