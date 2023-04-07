public class AIPlayer extends Player {

  public AIPlayer(char symbol, Board board, String name) {
    super(symbol, board, name);
  }

  public void makeMove(Board board) {
    int val;
    if (almostWin(board) == 0) {
      val = (int) (Math.random() * 7) + 1;
    } else {
      val = almostWin(board);
    }

    System.out.println(name + ", please input your move: " + val);

    while (!board.validate(val)) {
      System.out.println("Please Enter a valid move");
      val = (int) (Math.random() * 7) + 1;
      System.out.println(name + ", please input your move: " + val);
    }

    board.dropPieceAt(val, symbol);
  }

  private int almostWin(Board board) {
    int a = board.almostWinV();
    int b = board.almostWinD1();
    int c = board.almostWinD2();
    int d = board.almostWinH();

    if (a != 0) {
      return a;
    } else if (b != 0) {
      return b;
    } else if (c != 0) {
      return c;
    } else if (d != 0) {
      return d;
    } else {
      return 0;
    }
  }
}
