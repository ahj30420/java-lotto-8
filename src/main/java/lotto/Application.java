package lotto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.service.LottoService;
import lotto.service.LottoService.Result;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        int purchaseAmount = InputView.readPurchaseAmount();
        LottoService service = new LottoService();
        service.purchase(purchaseAmount);

        List<Lotto> lottos = service.getLottos();
        OutputView.printLottos(lottos);

        Lotto winningNumbers = InputView.readWinningNumbers();
        int bonus = InputView.readBonusNumber();

        Result result = service.calculateResult(winningNumbers, bonus);
        OutputView.printResult(result, purchaseAmount);
    }
}
