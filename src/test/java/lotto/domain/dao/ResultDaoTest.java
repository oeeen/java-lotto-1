package lotto.dao;

import lotto.domain.Rank;
import lotto.dto.ResultDto;
import lotto.domain.util.DBUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import static org.assertj.core.api.Assertions.assertThat;

class ResultDaoTest {
    private ResultDao resultDAO;
    private DataSource dataSource = DBUtil.getDataSource("lotto_test");


    @BeforeEach
    void setUp() {
        resultDAO = new ResultDao(dataSource);
    }

    @Test
    void add() throws SQLException {
        Map<Rank, Integer> winners = new TreeMap<>();
        for (Rank rank : Rank.values()) {
            winners.put(rank, 1);
        }
        double yield = 0.3;
        long winPrize = 100_000_000;
        ResultDto resultDTO = new ResultDto(winners, yield, winPrize);
        int round = 0;
        resultDAO.addResult(round, resultDTO);
    }

    @Test
    void searchWinnerCount() throws SQLException {
        Map<Rank, Integer> winnerCount = resultDAO.findWinnerCountByRound(0);
        Map<Rank, Integer> expected = new TreeMap<>();
        for (Rank rank : Rank.values()) {
            expected.put(rank, 1);
        }
        assertThat(winnerCount).isEqualTo(expected);
    }

    @Test
    void searchYield() throws SQLException {
        int round = 0;
        assertThat(resultDAO.findYieldByRound(round)).isEqualTo(0.3);
    }

    @Test
    void searchWinPrize() throws SQLException {
        int round = 0;
        assertThat(resultDAO.findWinPrizeByRound(round)).isEqualTo(100_000_000);
    }
}