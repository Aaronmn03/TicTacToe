package utils;

import javax.sound.sampled.AudioFileFormat.Type;

public class Turn {
    public enum TypePlayer{
        Player1,Player2,Empty;
    }
    private TypePlayer player;

    public Turn(){
        player = TypePlayer.Player1;
    }
    public Turn(TypePlayer player){
        this.player = player;
    }

    public TypePlayer getActualTurn(){
        return player;
    }

    public void setActualTurn(TypePlayer player){
        this.player = player;
    }

    public TypePlayer changeTurn(){
        if(player.equals(TypePlayer.Player1)){
            player = TypePlayer.Player2;
        }else{
            player = TypePlayer.Player1;
        }
        return player;
    }

    public String toString(){
        if(player.equals(TypePlayer.Player1)){
            return "Jugador1";
        }else{
            return "Jugador2";
        }
    }
}
