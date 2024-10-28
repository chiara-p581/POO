import java.util.*;

public class Mazo {
    private static List<Carta> cartas;

    public Mazo(){
        cartas = new ArrayList<>();
        String[] palo = {"Oro","Espada","Basto","Copa"};
        int[] numero = {1,2,3,4,6,7,10,11,12};

        for (String palos : palo){
            for (int numeros : numero){
                cartas.add(new Carta(palos,numeros));
            }
        }
        Collections.shuffle(cartas);
    }

    //public static void crearMazo(){}

    public List<Carta> repartir(){
        List<Carta> mano = new ArrayList<>();
        for(int i = 1;i<=3;i++){
            mano.add(cartas.removeFirst());
        }
        return mano;
    }
}
