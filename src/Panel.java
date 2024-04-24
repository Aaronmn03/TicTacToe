import java.util.ArrayList;
import java.util.List;

import utils.Pair;

public class Panel {
    private Box panel[][];

    public Panel(){
        panel = new Box[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                panel[i][j] = new Box();
            }
        }
    }

    public String toString(){
        String aux = new String();
        int x = 0;
        int y = 0;
        for(int i = 0; i < 7;i++){
            
            for(int j = 0; j < 13;j++){
                if(checkBorderPosition(j, i)){
                    aux += "*";
                }else if(checkValuePosition(j, i)){
                    aux += panel[x][y].toString();
                    x++;
                    if(x == 3){
                        y++;
                        x = 0;
                    }
                }else{
                    aux += " ";
                }
            }
            aux += "\n";
        }
        return aux;
    }

    /*
     * @ returns null si no gana nadie y si gana alguien devuelve el valor del ganador, que sera el que ha posicionado
     * 
     * 
     */
    public PlayerSelection positionSelected(int x, int y,PlayerSelection playerSelection){
        if(!panel[x][y].isEmpty()){
            throw new RuntimeException("La posicion seleccionada ya esta escogida");
        }else{
            panel[x][y].setValue(playerSelection);
            
        }
        if(checkWinner(x,y,playerSelection)){
            return playerSelection;
        }else{
            return null;
        }
        
    }

    public PlayerSelection determineWinner(int x, int y, PlayerSelection player){
        if (checkRow(y,player) || checkColumn(x,player) || checkDiagonal1(player) || checkDiagonal2(player)){
            return player;
        }
        return null;
    }



    private boolean checkRow(int y, PlayerSelection player){
        for(int i = 0; i < 3; i++){
            if(panel[i][y].getValue() != player){
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int x, PlayerSelection player){
        for(int i = 0; i < 3; i++){
            if(panel[x][i].getValue() != player){
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal1(PlayerSelection player){
        for(int i = 0; i < 3 ; i ++){
            if(panel[i][i].getValue() != player){
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal2(PlayerSelection player){
        for (int i = 0; i < 3; i++) {
            if (panel[i][2 - i].getValue() != player) {
                return false;
            }
        }
        return true;
    }

    public boolean checkWinner(int x, int y,PlayerSelection player){
        return determineWinner(x,y,player)!=null;
    }


    /*
     * @return true if the position requires a border
     * 
     */
    private boolean checkBorderPosition(int x, int y){
        if(isPair(y)){
            return true;
        }
        if(isMultiple4(x)){
            return true;
        }
        return false;
    }

    /*
     * @return true if the position requires a value
     * 
     */
    private boolean checkValuePosition(int x, int y){
        if(isMultiple4(x - 2) || isMultiple4(y - 2)){
            return true;
        }
        return false;
    }

    private boolean isPair(int v){
        return v % 2 == 0;
    }
    private boolean isMultiple4(int v){
        return v % 4 == 0;
    }


}
