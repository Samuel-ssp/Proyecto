package proyecto;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Clase principal de la aplicación de dibujo. Contiene la configuración de la ventana,
 * los menús y funcionalidades como cambiar color, grosor, borrar y guardar.
 */
public class Lienzo {

    /** Ventana principal del programa */
    JFrame jfPrincipal;

    /** Panel donde se realiza el dibujo */
    PanelDeDibujo jpLienzo;

    /** Barra de menús superior */
    JMenuBar jmbMenu;

    // Menús y submenús
    JMenu jmColor;
    JMenuItem jmiNegro;
    JMenuItem jmiRojo;
    JMenuItem jmiAzul;

    JMenu jmGrosor;
    JMenuItem jmiFino;
    JMenuItem jmiMedio;
    JMenuItem jmiGrueso;

    JMenuItem jmBorrar;
    JMenu jmArchivo; 
    JMenuItem jmiGuardarImagen; 

    /**
     * Constructor: inicializa el lienzo y muestra la interfaz gráfica.
     */
    public Lienzo() {
        frame();
        jfPrincipal.setVisible(true);
    }

    /**
     * Configura la ventana principal y crea todos los menús y acciones disponibles.
     */
    public void frame() {
        jfPrincipal = new JFrame("PacoPain");
        jfPrincipal.setSize(1150, 750);
        jfPrincipal.setLocationRelativeTo(null);
        jfPrincipal.setResizable(false);
        jfPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfPrincipal.setBackground(Color.white);

        Font paneles = new Font(null, Font.BOLD, 20);
        Font pinterior = new Font(null, Font.BOLD, 15);

        jpLienzo = new PanelDeDibujo();
        jfPrincipal.add(jpLienzo);

        jmbMenu = new JMenuBar();
        jmbMenu.setBackground(new Color(209, 226, 230));
        jfPrincipal.setJMenuBar(jmbMenu);

        // Menú Archivo
        jmArchivo = new JMenu("             Archivo             ");
        jmArchivo.setFont(paneles);
        jmbMenu.add(jmArchivo);

        jmiGuardarImagen = new JMenuItem("Guardar como...");
        jmiGuardarImagen.setFont(pinterior);
        jmiGuardarImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDibujoComoImagen();
            }
        });
        jmArchivo.add(jmiGuardarImagen);

        // Menú Color
        jmColor = new JMenu("             Color             ");
        jmColor.setFont(paneles);
        jmbMenu.add(jmColor);

        /** Opción para cambiar el color del trazo a negro */
        jmiNegro = new JMenuItem("Negro");
        jmiNegro.setFont(pinterior);
        jmiNegro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpLienzo.cambiarColor(Color.BLACK);
                jpLienzo.repaint();
            }
        });
        jmColor.add(jmiNegro);

        /** Opción para cambiar el color del trazo a rojo */
        jmiRojo = new JMenuItem("Rojo");
        jmiRojo.setFont(pinterior);
        jmiRojo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpLienzo.cambiarColor(Color.RED);
                jpLienzo.repaint();
            }
        });
        jmColor.add(jmiRojo);

        /** Opción para cambiar el color del trazo a azul */
        jmiAzul = new JMenuItem("Azul");
        jmiAzul.setFont(pinterior);
        jmiAzul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jpLienzo.cambiarColor(Color.BLUE);
                jpLienzo.repaint();
            }
        });
        jmColor.add(jmiAzul);

        // Menú Grosor
        jmGrosor = new JMenu("             Grosor             ");
        jmGrosor.setFont(paneles);
        jmbMenu.add(jmGrosor);

        /** Opción para establecer un trazo fino */
        jmiFino = new JMenuItem("Fino");
        jmiFino.setFont(pinterior);
        jmiFino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(
                        jpLienzo,
                        "¿Seguro que quieres establecer el grosor a Fino y cambiar todos los trazos?",
                        "Confirmar cambio de grosor",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (opcion == JOptionPane.YES_OPTION) {
                    jpLienzo.cambiargrosor(1f);
                    jpLienzo.repaint();
                }
            }
        });
        jmGrosor.add(jmiFino);

        /** Opción para establecer un trazo medio */
        jmiMedio = new JMenuItem("Medio");
        jmiMedio.setFont(pinterior);
        jmiMedio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(
                        jpLienzo,
                        "¿Seguro que quieres establecer el grosor a Medio y cambiar todos los trazos?",
                        "Confirmar cambio de grosor",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (opcion == JOptionPane.YES_OPTION) {
                    jpLienzo.cambiargrosor(3f);
                    jpLienzo.repaint();
                }
            }
        });
        jmGrosor.add(jmiMedio);

        /** Opción para establecer un trazo grueso */
        jmiGrueso = new JMenuItem("Grueso");
        jmiGrueso.setFont(pinterior);
        jmiGrueso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(
                        jpLienzo,
                        "¿Seguro que quieres establecer el grosor a Grueso y cambiar todos los trazos?",
                        "Confirmar cambio de grosor",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (opcion == JOptionPane.YES_OPTION) {
                    jpLienzo.cambiargrosor(7f);
                    jpLienzo.repaint();
                }
            }
        });
        jmGrosor.add(jmiGrueso);

        /** Opción para borrar todo el contenido del lienzo */
        jmBorrar = new JMenuItem("             Borrar             ");
        jmBorrar.setFont(paneles);
        jmBorrar.setBackground(new Color(209, 226, 230));
        jmBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(
                        jpLienzo,
                        "¿Seguro que quieres borrar todo el lienzo?",
                        "Confirmar borrado",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if (opcion == JOptionPane.YES_OPTION) {
                    jpLienzo.borrarLienzo();
                    jpLienzo.repaint();
                }
            }
        });
        jmbMenu.add(jmBorrar);
    }

    /**
     * Guarda el contenido actual del panel de dibujo como una imagen PNG o JPG.
     * Muestra un diálogo para elegir ruta y formato del archivo.
     */
    public void guardarDibujoComoImagen() {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Guardar dibujo como...");
        selector.setFileFilter(new FileNameExtensionFilter("Imágenes PNG", "png"));
        selector.setFileFilter(new FileNameExtensionFilter("Imágenes JPG", "jpg"));

        int opcion = selector.showSaveDialog(jfPrincipal);

        if (opcion == JFileChooser.APPROVE_OPTION) {
            File archivoimg = selector.getSelectedFile();
            String ruta = archivoimg.getAbsolutePath();

            if (!ruta.toLowerCase().endsWith(".png") && !ruta.toLowerCase().endsWith(".jpg")) {
                ruta += ".png";
                archivoimg = new File(ruta);
            }

            BufferedImage dibujo = new BufferedImage(jpLienzo.getWidth(), jpLienzo.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = dibujo.createGraphics();
            jpLienzo.printAll(g2d);
            g2d.dispose();

            try {
                String formato = ruta.toLowerCase().endsWith(".jpg") ? "jpg" : "png";
                ImageIO.write(dibujo, formato, archivoimg);
                JOptionPane.showMessageDialog(jfPrincipal, "Dibujo guardado correctamente en:\n" + archivoimg.getAbsolutePath(), "Guardado", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(jfPrincipal, "Error al guardar la imagen:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new Lienzo();
    }

}
