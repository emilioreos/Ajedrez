package ajedrez;

public class Movimiento{
	public Posicion origen;
	public Posicion destino;
	public Movimiento(Posicion o,Posicion d){
		origen=o;
		destino=d;
	}
	public String toString(){
		return origen.toString()+"->"+destino.toString();
	}
}