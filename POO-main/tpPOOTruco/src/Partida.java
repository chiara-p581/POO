
import java.util.*;

public class Partida {
    private Bot bot;
    private int puntajeBot;
    private int puntajeJugador;
    private boolean turno;
    private boolean juegoActivo;
    private List<Carta> manoJugador;
    private List<Carta> manoBot;

    public Partida() {
        this.bot = new Bot();
        this.puntajeBot = 0;
        this.puntajeJugador = 0;
        this.turno = true;
        this.juegoActivo = true; // Agregado para controlar el estado del juego
    }

    public void iniciarPartida() {
        manoJugador = repartirMano();
        manoBot = repartirMano();
        bot.setMano(manoBot);
        System.out.println("Mano de Bot: " + manoBot);
        System.out.println("Mano de Jugador: " + manoJugador);
    }

    public List<Carta> repartirMano() {
        Mazo mazo = new Mazo();
        return mazo.repartir();
    }

    public void ejecutarJuego() {
        Scanner scanner = new Scanner(System.in);
        iniciarPartida();

        // Preguntar al jugador si quiere cantar Envido
        System.out.println("¿Deseas cantar Envido? (s/n)");
        String respuestaEnvido = scanner.next();
        if (respuestaEnvido.equalsIgnoreCase("s")) {
            envido();
        }

        // Jugar las rondas hasta que termine el truco o se jueguen todas las rondas
        for (int i = 0; i < 3 && juegoActivo; i++) {
            System.out.println("\nRonda " + (i + 1));
            System.out.println("1: Jugar carta");
            System.out.println("2: Truco");
            System.out.print("¿Qué opción deseas elegir? ");

            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    jugada(pedirCartaAlJugador());
                    break;
                case 2:
                    truco();
                    if (!juegoActivo) {
                        // Si el truco termina la partida, mostramos el puntaje final
                        mostrarPuntajeFinal();
                        return;
                    }
                    break;
                default:
                    System.out.println("Opción inválida. Elige 1 o 2.");
                    i--; // Repetir la ronda si la opción es inválida
                    break;
            }
        }

        // Mostrar puntaje final si el juego sigue activo después de las rondas
        if (juegoActivo) {
            mostrarPuntajeFinal();
        }
    }

    public void jugada(Carta cartaJugador) {
        if (turno) {
            System.out.println("Jugador jugó la carta: " + cartaJugador);
            Carta cartaBot = bot.realizarJugada(cartaJugador.getJerarquia());
            System.out.println("Bot jugó la carta: " + cartaBot);

            if (cartaJugador.getJerarquia() > cartaBot.getJerarquia()) {
                System.out.println("¡Ganaste la ronda!");
                turno = true;
            } else {
                System.out.println("Perdiste la ronda.");
                turno = false;
            }
        } else {
            System.out.println("Bot jugó la carta: " + cartaJugador);
            Carta cartaJugadorNueva = pedirCartaAlJugador();
            System.out.println("Jugador jugó la carta: " + cartaJugadorNueva);

            if (cartaJugadorNueva.getJerarquia() > cartaJugador.getJerarquia()) {
                System.out.println("¡Ganaste la ronda!");
                turno = true;
            } else {
                System.out.println("Perdiste la ronda.");
                turno = false;
            }
        }
    }

    public void envido() {
        int envidoJugador = Carta.calcularEnvido(manoJugador);
        int envidoBot = Carta.calcularEnvido(manoBot);
        boolean botAcepta = bot.responderEnvido();

        if (botAcepta) {
            System.out.println("El bot acepta el envido.");
            System.out.println("Envido del jugador: " + envidoJugador);
            System.out.println("Envido del bot: " + envidoBot);

            if (envidoBot > envidoJugador) {
                puntajeBot += 2;
                System.out.println("El bot gana el envido.");
            } else {
                puntajeJugador += 2;
                System.out.println("El jugador gana el envido.");
            }
        } else {
            System.out.println("¡El bot rechaza el envido! ¡Ganaste el envido!");
            puntajeJugador += 1;
        }
    }

    public void truco() {
        System.out.println("Truco cantado! ¿Aceptas?");
        boolean botAcepta = bot.responderTruco();

        if (botAcepta) {
            System.out.println("El Bot acepta el Truco.");
        } else {
            System.out.println("El bot rechaza el Truco, ¡has ganado la partida!");
            puntajeJugador += 2; // Puntaje al jugador por truco rechazado
            juegoActivo = false;  // Terminar partida
        }
    }

    public Carta pedirCartaAlJugador() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Elija una de sus cartas: ");
        System.out.println(manoJugador);

        int num = scanner.nextInt();
        Carta cartaSeleccionada = manoJugador.get(num - 1);
        manoJugador.remove(cartaSeleccionada);

        return cartaSeleccionada;
    }

    public void mostrarPuntajeFinal() {
        System.out.println("\nPuntaje Final:");
        System.out.println("Jugador: " + puntajeJugador);
        System.out.println("Bot: " + puntajeBot);
    }
}