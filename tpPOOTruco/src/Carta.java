import java.util.*;

public class Carta {
    private String palo;  // El palo de la carta (Espada, Basto, Oro, Copa)
    private int valor;    // El valor numérico de la carta (1-7, 10-12)

    // Constructor de la clase Carta
    public Carta(String palo, int valor) {
        this.palo = palo;
        this.valor = valor;
    }

    // Getter para el palo de la carta
    public String getPalo() {
        return palo;
    }

    // Getter para el valor de la carta
    public int getValor() {
        return valor;
    }

    // Método para obtener la jerarquía del Truco
    public int getJerarquia() {

        if (palo.equals("Espada") && valor == 1) return 18;
        if (palo.equals("Basto") && valor == 1) return 17;
        if (palo.equals("Espada") && valor == 7) return 16;
        if (palo.equals("Oro") && valor == 7) return 15;
        if (palo.equals("Espada") && valor == 3) return 14;
        if (palo.equals("Basto") && valor == 3) return 13;
        if (palo.equals("Espada") && valor == 2) return 12;
        if (palo.equals("Basto") && valor == 2) return 11;
        if (palo.equals("Oro") && valor == 2) return 10;
        if (palo.equals("Copa") && valor == 2) return 9;
        if (valor == 1) return 8;
        if (valor == 12) return 7;
        if (valor == 11) return 6;
        if (valor == 10) return 5;
        if (valor == 7) return 4;
        if (valor == 6) return 3;
        if (valor == 5) return 2;
        return 1; // El 4
    }

    public int getValorEnvido(){
        if(valor == 10 || valor == 11 || valor ==12){
            return 0;
        }
        return valor;
    }

    public static int calcularEnvido(List<Carta> mano) {
        // Agrupar cartas por palo
        Map<String, List<Carta>> cartasPorPalo = new HashMap<>();
        for (Carta carta : mano) { //*********
            cartasPorPalo.computeIfAbsent(carta.getPalo(), k -> new ArrayList<>()).add(carta);
        }

        int maxEnvido = 0;

        // Calcular el envido según las cartas agrupadas por palo
        for (List<Carta> cartasMismoPalo : cartasPorPalo.values()) {
            if (cartasMismoPalo.size() > 1) {
                // Ordenar cartas del mismo palo por valor de envido, de mayor a menor
                cartasMismoPalo.sort((c1, c2) -> Integer.compare(c2.getValorEnvido(), c1.getValorEnvido()));
                int envido = cartasMismoPalo.get(0).getValorEnvido() + cartasMismoPalo.get(1).getValorEnvido() + 20;
                maxEnvido = Math.max(maxEnvido, envido);
            } else {
                // Si solo hay una carta de este palo, considerar su valor para envido
                maxEnvido = Math.max(maxEnvido, cartasMismoPalo.get(0).getValorEnvido());
            }
        }

        return maxEnvido;
    }
    


    // Método para representar la carta como una cadena de texto
    @Override
    public String toString() {
        return valor + " de " + palo;
    }
}
