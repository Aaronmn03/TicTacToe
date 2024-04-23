import java.util.Scanner;

import utils.Pair;

public class Match {
    private PlayerSelection turn;
    private Panel panel;

    public Match(){
        panel = new Panel();
        turn = PlayerSelection.Player1;
    }

    public void changeTurn(){
        if(turn.equals(PlayerSelection.Player1)){
            turn = PlayerSelection.Player2;
        }else{
            turn = PlayerSelection.Player1;
        }
    }

    public void play(){
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i<9 ; i++){
            System.out.println("Juega el " + turnToString());
            System.out.println(panel.toString());
            System.out.println("introduzca un valor (1-9): ");
            /*Falta comprobar que lo que hemos pulsado es un numero entre 1-9 */
            Pair selected = numberSelected(scanner.nextInt());
            panel.positionSelected(selected.getX(), selected.getY(), turn);
            changeTurn();
        }
        scanner.close();
    }

    private String turnToString(){
        if(turn.equals(PlayerSelection.Player1)){
            return "Jugador1";
        }else{
            return "Jugador2";
        }
    }

    private Pair numberSelected(int valuePress){
        switch (valuePress) {
            case 1: return new Pair(0, 2);
            case 2: return new Pair(1, 2);
            case 3: return new Pair(2, 2);
            case 4: return new Pair(0, 1);
            case 5: return new Pair(1, 1);
            case 6: return new Pair(2, 1);
            case 7: return new Pair(0, 0);
            case 8: return new Pair(1, 0);
            case 9: return new Pair(2, 0);
        }
        return null;
    }
}
