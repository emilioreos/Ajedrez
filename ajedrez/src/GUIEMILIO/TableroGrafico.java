package GUIEMILIO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import ajedrez.Contenedor;
import ajedrez.Movimiento;
import ajedrez.Nodo;
import ajedrez.Pieza;
import ajedrez.Posicion;
import ajedrez.Tablero;

public class TableroGrafico extends JPanel implements MouseListener,Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int PEON_B=0;
	public static final int TORRE_B=1;
	public static final int ALFIL_B=2;
	public static final int REY_B=3;
	public static final int REINA_B=4;
	public static final int CABALLO_B=5;
	public static final int PEON_N=6;
	public static final int TORRE_N=7;
	public static final int ALFIL_N=8;
	public static final int REY_N=9;
	public static final int REINA_N=10;
	public static final int CABALLO_N=11;
	public static final int ancho=60;
	private Tablero tablero;
	private Image imagenes[]=new Image[12];
	private Posicion inicio=null;
	private boolean turno=true;
	public TableroGrafico(Tablero t){
		addMouseListener(this);
		tablero=t;
		try {
			imagenes[PEON_B]=ImageIO.read(getClass().getResource("/imagenes/peonb.png"));
			imagenes[PEON_N]=ImageIO.read(getClass().getResource("/imagenes/peonn.png"));
			
			imagenes[REY_B]=ImageIO.read(getClass().getResource("/imagenes/reyb.png"));
			imagenes[REY_N]=ImageIO.read(getClass().getResource("/imagenes/reyn.png"));
			
			imagenes[REINA_B]=ImageIO.read(getClass().getResource("/imagenes/reinab.png"));
			imagenes[REINA_N]=ImageIO.read(getClass().getResource("/imagenes/reinan.png"));
			
			imagenes[ALFIL_B]=ImageIO.read(getClass().getResource("/imagenes/alfilb.png"));
			imagenes[ALFIL_N]=ImageIO.read(getClass().getResource("/imagenes/alfiln.png"));
			
			imagenes[TORRE_B]=ImageIO.read(getClass().getResource("/imagenes/torreb.png"));
			imagenes[TORRE_N]=ImageIO.read(getClass().getResource("/imagenes/torren.png"));
			
			imagenes[CABALLO_B]=ImageIO.read(getClass().getResource("/imagenes/caballob.png"));
			imagenes[CABALLO_N]=ImageIO.read(getClass().getResource("/imagenes/caballon.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void paint(Graphics g){
		pintarTablero(g);		
	}
	private void pintarTablero(Graphics g){
		boolean color=false;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(color){
					g.setColor(java.awt.Color.red);
					g.fillRect(j*ancho, i*ancho, ancho, ancho);
					color=false;
				}else{
					g.setColor(java.awt.Color.white);
					g.fillRect(j*ancho, i*ancho, ancho, ancho);
					color=true;
				}
				Pieza p=tablero.posiciones[7-i][j].pieza;
				if(p!=null){
					int pos=0;
					switch (p.getTipe()) {
					case ALFIL:pos=ALFIL_B;
						break;
					case PEON:pos=PEON_B;
						break;
					case CABALLO:pos=CABALLO_B;
						break;
					case REINA:pos=REINA_B;
						break;
					case REY:pos=REY_B;
						break;
					case TORRE:pos=TORRE_B;
						break;
					}
					pos=(p.color==ajedrez.Color.NEGRO)?pos+6:pos;
					g.drawImage(imagenes[pos], j*ancho, i*ancho, null);
				}
			}
			if(color){
				color=false;
			}else{
				color=true;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(turno){
			int x=e.getX()/ancho,y=e.getY()/ancho;
			Graphics g=this.getGraphics();
			Posicion actual=tablero.posiciones[7-y][x];
			Pieza p=actual.pieza;
			if(inicio==null){
				if(p!=null){
					inicio=actual;
					g.setColor(new Color(0, 255,255, 100));
					g.fillRect(x*ancho, y*ancho, ancho, ancho);
					inicio.pieza.calcularMovimientos(inicio);
					Object m[]=inicio.pieza.getMovimientos().toArray();
					for(int i=0;i<m.length;i++){
						g.fillRect(tablero.toColumna(((Movimiento)m[i]).destino.columna)*ancho, (8-((Movimiento)m[i]).destino.fila)*ancho, ancho, ancho);
					}
				}
			}else{
				Object m[]=inicio.pieza.getMovimientos().toArray();
				for(int i=0;i<m.length;i++){
					if(((Movimiento)m[i]).destino.equals(actual)){
						Movimiento mov=new Movimiento(inicio, actual);
						tablero.mover(mov);
						turno=false;
						Thread t=new Thread(this);
						t.start();
						break;
					}
				}
				inicio=null;
				pintarTablero(g);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(inicio==null){
			Nodo raiz =new Nodo(ajedrez.Color.NEGRO);
			raiz.tablero=tablero;
			Contenedor con=Nodo.crearArbol(raiz, Integer.MIN_VALUE,Integer.MAX_VALUE ,ajedrez.Color.NEGRO, (byte)4);
			tablero=con.tablero.tablero;
			pintarTablero(this.getGraphics());
			turno=true;
		}
		System.gc();
	}
}
