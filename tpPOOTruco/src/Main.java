import java.util.*;

public class Main {
    public static void main(String[] args) {
    try {
        Scanner scanner = new Scanner(System.in);
        Partida partida = new Partida();
        Puntaje puntaje = new Puntaje();
        
        Mazo mazo=new Mazo();
        List<Carta> manoJugador = partida.repartirMano();
        List<Carta> manoBot = partida.repartirMano();

        System.out.print("Bienvenido al truco. Ingrese nombre del jugador: ");
        String nombre = scanner.next();
        
        System.out.print("Tu mano: " + manoJugador);
        System.out.println("¿Deseas cantar Envido? (s/n)");
        String respuestaEnvido = scanner.next();
        
        if(respuestaEnvido.equalsIgnoreCase("s")) {
        	String resultadoEnvido=partida.verificarGanadorEnvido(manoJugador,manoBot);
        	System.out.println(resultadoEnvido);
        	if(resultadoEnvido.contains("Jugador")) {
        		puntaje.sumarPuntosEnvidoJugador(2);
        	} else if(resultadoEnvido.contains("Bot")) {
        		puntaje.sumarPuntosEnvidoBot(2);
        	}
        }
        
        int rondasJugador=0;
        int rondasBot=0;

        for (int i = 0; i < 3; i++) {
            System.out.println("Mano " + (i + 1));
            System.out.println("1: Continuar partida");
            System.out.println("2: Truco");
            System.out.println(nombre + " que opcion desea elegir?");
            int opcion = scanner.nextInt();
            
            if (opcion==2) {
            	System.out.println("El bot aceptara el truco?");
            	boolean trucoAceptado = new Random().nextBoolean();
            	
            	if(trucoAceptado) {
            		System.out.println("El bot acepto el truco");
            		System.out.println("Elige que carta jugar (1, 2 o 3): ");
                    int cartaTruco=scanner.nextInt();
                    Carta cartaTrucoElegida = manoJugador.get(cartaTruco - 1);
                    Carta cartaBot = manoBot.get(i);
                    System.out.println("El bot jugó: " + cartaBot);
                    
                    if (cartaTrucoElegida.getJerarquia() > cartaBot.getJerarquia()) {
                        System.out.println("Ganaste el truco");
                        puntaje.sumarPuntosTrucoJugador(2); 
                    } else if (cartaBot.getJerarquia() > cartaTrucoElegida.getJerarquia()) {
                        System.out.println("El bot gana el truco");
                        puntaje.sumarPuntosTrucoBot(2);
                    } else {
                        System.out.println("Es empate");
                    }
            	}
            	else {
            		System.out.println("El bot no quiso el truco y la partida finaliza. Sumas 1 punto");
            		puntaje.sumarPuntosTrucoJugador(1);
            	}
            	break;
            }
            //jugador
            System.out.println("Elige que carta jugar (1, 2 o 3): ");
            int cartaAJugar=scanner.nextInt();
            Carta cartaElegida=manoJugador.get(cartaAJugar-1);
            
            //bot
            Carta cartaBot=manoBot.get(i);
            System.out.println("El bot jugo: " + cartaBot);
            
            
            //comparar cartas
            if(cartaElegida.getJerarquia()>cartaBot.getJerarquia()) {
            	System.out.println("Ganaste esta ronda");
            	rondasJugador++;
            }
            else if(cartaBot.getJerarquia()>cartaElegida.getJerarquia()){
            	System.out.println("El bot gana esta ronda");
            	rondasBot++;
            }
            else {
            	System.out.println("Es empate");
            }
            if (rondasJugador == 2) {
                System.out.println("¡Ganaste la partida! Sumaste 1 punto.");
                puntaje.sumarPuntosJugador(1);
                break; // Termina el ciclo si el jugador ganó
            } else if (rondasBot == 2) {
                System.out.println("El bot ganó la partida.");
                puntaje.sumarPuntosBot(1);
                break; // Termina el ciclo si el bot ganó
            }
        }
        
        System.out.println("Puntajes finales: ");
        System.out.println("Jugador: " + puntaje.getPuntosJugador());
        System.out.println("Bot: " + puntaje.getPuntosBot());
        
    } catch(IndexOutOfBoundsException e) {
        System.out.println("El numero ingresado está fuera del rango permitido");
        }

    }
}
