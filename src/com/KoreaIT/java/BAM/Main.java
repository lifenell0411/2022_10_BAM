package com.KoreaIT.java.BAM;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
	@SuppressWarnings("removal")
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");
		Scanner sc = new Scanner(System.in);
		int lastArticleId = 0;
		List<Article> articles = new ArrayList<>();

		while (true) {
			System.out.printf("명령어)");
			String command = sc.nextLine().trim();

			if (command.equals("article write")) {
				int id = lastArticleId + 1;
				lastArticleId = id;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, title, body);
				articles.add(article);

				System.out.printf("%d번글이 생성되었습니다\n", id);

			}

			else if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;

				}
				System.out.println("번호 / 제목");

				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);

					System.out.println(article.id + "   / " + article.title);
				}

			} else if (command.startsWith("article detail ")) {
				String[] commandDiv = command.split(" ");
				int id = Integer.parseInt(commandDiv[2]);

				Article foundArticle = null;
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.println(id + "번 게시물은 존재하지 않습니다.");
					continue;
				} else {

					LocalDate now = LocalDate.now();
					LocalDateTime timePoint = LocalDateTime.now();
					LocalDateTime now1 = timePoint.truncatedTo(ChronoUnit.SECONDS);
					System.out.println("번호 : " + foundArticle.id);
					System.out.println("날짜 : " + now + " " + now1);
					System.out.println("제목 : " + foundArticle.title);
					System.out.println("내용 : " + foundArticle.body);
				}
			} else if (command.startsWith("article delete ")) {
				String[] commandDiv = command.split(" ");
				int id = Integer.parseInt(commandDiv[2]);

				int foundIndex = -1;
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundIndex = i;
						break;
					}
				}

				if (foundIndex == -1) {
					System.out.println(id + "번 게시물은 존재하지 않습니다.");
					continue;
				}

				articles.remove(foundIndex);
				System.out.println(id + "번 게시물을 삭제했습니다.");
			} else {
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

class Article {
	int id;
	String title;
	String body;

	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;

	}
}