package main;

import java.util.ArrayList;
import java.util.Scanner;

import model.Friend;

//상단 코드 생략
public class Subject3 {

	public static void main(String[] args) {
		ArrayList<Friend> list = new ArrayList<Friend>();
		Scanner in = new Scanner(System.in);
		
		int menuNo = 0;
		while(menuNo != 4) {
			System.out.println("*********** <메뉴 선택> ***********");
			System.out.println("* 1. 사용자 검색                                  *");
			System.out.println("* 2. 사용자 입력                                  *");
			System.out.println("* 3. 사용자 삭제                                  *");
			System.out.println("* 4. 프로그램 종료                               *");
			System.out.println("********************************");
			menuNo = in.nextInt();
			
			if(menuNo <= 0 || menuNo > 4) {
				System.out.println("잘못된 메뉴입니다.");
			} 

			if(menuNo == 1) {
				System.out.println("검색할 사용자의 이름을 입력하세요");
				String word = in.next();
				for(Friend f : list) {
					if(f.getName().contains(word)) {
						System.out.println(f.getName() + "\t" + f.getPhone() + "\t" + f.getNick() + "\t" + f.getCode() + "\t" + f.getAge());
					}
				}
			}
			if(menuNo == 2) {
				Friend friend = new Friend();
				System.out.println("사용자 이름을 입력하세요");
				String name = in.next();
				friend.setName(name);
				
				System.out.println("휴대폰 번호를 입력하세요");
				String phone = in.next();
				friend.setPhone(phone);
				
				System.out.println("별명을 입력하세요");
				String nick = in.next();
				friend.setNick(nick);
				
				System.out.println("학번을 입력하세요");
				String code = in.next();
				friend.setCode(code);
				
				System.out.println("나이를 입력하세요");
				int age = in.nextInt();
				friend.setAge(age);
				
				list.add(friend);
				System.out.println("사용자가 추가되었습니다.");
			}
			if(menuNo == 3) {
				System.out.println("삭제할 사용자의 전화번호를 입력하세요");
				String phone = in.next();
				int idx = -1;
				for(int i = 0; i < list.size(); i++) {
					if(list.get(i).getPhone().equals(phone)) {
						idx = i;
						break;
					}
				}
				if(idx >= 0) {
					list.remove(idx);
					System.out.println("삭제되었습니다.");
				}else {
					System.out.println("해당 사용자는 없습니다.");
				}
			}
		}
		
		System.out.println("프로그램이 종료됩니다.");
		
	}

}
