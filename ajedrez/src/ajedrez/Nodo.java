package ajedrez;

import java.util.LinkedList;

public class Nodo {
	public Tablero tablero;
	//public LinkedList<Nodo> hijos=new LinkedList<Nodo>();
	public Nodo hijos=null;
	private Color color;
	public static int posvis=0;
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
		hijos=n;
	}
	public String toString(){
		return "\n"+tablero.toString()+"\n";
	}
	public Contenedor crearArbol(Nodo n,int alfa,int beta,Color c,byte prof){
		//System.out.println(n.tablero.toString());
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
			Nodo nodos=null;
			if(c==Color.BLANCO){
				x.valor=alfa;
				for(int i=0;i<y;i++){
					nodos=new Nodo(n.nextColor());
					nodos.tablero=(Tablero)n.tablero.clone();
					nodos.tablero.mover(l.removeFirst());
					Contenedor z=crearArbol(nodos, alfa, beta, n.nextColor(), (byte)(prof-1));
					n.addHijo(z.tablero);
					if(z.valor>alfa){
						z.tablero=nodos;
						x.tablero=z.tablero;
						alfa=z.valor;
					}
					if(alfa>=beta){
						//System.out.println("Podado Blanco");
						return z;
					}
				}
				x.valor=alfa;
				return x;
			}else{
				x.valor=beta;
				for(int i=0;i<y;i++){
					nodos=new Nodo(n.nextColor());
					nodos.tablero=(Tablero)n.tablero.clone();
					nodos.tablero.mover(l.removeFirst());
					Contenedor z=crearArbol(nodos, alfa, beta, n.nextColor(), (byte)(prof-1));
					n.addHijo(z.tablero);
					if(z.valor<beta){
						z.tablero=nodos;
						x.tablero=z.tablero;
						beta=z.valor;
					}
					if(alfa>=beta){
						//System.out.println("Podado Negro");
						return z;
					}
				}
				x.valor=beta;
				return x;
			}
		}
	}
}
