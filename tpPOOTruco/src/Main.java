import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Partida partida = new Partida();

        System.out.print("Bienvenido al truco. Ingrese nombre del jugador: ");
        String nombre = scanner.next();
        
        partida.iniciarPartida();

        System.out.println("Tu mano: " + partida.manoJugador());
        System.out.println("¿Deseas cantar Envido? (s/n)");
        String respuestaEnvido = scanner.next();

        // Envido
        if (respuestaEnvido.equalsIgnoreCase("s")) {
            partida.envido();
        }

        // Jugar 3 rondas o hasta que alguien gane el truco
        for (int i = 0; i < 3; i++) {
            System.out.println("Ronda ");
            System.out.println("1: Jugar carta");
            System.out.println("2: Truco");

            System.out.print(nombre + ", ¿qué opción deseas elegir? ");
            int opcion = scanner.nextInt();

            if (opcion==1) {
                    System.out.println("Elige qué carta jugar (1, 2 o 3): ");
                    int cartaAJugar = scanner.nextInt();
                    List<Carta> manoJugador = partida.manoJugador();
                    
                    if (cartaAJugar > 0 && cartaAJugar <= manoJugador.size()) {
                        Carta cartaElegida = manoJugador.get(cartaAJugar - 1);
                        partida.jugada(cartaElegida);
                        manoJugador.remove(cartaElegida); // Eliminar carta jugada de la mano
                    } else {
                        System.out.println("Opción inválida. Selecciona un número entre 1 y " + manoJugador.size());
                    }
                    break;}

                if (opcion==2){
                    partida.truco();
                    break;}

                if else{
                    System.out.println("Opción inválida.");
                    i--; // Repetir la ronda si la opción es inválida
                    break;
            }

            // Verificar si alguien ha ganado el truco
            if (partida.manoJugador().isEmpty() || partida.manoBot().isEmpty()) {
                break;
            }
        }

        // Mostrar los puntajes finales
        System.out.println("Puntaje Final:");
        System.out.println("Jugador: " + partida.puntaje_jugador);
        System.out.println("Bot: " + partida.puntaje_bot);
    }
}
