public class EstadoJuego {
    private boolean faseCanto;
    private boolean cantoEnvido;
    private boolean cantoTruco;
    private boolean primeraMano;
    private boolean segundaMano;
    private boolean terceraMano;
    private int puntajeEnvido;

    // Constructor para inicializar el estado del juego
    public EstadoJuego(boolean faseCanto, boolean cantoEnvido, boolean cantoTruco,
                       boolean primeraMano, boolean segundaMano, boolean terceraMano,
                       int puntajeEnvido) {
        this.faseCanto = faseCanto;
        this.cantoEnvido = cantoEnvido;
        this.cantoTruco = cantoTruco;
        this.primeraMano = primeraMano;
        this.segundaMano = segundaMano;
        this.terceraMano = terceraMano;
        this.puntajeEnvido = puntajeEnvido;
    }

    // Métodos de consulta
    public boolean esFaseCanto() { return faseCanto; }
    public boolean sePuedeCantarEnvido() { return !cantoEnvido; }
    public boolean envidoCantado() { return cantoEnvido; }
    public boolean sePuedeCantarTruco() { return !cantoTruco; }
    public boolean trucoCantado() { return cantoTruco; }
    public boolean esPrimeraMano() { return primeraMano; }
    public boolean esSegundaMano() { return segundaMano; }
    public boolean esTerceraMano() { return terceraMano; }
    public int puntajeEnvido() { return puntajeEnvido; }

    // Métodos para saber el resultado de las manos previas
    public boolean perdioPrimeraMano() {
        // Lógica para saber si el bot perdió la primera mano
        return false; // Ejemplo, ajustar según estado real
    }

    public boolean ganoPrimeraMano() {
        // Lógica para saber si el bot ganó la primera mano
        return true; // Ejemplo, ajustar según estado real
    }

    public boolean empateHastaAhora() {
        // Lógica para saber si el juego está empatado
        return false; // Ejemplo, ajustar según estado real
    }

    public boolean ganoAmbasManosAnteriores() {
        // Lógica para saber si el bot ganó las dos primeras manos
        return false; // Ejemplo, ajustar según estado real
    }
}
