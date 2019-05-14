package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class RecordFile {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("파일명을 입력하세요");
		String fileName = in.nextLine();
		
		File file = new File(fileName);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			System.out.println("문장을 입력하세요. 공백입력시 종료됩니다.");
			while(true) {
				String line = in.nextLine();
				if(line.isEmpty()) break;
				bw.write(line);
				bw.newLine();
			}
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("파일 기록중 오류발생");
		}
	}
}
