package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {

        play();
        while (true) {
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

            int choice = 0;
            try {
                choice = Integer.parseInt(Console.readLine());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException();
            }
            if (choice == 1) {
                play();
            } else if (choice == 2) {
                break;
            } else {
                System.out.println("1, 2 중 선택");
            }
        }

    }

    // 게임 한 판
    private static void play() {
        // 답 생성
        int[] computer = new int[3];
        int i = 0;
        while (i < computer.length) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            computer[i] = randomNumber;
            for (int j = 0; j < i; j++) {
                if (computer[j] == randomNumber) {
                    i--;
                    break;
                }
            }
            i++;
        }

//        // 답 확인
//        for (int n : computer) {
//            System.out.println(n);
//        }

        System.out.println("숫자 야구 게임을 시작합니다.");

        while (true) {
            System.out.print("숫자를 입력해 주세요 : ");

            int userNumber;
            try {
                userNumber = Integer.parseInt(Console.readLine());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException();
            }

            if ((userNumber < 111) || (userNumber > 999)) {
                throw new IllegalArgumentException();
            }

            int[] user = new int[3];
            user[0] = userNumber / 100;
            user[1] = (userNumber / 10) % 10;
            user[2] = userNumber % 10;

            // 숫자 중복 입력
            if ((user[0] == user[1]) || (user[0] == user[2]) || (user[1] == user[2])) {
                throw new IllegalArgumentException();
            }

            String ballCount = getBallCount(computer, user);
            System.out.println(ballCount);

            // 정답
            if (ballCount.equals("3스트라이크")) {
                System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
                break;
            }
        }
    }

    // 볼카운트 얻기
    private static String getBallCount(int[] computer, int[] user) {
        String ballCount;
        int ball = 0;
        int strike = 0;

        // 볼카운트 계산
        for (int i = 0; i < user.length; i++) {
            for (int j = 0; j < computer.length; j++) {
                if ((user[i] == computer[j]) && (i == j)) {
                    strike++;
                } else if ((user[i] == computer[j]) && (i != j)) {
                    ball++;
                }
            }
        }

        if ((ball != 0) && (strike != 0)) {
            ballCount = ball + "볼 " + strike +  "스트라이크";
        } else if ((ball == 0) && (strike != 0)) {
            ballCount = strike + "스트라이크";
        } else if ((ball != 0) && (strike == 0)) {
            ballCount = ball + "볼";
        } else {
            ballCount = "낫싱";
        }
        return ballCount;
    }
}
