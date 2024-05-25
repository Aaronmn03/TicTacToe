package Game;

import java.util.List;

import utils.Pair;
import utils.Turn;

public class IAPlayer {

    private float difficult;
    private Turn iaPlayer;
    private Turn player;

    public IAPlayer(float difficult, Turn turn){
        this.difficult = difficult;
        iaPlayer = new Turn();
        iaPlayer.setActualTurn(turn.getActualTurn());
        turn.changeTurn();
        player = new Turn();
        player.setActualTurn(turn.getActualTurn());
    }

    public Pair determinePosition(Panel panel) {
        int mejorValor = Integer.MIN_VALUE;
        Pair bestMovement = null;
        List<Pair> listMovementsAvaible = panel.getPossiblePairs();
        for(Pair movement : listMovementsAvaible){
            Panel newPanel = new Panel(panel);
            if(!newPanel.isFull()){
                newPanel.positionSelected(movement.getX(), movement.getY(), iaPlayer.getActualTurn());
                int valor;
                if(Math.random() < difficult){
                    valor = minValor(newPanel, Integer.MIN_VALUE, Integer.MAX_VALUE);
                }else{
                    valor = Math.random() < 0.5 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                

                if (valor > mejorValor) {
                    mejorValor = valor;
                    bestMovement = movement;
                }
                
            }
        }
        return bestMovement;
    }
    
    private int maxValor(Panel panel, int a, int b) {
        if (panel.isEnded()) {
            return panel.evaluar();

        }
        int valor = Integer.MIN_VALUE;
        List<Pair> movementsAvaible = panel.getPossiblePairs();
        for (Pair position : movementsAvaible) {
            Panel newPanel = new Panel(panel);
            newPanel.positionSelected(position.getX(), position.getY(), iaPlayer.getActualTurn());
            valor = Math.max(valor, minValor(newPanel, a, b));
            if (valor >= b) {
                return valor;
            }
            a = Math.max(a, valor);
        }
        return valor;
    }

    private int minValor(Panel panel, int a, int b) {
        if (panel.isEnded()) {
            return panel.evaluar();
        }
        int valor = Integer.MAX_VALUE;
        List<Pair> movementsAvaible = panel.getPossiblePairs();
        for (Pair position : movementsAvaible) {
            Panel newPanel = new Panel(panel);
            newPanel.positionSelected(position.getX(), position.getY(), player.getActualTurn());
            valor = Math.min(valor,maxValor(newPanel, a, b));
            if (valor <= a) {
                return valor;
            }
            b = Math.min(b,valor);
        }
        return valor;
    }

}
