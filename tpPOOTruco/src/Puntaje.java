// no se necesita

public class Puntaje {
    /*envido:
    quiero -> 2 puntos
    no quiero -> 1 punto
    truco:
     quiero -> 2 puntos
     no quiero -> 1 punto
    partida continua 1 punto
    */
	    private int puntosJugador;
	    private int puntosBot;
	    private int puntosEnvidoJugador;
	    private int puntosEnvidoBot;
	    private int puntosTrucoJugador;
	    private int puntosTrucoBot;
	    private int rondasJugador;
	    private int rondasBot;

	    public Puntaje() {
	        this.puntosJugador = 0;
	        this.puntosBot = 0;
	        this.puntosEnvidoJugador = 0;
	        this.puntosEnvidoBot = 0;
	        this.puntosTrucoJugador = 0;
	        this.puntosTrucoBot = 0;
	    }
    
	    //Metodos por si no hay envido ni truco
	    public void sumarPuntosJugador(int puntos) { 
	    	 rondasJugador += puntos;
		     puntosJugador += puntos;
	    }
	    public void sumarPuntosBot(int puntos) {
	    	 rondasBot += puntos;
		     puntosJugador += puntos;
	    }

	    // Métodos para sumar puntos de envido
	    public void sumarPuntosEnvidoJugador(int puntos) {
	        puntosEnvidoJugador += puntos;
	        puntosJugador += puntos;
	    }

	    public void sumarPuntosEnvidoBot(int puntos) {
	        puntosEnvidoBot += puntos;
	        puntosBot += puntos;
	    }

	    // Métodos para sumar puntos de truco
	    public void sumarPuntosTrucoJugador(int puntos) {
	        puntosTrucoJugador += puntos;
	        puntosJugador += puntos;
	    }

	    public void sumarPuntosTrucoBot(int puntos) {
	        puntosTrucoBot += puntos;
	        puntosBot += puntos;
	    }

	    // Métodos para obtener los puntos totales
	    public int getPuntosJugador() {
	        return puntosJugador;
	    }

	    public int getPuntosBot() {
	        return puntosBot;
	    }
	}

