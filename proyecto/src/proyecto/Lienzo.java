package proyecto;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Lienzo {
    JFrame			jfPrincipal;
    PanelDeDibujo	jpLienzo;
    JMenuBar		jmbMenu;
    JMenu			jmColor;
    JMenuItem		jmiNegro;
    JMenuItem		jmiRojo;
    JMenuItem		jmiAzul;
    JMenu			jmGrosor;
    JMenuItem		jmiFino;
    JMenuItem		jmiMedio;
    JMenuItem		jmiGrueso;
    JMenuItem		jmBorrar;

    public Lienzo() {

        frame();

        jfPrincipal.setVisible(true);
    }
    public void frame(){
        //Frame
        jfPrincipal= new JFrame("PacoPain");
        jfPrincipal.setSize(1150,750);
        jfPrincipal.setLocationRelativeTo(null);
        jfPrincipal.setResizable(false);
        jfPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfPrincipal.setBackground(Color.white);
        ///TAMAÑOS TEXTOS MENU
        Font paneles= new Font(null,Font.BOLD,20);
        Font pinterior= new Font(null,Font.BOLD,15);
        //Panel lienzo
        jpLienzo= new PanelDeDibujo();
        jfPrincipal.add(jpLienzo);

        ////////////////////////////BARRA MENUS

        jmbMenu = new JMenuBar();
        jmbMenu.setBackground(new Color(209, 226, 230));
        jfPrincipal.setJMenuBar(jmbMenu);

        ////////////////Menus
        //////////COLOR
        jmColor=new JMenu("             Color             ");
        jmColor.setFont(paneles);
        jmbMenu.add(jmColor);
        //NEGRO
        jmiNegro=new JMenuItem("Negro");
        jmiNegro.setFont(pinterior);
        jmiNegro.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                jpLienzo.cambiarColor(Color.BLACK);
                jpLienzo.repaint();
            }
        });
        jmColor.add(jmiNegro);
        //ROJO
        jmiRojo=new JMenuItem("Rojo");
        jmiRojo.setFont(pinterior);
        jmiRojo.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        jpLienzo.cambiarColor(Color.RED);
                        jpLienzo.repaint();
                    }
                });
        jmColor.add(jmiRojo);

        //AZUL
        jmiAzul=new JMenuItem("Azul");
        jmiAzul.setFont(pinterior);
        jmiAzul.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        jpLienzo.cambiarColor(Color.BLUE);
                        jpLienzo.repaint();
                    }
                });
        jmColor.add(jmiAzul);


        ///GROSOR
        jmGrosor=new JMenu("             Grosor             ");
        jmGrosor.setFont(paneles);
        jmbMenu.add(jmGrosor);
        //FINO
        jmiFino=new JMenuItem("Fino");
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
        //MEDIO
        jmiMedio=new JMenuItem("Medio");
        jmiMedio.setFont(pinterior);
        jmiMedio.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int opcion=JOptionPane.showConfirmDialog(
                                jpLienzo,
                                "¿Seguro que quieres establecer el grosor a Medio y cambiar todos los trazos?",
                                "Confirmar cambio de grosor",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);
                        if(opcion == JOptionPane.YES_OPTION) {
                            jpLienzo.cambiargrosor(3f);
                            jpLienzo.repaint();
                        }


                    }
                });
        jmGrosor.add(jmiMedio);
        //Grueso
        jmiGrueso=new JMenuItem("Grueso");
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


        ///BORRAR
        jmBorrar=new JMenuItem("             Borrar             ");
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
                        JOptionPane.ERROR_MESSAGE);
                if (opcion == JOptionPane.YES_OPTION) {
                    jpLienzo.borrarLienzo();
                    jpLienzo.repaint();
                }
            }
        });
        jmbMenu.add(jmBorrar);

    }
    public static void main(String[] args) {
        new  Lienzo();
    }

}