/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exa3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Pila {
    private Muñeca[] muñecas;
    private int tamaño;
    private int tope;
//creacion de la pila
    public Pila(int tamaño) {
        this.tamaño = tamaño;
        this.muñecas = new Muñeca[tamaño];
        this.tope = -1;
    }

    public boolean estaVacia() {
        return tope == -1;
    }

    public boolean estaLlena() {
        return tope == tamaño - 1;
    }
///////////////////////apilar las mñecas
    public void apilar(Muñeca muñeca) {
        if (!estaLlena()) {
            muñecas[++tope] = muñeca;
        } else {
            JOptionPane.showMessageDialog(null, "La pila está llena", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
///////////////////////desapilar las mñecas
    public Muñeca desapilar() {
        if (!estaVacia()) {
            return muñecas[tope--];
        } else {
            JOptionPane.showMessageDialog(null, "La pila está vacía", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}

class Muñeca extends JPanel {
    private int tamaño;

    ////////////////////separacion
    public Muñeca(int tamaño) {
        this.tamaño = tamaño;
        setPreferredSize(new Dimension(50 * tamaño, 50));
    }

    @Override
    ///////////////////creacion de las muñecas
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 10;
        int alto=50;
        int ancho=50;
        int altura=10;
        for (int i = 0; i < tamaño; i++) {
            g.setColor(Color.MAGENTA);
            g.fillRect(10, x, 30 , 30);  //(x, y, ancho,alto)
            x += 40;
            
            
        }
    }
}

/////////////////////////////////////////////verificacion del valor ingresado
public class Matrioska {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int tamañoPila = 0;
            while (tamañoPila <= 0) {
                try {
                    tamañoPila = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de muñecas (tamaño de la pila):"));
                    if (tamañoPila <= 0) {
                        JOptionPane.showMessageDialog(null, "El tamaño de la pila debe ser un número positivo mayor que cero", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            Pila pila = new Pila(tamañoPila);

            JFrame frame = new JFrame("Matrioska");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());

            JButton apilarBtn = new JButton("Apilar");
            JButton desapilarBtn = new JButton("Desapilar");
            JPanel muñecaPanel = new JPanel();

            apilarBtn.addActionListener(e -> {
                if (!pila.estaLlena()) {
                    Muñeca muñeca = new Muñeca(1);
                    pila.apilar(muñeca);
                    muñecaPanel.add(muñeca);
                    muñecaPanel.revalidate();
                    muñecaPanel.repaint();
                }
            });

            desapilarBtn.addActionListener(e -> {
                if (!pila.estaVacia()) {
                    Muñeca muñeca = pila.desapilar();
                    muñecaPanel.remove(muñeca);
                    muñecaPanel.revalidate();
                    muñecaPanel.repaint();
                }
            });

            frame.add(apilarBtn);
            frame.add(desapilarBtn);
            frame.add(muñecaPanel);

            frame.setSize(1300, 100);
            frame.setVisible(true);
        });
    }
}
