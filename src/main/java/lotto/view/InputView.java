package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;

public class InputView {
    public static int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        while (true) {
            try {
                String input = Console.readLine();
                int amount = Integer.parseInt(input);

                if (amount < 1000 || amount % 1000 != 0) {
                    throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
                }

                return amount;
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 숫자 형식의 금액을 입력해야 합니다.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Lotto readWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String input = Console.readLine();
        String[] numbers = input.split(",");
        if (numbers.length != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
        List<Integer> list = new ArrayList<>();
        for (String number : numbers) {
            int n = parseNumber(number);
            validateRange(n);
            if (list.contains(n)) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복될 수 없습니다.");
            }
            list.add(n);
        }
        return new Lotto(list);
    }

    public static int readBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        String input = Console.readLine();
        int number = parseNumber(input);
        validateRange(number);
        return number;
    }

    private static int parseNumber(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자 형식의 로또 번호여야 합니다.");
        }
    }

    private static void validateRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }
}
