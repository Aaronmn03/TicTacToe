package Game;

import utils.Turn.TypePlayer;

public class Box {
    private TypePlayer value;

    public Box(TypePlayer value) {
        this.value = value;
    }

    public Box (){
        value = TypePlayer.Empty;
    }

    public TypePlayer getValue() {
        return value;
    }

    public void setValue(TypePlayer value) {
        this.value = value;
    }
    
    public boolean isEmpty(){
        return value.equals(TypePlayer.Empty);
    }

    public boolean isSelectedBy(TypePlayer p){
        return value.equals(p);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Box box = (Box) obj;
        return value == box.value;
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
