import java.util.Scanner;

import Game.Match;
import utils.Turn.TypePlayer;

public class Main {
    public static void main(String[] args){
        int randomNumber = (int) (Math.random() * 2) + 1;
        System.out.println(randomNumber);
        TypePlayer player;
        if(randomNumber == 1){
            player = TypePlayer.Player1;
        }else{
            player = TypePlayer.Player2;
        }
        Match match = new Match(player);
        Scanner scanner = new Scanner(System.in);
        System.out.println("¿Cuantas personas van a jugar?");
        if(scanner.nextInt() == 2){
            match.play2Players();
        }else{
            System.out.println("Dime el nivel de dificultad (0-3) de mas facil a mas dificil");
            float difficult;
            int select = scanner.nextInt();
            if(select == 0){
                difficult = 0.75f;
            }else if(select == 1){
                difficult = 0.875f;
            }else if(select == 2){
                difficult = 0.95f;
            }else{
                difficult = 1;
            }
            match.play1Player(difficult,player);
        }
        
        scanner.close();
    }
}
