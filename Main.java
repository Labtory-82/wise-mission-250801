package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String command = "";

        Scanner sc = new Scanner(System.in);
        String[] content = new String[999];
        Arrays.fill(content, "");
        String[] author = new String[999];
        Arrays.fill(author, "");
        int number = -1;
        int tmpNumber = 0;

        System.out.println("== 명언 앱 ==");
        while (!command.equals("종료")) {
            System.out.print("명령) ");
            command = sc.nextLine();

            if (command.equals("등록")) {
                number++;

                System.out.print("명언 : ");
                content[number] = sc.nextLine();
                System.out.print("작가 : ");
                author[number] = sc.nextLine();
                System.out.println((number + 1) + "번 명언이 등록되었습니다.");
            }

            if (command.equals("목록")) {
                tmpNumber = number;
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                while (tmpNumber + 1 > 0) {

                    if (content[tmpNumber].equals("") && author[tmpNumber].equals("")) {
                        tmpNumber--;
                        continue;
                    }

                    System.out.println((tmpNumber + 1) + " / " + author[tmpNumber] + " / " + content[tmpNumber]);
                    tmpNumber--;
                }
            }

            if (command.matches("^삭제\\?id=\\d+$")) {
                tmpNumber = Integer.parseInt(command.substring(6)) - 1;

                if (tmpNumber > 999 || (content[tmpNumber].equals("") && author[tmpNumber].equals(""))) {
                    System.out.println((tmpNumber+1) + "번 명언은 존재하지 않습니다.");
                } else {
                    content[tmpNumber] = "";
                    author[tmpNumber] = "";
                    System.out.println((tmpNumber+1) + "번 명언이 삭제되었습니다.");
                }
            }

            if (command.matches("^수정\\?id=\\d+$")) {
                tmpNumber = Integer.parseInt(command.substring(6)) - 1;
                if (tmpNumber > 999 || (content[tmpNumber].equals("") && author[tmpNumber].equals(""))) {
                    System.out.println((tmpNumber+1) + "번 명언은 존재하지 않습니다.");
                } else {
                    System.out.println("명언(기존) : " + content[tmpNumber]);
                    System.out.print("명언 : ");
                    content[tmpNumber] = sc.nextLine();
                    System.out.println("작가(기존) : " + author[tmpNumber]);
                    System.out.print("작가 : ");
                    author[tmpNumber] = sc.nextLine();
                }
            }
        }
    }
}

