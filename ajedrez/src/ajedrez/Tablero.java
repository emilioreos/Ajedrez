package ajedrez;

public class Tablero {
	public Pieza[] blancas;
	public Pieza[] negras;
	public boolean getPosicion(char columna,byte fila,Color color){
		if(columna<'a'||fila<1){
			return false;
		}else{
			for(int i=0;i<blancas.length;i++){
				if(blancas[i]!=null){
					
				}
			}
		}
		return false;
	}
	//para jorge
	public Pieza getPieza(char Columna,byte fila){
		for(int i=0;i<blancas.length;i++){
			if(blancas[i]!=null){
				if(blancas[i].fila==fila&&blancas[i].columna==Columna){
					return blancas[i];
				}
			}
			if(negras[i]!=null){
				if(negras[i].fila==fila&&negras[i].columna==Columna){
					return negras[i];
				}
			}
		}
		return null;
	}
}
