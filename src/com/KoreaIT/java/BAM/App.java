package com.KoreaIT.java.BAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BAM.dto.Article;
import com.KoreaIT.java.BAM.util.Util;

public class App {

	public static List<Article> articles = new ArrayList<>();

	public void run() {
		System.out.println("==프로그램 시작==");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.printf("명령어 ) ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (command.equals("exit")) {
				break;
			}

			if (command.equals("article write")) {
				int id = articles.size() + 1;
				String regDate = Util.getTimeAndDateStr();
				String updateDate = regDate;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, regDate, updateDate, title, body);
				articles.add(article);

				System.out.printf("%d번 글이 생성되었습니다\n", id);

			} else if (command.equals("article list")) {

				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
					continue;
				}
				System.out.println("번호     /    제목      /     조회");
				String tmpTitle = null;
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					if (article.title.length() > 4) {
						tmpTitle = article.title.substring(0, 4);
						System.out.printf("%3d    /   %6s    /   %5d\n", article.id, tmpTitle + "...", article.hit);
						continue;
					}
					System.out.printf("%3d    /   %6s    /   %5d\n", article.id, article.title, article.hit);
				}
			} else if (command.startsWith("article detail ")) {

				String[] commandDiv = command.split(" ");
				int id = Integer.parseInt(commandDiv[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}

				foundArticle.increaseHitCount();

				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("작성날짜 : %s\n", foundArticle.regDate);
				System.out.printf("수정날짜 : %s\n", foundArticle.updateDate);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				System.out.printf("조회 : %d\n", foundArticle.hit);

			} else if (command.startsWith("article modify ")) {
				String[] commandDiv = command.split(" ");
				int id = Integer.parseInt(commandDiv[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}

				System.out.printf("제목 : ");
				String newTitle = sc.nextLine();
				System.out.printf("내용 : ");
				String newBody = sc.nextLine();

				foundArticle.title = newTitle;
				foundArticle.body = newBody;
				foundArticle.updateDate = Util.getTimeAndDateStr();

				System.out.printf("%d번 게시물을 수정했습니다\n", id);

			} else if (command.startsWith("article delete ")) {
				String[] commandDiv = command.split(" ");
				int id = Integer.parseInt(commandDiv[2]);

				int foundIndex = getArticleIndexById(id);

				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
					continue;
				}

				articles.remove(foundIndex);
				System.out.printf("%d번 게시물을 삭제했습니다\n", id);

			} else {
				System.out.println("존재하지 않는 명령어입니다");
			}
		}

		System.out.println("==프로그램 끝==");
		sc.close();

	}

	private static void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다");

		articles.add(new Article(1, Util.getTimeAndDateStr(), Util.getTimeAndDateStr(), "제목1", "내용1", 11));
		articles.add(new Article(2, Util.getTimeAndDateStr(), Util.getTimeAndDateStr(), "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getTimeAndDateStr(), Util.getTimeAndDateStr(), "제목3", "내용3", 33));
	}

	private static int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : articles) {

			if (article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private static Article getArticleById(int id) {

		int index = getArticleIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}
}