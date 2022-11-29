/**
 * Name: Wenjun Lu
 * Pennkey: wenjunlu
 * Execution: don't execute this one 
 *
 * Description: the basic object in minesweeper which is a block.
**/
public class Block {
    private boolean status;
    private int aroundingBomb;
    private double xCordinates;
    private double yCordinates;
    private boolean revealed;
    private boolean checked;
    
    public Block() {
        this.status = true;
        this.aroundingBomb = 0;
        this.xCordinates = 0;
        this.yCordinates = 0;
        this.revealed = false;
        this.checked = false;
    }
    
    /**
     * Inputs: a.changeChecked() where a have to be a block object
     * Outputs: Nothing
     * Description: change the "check" of a block, usually fron false to true
    */
    public void changeChecked() {
        checked = true;
    }
    
    /**
     * Inputs: a.getChecked() where a have to be a block object
     * Outputs: a boolean
     * Description: getting the value of "checked" since it is a private variable
     * used in the recursive function where tried to reveal all blocks whose 
     * number of aroundingBomb is zero at the same time 
    */
    public boolean getChecked() {
        return checked;
    }
    
    /**
     * Inputs: a.changeRevealed() where a have to be a block object
     * Outputs: Nothing
     * Description: change the "revealed" of a block, usually fron false to true
    */
    public void changeRevealed() {
        revealed = true;
    }
    
    /**
     * Inputs: a.getRevealed() where a have to be a block object
     * Outputs: a boolean
     * Description: getting the value of "revealed" since it is a private variable
    */
    public boolean getRevealed() {
        return revealed;
    }
    
    /**
     * Inputs: a.getStatus() where a have to be a block object
     * Outputs: a boolean
     * Description: getting the value of "revealed" since it is a private variable
    */
    public boolean getStatus() {
        return status;
    }
    
    /**
     * Inputs: a.changeStatus() where a have to be a block object
     * Outputs: Nothing
     * Description: change the "status" of a block, usually fron false to true
     * denoting whether a block is a mine or a safe block, false represent
     * mine
    */
    public void changeStatus() {
        status = !status;
    }
    
    /**
     * Inputs: a.getAroundingBomb() where a is a block object
     * Outputs: a int, the number of mines around a block
     * Description: getting the number of mines since it is a private variable
    */
    public int getAroundingBomb() {
        return this.aroundingBomb;
    }
    
    /**
     * Inputs: a.setAroundingBomb() where a is a block object
     * Outputs: nothing
     * Description: updating the aroundingBomb variable which will later be shown
    */
    public void setAroundingBomb(int num) {
        this.aroundingBomb = num;
    }
    
    /**
     * Inputs: a.getXCordinates() where a is a block object
     * Outputs: a double which is the x cordinate of a block's center
     * Description: getting the x cordinate of a block's center
     * since it is a private variable
    */
    public double getXCordinates() {
        return this.xCordinates;
    }
    
    /**
     * Inputs: a.setXCordinates() where a is a block object
     * Outputs: nothing
     * Description: updating the x cordinate of a block's center
    */
    public void setXCordinates(double x) {
        xCordinates = x;
    }
    
    /**
     * Inputs: a.getYCordinates() where a is a block object
     * Outputs: a double which is the y cordinate of a block's center
     * Description: getting the y cordinate of a block's center
     * since it is a private variable
    */
    public double getYCordinates() {
        return this.yCordinates;
    }
    
    /**
     * Inputs: a.setYCordinates() where a is a block object
     * Outputs: nothing
     * Description: updating the Y cordinate of a block's center
    */
    public void setYCordinates(double y) {
        yCordinates = y;
    }
    
    /**
     * Inputs: a.draw() where a is a block object
     * Outputs: nnothing
     * Description: draw a block
    */
    public void draw() {
        if (status == true) {
            PennDraw.picture(xCordinates, yCordinates, "Block.png", 70, 70);
        }
        
    }
    
    /**
     * Inputs: a.show() where a is a block object
     * Outputs: nothing, changing the visual representation of a block
     * Description: showing the number of aroundingBomb if the block is a 
     * safe one
     * if it is a bomb, show a mine symbol
    */
    public void show() {
        String num;
        // so that if the number is 0, it wont show
        if (aroundingBomb == 0) {
            num = "";
            } else {
            num = this.aroundingBomb + "";
        }
        // the bomb display
        if (status == false) {
            PennDraw.picture(xCordinates, yCordinates, "Mine.png", 70, 70);
            PennDraw.setPenColor(PennDraw.BLACK);
            PennDraw.setPenRadius(10.0);
            PennDraw.text(0.5, 0.5, "You Lost");
            PennDraw.text(0.5, 0.3, "Press Any Key to Restart");
            
            } else {
            // a regular block display
            PennDraw.setPenColor(PennDraw.BLACK);
            PennDraw.setPenRadius(0.5);
            PennDraw.picture(xCordinates, yCordinates, "blank.png", 70, 70);
            PennDraw.text(xCordinates, yCordinates, num);
            
        }
        this.changeRevealed();
    }
}

