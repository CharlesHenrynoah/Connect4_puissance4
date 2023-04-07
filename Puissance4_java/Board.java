public class Board {
  private char[][] board;

  public Board() {
    board = new char[6][7];
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        board[i][j] = '-';
      }
    }
  }

  public void reset() {
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        board[i][j] = '-';
      }
    }
  }

  public void dropPieceAt(int col, char piece) {
    int row = 5;
    while (row >= 0) {
      if (board[row][col - 1] == '-') {
        board[row][col - 1] = piece;
        break;
      }
      row--;
    }
  }

  public boolean validate(int col) {
    if (col < 1 || col > 7) {
      return false;
    }
    if (board[0][col - 1] != '-') {
      return false;
    }
    return true;
  }

  public void printBoard() {
    for (int i = 0; i < 6; i++) {
      String row = "";
      for (int j = 0; j < 7; j++) {
        row += board[i][j] + " ";
      }
      System.out.println(row);
    }
    System.out.println("1 2 3 4 5 6 7");
  }

  public boolean containsWin() {
    return almostWinV() != 0 || almostWinD1() != 0 || almostWinD2() != 0 || almostWinH() != 0;
  }

  public boolean isTie() {
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        if (board[i][j] == '-') {
          return false;
        }
      }
    }
    return true;
  }

  public int almostWinV() {
    for (int c = 0; c < 7; c++) {
      for (int r = 0; r < 3; r++) {
        if (board[r][c] == board[r + 1][c] && board[r + 1][c] == board[r + 2][c] && board[r + 2][c] == board[r + 3][c]
            && board[r][c] != '-') {
          return c + 1;
        }
      }
    }
    return 0;
  }

  public int almostWinD1() {
    for (int c = 0; c < 4; c++) {
      for (int r = 0; r < 3; r++) {
        if (board[r][c] == board[r + 1][c + 1] && board[r + 1][c + 1] == board[r + 2][c + 2]
            && board[r + 2][c + 2] == board[r + 3][c + 3] && board[r][c] != '-') {
          return c + 1;
        }
      }
    }
    return 0;
  }

  public int almostWinD2() {
    for (int c = 3; c < 7; c++) {
      for (int r = 0; r < 3; r++) {
        if (board[r][c] == board[r + 1][c - 1] && board[r + 1][c - 1] == board[r + 2][c - 2]
            && board[r + 2][c - 2] == board[r + 3][c - 3] && board[r][c] != '-') {
          return c + 1;
        }
      }
    }
    return 0;
  }

  public int almostWinH() {
    for (int c = 0; c < 4; c++) {
      for (int r = 0; r < 6; r++) {
        if (board[r][c] == board[r][c + 1] && board[r][c + 1] == board[r][c + 2] && board[r][c + 2] == board[r][c + 3]
            && board[r][c] != '-') {
          return c + 1;
        }
      }
    }
    return 0;
  }
}
