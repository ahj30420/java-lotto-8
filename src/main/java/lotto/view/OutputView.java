package lotto.view;

import java.util.List;
import lotto.domain.Lotto;
import lotto.service.LottoService.Result;

public class OutputView {
    public static void printLottos(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
        System.out.println();
    }

    public static void printResult(Result result, int purchaseAmount) {
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println("3개 일치 (5,000원) - " + result.getFifth() + "개");
        System.out.println("4개 일치 (50,000원) - " + result.getFourth() + "개");
        System.out.println("5개 일치 (1,500,000원) - " + result.getThird() + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + result.getSecond() + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + result.getFirst() + "개");

        double rate = calculateRate(result.totalPrize(), purchaseAmount);
        System.out.println("총 수익률은 " + formatRate(rate) + "%입니다.");
    }

    private static double calculateRate(double totalPrize, int purchaseAmount) {
        if (purchaseAmount == 0) return 0.0;
        return (double) totalPrize / purchaseAmount * 100.0;
    }

    public static String formatRate(double rate) {
        return String.format("%,.1f", rate);
    }
}
