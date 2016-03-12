package ajedrez;

public class Tablero {
	public Posicion[][] posiciones=new Posicion[8][8];
	
	public Tablero(){
		for(byte i=0;i<8;i++){
			char l='A';
			for(byte j=0;j<8;j++,l++){
				posiciones[i][j]=new Posicion(l,(byte)(i+1));
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
		byte col=-1;
			switch(p.columna){
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
			byte col=-1;
				switch(p.columna){
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
			byte col=-1,col1=-1;
				switch(m.origen.columna){
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
				switch(m.destino.columna){
					case 'A': col1=0;
						break;
					case 'B': col1=1;
						break;
					case 'C': col1=2;
						break;
					case 'D': col1=3;
						break;
					case 'E': col1=4;
						break;
					case 'F': col1=5;
						break;
					case 'G': col1=6;
						break;
					case 'H': col1=7;
						break;
				}
				if(col<0||col1<0){
					return;
				}
				Posicion po=posiciones[m.origen.fila-1][col];
				posiciones[m.destino.fila-1][col1].pieza=po.pieza;
				po.pieza=null;
		}
	}
	//main para prueva de componentes
	public static void main(String[] args){
		Tablero t=new Tablero();
		t.mover(new Movimiento(new Posicion('B', (byte)7), new Posicion('B',(byte)3)));
		Pieza p=t.getPieza(new Posicion('C',(byte)2));
		p.calcularMovimientos(new Posicion('C',(byte)2));
		System.out.println(p.getMovimientos());
	}
}
