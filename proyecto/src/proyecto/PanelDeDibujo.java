package proyecto;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class PanelDeDibujo extends JPanel {

    // Creamos una lista que contendrá otras listas de puntos.
    // Cada una de estas sublistas representará un "trazo" diferente
    // que el usuario dibuja manteniendo presionado el botón del ratón.
    private ArrayList<ArrayList<Point>> trazos = new ArrayList<>();
    // Esta variable guardará la lista de puntos del trazo que se está dibujando
    // actualmente. Será 'null' cuando no se esté dibujando.
    private ArrayList<Point> trazoActual = null;

    // Constructor de la clase PanelDeDibujo. Se llama cuando se crea un objeto PanelDeDibujo.
    public PanelDeDibujo() {
        // Establecemos el color de fondo del panel a blanco.
        setBackground(Color.WHITE);

        // Añadimos un listener (escuchador) para los eventos del ratón.
        // Usamos MouseAdapter para no tener que implementar todos los métodos
        // de la interfaz MouseListener, solo los que nos interesan.
        addMouseListener(new MouseAdapter() {
            // Este método se llama cuando se PRESIONA un botón del ratón dentro del panel.
            @Override
            public void mousePressed(MouseEvent e) {
                // Cuando se presiona el ratón, creamos una nueva lista para almacenar
                // los puntos del nuevo trazo que va a comenzar.
                trazoActual = new ArrayList<>();
                // Añadimos a esta nueva lista el primer punto donde se presionó el ratón.
                // 'e.getPoint()' nos da las coordenadas (x, y) del evento del ratón.
                trazoActual.add(e.getPoint());
                // Añadimos esta lista del trazo actual a nuestra lista principal de trazos ('trazos').
                trazos.add(trazoActual);
                // Le pedimos al panel que se vuelva a dibujar. Esto llamará al método 'paintComponent'.
                repaint();
            }

            // Este método se llama cuando se SUELTA un botón del ratón dentro del panel.
            @Override
            public void mouseReleased(MouseEvent e) {
                // Cuando se suelta el ratón, significa que el trazo actual ha terminado.
                // Establecemos 'trazoActual' a 'null' para indicar que no hay un trazo activo.
                trazoActual = null;
            }
        });

        // Añadimos otro listener para los eventos de MOVIMIENTO del ratón.
        // También usamos MouseAdapter para solo implementar el método que nos interesa.
        addMouseMotionListener(new MouseAdapter() {
            // Este método se llama cuando se ARRASTRA el ratón (se mueve mientras un botón está presionado) dentro del panel.
            @Override
            public void mouseDragged(MouseEvent e) {
                // Solo añadimos el punto actual a la lista del trazo actual SI 'trazoActual' no es 'null'.
                // Esto asegura que solo guardamos puntos mientras se está presionando el botón del ratón.
                if (trazoActual != null) {
                    // Añadimos las coordenadas actuales del ratón a la lista del trazo actual.
                    trazoActual.add(e.getPoint());
                    // Le pedimos al panel que se vuelva a dibujar para mostrar la nueva línea.
                    repaint();
                }
            }
        });
    }

    // Este método se llama automáticamente cuando el panel necesita ser redibujado
    // (por ejemplo, después de llamar a 'repaint()').
    @Override
    protected void paintComponent(Graphics g) {
        // Llamamos al método 'paintComponent' de la clase padre (JPanel).
        // Esto es importante para que el fondo del panel se pinte correctamente
        // (en este caso, de blanco, como lo establecimos en el constructor).
        super.paintComponent(g);

        // Iteramos sobre cada una de las listas de puntos que tenemos guardadas en 'trazos'.
        // Cada una de estas listas representa un trazo completo.
        for (ArrayList<Point> trazo : trazos) {
            // Dentro de cada trazo, iteramos desde el primer punto hasta el penúltimo.
            for (int i = 0; i < trazo.size() - 1; i++) {
                // Obtenemos el punto actual.
                Point p1 = trazo.get(i);
                // Obtenemos el siguiente punto en la lista del trazo.
                Point p2 = trazo.get(i + 1);
                // Usamos el objeto 'Graphics' ('g') para dibujar una línea recta
                // desde las coordenadas de 'p1' hasta las coordenadas de 'p2'.
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }
}