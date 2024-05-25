package Game;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils.Pair;
import utils.Turn.TypePlayer;

public class Panel {
    private Box panel[][];
    private boolean isEnd;

    public Panel(){
        panel = new Box[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                panel[i][j] = new Box();
            }
        }
    }

    public Panel(Panel panel){
        this();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.panel[i][j].setValue(panel.getPanel()[i][j].getValue());
            }
        }
    }

    public Panel(Box[][] panel){
        this.panel = panel;
    }


    public Box[][] getPanel() {
        return panel;
    }

    public void setPanel(Box[][] panel) {
        this.panel = panel;
    }
    
    public List<Panel> getPossibleMoves(TypePlayer actualPlayer){
        List<Panel> list = new ArrayList<>();
        Panel aux = new Panel(panel);
        Panel movement = aux;
        for(int i = 0; i < 3 ; i++){
            for(int j = 0; j < 3 ; j++){
                if(aux.getPanel()[i][j].isEmpty()){
                    movement.positionSelected(i, j, actualPlayer);
                    if(actualPlayer.equals(TypePlayer.Player1)){
                        actualPlayer = TypePlayer.Player2;
                    }else{
                        actualPlayer = TypePlayer.Player1;
                    }
                    list.add(movement);
                }
            }
            movement = aux;
        }
        Collections.shuffle(list);
        return list;
    }

    public List<Pair> getPossiblePairs(){
        List<Pair> list = new ArrayList<>();
        for(int i = 0; i < 3 ; i++){
            for(int j = 0; j < 3 ; j++){
                if(panel[j][i].isEmpty()){
                    list.add(new Pair(j, i));
                }
            }
        }
        Collections.shuffle(list);
        return list;
    }
    

    /*
     * @ returns null si no gana nadie y si gana alguien devuelve el valor del ganador, que sera el que ha posicionado
     * 
     * 
     */
    public TypePlayer positionSelected(int x, int y,TypePlayer playerSelection){
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

    public int evaluar() {
        //columas
        for (int row = 0; row < 3; row++) {
            if (panel[row][0].equals(panel[row][1]) && panel[row][1].equals(panel[row][2])) {
                if (panel[row][0].getValue().equals(TypePlayer.Player1)) {
                    return -10;
                } else if (panel[row][0].getValue().equals(TypePlayer.Player2)) {
                    return 10;
                }
            }
        }

        for (int col = 0; col < 3; col++) {
            if (panel[0][col].equals(panel[1][col]) && panel[1][col].equals(panel[2][col])) {
                if (panel[0][col].getValue().equals(TypePlayer.Player1)) {
                    return -10;
                } else if (panel[0][col].getValue().equals(TypePlayer.Player2)) {
                    return 10;
                }
            }
        }
        if ((panel[0][0].equals(panel[1][1]) && panel[1][1].equals(panel[2][2])) || (panel[0][2].equals(panel[1][1]) && panel[1][1].equals(panel[2][0]))) {
            if (panel[1][1].getValue().equals(TypePlayer.Player1)) {
                return -10;
            } else if (panel[1][1].getValue().equals(TypePlayer.Player2)) {
                return 10;
            }
        }
        return 0;
    }

    public TypePlayer determineWinner(int x, int y, TypePlayer player){
        if (checkRow(y,player) || checkColumn(x,player) || checkDiagonal1(player) || checkDiagonal2(player)){
            isEnd=true;
            return player;
        }
        return null;
    }

    public boolean checkWinner(int x, int y,TypePlayer player){
        return determineWinner(x,y,player)!=null;
    }

    public boolean isFull(){
        for(int i = 0; i < panel.length ; i++){
            for(int j = 0; j < panel.length ; j++){
                if(panel[i][j].isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isEnded(){
        if(isEnd){
            return true;
        }
        if (isFull()) {
            return true;
        }
        return false;
    }

    private boolean checkRow(int y, TypePlayer player){
        for(int i = 0; i < 3; i++){
            if(panel[i][y].getValue() != player){
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int x, TypePlayer player){
        for(int i = 0; i < 3; i++){
            if(panel[x][i].getValue() != player){
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal1(TypePlayer player){
        for(int i = 0; i < 3 ; i ++){
            if(panel[i][i].getValue() != player){
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal2(TypePlayer player){
        for (int i = 0; i < 3; i++) {
            if (panel[i][2 - i].getValue() != player) {
                return false;
            }
        }
        return true;
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
}
