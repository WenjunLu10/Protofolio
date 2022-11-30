/**
 * Execution: java MineSweeper
 *
 * Description: Using java to reproduce the classic minesweeper game
**/
public class MineSweeper {
    
    /**
     * Inputs: newGame()
     * Outputs: nothing, starts a new game
     * Description: the body part of the game where we use every function 
     * created before and merged into one game
    */
    public static void newGame() {
        Board a = new Board();
        a.setBlocks();
        Block[][] b = a.getGameBoard();
        boolean win = true;
        PennDraw.enableAnimation(30);
        
        boolean set = true;
        while (set) {
            //set the board in advance, by doing so, we prevent the situation
            //in which the first block clicked on happens to be a bomb
            if (PennDraw.mousePressed()) {
                double mouseX = PennDraw.mouseX();
                double mouseY = PennDraw.mouseY();
                a.generateBombs(mouseX, mouseY);
                a.setNumBomb();
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (1.00 / 18.00 * 2 * j <= mouseX &&
                        mouseX <= 1.00 / 18.00 * 2 * (j + 1) &&
                        1 - (1.00 / 18.00 * 2 * i) >= mouseY &&
                        mouseY >= 1 - (1.00 / 18.00 * 2 * (i + 1))) {
                            b[i][j].show();
                        }
                    }
                }
                set = false;
            }
        }
        
        while (win) {
            if (PennDraw.mousePressed()) {
                double mouseX = PennDraw.mouseX();
                double mouseY = PennDraw.mouseY();
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        // check which block in the 2darray the user clicked on
                        if (1.00 / 18.00 * 2 * j <= mouseX &&
                        mouseX <= 1.00 / 18.00 * 2 * (j + 1) &&
                        1 - (1.00 / 18.00 * 2 * i) >= mouseY &&
                        mouseY >= 1 - (1.00 / 18.00 * 2 * (i + 1))) {
                            double tempx =  1.00 / 18.00 * (2 * j + 1);
                            double tempy = 1 - (1.00 / 18.00 * (2 * i + 1));
                            b[i][j].setXCordinates(tempx);
                            b[i][j].setYCordinates(tempy);
                            b[i][j].show();
                            a.showAround(mouseX, mouseY);
                            //have to get out of the while loop when steped on mine
                            if (b[i][j].getStatus() == false) {
                                win = false;
                            }
                            
                            //check for win, we win if we exhaust all number blocks
                            int num = 0;
                            for (int m = 0; m < 9; m++) {
                                for (int n = 0; n < 9; n++) {
                                    if (b[m][n].getRevealed() == true) {
                                        num++;
                                    }
                                    
                                }
                            }
                            if (num == 71) {
                                PennDraw.setPenColor(PennDraw.BLACK);
                                PennDraw.setPenRadius(10.0);
                                PennDraw.text(0.5, 0.5, "You Win");
                                PennDraw.text(0.5, 0.3, "Press Any Key to Restart");
                                win = false;
                                
                            }
                        }
                        
                    }
                }
            }
            PennDraw.advance();
        }
        //allow user to restart the game when the user pressed on any key
        while (true) {
            if (PennDraw.hasNextKeyTyped()) {
                PennDraw.nextKeyTyped();
                newGame();
                
            }
            
        }
    }
    //only have to call newGame()
    public static void main(String[] args) {
        newGame();
        
    }
}
