package Game;
import java.util.Scanner;

import utils.Pair;
import utils.Turn;
import utils.Turn.TypePlayer;

public class Match {
    private Turn turn;
    private Panel panel;
    private TypePlayer winner;

    public Match(TypePlayer player){
        panel = new Panel();
        turn = new Turn(player);
        winner = null;
    }

    public void play2Players(){
        Scanner scanner = new Scanner(System.in);

        for(int i = 0; i<9 && winner == null; i++){
            Pair selected = suggestForPosition(scanner);
            winner = panel.positionSelected(selected.getX(), selected.getY(), turn.getActualTurn());
            if(winner != null){
                System.out.println("El ganador es: " + turn.toString());
            }else{
                turn.changeTurn();
            }
        }
        System.out.println(panel.toString());
        if(winner == null){
            System.out.println("Empate");
        }
        scanner.close();
    }

    public void play1Player(float difficult,TypePlayer player){//VAMOS A EMPEZAR PONIENDO QUE SIEMPRE EMPIEZA EL JUGADOR PERSONA que sera el 1
        IAPlayer iaPlayer = new IAPlayer(difficult,new Turn(player));
        Scanner scanner = new Scanner(System.in);
        Pair selected;
        while(!panel.isFull() && winner == null){
            if(turn.getActualTurn().equals(TypePlayer.Player1)){
                selected = suggestForPosition(scanner);
                System.out.println(panel.toString());
            }else{
                selected = iaPlayer.determinePosition(panel);
            }
            winner = panel.positionSelected(selected.getX(), selected.getY(), turn.getActualTurn());
            if(winner != null){
                System.out.println("El ganador es: " + turn.toString());
            }else{
                turn.changeTurn();
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
            System.out.println("Juega el " + turn.toString());
            System.out.println(panel.toString());
            System.out.println("Introduzca un valor (1-9): ");
            num = scanner.nextInt();
        }
        return numberSelected(num);
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
