package ajedrez;

public class Posicion{
	public byte fila;
	public char columna;
	public Pieza pieza;
	public Posicion(){
		this('A',(byte)0);
	}
	public Posicion(char col,byte fil){
		fila=fil;
		columna=col;
	}
	public Posicion getSuperior(){
		if(fila==8){
			return null;
		}
		return new Posicion((char)(columna),(byte)(fila+1));
	}
	public Posicion getInferior(){
		if(fila==1){
			return null;
		}
		return new Posicion((char)(columna),(byte)(fila-1));
	}
	public Posicion getIzquierda(){
		if(columna=='A'){
			return null;
		}
		return new Posicion((char)(columna-1),(byte)(fila));
	}
	public Posicion getDerecha(){
		if(columna=='H'){
			return null;
		}
		return new Posicion((char)(columna+1),(byte)(fila));
	}
	public Posicion getSuperiorIzquierda(){
		if(columna=='A'||fila==8){
			return null;
		}
		return new Posicion((char)(columna-1),(byte)(fila+1));
	}
	public Posicion getSuperiorDerecha(){
		if(columna=='H'||fila==8){
			return null;
		}
		return new Posicion((char)(columna+1),(byte)(fila+1));
	}
	public Posicion getInferiorIzquierda(){
		if(columna=='A'||fila==1){
			return null;
		}
		return new Posicion((char)(columna-1),(byte)(fila-1));
	}
	public Posicion getInferiorDerecha(){
		if(columna=='H'||fila==1){
			return null;
		}
		return new Posicion((char)(columna+1),(byte)(fila-1));
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
}