package ajedrez;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

import GUIEMILIO.TableroGrafico;
public class Tablero{
	public Posicion[][] posiciones=new Posicion[8][8];
	public Tablero(Object o){
		
	}
	public Tablero(){
		for(byte i=0;i<8;i++){
			char l='A';
			for(byte j=0;j<8;j++,l++){
				posiciones[i][j]=new Posicion(l,(byte)(i+1),this);
				if(i==1){
					posiciones[1][j].pieza=new Pieza(Tipo.PEON, this, Color.BLANCO);
				}else if(i==6){
					posiciones[6][j].pieza=new Pieza(Tipo.PEON, this, Color.NEGRO);
				}
			}
		}
		posiciones[0][0].pieza=new Pieza(Tipo.TORRE, this, Color.BLANCO);
		posiciones[0][1].pieza=new Pieza(Tipo.CABALLO, this, Color.BLANCO);
		posiciones[0][2].pieza=new Pieza(Tipo.ALFIL, this, Color.BLANCO);
		posiciones[0][3].pieza=new Pieza(Tipo.REINA, this, Color.BLANCO);
		posiciones[0][4].pieza=new Pieza(Tipo.REY, this, Color.BLANCO);
		posiciones[0][5].pieza=new Pieza(Tipo.ALFIL, this, Color.BLANCO);
		posiciones[0][6].pieza=new Pieza(Tipo.CABALLO, this, Color.BLANCO);
		posiciones[0][7].pieza=new Pieza(Tipo.TORRE, this, Color.BLANCO);

		posiciones[7][0].pieza=new Pieza(Tipo.TORRE, this, Color.NEGRO);
		posiciones[7][1].pieza=new Pieza(Tipo.CABALLO, this, Color.NEGRO);
		posiciones[7][2].pieza=new Pieza(Tipo.ALFIL, this, Color.NEGRO);
		posiciones[7][3].pieza=new Pieza(Tipo.REINA, this, Color.NEGRO);
		posiciones[7][4].pieza=new Pieza(Tipo.REY, this, Color.NEGRO);
		posiciones[7][5].pieza=new Pieza(Tipo.ALFIL, this, Color.NEGRO);
		posiciones[7][6].pieza=new Pieza(Tipo.CABALLO, this, Color.NEGRO);
		posiciones[7][7].pieza=new Pieza(Tipo.TORRE, this, Color.NEGRO);
	}
	
