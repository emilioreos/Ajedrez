package ajedrez;

import java.util.LinkedList;

public class Pieza {
	private int valor;
	private LinkedList<Movimiento> movimientos;
	private Tipo tipo;
	private Tablero tablero;
	public Color color;
	//public boolean primera=true;
	
	public Pieza(Tipo tipo,Tablero tablero,Color color){
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
		this.tablero=tablero;
		this.color=color;
	}
	public String toString(){
		return tipo.toString();
	}
	public LinkedList<Movimiento> getMovimientos(){
		return movimientos;
	}
	public void calcularMovimientos(Posicion p){
		switch(tipo){
		case REY: 		Rey(p);
				  		break;
		case REINA: 	Reina(p);
						break;
		case ALFIL: 	Alfil(p);
						break;
		case TORRE: 	Torre(p);
						break;
		case CABALLO: 	Caballo(p);
					  	break;
		case PEON: 		Peon(p);
				   		break;
		}
	}
	public Tipo getTipe(){
		return tipo;
	}
	public int getValor(){
		return valor;
	}
	public void Peon(Posicion p){
		movimientos=new LinkedList<Movimiento>();
		if(color==Color.BLANCO){
			Posicion is,ds,ad;
			is=p.getSuperiorIzquierda();
			ds=p.getSuperiorDerecha();
			ad=p.getSuperior();
			Pieza IS,DS,AD;
			IS=tablero.getPieza(is);
			DS=tablero.getPieza(ds);
			AD=tablero.getPieza(ad);
			if(IS!=null&&IS.color==Color.NEGRO){
				movimientos.add(new Movimiento(p, is));
			}
			if(DS!=null&&DS.color==Color.NEGRO){
				movimientos.add(new Movimiento(p, ds));
			}
			if(AD==null){
				movimientos.add(new Movimiento(p, ad));
				if(p.fila==2){
					ad=ad.getSuperior();
					AD=tablero.getPieza(ad);
					if(AD==null){
						movimientos.add(new Movimiento(p, ad));
					}
				}
			}
			
		}else{
			Posicion is,ds,ad;
			is=p.getInferiorIzquierda();
			ds=p.getInferiorDerecha();
			ad=p.getInferior();
			Pieza IS,DS,AD;
			IS=tablero.getPieza(is);
			DS=tablero.getPieza(ds);
			AD=tablero.getPieza(ad);
			if(IS!=null&&IS.color==Color.BLANCO){
				movimientos.add(new Movimiento(p, is));
			}
			if(DS!=null&&DS.color==Color.BLANCO){
				movimientos.add(new Movimiento(p, ds));
			}
			if(AD==null){
				movimientos.add(new Movimiento(p, ad));
				if(p.fila==7){
					ad=ad.getInferior();
					AD=tablero.getPieza(ad);
					if(AD==null){
						movimientos.add(new Movimiento(p, ad));
					}
				}
			}
			
		}
	}
	public void Rey(Posicion p){
	
	}
	public void Reina(Posicion p){
		
	}
	public void Alfil(Posicion p){
		
	}
	public void Torre(Posicion p){
		
	}
	public void Caballo(Posicion p){
		
	}
}
