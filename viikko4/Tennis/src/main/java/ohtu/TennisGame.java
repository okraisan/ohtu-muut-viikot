package ohtu;

public class TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public static String getNumericScoreName(int points) {
        switch (points) {
          case 0:
            return "Love";
          case 1:
            return "Fifteen";
          case 2:
            return "Thirty";
          case 3:
            return "Forty";
          default:
            return "";
        }
    }

    public String getScore() {
        String score = "";

        if (m_score1 < 4 && m_score2 < 4) {
            score = getNumericScoreName(m_score1) + "-";
            if (m_score1 == m_score2)
              score += "All";
            else
              score += getNumericScoreName(m_score2);

        } else if (m_score1 == 4 && m_score2 == 4) {
            score = "Deuce";

        } else {
            int playerAdvantage = m_score1 - m_score2;
            String whichPlayerHasAdvantage = playerAdvantage > 0 ? "1" : "2";

            if (Math.abs(playerAdvantage) == 1)
              score = "Advantage player" + whichPlayerHasAdvantage;
            else if (Math.abs(playerAdvantage) > 1)
              score = "Win for player" + whichPlayerHasAdvantage;
        }
        return score;
    }
}