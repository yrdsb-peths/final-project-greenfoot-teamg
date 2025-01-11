import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class HighScore extends World {
  private MenuScreen menuScreen;
  private static final int MAX_SCORES = 5; // Maximum number of scores that are accepted

  public HighScore(MenuScreen menuScreen) {
    super(600, 750, 1);
    GreenfootImage background = new GreenfootImage("CharacterSelection.jpg");
    background.scale(getWidth(), getHeight()); // Adjust to world size (600, 750)
    setBackground(background);

    this.menuScreen = menuScreen;

    displayHighScores();

    addLabels();
  }

  public void act() {
    // Update volume
    Util.handleEscapeKey(this, menuScreen);
}

private void addLabels() {
    addObject(new Label("ESC", 30), 40, 725);
    addObject(new Label("Back", 25), 100, 725);
}

  public void displayHighScores() {
    // y-intercept
    int yint = 150;
    int Header_FontSize = 30; // Font size for the header
    int Label_FontSize = 30; // Font size for the label
    int rank = 1;

    // Get and sort the scores
    List<NameScore> sortedScores = getSortTopScore();

    Label title = new Label("LEADERBOARD", 50);
    addObject(title, getWidth() / 2, 70);

    Label headerRank = new Label("Rank", Header_FontSize);
    Label headerName = new Label("Name", Header_FontSize);
    Label headerScore = new Label("Score", Header_FontSize);

    addObject(headerRank, 150, yint);
    addObject(headerName, 300, yint);
    addObject(headerScore, 450, yint);

    yint += 50;

    // looping over the sorted Player Info (rank, name, score)
    for (NameScore name : sortedScores) {
      Label rankLabel = new Label(rank, Label_FontSize);
      Label nameLabel = new Label(name.getName(), Label_FontSize);
      Label scoreLabel = new Label(String.valueOf(name.getScores()), Label_FontSize);

      addObject(rankLabel, 150, yint);
      addObject(nameLabel, 300, yint);
      addObject(scoreLabel, 450, yint);

      rank++;
      yint += 50;
    }
  }

  // Sort the player info in descending order
  private List<NameScore> getSortTopScore() {
    // Get the scores from GameOver.UserNames
    List<NameScore> score = new ArrayList<>(GameOver.UserNames);
    Collections.sort(score);

    // Limit the list to the MAX_SCORES
    if (score.size() > MAX_SCORES) {
        score = score.subList(0, MAX_SCORES);
    }

    return score;
  }

   /**
     * Transitions to the menu screen and handles music cleanup.
     */
  public void goMenuScreen() {
    Greenfoot.setWorld(menuScreen);
  }
}
