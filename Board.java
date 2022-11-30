/**
 * Execution: dont execute this one 
 *
 * Description: a bigger class, the playboard, something containing all blocks
**/
public class Board {
    private Block[][] gameBoard;
    private int numOfMine;
    
    public Board() {
        numOfMine = 10;
        gameBoard = new Block[9][9];
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                gameBoard[i][j] = new Block();
            }
        }
        
    }
    
    /**
     * Inputs: a.getGameBoard() where a is a board object
     * Outputs: a 2darray where every entry is a block
     * Description: most operations are done to the block 2darray thus we have to 
     * get it since it's a private variable
    */
    public Block[][] getGameBoard() {
        return this.gameBoard;
    }
    
    
    
    /**
     * Inputs: a.generateBombs(double, double) a is a board object
     * and x cor y cor together represent where the mouse clicks
     * Outputs: nothing
     * Description: allocate the 10 bombs randomly 
    */
    public void generateBombs(double xCor, double yCor) {
        int xBlock = 0;
        int yBlock = 0;
        //locating the block where the mouse clicked on
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (1.00 / 18.00 * 2 * j <= xCor &&
                xCor <= 1.00 / 18.00 * 2 * (j + 1) &&
                1 - (1.00 / 18.00 * 2 * i) >= yCor &&
                yCor >= 1 - (1.00 / 18.00 * 2 * (i + 1))) {
                    xBlock = i;
                    yBlock = j;
                }
            }
        }
        //going through the board, every block has 10/81 
        //chance to become a mine block
        int count = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                double a = Math.random();
                if (a < 10.00 / 81.00 && count < 10) {
                    gameBoard[i][j].changeStatus();
                    count++;
                }
            }
        }
        if (count < 10) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    double a = Math.random();
                    if (a < 10.00 / 81.00 && count < 10) {
                        gameBoard[i][j].changeStatus();
                        count++;
                    }
                }
            }
        }
        // if the first block mouse clicks on happens to be a bomb, reallocate
        if (gameBoard[xBlock][yBlock].getStatus() == false) {
            generateBombs(xCor, yCor);
        }
        
    }
    
    /**
     * Inputs: a.setNumBomb() a is a board
     * Outputs: Nothing
     * Description: setting the arroundingBomb field for every block
    */
    public void setNumBomb() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int count = 0;
                //going through the 9 blocks around
                for (int a = i - 1; a <= i + 1; a++) {
                    for (int b = j - 1; b <= j + 1; b++) {
                        if (a < 0 || b < 0) {
                            continue;
                            } else if (a > 8 || b > 8) {
                            continue;
                        }
                        if (gameBoard[a][b].getStatus() == false) {
                            count++;
                        }
                        
                    }
                }
                gameBoard[i][j].setAroundingBomb(count);
            }
        }
    }
    
    /**
     * Inputs: a.setBlocks()
     * Outputs: nothing a board
     * Description: draw the whole board at its original status, 
     * the first thing first for the game
    */
    public void setBlocks() {
        PennDraw.setCanvasSize(630, 630);
        PennDraw.clear(0, 0, 0);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (gameBoard[i][j].getStatus() == true) {
                    double tempx =  1.00 / 18.00 * (2 * j + 1);
                    double tempy = 1 - (1.00 / 18.00 * (2 * i + 1));
                    gameBoard[i][j].setXCordinates(tempx);
                    gameBoard[i][j].setYCordinates(tempy);
                    gameBoard[i][j].draw();
                }
                
            }
        }
        
    }
    
    /**
     * Inputs: a.showAround(double, double) a is a board and the two doubles
     * together stand for where the mouse clicked on
     * Outputs: reveal all clocks with 0 arroundingBombs who are next to each other
     * at the same time
     * Description: reveal all clocks with 0 arroundingBombs 
     * who are next to each other at the same time
    */
    public void showAround(double xCordinate, double yCordinate) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (1.00 / 18.00 * 2 * j <= xCordinate &&
                xCordinate <= 1.00 / 18.00 * 2 * (j + 1) &&
                1 - (1.00 / 18.00 * 2 * i) >= yCordinate &&
                yCordinate >= 1 - (1.00 / 18.00 * 2 * (i + 1))) {
                    if (gameBoard[i][j].getAroundingBomb() == 0) {
                        gameBoard[i][j].changeChecked();
                        for (int a = i - 1; a <= i + 1; a++) {
                            for (int b = j - 1; b <= j + 1; b++) {
                                if (a < 0 || b < 0) {
                                    continue;
                                    } else if (a > 8 || b > 8) {
                                    continue;
                                }
                                /**have to check otherwise when the [a][b]in the 
                                 *recursive call reaches the former [i][j],it 
                                 *becomes an infinite loop
                                **/
                                if (gameBoard[a][b].getChecked() == false) {
                                    gameBoard[a][b].show();
                                    double tempA = xCordinate + 1.00 / 9.00 *
                                        (b - j);
                                    double tempB = yCordinate + 1.00 / 9.00 *
                                        (i - a);
                                    showAround(tempA, tempB);
                                }
                                
                            }
                        }
                    }
                }
            }
        }
    }
    
}
