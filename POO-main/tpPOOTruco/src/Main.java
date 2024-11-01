
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Bienvenido al truco. Ingrese nombre del jugador: ");
        String nombre = scanner.next();

        Partida partida = new Partida();
        System.out.println("Hola " + nombre + ", ¡vamos a empezar la partida!");

        partida.ejecutarJuego();

        System.out.println("Gracias por jugar, " + nombre + "! Fin del juego.");
    }
}
