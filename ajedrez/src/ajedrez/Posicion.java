package ajedrez;

public class Posicion{
	public byte fila;
	public char columna;
	public Pieza pieza;
	public Tablero tablero;
	public Posicion(){
		this('A',(byte)0,null);
	}
	public Posicion(char col,byte fil,Tablero t){
		fila=fil;
		columna=col;
		tablero=t;
	}
	public Object clone(){
		Posicion p=new Posicion(columna, fila,null);
		if(pieza!=null){
			p.pieza=(Pieza)pieza.clone();
		}
		return p;
	}
	public Posicion getSuperior(){
		if(fila==8){
			return null;
		}
		byte col=tablero.toColumna(columna);
		return tablero.posiciones[fila][col];
		//return new Posicion((char)(columna),(byte)(fila+1));
	}
	public Posicion getInferior(){
		if(fila==1){
			return null;
		}
		byte col=tablero.toColumna(columna);
		return tablero.posiciones[fila-2][col];
		//return new Posicion((char)(columna),(byte)(fila-1));
	}
	public Posicion getIzquierda(){
		if(columna=='A'){
			return null;
		}
		byte col=tablero.toColumna(columna);
		return tablero.posiciones[fila-1][col-1];
		//return new Posicion((char)(columna-1),(byte)(fila));
	}
	public Posicion getDerecha(){
		if(columna=='H'){
			return null;
		}
		byte col=tablero.toColumna(columna);
		return tablero.posiciones[fila-1][col+1];
		//return new Posicion((char)(columna+1),(byte)(fila));
	}
	public Posicion getSuperiorIzquierda(){
		if(columna=='A'||fila==8){
			return null;
		}
		byte col=tablero.toColumna(columna);
		return tablero.posiciones[fila][col-1];
		//return new Posicion((char)(columna-1),(byte)(fila+1));
	}
	public Posicion getSuperiorDerecha(){
		if(columna=='H'||fila==8){
			return null;
		}
		byte col=tablero.toColumna(columna);
		return tablero.posiciones[fila][col+1];
		//return new Posicion((char)(columna+1),(byte)(fila+1));
	}
	public Posicion getInferiorIzquierda(){
		if(columna=='A'||fila==1){
			return null;
		}
		byte col=tablero.toColumna(columna);
		return tablero.posiciones[fila-2][col-1];
		//return new Posicion((char)(columna-1),(byte)(fila-1));
	}
	public Posicion getInferiorDerecha(){
		if(columna=='H'||fila==1){
			return null;
		}
		byte col=tablero.toColumna(columna);
		return tablero.posiciones[fila-2][col+1];
		//return new Posicion((char)(columna+1),(byte)(fila-1));
	}
	@Override
	public boolean equals(Object o){
		if(o instanceof Posicion){
			if(((Posicion)o).fila==fila&&((Posicion)o).columna==columna){
				return true;
			}
		}
		return false;
	}
	@Override
	public String toString(){
		return (pieza==null)?""+columna+","+fila:pieza.toString()+" "+columna+","+fila;
	}
	public String aCadena(){
		return (pieza==null)?" ":pieza.toString();
	}
}