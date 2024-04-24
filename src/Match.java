import java.util.Scanner;

import utils.Pair;

public class Match {
    private PlayerSelection turn;
    private Panel panel;
    private PlayerSelection winner;

    public Match(){
        panel = new Panel();
        turn = PlayerSelection.Player1;
        winner = null;
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

        for(int i = 0; i<9 && winner == null; i++){
            Pair selected = suggestForPosition(scanner);
            winner = panel.positionSelected(selected.getX(), selected.getY(), turn);
            if(winner != null){
                System.out.println("El ganador es: " + turnToString());
            }else{
                changeTurn();
            }
        }
        System.out.println(panel.toString());
        if(winner == null){
            System.out.println("Empate");
        }
        scanner.close();
    }

    private Pair suggestForPosition(Scanner scanner){
        int num = 10;
        while(num < 1 || num > 9){
            System.out.println("Juega el " + turnToString());
            System.out.println(panel.toString());
            System.out.println("Introduzca un valor (1-9): ");
            num = scanner.nextInt();
        }
        return numberSelected(num);
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
