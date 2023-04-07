import java.util.Scanner;

public class Menu {
  private Board board;
  private ConnectFour game;
  private Player player1;
  private Player player2;

  public Menu() {
    board = new Board();
    game = new ConnectFour(board);
  }

  public void start() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Bienvenue dans le jeu de Puissance 4 !");
    System.out.println("Veuillez choisir le mode de jeu :");
    System.out.println("1. Joueur vs. IA");
    System.out.println("2. Joueur vs. Joueur");
    System.out.println("3. IA vs. IA");

    String choice = scanner.nextLine();

    if (choice.equals("1")) {
      System.out.print("Entrez votre nom : ");
      String name = scanner.nextLine();
      System.out.print("Entrez votre signe (S ou B) : ");
      String sign = scanner.nextLine();
      player1 = new HumanPlayer(sign.charAt(0), board, name);
      this.player2 = new AIPlayer((sign.equals("S") ? 'B' : 'S'), this.board, "IA");
    } else if (choice.equals("2")) {
      System.out.print("Entrez le nom du Joueur 1 : ");
      String name1 = scanner.nextLine();
      System.out.print("Entrez le signe du Joueur 1 (S ou B) : ");
      String sign1 = scanner.nextLine();
      System.out.print("Entrez le nom du Joueur 2 : ");
      String name2 = scanner.nextLine();
      String sign2;
      do {
        System.out.print("Entrez le signe du Joueur 2 (S ou B) : ");
        sign2 = scanner.nextLine();
      } while (sign2.equals(sign1));
      player1 = new HumanPlayer(sign1.charAt(0), board, name1);
      player2 = new HumanPlayer(sign2.charAt(0), board, name2);
    } else if (choice.equals("3")) {
      player1 = new AIPlayer('S', board, "IA 1");
      player2 = new AIPlayer('B', board, "IA 2");
    } else {
      System.out.println("Choix invalide. Veuillez entrer 1, 2 ou 3.");
      start();
      return;
    }

    game.setPlayer1(player1);
    game.setPlayer2(player2);
    game.playGame();
  }
}
