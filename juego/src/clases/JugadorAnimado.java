package clases;

import java.util.HashMap;



import implementacion.juego;
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class JugadorAnimado extends ObjetoJuego{		
		private int Vidas;
		private HashMap<String, Animacion> animaciones;
		private int xImagen;
		private int yImagen;
		private int anchoImagen;
		private int altoImagen;
		private String animacionActual;
		private int direccion =1;
		private int puntuacion;
		
		

		public int getDireccion() {
			return direccion;
		}
		public void setDireccion(int direccion) {
			this.direccion = direccion;
		}
		public JugadorAnimado(int x, int y, String nombreImagen, int velocidad, int vidas, String animacionActual) {
			super(x, y, nombreImagen, velocidad);
			this.Vidas = vidas;
			this.animacionActual = animacionActual;
			animaciones = new HashMap<String , Animacion>();
			inicializarAnimaciones();
			
		}
		public int getVidas() {
			return Vidas;
		}
		public void setVidas(int vidas) {
			Vidas = vidas;
			
		} public void inicializarAnimaciones() {
			Rectangle coordenadasCorrer[]= {
		
			new Rectangle(96, 63, 50,70),
			new Rectangle(196,63, 50,70),
			new Rectangle(296,63, 50,70),
			new Rectangle(398,63, 50,70),
			new Rectangle(497,63, 50,70),
			new Rectangle(600,63, 50,70),
			new Rectangle(697,63, 50,70),
			new Rectangle(794,5,  50,70),
		//	new Rectangle(794,63, 50,80)
				
			};
			Animacion animacionCorrer = new Animacion(0.1,coordenadasCorrer);
			animaciones.put("correr", animacionCorrer);
			
			Rectangle coordenadasDescanso[]= {
					///////////    y, x   anchox alto
					new Rectangle(96, 8, 50,70),
					new Rectangle(196,8, 50,70),
					new Rectangle(296,8, 50,70),
					new Rectangle(399,8, 50,70),
					new Rectangle(496,8, 50,70),
					new Rectangle(600,8, 50,70),
					new Rectangle(96, 8, 50,70)
					
			};
			Animacion animacionDescanso = new Animacion(0.2,coordenadasDescanso);
			animaciones.put("descanso", animacionDescanso);
			/////////////////////////////////////////////////////////////////////////////

			
			Rectangle coordenadasSalto[]= {
			
					new Rectangle(96, 394, 50,70),
					new Rectangle(196,394, 50,70),
					new Rectangle(296,394, 50,70),
					new Rectangle(399,394, 50,70),
					new Rectangle(496,394, 50,70),
					new Rectangle(600,394, 50,70),
					new Rectangle(96, 394, 50,70)
					
			};
			Animacion animacionSalto = new Animacion(0.2,coordenadasSalto);
			animaciones.put("Salto", animacionSalto);
			///////////////////////////////////////////////

			Rectangle coordenadasbatalla[]= {
			//		new Rectangle(96, 8, 50,68),
					new Rectangle(96, 112, 50,70),
					new Rectangle(196,112, 50,70),
					new Rectangle(296,112, 50,70),
					new Rectangle(399,112, 50,70),
					new Rectangle(496,112, 50,70),
					new Rectangle(600,112, 50,70),
					new Rectangle(96, 112, 50,70)
					
			};
			Animacion animacionpelea = new Animacion(0.25,coordenadasbatalla);
			animaciones.put("Ataque", animacionpelea);
			
			
		}
		public void calcularFrame(double t) {
			Rectangle coordenadas = animaciones.get(animacionActual).calcularFrameActual( t);	
			this.xImagen = (int) coordenadas.getX();
			this.yImagen = (int)coordenadas.getY();
			this.altoImagen = (int)coordenadas.getWidth();
			this.anchoImagen = (int)coordenadas.getHeight();
		}
		public Rectangle obtenerRectangulo() {
			return new Rectangle(X,Y,(direccion*anchoImagen)-3,altoImagen);
		}
		@Override
		
		public void pintar(GraphicsContext graficos) {
			graficos.drawImage(juego.imagenes.get(nombreImagen),xImagen,yImagen,anchoImagen,altoImagen,X + (direccion==-1?anchoImagen:0),Y,direccion*anchoImagen,altoImagen);
		//	graficos.setStroke(Color.YELLOW);
		//	graficos.strokeRect(X,Y,anchoImagen-3,altoImagen);
			graficos.fillText("Puntuacion " + puntuacion, 0, 400);
		}
		
		@Override
		// se ejecuta por cada iteracion del juego
		public void mover() {
			if(X>700 )
				X=-80;
			if (juego.derecha)
				X+=velocidad;
			if (juego.izquierda)
				X-=velocidad;
		//	if (juego.derecha)
		//		Y-=velocidad;
		/*	if (juego.abajo)
				Y+=velocidad;
				*/
		}
		
		public String getAnimacionActual() {
			return animacionActual;
		}
		public void setAnimacionActual(String animacionActual) {
			this.animacionActual = animacionActual;
		}
		public void verificarColisionesItem(Item item) {
    /*	if (this.obtenerRectangulo().getBoundsInLocal().intersects(item.obtenerRectangulo().getBoundsInLocal()));
		{
			item.setCapturado(true);
		}
		   this.Vidas += item.getCantidadVidas();
	   System.out.println("ESTA EN COLISION");
	   
	*/
			if (this.obtenerRectangulo().intersects(item.obtenerRectangulo().getBoundsInLocal())) {
				if (!item.isCapturado())
					this.Vidas++;
				item.setCapturado(true);				
		}
			
		}
		
		

	

}
