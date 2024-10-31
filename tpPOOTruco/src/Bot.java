import java.util.*;

public class Bot {
    private List<Carta> mano;
    private boolean primera;
    private int envido;

    public Bot() {
        primera = true;
    }

    public void setMano(List<Carta> mano) {
        this.mano = new ArrayList<>(mano); // Copia la mano para evitar modificarla externamente
        this.mano.sort(Comparator.comparingInt(Carta::getJerarquia).reversed()); // Ordena la mano por jerarquía descendente
        envido = Carta.calcularEnvido(this.mano);
    }

    public boolean responderEnvido() {
        if (envido > 26) {
            return true;
        } else if (envido >= 20 && Math.random() < 0.5) { // 50% probabilidad de aceptar un envido para mentir
            return true;
        }
        return false;
    }

    public boolean responderTruco() {
        return !mano.isEmpty() && jugarCartaAlta().getJerarquia() > 7; // Acepta el truco si tiene una carta alta
    }

    public Carta realizarJugada(int jerarquiaRival) {
        Carta cartaAJugar;
        if (jerarquiaRival == 0) { // Primera jugada del bot
            cartaAJugar = esPrimera() && jugarCartaMedia() != null && jugarCartaMedia().getJerarquia() > 4
                    ? jugarCartaMedia() : jugarCartaBaja();
        } else {
            cartaAJugar = elegirMejorOpcion(jerarquiaRival); // Juega la mejor opción posible
            if (cartaAJugar == null) {
                cartaAJugar = jugarCartaBaja(); // Si no tiene mejor opción, juega la carta baja
            }
        }
        mano.remove(cartaAJugar); // Elimina la carta jugada de la mano del bot
        return cartaAJugar;
    }

    public void cantarTruco() {
        System.out.println("El bot canta truco.");
    }

    private Carta jugarCartaAlta() {
        return mano.isEmpty() ? null : mano.get(0); // La carta más alta es la primera después de ordenar
    }

    private Carta jugarCartaMedia() {
        return mano.size() == 3 ? mano.get(1) : null; // Devuelve la carta media si tiene tres cartas en la mano
    }

    private Carta jugarCartaBaja() {
        return mano.isEmpty() ? null : mano.get(mano.size() - 1); // La carta baja es la última después de ordenar
    }

    private Carta elegirMejorOpcion(int jerarquiaRival) {
        for (Carta carta : mano) { // Busca la carta más baja que aún gane a la carta del jugador
            if (carta.getJerarquia() > jerarquiaRival) {
                return carta;
            }
        }
        return null; // Si no encuentra una carta mejor, devuelve null
    }

    public void setPrimera(boolean primera) {
        this.primera = primera;
    }

    public boolean esPrimera() {
        return mano.size() == 3;
    }
}
