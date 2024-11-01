import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Resumen {
    private List<Carta> mano_jugador;
    private List<Carta> mano_bot;


    public Resumen(List<Carta> mano_jugador, List<Carta> mano_bot) {
        this.mano_jugador = mano_jugador;
        this.mano_bot = mano_bot;
    }

    public void resumenJuego(int puntaje_jugador, int puntaje_bot){
        String nombreArchivo = "resumen_partida.txt";
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write("Resumen de la partida de Truco\n");
            writer.write("-----------------------------\n");
            writer.write("Puntaje final:\n");
            writer.write("Jugador: " + puntaje_jugador + "\n");
            writer.write("Bot: " + puntaje_bot + "\n\n");

            writer.write("Mano del jugador:\n");
            for (Carta carta : mano_jugador) {
                writer.write(carta + "\n");
            }

            writer.write("\nMano del bot:\n");
            for (Carta carta : mano_bot) {
                writer.write(carta + "\n");
            }

            // Agrega otros detalles, como el resultado de cada ronda o el envido
            writer.write("\nDetalles adicionales...\n");

            System.out.println("Resumen de la partida guardado en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar el resumen de la partida: " + e.getMessage());
        }
    }
}
