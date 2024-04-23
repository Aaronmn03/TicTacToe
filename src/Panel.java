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

    public PlayerSelection positionSelected(int x, int y,PlayerSelection playerSelection){
        if(!panel[x][y].isEmpty()){
            throw new RuntimeException("La posicion seleccionada ya esta escogida");
        }else{
            panel[x][y].setValue(playerSelection);
            
        }
        return playerSelection;
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
