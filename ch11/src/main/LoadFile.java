package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class LoadFile {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("읽어올 파일 이름을 입력하세요");
		String filename = in.nextLine();
		
		File file = new File(filename);
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isw = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isw);
			
			while(true) {
				String line = br.readLine();
				if(line == null) break;
				System.out.println(line);
			}
			
			br.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("존재하지 않습니다.");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("에러 발생");
		}
	}
}
