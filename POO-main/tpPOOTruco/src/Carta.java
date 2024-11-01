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
        if (palo.equals("Espada") && valor == 1) return 14; // 1 de Espada
        if (palo.equals("Basto") && valor == 1) return 13;  // 1 de Basto
        if (palo.equals("Espada") && valor == 7) return 12; // 7 de Espada
        if (palo.equals("Oro") && valor == 7) return 11;    // 7 de Oro
        if (valor == 3) return 10;                           // Todos los 3
        if (valor == 2) return 9;                            // Todos los 2
        if (valor == 1 && (palo.equals("Oro") || palo.equals("Copa"))) return 8; // 1 de Oro y Copa
        if (valor == 12) return 7;                           // Todos los 12
        if (valor == 11) return 6;                           // Todos los 11
        if (valor == 10) return 5;                           // Todos los 10
        if (valor == 7 && (palo.equals("Basto") || palo.equals("Copa"))) return 4; // 7 de Basto y Copa
        if (valor == 6) return 3;                            // Todos los 6
        if (valor == 5) return 2;                            // Todos los 5
        return 1;                                            // El 4 y otros valores bajos
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