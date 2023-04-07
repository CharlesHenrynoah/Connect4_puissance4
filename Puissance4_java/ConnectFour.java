import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class ConnectFour {
  private Player player1;
  private Player player2;
  private boolean isP1Turn; // true si c'est au tour du joueur 1 de jouer
  private Board board;

  public ConnectFour(Board board) {
    this.board = board;
    this.player1 = null;
    this.player2 = null;
    this.isP1Turn = true;
  }

  public void setPlayer1(Player player1) {
    this.player1 = player1;
  }

  public void setPlayer2(Player player2) {
    this.player2 = player2;
  }

  public void playGame() {
    board.reset();
    board.printBoard();
    Instant start = Instant.now();
    while (!gameIsOver()) {
      System.out.println("C'est le tour de " + getCurrentPlayer().getName() + ".");
      getCurrentPlayer().makeMove(board);
      board.printBoard();
      if (board.containsWin()) {
        Instant end = Instant.now();
        Duration elapsed = Duration.between(start, end);
        System.out.println("Félicitations " + getCurrentPlayer().getName() + ", vous avez gagné en "
            + elapsed.getSeconds() + " secondes!");
        updateLeaderboard(getCurrentPlayer().getName(), elapsed.getSeconds());
        return;
      }
      changeTurns();
    }
    System.out.println("Le jeu est une égalité. Vous avez tous les deux perdu.");
  }

  public boolean gameIsOver() {
    return board.containsWin() || board.isTie();
  }

  public Player getCurrentPlayer() {
    if (isP1Turn) {
      return player1;
    }
    return player2;
  }

  public void changeTurns() {
    isP1Turn = !isP1Turn;
  }

  public void updateLeaderboard(String playerName, long elapsedMilliseconds) {
    try (FileWriter writer = new FileWriter("top_time.txt", true)) {
      writer.write("Nom du joueur : " + playerName + " / Temps de victoire : " + elapsedMilliseconds / 10
          + " centièmes de seconde\n");
    } catch (IOException e) {
      System.out.println("Erreur lors de l'écriture dans le fichier top_time.txt");
    }
  }

}
