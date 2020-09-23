import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Dama dama = new Dama();
        Scanner scan = new Scanner(System.in);
        while(true){
            int rowIncrement=0;
            System.out.println(dama.toString());
            System.out.println("Insert row: ");
            int brownRow=scan.nextInt();
 
            System.out.println("Insert column: ");
            int brownColumn=scan.nextInt();
            if(dama.getPawn(brownRow, brownColumn).isDama){
                System.out.println("press 1 to go up or 2 to go down");
                int verticalDriection=scan.nextInt();
                switch(verticalDriection){
                    case 1: {
                          rowIncrement=-1;
                    break;
                    }
                    case 2:{
                         rowIncrement=1;
                        break;
                    }
                }
            }else{
                rowIncrement = dama.getPawn(brownRow,brownColumn).isBrown ? 1 : -1;
            }
            System.out.println("Press 1 to go Left or 2 to go Right: ");
            int choice=scan.nextInt();
            switch (choice) {
                case 1: {
                        dama.move(-1, rowIncrement,dama.getPawn(brownRow, brownColumn));
                    break;
                }
                case 2: {
                        dama.move(1, rowIncrement, dama.getPawn(brownRow, brownColumn));
                    }
                    break;
                }
            }
        }
    }
