package lotto.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;
import lotto.domain.Lotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LottoServiceTest {

    private LottoService service;

    @BeforeEach
    void setup() {
        service = new LottoService();
    }

    @Test
    void 구입금액이_1000원_단위가_아니면_예외가_발생한다() {
        // given
        int invalidAmount = 1500;

        // when & then
        assertThatThrownBy(() -> service.purchase(invalidAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 구입금액에_비례하여_로또가_생성된다() {
        // given
        int amount = 5000;

        // when
        service.purchase(amount);

        // then
        List<Lotto> lottos = service.getLottos();
        assertThat(lottos).hasSize(5);
    }
}