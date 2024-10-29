import java.util.*;

public class Main {
    public static void main(String[] args) {
    try {
        Scanner scanner = new Scanner(System.in);
        Partida partida = new Partida();

        System.out.print("Bienvenido al truco. Ingrese nombre del jugador: ");
        String nombre = scanner.next();

        partida.iniciarPartida();


        for (int i = 0; i < 3; i++) {
            System.out.println("Mano " + (i + 1));
            System.out.println("1: partida normal");
            System.out.println("2: Truco");
            System.out.println("3: Envido");
            System.out.println(nombre + " que opción desea elegir?");
            int opcion = scanner.nextInt();
            partida.ronda(opcion); // Solo llamamos a ronda una vez por acción
            if (partida.turno) {
                System.out.print("Elige qué carta jugar: 1, 2, 3? ");
                int cartaAJugar = scanner.nextInt() - 1; // ajustar el índice
                partida.jugada(partida.manoJugador().get(cartaAJugar));
            }
        }
    } catch(IndexOutOfBoundsException e) {
        System.out.println("El numero ingresado está fuera del rango permitido");
        }

    }
}