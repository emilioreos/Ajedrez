package ajedrez;

import java.util.LinkedList;

public class Pieza {
	private int valor;
	public LinkedList<Pieza> movimientos;
	public int fila;
	public char columna;
	private Tipo tipo;
	private Tablero tablero;
	public Color color;
	private boolean primera=true;
	public Pieza(Tipo tipo,int fila,char columna,Tablero tablero,Color color){
		switch(tipo){
		case REY: valor=99999999;
				  break;
		case REINA: valor=9;
					break;
		case ALFIL: valor=3;
					break;
		case TORRE: valor=5;
					break;
		case CABALLO: valor=3;
					break;
		case PEON: valor=1;
				   break;
		}
		this.tipo=tipo;
		this.fila=fila;
		this.columna=columna;
		this.tablero=tablero;
		this.color=color;
	}
	public void calcularMovimientos(){
		switch(tipo){
		case REY: 		Rey();
				  		break;
		case REINA: 	Reina();
						break;
		case ALFIL: 	Alfil();
						break;
		case TORRE: 	Torre();
						break;
		case CABALLO: 	Caballo();
					  	break;
		case PEON: 		Peon();
				   		break;
		}
	}
	public Tipo getTipe(){
		return tipo;
	}
	public int getValor(){
		return valor+1;
	}
	public void Peon(){
		
	}
	public void Rey(){
	
	}
	public void Reina(){
		
	}
	public void Alfil(){
		
	}
	public void Torre(){
		
	}
	public void Caballo(){
		
	}
}
