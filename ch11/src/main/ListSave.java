package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ListSave {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		
		list.add("쯔위");		list.add("지효");		list.add("다현");			
		list.add("사나");		list.add("나연");		

		File saveFile = new File("data.txt");
		
		try {
			FileOutputStream fos = new FileOutputStream(saveFile);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			
			for(String item : list) {
				bw.write(item);
				bw.newLine();
			}
			
			bw.flush();  	// 이작업을 해야 실제로 기록된다.
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("기록중 오류발생");
		}

	}

}