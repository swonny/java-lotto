package lotto;

import controller.LottoController;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new ResultMaker());
        lottoController.run();
    }
}
