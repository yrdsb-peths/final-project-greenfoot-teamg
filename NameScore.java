import greenfoot.Actor;

public class NameScore extends Actor  implements Comparable <NameScore>{
  String PlayerName;
  int PlayerScores;
  
  /**
   * Constructor that sets the player name and score for the class.
   */
  public NameScore(String PlayerName, int playerScores) {
    this.PlayerName = PlayerName;
    this.PlayerScores = playerScores;
  }

  /**
   * Returns the name of the player.
   */
  public String getName() {
    return PlayerName;
  }

  /**
   * Returns the score of the player.
   */
  public int getScores() {
    return PlayerScores;
  }

  /**
   * returns 0 if the scores are equal, -1 if other score is less than player score, 1 if other is more than player score.
   */
  public int compareTo(NameScore other) {
    return Integer.compare(other.PlayerScores, this.PlayerScores);
  }
}
