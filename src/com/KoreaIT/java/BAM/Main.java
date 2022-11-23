package com.KoreaIT.java.BAM;

import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");
		Scanner sc = new Scanner(System.in);
		int lastArticleId = 0;
		
		
		while (true) {
			System.out.printf("명령어)");
			String command = sc.nextLine();
			
			
			if (command.equals("article write")) {
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				System.out.println("글이 생성되었습니다");
				
				
			} else if (command.equals("article list")) {
				System.out.println("게시글이 없습니다.");
			
			}else {
				System.out.println("존재하지 않는 명령어입니다");
			}
			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}
			if (command.equals("exit")) {
				break;
			}
		}
		System.out.println("==프로그램 종료==");
		sc.close();
	}
}