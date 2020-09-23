import java.util.ArrayList;

public class Dama {
    enum Status {IN_GAME, LOSE, WIN}
 
    public Status currentStatus = Status.IN_GAME;
    ArrayList<Pawn> playerTop = new ArrayList<>();
    ArrayList<Pawn> playerBottom = new ArrayList<>();
 
    public Dama() {
        pawnGenerator();
    }
 
    public void pawnGenerator() {
        for (int row = 1; row < 4; row++) {//player top
            if (row == 1 || row == 3) {
                for (int column = 2; column <= 8; column += 2) {
                    this.playerTop.add(new Pawn(row, column, true, false));
                }
            } else {
                for (int column = 1; column <= 7; column += 2) {
                    this.playerTop.add(new Pawn(row, column, true, false));
                }
            }
        }
        for (int row = 6; row < 9; row++) {//player bottom
            if (row == 7) {
                for (int column = 2; column <= 8; column += 2) {
                    this.playerTop.add(new Pawn(row, column, false, false));
                }
            } 
            else {
                for (int column = 1; column <= 7; column += 2) {
                    this.playerTop.add(new Pawn(row, column, false, false));
                }
            }
        }
    }
 
    public Pawn getPawn(int row, int column) {
        for (Pawn pawn : playerTop) {
            if (pawn.getRow() == row && pawn.getColumn() == column) {
                return pawn;
            }
        }
        for (Pawn pawn : playerBottom) {
            if (pawn.getRow() == row && pawn.getColumn() == column) {
                return pawn;
            }
        }
        if(row > 9 || row < 0 || column >9 || column <0){
            return new Pawn(true);
        }
        return new Pawn(-1);
    }
 
    public boolean isEmpty(int row, int column) {
        for (Pawn brown : playerTop) {
            if (brown.getColumn() == column && brown.getRow() == row) {
                return false;
            }
        }
        for (Pawn white : playerBottom) {
            if (white.getColumn() == column && white.getRow() == row) {
                return false;
            }
        }
        return true;
    }
 
   public int moveForbiden(int row, int column){
            if(getPawn(row, column).isBrown){
                if((!isEmpty(row+1, column-1) && !getPawn(row+1, column-1).isBrown) && isEmpty(row+2, column-2)){//|| (!isEmpty(row+1, column+1) && !getPawn(row+1, column+1).isBrown)
                    System.out.println("il marrone deve mangiare a sinistra");
                    return -1; // nel main mangio a isnistra in basso e incremento muliplicato per 2
                }
                else if((!isEmpty(row+1, column+1) && !getPawn(row+1, column+1).isBrown) && isEmpty(row+2, column+2)){//|| (!isEmpty(row+1, column+1) && !getPawn(row+1, column+1).isBrown)
                    System.out.println("il marrone deve mangiare a destra");
                    return -1;//nel main mangio a destra in basso e incremento muliplicato per 2
                }
                else {
                    return 0;
                }
            }else if(!getPawn(row, column).isBrown){
                if((!isEmpty(row-1, column-1) && getPawn(row-1, column-1).isBrown) && isEmpty(row-2, column-2)){//|| (!isEmpty(row+1, column+1) && !getPawn(row+1, column+1).isBrown)
                    System.out.println("il bianco deve mangiare a sinistra");
                    return -1; // nel main mangio a isnistra in basso e incremento muliplicato per 2
                }
                else if((!isEmpty(row-1, column+1) && getPawn(row-1, column+1).isBrown) && isEmpty(row-2, column+2)){//|| (!isEmpty(row+1, column+1) && !getPawn(row+1, column+1).isBrown)
                    System.out.println("il bianco deve mangiare a destra");
                    return -1;//nel main mangio a destra in basso e incremento muliplicato per 2
                }
                else {
                    return 0;
                }
            }
        return 0;
    }

    public void turnDama(Pawn pawn){
        if(pawn.isBrown){
            System.out.println("control everything");
            if(pawn.getRow()==9){
                System.out.println("control isDama");
                pawn.isDama=true;
            }
        }
        else{
            System.out.println("control everything");
            if(pawn.getRow()==1){
                System.out.println("control isDama");
                pawn.isDama=true;
            }
        }
    }

    public void move(int columnIncrement, int rowIncrement, Pawn pawn){
        /*boolean control=false;
        for(Pawn broPawn:playerTop){
             if(moveForbiden(broPawn.getRow(), broPawn.getColumn()) == -1) {
                control = true;
            }
        }*/
        int rowDestination = pawn.getRow()+ rowIncrement;
        int columnDestination = pawn.getColumn()+ columnIncrement;
        int rowDestination2 = pawn.getRow()+ rowIncrement*2;
        int columnDestination2 = pawn.getColumn()+ columnIncrement*2;
        System.out.println("hai scelto di andare in "+rowDestination+","+columnDestination);
        if(!isEmpty(rowDestination, columnDestination)){
            System.out.println("c'è una pedina, vediamo se si può mangiare");
            if(!getPawn(rowDestination2, columnDestination2).isFake() && isEmpty(rowDestination2, columnDestination2) ){
                eatPawn(rowDestination, columnDestination);
                pawn.setRow(rowDestination2);
                pawn.setColumn(columnDestination2);
                System.out.println("pedina mangiata!!");
            }
            System.out.println("mi sa che non si può");
        }
        else if(rowDestination < 9 && rowDestination > 0 && columnDestination <9 && columnDestination > 0) {
            pawn.setRow(rowDestination);
            pawn.setColumn(columnDestination);
 
        }
        turnDama(pawn);
    }
    public boolean eatPawn(int row, int column) {
            for (Pawn pawn : playerBottom) {
                if (pawn.equals(new Pawn(row, column, true, false))) {
                    playerBottom.remove(pawn);
                    return true;
                }
            }
            for (Pawn pawn : playerTop) {
                if (pawn.equals(new Pawn(row, column, false, false))) {
                    playerTop.remove(pawn);
                    return true;
                }
            }
        return false;
    }

    public String toString() {
        String result = this.currentStatus + "\n";
        for (int row = 1; row<9; row++) {
            result+="\n[";
            for (int column = 1; column < 9; column++) {
                if (getPawn(row, column).isBrown && !getPawn(row, column).isFake()  && !getPawn(row, column).isEaten){
                    if(getPawn(row, column).isDama){
                        result += "[🔵]";
                    }else{
                         result += "[🟤]";
                    }
                } else if (!getPawn(row, column).isBrown && !getPawn(row, column).isFake()  && !getPawn(row, column).isEaten){
                    if(getPawn(row, column).isDama){
                        result += "[​🟢​]";
                    }else{
                    result +="[⚪]";
                    }
                }
                else {
                    result += "["+row+","+column+"]";
                }
            } result += "]\n";
        }
        return result;
    }
}