package recursividad;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {

    private JButton[] seatButtons = new JButton[30];
    private JTextField nameField = new JTextField(15);
    private PalindromoAir avion1 = new PalindromoAir();

    public Ventana() {
        super("PalindromoAir");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 350);
        setLayout(new FlowLayout());

        JPanel pAsientos = new JPanel(new GridLayout(6, 5, 5, 5));
        for (int i = 0; i < 30; i++) {
            seatButtons[i] = new JButton("A" + (i + 1));
            seatButtons[i].setBackground(Color.GREEN);
            pAsientos.add(seatButtons[i]);
        }

        JPanel pControl = new JPanel(new GridLayout(4, 2, 5, 5));
        JButton sellBtn = new JButton("Sell Ticket");
        JButton cancelBtn = new JButton("Cancel Ticket");
        JButton searchBtn = new JButton("Search Passenger");
        JButton printBtn = new JButton("Print Passengers");
        JButton incomeBtn = new JButton("View Income");
        JButton dispatchBtn = new JButton("Dispatch");

        pControl.add(new JLabel("Nombre:"));
        pControl.add(nameField);
        pControl.add(sellBtn);
        pControl.add(cancelBtn);
        pControl.add(searchBtn);
        pControl.add(printBtn);
        pControl.add(incomeBtn);
        pControl.add(dispatchBtn);

        add(pAsientos);
        add(pControl);

        sellBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) return;
            
            int pos = avion1.firtsAvailable();
            if (pos != -1) {
                boolean palin = avion1.isPalindromo(name);
                double precio = palin ? 80.0 : 100.0;
                
                avion1.avion[pos] = new Ticket(name, precio);
                seatButtons[pos].setText(name);
                seatButtons[pos].setBackground(palin ? Color.BLUE : Color.RED);
                System.out.println("Vendido a: " + name + " - Total: " + precio);
                nameField.setText("");
            } else {
                System.out.println("Avión lleno");
            }
        });

        cancelBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            int pos = avion1.searchPassenger(name);
            if (pos != -1) {
                avion1.avion[pos] = null;
                seatButtons[pos].setText("A" + (pos + 1));
                seatButtons[pos].setBackground(Color.GREEN);
                System.out.println("Cancelado: " + name);
                nameField.setText("");
            } else {
                System.out.println("No encontrado");
            }
        });

        searchBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            int pos = avion1.searchPassenger(name);
            if (pos != -1) {
                System.out.println("Pasajero en Asiento: " + (pos + 1));
            } else {
                System.out.println("No encontrado");
            }
        });

        printBtn.addActionListener(e -> {
            avion1.printPassengers();
        });

        incomeBtn.addActionListener(e -> {
            System.out.println("Ingresos Totales: " + avion1.income());
        });

        dispatchBtn.addActionListener(e -> {
            System.out.println("Despachado. Total: " + avion1.income());
            for (int i = 0; i < 30; i++) {
                avion1.avion[i] = null;
                seatButtons[i].setText("A" + (i + 1));
                seatButtons[i].setBackground(Color.GREEN);
            }
        });
    }

    public static void main(String[] args) {
        new Ventana().setVisible(true);
    }
}