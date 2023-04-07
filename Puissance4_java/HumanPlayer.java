import java.util.Scanner;

public class HumanPlayer extends Player {

  public HumanPlayer(char symbol, Board board, String name) {
    super(symbol, board, name);
  }

  @Override
  public void makeMove(Board board) {
    Scanner scanner = new Scanner(System.in);
    System.out.print(name + ", veuillez entrer votre coup (1-7) : ");
    String valStr = scanner.nextLine();
    while (!valStr.matches("\\d+") || !board.validate(Integer.parseInt(valStr))) {
      System.out.print("Veuillez entrer un nombre valide (1-7) : ");
      valStr = scanner.nextLine();
    }
    int val = Integer.parseInt(valStr);
    board.dropPieceAt(val, symbol);
  }
}
