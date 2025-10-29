package lotto.service;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Prize;
import lotto.util.LottoUtils;

public class LottoService {

    private List<Lotto> lottos = new ArrayList<>();

    public void purchase(int amount) {
        if (amount < 1000 || amount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
        }
        int count = amount / 1000;
        for (int i = 0; i < count; i++) {
            lottos.add(LottoUtils.generateRandomLotto());
        }
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }

    public Result calculateResult(Lotto winningNumbers, int bonus) {
        Result count = new Result();

        for (Lotto lotto : lottos) {
            int match = lotto.matchCount(winningNumbers);
            boolean bonusMatched = match == 5 && lotto.contains(bonus);

            if (match == 6) {
                count.addPrize(Prize.FIRST);
                continue;
            }
            if (match == 5 && bonusMatched) {
                count.addPrize(Prize.SECOND);
                continue;
            }
            if (match == 5) {
                count.addPrize(Prize.THIRD);
                continue;
            }
            if (match == 4) {
                count.addPrize(Prize.FOURTH);
                continue;
            }
            if (match == 3) {
                count.addPrize(Prize.FIFTH);
                continue;
            }
            count.addPrize(Prize.NONE);
        }
        return count;
    }

    public static class Result {
        private int first;
        private int second;
        private int third;
        private int fourth;
        private int fifth;

        public void addPrize(Prize p) {
            if (p == Prize.FIRST) {
                first++;
                return;
            }
            if (p == Prize.SECOND) {
                second++;
                return;
            }
            if (p == Prize.THIRD) {
                third++;
                return;
            }
            if (p == Prize.FOURTH) {
                fourth++;
                return;
            }
            if (p == Prize.FIFTH) {
                fifth++;
                return;
            }
        }

        public int getFirst() { return first; }
        public int getSecond() { return second; }
        public int getThird() { return third; }
        public int getFourth() { return fourth; }
        public int getFifth() { return fifth; }

        public long totalPrize() {
            long sum = 0;
            sum += (long) first * Prize.FIRST.getPrize();
            sum += (long) second * Prize.SECOND.getPrize();
            sum += (long) third * Prize.THIRD.getPrize();
            sum += (long) fourth * Prize.FOURTH.getPrize();
            sum += (long) fifth * Prize.FIFTH.getPrize();
            return sum;
        }
    }
}
