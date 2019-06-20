package lotto.controller;

import lotto.domain.LottoResult;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.dao.ResultDao;
import lotto.dao.RoundDao;
import lotto.dao.WinningLottoDao;
import lotto.dto.ResultDto;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class ResultController {
    private static final String RESULT_HTML = "result.html";

    private final RoundDao roundDao;
    private final WinningLottoDao winningLottoDao;
    private final ResultDao resultDao;

    public ResultController(final RoundDao roundDao, final WinningLottoDao winningLottoDao, final ResultDao resultDao) {
        this.roundDao = roundDao;
        this.winningLottoDao = winningLottoDao;
        this.resultDao = resultDao;
    }

    public Object print(Request req, Response res) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        int maxRound = roundDao.getMaxRound();

        WinningLotto winningLotto = getWinningLotto(req, maxRound);
        LottoResult lottoResult = new LottoResult(req.session().attribute("totalLottos"), winningLotto);

        putLottoWinners(model, maxRound, lottoResult);
        putYield(model, maxRound);

        return render(model, RESULT_HTML);
    }

    private WinningLotto getWinningLotto(Request req, int maxRound) throws SQLException {
        String input = req.queryParams("winningLotto");
        int bonusNum = Integer.parseInt(req.queryParams("bonusBall"));
        WinningLotto winningLotto = new WinningLotto(input, bonusNum);
        winningLottoDao.addWinningLotto(maxRound, winningLotto);
        return winningLotto;
    }

    private void putLottoWinners(Map<String, Object> model, int maxRound, LottoResult lottoResult) throws SQLException {
        Map<Rank, Integer> winners = lottoResult.getWinners();
        ResultDto resultDto = new ResultDto(winners, lottoResult.getYield(), lottoResult.getTotalWinningMoney());
        resultDao.addResult(maxRound, resultDto);
        for (Rank rank : Rank.values()) {
            model.put(rank.name(), winners.get(rank));
        }
    }

    private void putYield(Map<String, Object> model, int maxRound) throws SQLException {
        model.put("yield", resultDao.findYieldByRound(maxRound) * 100);
    }
}
