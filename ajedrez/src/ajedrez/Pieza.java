package ajedrez;

import java.util.LinkedList;
public class Pieza {
	public static final byte INFERIORES=0;
	public static final byte DERECHA=1;
	public static final byte IZQUIERDA=2;
	public static final byte SUPERIORES=3;
	public static final byte DERECHASUPERIOR=4;
	public static final byte IZQUIERDASUPERIOR=5;
	public static final byte DERECHAINFERIOR=6;
	public static final byte IZQUIERDAINFERIOR=7;
	
	private int valor;
	private LinkedList<Movimiento> movimientos;
	private Tipo tipo;
	private Tablero tablero;
	public Color color;
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
		String t=null;
		switch(tipo){
		case PEON: t="P";
		break;
		case REINA: t="Q";
		break;
		case REY : t="K";
		break;
		case CABALLO: t="C";
		break;
		case ALFIL:t="A";
		break;
		case TORRE:t="T";
		break;
		}
		if(color==Color.NEGRO){
			t=t.toLowerCase();
		}
		return t;
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
	public void setTablero(Tablero t){
		if(tablero==null){
			tablero=t;
		}
	}
	private void Peon(Posicion p){
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
			if(AD==null&&ad!=null){
				movimientos.add(new Movimiento(p, ad));
				if(p.fila==2){
					ad=ad.getSuperior();
					AD=tablero.getPieza(ad);
					if(AD==null){
						movimientos.add(new Movimiento(p, ad));
					}
				}
			}
			if(ad!=null&&ad.fila==8){
				tipo=Tipo.REINA;
				valor=9;
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
			if(AD==null&&ad!=null){
				movimientos.add(new Movimiento(p, ad));
				if(p.fila==7){
					ad=ad.getInferior();
					AD=tablero.getPieza(ad);
					if(AD==null){
						movimientos.add(new Movimiento(p, ad));
					}
				}
			}
			if(ad!=null&&ad.fila==1){
				tipo=Tipo.REINA;
				valor=9;
			}
		}
	}
	private void Rey(Posicion p){
		Posicion is,s,ds,i,d,ii,in,di;
		movimientos=new LinkedList<Movimiento>();
		is=p.getSuperiorIzquierda();
		s=p.getSuperior();
		ds=p.getSuperiorDerecha();
		i=p.getIzquierda();
		d=p.getDerecha();
		ii=p.getInferiorIzquierda();
		in=p.getInferior();
		di=p.getInferiorDerecha();
		Pieza IS=tablero.getPieza(is),
				S=tablero.getPieza(s),
				DS=tablero.getPieza(ds),
				I=tablero.getPieza(i),
				D=tablero.getPieza(d),
				II=tablero.getPieza(ii),
				IN=tablero.getPieza(in),
				DI=tablero.getPieza(di);
		if((is!=null&&IS==null)||(IS!=null&&IS.color!=color)){
			movimientos.add(new Movimiento(p, is));
		}
		if((s!=null&&S==null)||(S!=null&&S.color!=color)){
			movimientos.add(new Movimiento(p, s));
		}
		if((ds!=null&&DS==null)||(DS!=null&&DS.color!=color)){
			movimientos.add(new Movimiento(p, ds));
		}
		if((i!=null&&I==null)||(I!=null&&I.color!=color)){
			movimientos.add(new Movimiento(p, i));
		}
		if((d!=null&&D==null)||(D!=null&&D.color!=color)){
			movimientos.add(new Movimiento(p, d));
		}
		if((ii!=null&&II==null)||(II!=null&&II.color!=color)){
			movimientos.add(new Movimiento(p, ii));
		}
		if((in!=null&&IN==null)||(IN!=null&&IN.color!=color)){
			movimientos.add(new Movimiento(p, in));
		}
		if((di!=null&&DI==null)||(DI!=null&&DI.color!=color)){
			movimientos.add(new Movimiento(p, di));
		}
	}
	private void Reina(Posicion p){
		movimientos=new LinkedList<Movimiento>();
		movimientos.addAll(movimientosPorForma(p, IZQUIERDASUPERIOR));
		movimientos.addAll(movimientosPorForma(p,IZQUIERDAINFERIOR));
		movimientos.addAll(movimientosPorForma(p, DERECHASUPERIOR));
		movimientos.addAll(movimientosPorForma(p, DERECHAINFERIOR));
		movimientos.addAll(movimientosPorForma(p, SUPERIORES));
		movimientos.addAll(movimientosPorForma(p,INFERIORES));
		movimientos.addAll(movimientosPorForma(p, IZQUIERDA));
		movimientos.addAll(movimientosPorForma(p, DERECHA));
	}
	private void Alfil(Posicion p){
		movimientos=new LinkedList<Movimiento>();
		movimientos.addAll(movimientosPorForma(p, IZQUIERDASUPERIOR));
		movimientos.addAll(movimientosPorForma(p,IZQUIERDAINFERIOR));
		movimientos.addAll(movimientosPorForma(p, DERECHASUPERIOR));
		movimientos.addAll(movimientosPorForma(p, DERECHAINFERIOR));
	}
	public void Torre(Posicion p){
		movimientos=new LinkedList<Movimiento>();
		movimientos.addAll(movimientosPorForma(p, SUPERIORES));
		movimientos.addAll(movimientosPorForma(p,INFERIORES));
		movimientos.addAll(movimientosPorForma(p, IZQUIERDA));
		movimientos.addAll(movimientosPorForma(p, DERECHA));
	}
	private void Caballo(Posicion p){
		movimientos = new LinkedList<Movimiento>();
		Posicion esi=p.getSuperiorIzquierda(),esd=p.getSuperiorDerecha(),eii=p.getInferiorIzquierda(),eid=p.getInferiorDerecha();
		if(esi!=null){
			Posicion p1=esi.getSuperior(),p2=esi.getIzquierda();
			if(p1!=null){
				Pieza x=tablero.getPieza(p1);
				if(x==null||x.color!=color){
					movimientos.add(new Movimiento(p, p1));
				}
			}
			if(p2!=null){
				Pieza x=tablero.getPieza(p2);
				if(x==null||x.color!=color){
					movimientos.add(new Movimiento(p, p2));
				}
			}
		}
		if(esd!=null){
			Posicion p1=esd.getSuperior(),p2=esd.getDerecha();
			if(p1!=null){
				Pieza x=tablero.getPieza(p1);
				if(x==null||x.color!=color){
					movimientos.add(new Movimiento(p, p1));
				}
			}
			if(p2!=null){
				Pieza x=tablero.getPieza(p2);
				if(x==null||x.color!=color){
					movimientos.add(new Movimiento(p, p2));
				}
			}
		}
		if(eii!=null){
			Posicion p1=eii.getInferior(),p2=eii.getIzquierda();
			if(p1!=null){
				Pieza x=tablero.getPieza(p1);
				if(x==null||x.color!=color){
					movimientos.add(new Movimiento(p, p1));
				}
			}
			if(p2!=null){
				Pieza x=tablero.getPieza(p2);
				if(x==null||x.color!=color){
					movimientos.add(new Movimiento(p, p2));
				}
			}
		}
		if(eid!=null){
			Posicion p1=eid.getInferior(),p2=eid.getDerecha();
			if(p1!=null){
				Pieza x=tablero.getPieza(p1);
				if(x==null||x.color!=color){
					movimientos.add(new Movimiento(p, p1));
				}
			}
			if(p2!=null){
				Pieza x=tablero.getPieza(p2);
				if(x==null||x.color!=color){
					movimientos.add(new Movimiento(p, p2));
				}
			}
		}	
	}
	public Object clone(){
		Pieza p=new Pieza(tipo,null, color);
		return p;
	}
	public Posicion getPosForma(Posicion p,byte forma){
		Posicion s=null;
		if(forma==INFERIORES){
			s=p.getInferior();
		}else if(forma==DERECHA){
			s=p.getDerecha();
		}else if(forma==IZQUIERDA){
			s=p.getIzquierda();
		}else if(forma==SUPERIORES){
			s=p.getSuperior();
		}else if(forma==DERECHASUPERIOR){
			s=p.getSuperiorDerecha();
		}else if(forma==IZQUIERDASUPERIOR){
			s=p.getSuperiorIzquierda();
		}else if(forma==DERECHAINFERIOR){
			s=p.getInferiorDerecha();
		}else if(forma==IZQUIERDAINFERIOR){
			s=p.getInferiorIzquierda();
		}
		return s;
	}
	private LinkedList<Movimiento> movimientosPorForma(Posicion p,byte forma){
		LinkedList<Movimiento> movimientos=new LinkedList<Movimiento>();
		Posicion s=getPosForma(p, forma);
		Pieza S=tablero.getPieza(s);
		while(s!=null){
			if(S==null){
				movimientos.add(new Movimiento(p, s));
			}else if(S!=null&&S.color!=color){
				movimientos.add(new Movimiento(p, s));
				break;
			}else{
				break;
			}
			s=getPosForma(s, forma);
			S=tablero.getPieza(s);
		}
		return movimientos;
	}
}