	public boolean hayPieza(Posicion p){
		if(p!= null){
			byte col=toColumna(p.columna);
			if(col<0){
				return false;
			}
			Pieza ps=posiciones[p.fila-1][col].pieza;
			if(ps==null){
				return false;
			}
			return true;
		}
		return false;
	}
	public Pieza getPieza(Posicion p){
		if(p!= null){
			byte col=toColumna(p.columna);
			if(col<0){
				return null;
			}
			Pieza ps=posiciones[p.fila-1][col].pieza;
			return ps;
		}
		return null;
	}
	public void mover(Movimiento m){
		if(m!= null){
			byte col=toColumna(m.origen.columna),col1=toColumna(m.destino.columna);
			if(col<0||col1<0){
				return;
			}
			Posicion po=posiciones[m.origen.fila-1][col];
			posiciones[m.destino.fila-1][col1].pieza=po.pieza;
			po.pieza=null;
		}
	}
	public byte toColumna(char c){
		byte col=-1;
		switch(c){
		case 'A': col=0;
			break;
		case 'B': col=1;
			break;
		case 'C': col=2;
			break;
		case 'D': col=3;
			break;
		case 'E': col=4;
			break;
		case 'F': col=5;
			break;
		case 'G': col=6;
			break;
		case 'H': col=7;
			break;
	}
		return col;
	}
	public LinkedList<Movimiento> getMovimientos(Color c){
		LinkedList<Movimiento> movimientos=new LinkedList<Movimiento>();
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				Pieza p=posiciones[i][j].pieza;
				if(p!=null&&p.color==c){
					p.calcularMovimientos(posiciones[i][j]);
					movimientos.addAll(p.getMovimientos());
				}
			}
		}
		return movimientos;
	}
	public int getValor(){
		int valor=0;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				Pieza p=posiciones[i][j].pieza;
				if(p!=null){
					if(p.color==Color.BLANCO){
						valor+=p.getValor();
					}else{
						valor-=p.getValor();
					}
				}
			}
		}
		return valor;
	}
	public Object clone(){
		Tablero t=new Tablero(null);
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				t.posiciones[i][j]=(Posicion)posiciones[i][j].clone();
				if(t.posiciones[i][j].pieza!=null){
					t.posiciones[i][j].pieza.setTablero(t);
				}
				t.posiciones[i][j].tablero=t;
			}
		}
		return t;
	}
	public String toString(){
		StringBuilder sb=new StringBuilder();
		for(int i=7;i>-1;i--){
			for(int j=7;j>-1;j--){
				sb.append(posiciones[i][j].aCadena()+"|");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	//para debugeo
	/* por ser para debugeo la comente
	private static void imprimir(Nodo raiz){
		if(raiz.hijos.size()==0){
			System.out.println(raiz.tablero.toString());
		}
		System.out.println(raiz.tablero.toString());
		for(int i=0;i<raiz.hijos.size();i++){
			imprimir(raiz.hijos.get(i));
		}
		System.out.println();
		System.out.println();
	}*/
	//main para prueva de componentes
	public static void main(String[] args){
		Tablero tt=new Tablero();
		TableroGrafico t= new TableroGrafico(tt);
		JFrame x=new JFrame();
		x.add(t);
		t.setSize(8*TableroGrafico.ancho, 8*TableroGrafico.ancho);
		x.setSize(8*TableroGrafico.ancho+5, 8*TableroGrafico.ancho+30);
		x.setVisible(true);
		x.setResizable(false);
		x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*Tablero t=new Tablero();
		//Scanner sc=new Scanner(System.in);
		int veces=20;//sc.nextInt();
		//Random r=new Random();
		Nodo raiz =new Nodo(Color.BLANCO),raiz2=new Nodo(Color.NEGRO);
		raiz.tablero=t;
		Contenedor con=Nodo.crearArbol(raiz, Integer.MIN_VALUE,Integer.MAX_VALUE , Color.BLANCO, (byte)4);
		t=con.tablero.tablero;
		System.out.println(t.toString());
		raiz2.tablero=t;
		con=Nodo.crearArbol(raiz2, Integer.MIN_VALUE,Integer.MAX_VALUE , Color.NEGRO, (byte)4);
		t=con.tablero.tablero;
		System.out.println(t.toString());
		for(int i=0;i<veces;i++){
			System.out.println(i+1);
			raiz=new Nodo(Color.BLANCO);
			raiz.tablero=t;
			byte inte=4;
			con=Nodo.crearArbol(raiz, Integer.MIN_VALUE,Integer.MAX_VALUE , Color.BLANCO, inte);
			t=con.tablero.tablero;
			System.out.println(t.toString());
			raiz2=new Nodo(Color.NEGRO);
			raiz2.tablero=t;
			
			con=Nodo.crearArbol(raiz2, Integer.MIN_VALUE,Integer.MAX_VALUE , Color.NEGRO, inte);
			t=con.tablero.tablero;
			System.out.println(t.toString());
			System.out.println(t.getValor());
			System.gc();
		}*/
		/*System.out.println(t.toString());
		t.mover(new Movimiento(new Posicion('H', (byte)7), new Posicion('H',(byte)3)));
		t.mover(new Movimiento(new Posicion('F', (byte)7), new Posicion('F',(byte)3)));
		System.out.println(t.toString());
		Nodo raiz =new Nodo(Color.BLANCO);
		raiz.tablero=t;
		Contenedor con=Nodo.crearArbol(raiz, Integer.MIN_VALUE,Integer.MAX_VALUE , Color.BLANCO, (byte)4);
		t=con.tablero.tablero;
		System.out.println(t.toString());
		t.mover(new Movimiento(new Posicion('H', (byte)8), new Posicion('H',(byte)4)));
		System.out.println(t.toString());
		System.gc();
		raiz.tablero=t;
		con=Nodo.crearArbol(raiz, Integer.MIN_VALUE,Integer.MAX_VALUE , Color.BLANCO, (byte)4);
		t=con.tablero.tablero;
		System.out.println(t.toString());
		t.mover(new Movimiento(new Posicion('G', (byte)7), new Posicion('G',(byte)5)));
		System.out.println(t.toString());
		System.gc();
		raiz.tablero=t;
		con=Nodo.crearArbol(raiz, Integer.MIN_VALUE,Integer.MAX_VALUE , Color.BLANCO, (byte)4);
		t=con.tablero.tablero;
		System.out.println(t.toString());*/
		//System.out.println(raiz.hijos);
	}
}
