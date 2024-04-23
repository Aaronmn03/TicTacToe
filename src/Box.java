public class Box {
    private PlayerSelection value;

    public Box(PlayerSelection value) {
        this.value = value;
    }

    public Box (){
        value = PlayerSelection.Empty;
    }

    public PlayerSelection getValue() {
        return value;
    }

    public void setValue(PlayerSelection value) {
        this.value = value;
    }
    
    public boolean isEmpty(){
        return value.equals(PlayerSelection.Empty);
    }

    public boolean isSelectedBy(PlayerSelection p){
        return value.equals(p);
    }

    public String toString(){
        String auxValue;
        switch (this.value) {
            case Player1 : auxValue = "O";
                break;
            case Player2 : auxValue = "X";
                break;
            default:
                auxValue = " ";
                break;
        }
        //return "*****\n* " + auxValue + " *\n*****\n";
        return auxValue;
    }
}
