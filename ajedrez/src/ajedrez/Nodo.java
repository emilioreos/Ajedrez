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
	public String toString(){
		return "\n"+tablero.toString()+"\n";
	}
	public static Contenedor crearArbol(Nodo n,int alfa,int beta,Color c,byte prof){
		if(prof==0){
			Contenedor x=new Contenedor();
			x.tablero=n;
			x.valor=n.tablero.getValor();
			return x;
		}else{
			Contenedor x=new Contenedor();
			x.tablero=null;
			LinkedList<Movimiento> l=n.tablero.getMovimientos(c);
			int y=l.size();
			if(y==0){
				x.valor=n.tablero.getValor();
				return x;
			}
			Nodo nodos[]=new Nodo[y];
			if(c==Color.BLANCO){
				x.valor=alfa;
				for(int i=0;i<y;i++){
					nodos[i]=new Nodo(n.nextColor());
					nodos[i].tablero=(Tablero)n.tablero.clone();
					nodos[i].tablero.mover(l.removeFirst());
					Contenedor z=crearArbol(nodos[i], alfa, beta, n.nextColor(), (byte)(prof-1));
					n.addHijo(nodos[i]);
					if(z.valor>alfa){
						z.tablero=nodos[i];
						x.tablero=z.tablero;
						alfa=z.valor;
					}
					if(alfa>=beta){
						//System.out.println("Podado");
						return z;
					}
				}
				x.valor=alfa;
				//System.out.println("Alfa "+alfa);
				//System.out.println("Beta "+beta);
				return x;
			}else{
				x.valor=beta;
				for(int i=0;i<y;i++){
					nodos[i]=new Nodo(n.nextColor());
					nodos[i].tablero=(Tablero)n.tablero.clone();
					nodos[i].tablero.mover(l.removeFirst());
					Contenedor z=crearArbol(nodos[i], alfa, beta, n.nextColor(), (byte)(prof-1));
					n.addHijo(nodos[i]);
					if(z.valor<beta){
						z.tablero=nodos[i];
						x.tablero=z.tablero;
						beta=z.valor;
					}
					if(alfa>=beta){
						//System.out.println("Podado");
						return z;
					}
				}
				x.valor=beta;
				//System.out.println("Alfa "+alfa);
				//System.out.println("Beta "+beta);
				return x;
			}
		}
	}
}
