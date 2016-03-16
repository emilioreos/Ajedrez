package ajedrez;

import java.util.LinkedList;

public class Nodo {
	public Tablero tablero;
	public LinkedList<Nodo> hijos=new LinkedList<Nodo>();
	private Color color;
	public Nodo(/*Tablero t,*/Color c){
		//tablero=t;
		color=c;
	}
	public Color nextColor(){
		if(color==Color.BLANCO){
			return Color.NEGRO;
		}
		return Color.BLANCO;
	}
	public void addHijo(Nodo n){
		hijos.add(n);
	}
	public Contenedor crearArbol(Nodo n,int alfa,int beta,Color c,byte prof){
		if(n.hijos.size()==0||prof==0){
			Contenedor x=new Contenedor();
			x.tablero=null;
			x.valor=n.tablero.getValor();
			return x;
		}
		return null;
	}
}
