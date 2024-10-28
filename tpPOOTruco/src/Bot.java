import java.util.*;
public class Bot {
    private List<Carta> mano;
    private boolean primera;
    private boolean segunda;
    private int envido;

    public Bot(){
        primera = true;
        segunda = true;
    }

    public void setMano(List<Carta> mano) {
        mano.sort(Comparator.comparingInt(Carta::getJerarquia).reversed());
        System.out.print("Mano del bot: ");
        System.out.println(mano.toString());
        System.out.println("");
        envido = Carta.calcularEnvido(mano);
    }

    public boolean responderEnvido(){
        if (envido > 26){
            return true;
        } else if(envido == 20 && Math.random()<0.5){ // 50% de probabilidad de aceptar un envido para mentir
            return true;
        }else{
            return false;
        }
    }

    public Carta realizarJugada(int num){

            if(num ==0){
                if(esPrimera()){
                    if(jugarCartaMedia().getJerarquia() > 4){
                        return jugarCartaMedia();
                    } else{
                        return jugarCartaBaja();
                    }
                }
                if(esSegunda()){
                    if(jugarCartaBaja().getJerarquia() > 7){
                        //cantar truco
                    }else{
                        return jugarCartaBaja();
                    }
                }
                if (jugarCartaAlta().getJerarquia() > 7){
                    //cantar truco
                    return jugarCartaAlta();
                }
            }

            if(num != 0){
                if(jugarParda(num) != null && jugarCartaAlta().getJerarquia()>5){
                    return jugarParda(num);
                }
                if(elegirMejorOpcion(num) != null){
                    return elegirMejorOpcion(num);
                }else{
                    return jugarCartaBaja();
                }
            }

        return null;
    }

    public Carta jugarCartaAlta() {
        return mano.get(0);
    }
    public Carta jugarCartaMedia() {
        if(mano.size() == 3){
            return mano.get(1);
        }
        return null;
    }

    public Carta jugarCartaBaja() {
        List<Carta> aux = mano.reversed();
        return aux.get(0);
    }
    public Carta elegirMejorOpcion(int valor){
        List<Carta> aux = mano.reversed();
        for(Carta cart : aux){
            if (cart.getJerarquia() > valor){
                return cart;
            }
        }
        return null;
    }
    public Carta jugarParda(int valor){
        for(Carta carta : mano){
            if(carta.getValor() ==valor){
                return carta;
            }
        }
        return null;
    }


    public void setPrimera(boolean primera) {
        this.primera = primera;
    }

    public void setSegunda(boolean segunda) {
        this.segunda = segunda;
    }
    public boolean esPrimera(){
        return mano.size()==3;
    }
    public boolean esSegunda(){
        return mano.size()==2;
    }
}
