public class Pawn {
    private int row;
    private int column;
    private boolean fake;
 
    boolean isBrown;
    boolean isEaten;
    boolean isDama;
 
    public Pawn(int row, int column, boolean isBrown, boolean isEaten) {
        this.row = row;
        this.column = column;
        this.isBrown = isBrown;
        this.isEaten = isEaten;
 
    }public Pawn(boolean isFake) {
        this.fake = true;
    }public Pawn(int isEmpty) {
        this.isEaten = true;
    }
 
    public int getRow() {
        return this.row;
    }
 
    public int getColumn() {
        return this.column;
    }
 
    public void setRow(int row) {
        this.row = row;
    }
 
    public void setColumn(int column) {
        this.column = column;
    }
 
    public boolean equals(Pawn pawn) {
        return pawn.getRow() == this.row && pawn.getColumn() == this.column;
    }
 
    public boolean isFake() {
        return fake;
    }
    public boolean isForbiden(){
        return isForbiden();
    }
}