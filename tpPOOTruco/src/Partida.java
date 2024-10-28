import java.util.*;

public class Partida {
    private Bot bot;
    private int puntaje_bot;
    private int puntaje_jugador;
    protected boolean turno;
    private List<Carta> manoJugador;
    private List<Carta> manoBot;

    public Partida() {
        this.bot = new Bot();
        this.puntaje_bot = 0;
        this.puntaje_jugador = 0;
        this.turno = true;
    }

    public void iniciarPartida(){
        manoJugador = repartirMano();
        manoBot = repartirMano();
        System.out.println("Mano de Bot:"+ manoBot);
        System.out.println("Mano de Jugador:"+ manoJugador);
    }

    public List<Carta> repartirMano(){
        Mazo mazo = new Mazo();
        bot.setMano(mazo.repartir());
        return mazo.repartir();
    }

    public void jugada(Carta carta){
        if (turno){
            System.out.println("Jugador jugó la carta: "+ carta.toString());
            Carta carta_bot = bot.realizarJugada(carta.getJerarquia());
            System.out.println("Bot jugó la carta: "+ carta_bot.toString());
            if (carta.getJerarquia() > carta_bot.getJerarquia()){
                System.out.println("Ganaste la partida!");
                bot.setPrimera(false);
            }else{
                System.out.println("Perdiste la partida!");
                bot.setPrimera(true);
            }

        }
    }

    public void envido(){
        int envidoJugador = Carta.calcularEnvido(manoJugador);
        int envidoBot = Carta.calcularEnvido(manoBot);
        boolean botAcepta = bot.responderEnvido();
        if (botAcepta) {
            System.out.println("El bot acepta el envido.");
            System.out.println("Envido de jugador: "+ envidoJugador);
            System.out.println("Envido de Bot: "+ envidoBot);
            if (envidoBot > envidoJugador) {
                puntaje_bot += 2;
                System.out.println("El bot gana el envido.");
            } else {
                // Si el jugador tiene un envido igual o mayor
                puntaje_jugador += 2;
                System.out.println("El jugador gana el envido.");
            }
        } else {
            System.out.println("¡El bot rechaza el envido! ¡Ganaste el envido!");
            puntaje_jugador += 2;
        }
    }

    public void truco(){
        
    }

    public void ronda(int valor){
        for(int i=0;i<3;i++){
            switch (valor){
                case 1:
                    //jugada
                    if (!repartirMano().isEmpty()){

                    }
                    break;
                case 2:
                    //truco
                case 3:
                    //envido
            }
        }
    }

    public List<Carta> manoJugador(){
        return manoJugador;
    }

    public List<Carta> manoBot(){
        return manoBot;
    }

}
