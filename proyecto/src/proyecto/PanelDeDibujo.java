package proyecto;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PanelDeDibujo extends JPanel {

    // Lista para almacenar todos los trazos dibujados. Cada trazo es una lista de puntos.
    private ArrayList<ArrayList<Point>> trazos = new ArrayList<>();
    // Almacenar temporalmente los puntos del trazo que se está dibujando
    private ArrayList<Point> trazoActual = null;
    ////////////////////////////////////////////////////////////////Varible para cambiar el color de la traza
    // Inicialmente en negro
    private Color colortraza = Color.BLACK;
    // Método para cambiar el color de la traza actual.
    public void cambiarColor(Color nuevoColor) {
    	this.colortraza=nuevoColor;
    }
    /////////////////////////////////////////////////////////////////Varible para cambiar el grosor de la traza
	// Inicialmente medio
    private Float grosor=3f;
    // Método para cambiar el grosor de la traza actual.
    public void cambiargrosor(Float ngrosor) {
    	this.grosor=ngrosor;
    }
	/////////////////////////////////////////////////////////////////Borrar trazos
	//  eliminar todos los trazos dibujados y repintar el lienzo.
	public void borrarLienzo() {
	    trazos.clear();
	    repaint();
	}
    // Constructor de la clase PanelDeDibujo.
    public PanelDeDibujo() {
        setBackground(Color.WHITE); // Establece el color de fondo del panel a blanco.

        // Listener para (presionar).
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Cuando se presiona el ratón, se inicia un nuevo trazo
                trazoActual = new ArrayList<>();
                // Se añade el punto donde se presionó el ratón al trazo actual
                trazoActual.add(e.getPoint());
                // Se añade el trazo actual a la lista de todos los trazos
                trazos.add(trazoActual);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Cuando se suelta el ratón, el trazo actual se considera terminado.
                trazoActual = null;
            }

        });

        // Listener para (arrastrar).
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Si se está arrastrando el ratón
                if (trazoActual != null) {
                    // Se añade el nuevo punto a la lista del trazo actual.
                    trazoActual.add(e.getPoint());
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Dibujar el fondo.
        //////////////////////////////////////////////////////////////Cambiar color
        g.setColor(colortraza); // Establece el color con el que se van a dibujar los trazos.

        //////////////////////////////////////////////////////////////// Usar Graphics2d para la funcion de grosor
        Graphics2D g2d = (Graphics2D) g; // Castea el objeto Graphics a Graphics2D para tener más funcionalidades.

        //////////////////////////////////////////////////////////////// Cambiar grosor
        g2d.setStroke(new BasicStroke(grosor)); // Establece el grosor de la línea para los trazos.

     
        for (ArrayList<Point> trazo : trazos) {
            
            for (int i = 0; i < trazo.size() - 1; i++) {
                // Obtiene el punto actual.
                Point p1 = trazo.get(i);
                // Obtiene el siguiente punto.
                Point p2 = trazo.get(i + 1);
                // Dibuja una línea entre el punto actual y el siguiente.
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }
}