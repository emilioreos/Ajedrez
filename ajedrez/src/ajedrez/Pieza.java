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
		
		if(color==Color.BLANCO){
			if((is!=null&&IS==null)||(IS!=null&&IS.color==Color.NEGRO)){
				movimientos.add(new Movimiento(p, is));
			}
			if((s!=null&&S==null)||(S!=null&&S.color==Color.NEGRO)){
				movimientos.add(new Movimiento(p, s));
			}
			if((ds!=null&&DS==null)||(DS!=null&&DS.color==Color.NEGRO)){
				movimientos.add(new Movimiento(p, ds));
			}
			if((i!=null&&I==null)||(I!=null&&I.color==Color.NEGRO)){
				movimientos.add(new Movimiento(p, i));
			}
			if((d!=null&&D==null)||(D!=null&&D.color==Color.NEGRO)){
				movimientos.add(new Movimiento(p, d));
			}
			if((ii!=null&&II==null)||(II!=null&&II.color==Color.NEGRO)){
				movimientos.add(new Movimiento(p, ii));
			}
			if((in!=null&&IN==null)||(IN!=null&&IN.color==Color.NEGRO)){
				movimientos.add(new Movimiento(p, in));
			}
			if((di!=null&&DI==null)||(DI!=null&&DI.color==Color.NEGRO)){
				movimientos.add(new Movimiento(p, di));
			}
		}else{
			if((is!=null&&IS==null)||(IS!=null&&IS.color==Color.BLANCO)){
				movimientos.add(new Movimiento(p, is));
			}
			if((s!=null&&S==null)||(S!=null&&S.color==Color.BLANCO)){
				movimientos.add(new Movimiento(p, s));
			}
			if((ds!=null&&DS==null)||(DS!=null&&DS.color==Color.BLANCO)){
				movimientos.add(new Movimiento(p, ds));
			}
			if((i!=null&&I==null)||(I!=null&&I.color==Color.BLANCO)){
				movimientos.add(new Movimiento(p, i));
			}
			if((d!=null&&D==null)||(D!=null&&D.color==Color.BLANCO)){
				movimientos.add(new Movimiento(p, d));
			}
			if((ii!=null&&II==null)||(II!=null&&II.color==Color.BLANCO)){
				movimientos.add(new Movimiento(p, ii));
			}
			if((in!=null&&IN==null)||(IN!=null&&IN.color==Color.BLANCO)){
				movimientos.add(new Movimiento(p, in));
			}
			if((di!=null&&DI==null)||(DI!=null&&DI.color==Color.BLANCO)){
				movimientos.add(new Movimiento(p, di));
			}
		}
	}
	private void Reina(Posicion p){
		movimientos=new LinkedList<Movimiento>();
		movimientos.addAll(movimientosDSIzquierda(p));
		movimientos.addAll(movimientosDIIzquierda(p));
		movimientos.addAll(movimientosDSDerecha(p));
		movimientos.addAll(movimientosDIDerecha(p));
		movimientos.addAll(movimientosSuperiores(p));
		movimientos.addAll(movimientosInferiores(p));
		movimientos.addAll(movimientosIzquierda(p));
		movimientos.addAll(movimientosDerecha(p));
	}
	private void Alfil(Posicion p){
		movimientos=new LinkedList<Movimiento>();
		movimientos.addAll(movimientosDSIzquierda(p));
		movimientos.addAll(movimientosDIIzquierda(p));
		movimientos.addAll(movimientosDSDerecha(p));
		movimientos.addAll(movimientosDIDerecha(p));
	}
	public void Torre(Posicion p){
		movimientos=new LinkedList<Movimiento>();
		movimientos.addAll(movimientosSuperiores(p));
		movimientos.addAll(movimientosInferiores(p));
		movimientos.addAll(movimientosIzquierda(p));
		movimientos.addAll(movimientosDerecha(p));
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
	private LinkedList<Movimiento> movimientosDSIzquierda(Posicion p){
		LinkedList<Movimiento> movimientos=new LinkedList<Movimiento>();
		Posicion s=p.getSuperiorIzquierda();
		Pieza S=tablero.getPieza(s);
		//todos los movimientos hacia la izquierda superior
		while(s!=null){
			if(color==Color.BLANCO){
				if(S==null){
					movimientos.add(new Movimiento(p, s));
				}else if(S!=null&&S.color==Color.NEGRO){
					movimientos.add(new Movimiento(p, s));
					break;
				}else{
					break;
				}
				s=s.getSuperiorIzquierda();
				S=tablero.getPieza(s);
			}else{
				if(S==null){
					movimientos.add(new Movimiento(p, s));
				}else if(S!=null&&S.color==Color.BLANCO){
					movimientos.add(new Movimiento(p, s));
					break;
				}else{
					break;
				}
				s=s.getSuperiorIzquierda();
				S=tablero.getPieza(s);
			}
		}
		return movimientos;
	}
	private LinkedList<Movimiento> movimientosDSDerecha(Posicion p){
		LinkedList<Movimiento> movimientos=new LinkedList<Movimiento>();
		Posicion s=p.getSuperiorDerecha();
		Pieza S=tablero.getPieza(s);
		//todos los movimientos hacia la derecha superior
		while(s!=null){
			if(color==Color.BLANCO){
				if(S==null){
					movimientos.add(new Movimiento(p, s));
				}else if(S!=null&&S.color==Color.NEGRO){
					movimientos.add(new Movimiento(p, s));
					break;
				}else{
					break;
				}
				s=s.getSuperiorDerecha();
				S=tablero.getPieza(s);
			}else{
				if(S==null){
					movimientos.add(new Movimiento(p, s));
				}else if(S!=null&&S.color==Color.BLANCO){
					movimientos.add(new Movimiento(p, s));
					break;
				}else{
					break;
				}
				s=s.getSuperiorDerecha();
				S=tablero.getPieza(s);
			}
		}
		return movimientos;
	}
	private LinkedList<Movimiento> movimientosDIIzquierda(Posicion p){
		LinkedList<Movimiento> movimientos=new LinkedList<Movimiento>();
		Posicion s=p.getInferiorIzquierda();
		Pieza S=tablero.getPieza(s);
		//todos los movimientos hacia la izquierda inferior
		while(s!=null){
			if(color==Color.BLANCO){
				if(S==null){
					movimientos.add(new Movimiento(p, s));
				}else if(S!=null&&S.color==Color.NEGRO){
					movimientos.add(new Movimiento(p, s));
					break;
				}else{
					break;
				}
				s=s.getInferiorIzquierda();
				S=tablero.getPieza(s);
			}else{
				if(S==null){
					movimientos.add(new Movimiento(p, s));
				}else if(S!=null&&S.color==Color.BLANCO){
					movimientos.add(new Movimiento(p, s));
					break;
				}else{
					break;
				}
				s=s.getInferiorIzquierda();
				S=tablero.getPieza(s);
			}
		}
		return movimientos;
	}
	private LinkedList<Movimiento> movimientosDIDerecha(Posicion p){
		LinkedList<Movimiento> movimientos=new LinkedList<Movimiento>();
		Posicion s=p.getInferiorDerecha();
		Pieza S=tablero.getPieza(s);
		//todos los movimientos hacia la derecha inferior
		while(s!=null){
			if(color==Color.BLANCO){
				if(S==null){
					movimientos.add(new Movimiento(p, s));
				}else if(S!=null&&S.color==Color.NEGRO){
					movimientos.add(new Movimiento(p, s));
					break;
				}else{
					break;
				}
				s=s.getInferiorDerecha();
				S=tablero.getPieza(s);
			}else{
				if(S==null){
					movimientos.add(new Movimiento(p, s));
				}else if(S!=null&&S.color==Color.BLANCO){
					movimientos.add(new Movimiento(p, s));
					break;
				}else{
					break;
				}
				s=s.getInferiorDerecha();
				S=tablero.getPieza(s);
			}
		}
		return movimientos;
	}
	private LinkedList<Movimiento> movimientosDerecha(Posicion p){
		LinkedList<Movimiento> movimientos=new LinkedList<Movimiento>();
		Posicion s=p.getDerecha();
		Pieza S=tablero.getPieza(s);
		//todos los movimientos hacia la derecha
		while(s!=null){
			if(color==Color.BLANCO){
				if(S==null){
					movimientos.add(new Movimiento(p, s));
				}else if(S!=null&&S.color==Color.NEGRO){
					movimientos.add(new Movimiento(p, s));
					break;
				}else{
					break;
				}
				s=s.getDerecha();
				S=tablero.getPieza(s);
			}else{
				if(S==null){
					movimientos.add(new Movimiento(p, s));
				}else if(S!=null&&S.color==Color.BLANCO){
					movimientos.add(new Movimiento(p, s));
					break;
				}else{
					break;
				}
				s=s.getDerecha();
				S=tablero.getPieza(s);
			}
		}
		return movimientos;
	}
	private LinkedList<Movimiento> movimientosIzquierda(Posicion p){
		LinkedList<Movimiento> movimientos=new LinkedList<Movimiento>();
		Posicion s=p.getIzquierda();
		Pieza S=tablero.getPieza(s);
		//todos los movimientos hacia la izquierda
		while(s!=null){
			if(color==Color.BLANCO){
				if(S==null){
					movimientos.add(new Movimiento(p, s));
				}else if(S!=null&&S.color==Color.NEGRO){
					movimientos.add(new Movimiento(p, s));
					break;
				}else{
					break;
				}
				s=s.getIzquierda();
				S=tablero.getPieza(s);
			}else{
				if(S==null){
					movimientos.add(new Movimiento(p, s));
				}else if(S!=null&&S.color==Color.BLANCO){
					movimientos.add(new Movimiento(p, s));
					break;
				}else{
					break;
				}
				s=s.getIzquierda();
				S=tablero.getPieza(s);
			}
		}
		return movimientos;
	}
	private LinkedList<Movimiento> movimientosInferiores(Posicion p){
		LinkedList<Movimiento> movimientos=new LinkedList<Movimiento>();
		Posicion s=p.getInferior();
		Pieza S=tablero.getPieza(s);
		//todos los movimientos hacia abajo
		while(s!=null){
			if(color==Color.BLANCO){
				if(S==null){
					movimientos.add(new Movimiento(p, s));
				}else if(S!=null&&S.color==Color.NEGRO){
					movimientos.add(new Movimiento(p, s));
					break;
				}else{
					break;
				}
				s=s.getInferior();
				S=tablero.getPieza(s);
			}else{
				if(S==null){
					movimientos.add(new Movimiento(p, s));
				}else if(S!=null&&S.color==Color.BLANCO){
					movimientos.add(new Movimiento(p, s));
					break;
				}else{
					break;
				}
				s=s.getInferior();
				S=tablero.getPieza(s);
			}
		}
		return movimientos;
	}
	private LinkedList<Movimiento> movimientosSuperiores(Posicion p){
		LinkedList<Movimiento> movimientos=new LinkedList<Movimiento>();
		Posicion s=p.getSuperior();
		Pieza S=tablero.getPieza(s);
		//todos los movimientos posibles hacia arriba
		while(s!=null){
			if(color==Color.BLANCO){
				if(S==null){
					movimientos.add(new Movimiento(p, s));
				}else if(S!=null&&S.color==Color.NEGRO){
					movimientos.add(new Movimiento(p, s));
					break;
				}else{
					break;
				}
				s=s.getSuperior();
				S=tablero.getPieza(s);
			}else{
				if(S==null){
					movimientos.add(new Movimiento(p, s));
				}else if(S!=null&&S.color==Color.BLANCO){
					movimientos.add(new Movimiento(p, s));
					break;
				}else{
					break;
				}
				s=s.getSuperior();
				S=tablero.getPieza(s);
			}
		}
		return movimientos;
	}
}
