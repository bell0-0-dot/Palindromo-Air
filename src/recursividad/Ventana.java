package recursividad1;

import javax.swing.*;
import java.awt.*;

public class Recursividad1 extends JFrame {
    private PalindromoAir airSystem = new PalindromoAir();
    private JButton[] seatButtons = new JButton[30];
    private JTextField txtName = new JTextField(15);
    private int selectedSeatIndex = -1;

    public Recursividad1() {
        setTitle("PalindromoAir - Control de Asientos");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.add(new JLabel("Pasajero:"));
        topPanel.add(txtName);

        JButton btnSell = new JButton("Vender");
        JButton btnCancel = new JButton("Cancelar");
        JButton btnSearch = new JButton("Buscar");
        JButton btnPrint = new JButton("Imprimir");
        JButton btnIncome = new JButton("Ingreso Total");
        JButton btnDispatch = new JButton("Despachar");

        topPanel.add(btnSell);
        topPanel.add(btnCancel);
        topPanel.add(btnSearch);
        topPanel.add(btnPrint);
        topPanel.add(btnIncome);
        topPanel.add(btnDispatch);

        add(topPanel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(5, 6, 8, 8));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < 30; i++) {
            final int index = i;
            seatButtons[i] = new JButton("Asiento " + (i + 1));
            seatButtons[i].setBackground(Color.GREEN);
            
            seatButtons[i].addActionListener(e -> {
                if (airSystem.getSeats()[index] == null) {
                    selectedSeatIndex = index;
                    updateGrid();
                } else {
                    JOptionPane.showMessageDialog(this, "El asiento " + (index + 1) + " ya está ocupado.");
                }
            });

            gridPanel.add(seatButtons[i]);
        }

        add(gridPanel, BorderLayout.CENTER);

        btnSell.addActionListener(e -> {
            String name = txtName.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el nombre del pasajero.");
                return;
            }

            if (selectedSeatIndex != -1) {
                if (airSystem.getSeats()[selectedSeatIndex] == null) {
                    double price = airSystem.isPalindrome(name) ? 200.0 * 0.80 : 200.0;
                    airSystem.getSeats()[selectedSeatIndex] = new Ticket(name, price);
                    System.out.println("Venta exitosa para: " + name + " en el asiento " + (selectedSeatIndex + 1));
                    selectedSeatIndex = -1;
                    txtName.setText("");
                    updateGrid();
                } else {
                    JOptionPane.showMessageDialog(this, "El asiento seleccionado ya está ocupado.");
                }
            } else {
                int seatAssigned = airSystem.sellTicket(name);
                if (seatAssigned != -1) {
                    updateGrid();
                    System.out.println("Venta exitosa para: " + name + " en el asiento " + (seatAssigned + 1));
                    txtName.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "El vuelo está lleno.");
                }
            }
        });

        btnCancel.addActionListener(e -> {
            String name = txtName.getText().trim();
            if (airSystem.cancelTicket(name)) {
                selectedSeatIndex = -1;
                updateGrid();
                System.out.println("Cancelado con éxito el boleto de: " + name);
                txtName.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Pasajero no encontrado.");
            }
        });

        btnSearch.addActionListener(e -> {
            String name = txtName.getText().trim();
            int index = airSystem.searchPassenger(name, 0);
            if (index != -1) {
                System.out.println("Pasajero " + name + " encontrado en el asiento " + (index + 1));
                JOptionPane.showMessageDialog(this, "Ubicado en el asiento " + (index + 1));
            } else {
                JOptionPane.showMessageDialog(this, "Pasajero no reservado.");
            }
        });

        btnPrint.addActionListener(e -> {
            airSystem.printPassengers(0);
        });

        btnIncome.addActionListener(e -> {
            double total = airSystem.totalIncome(0);
            System.out.println("Ingreso total generado: " + total);
            JOptionPane.showMessageDialog(this, "Ingreso total: " + total);
        });

        btnDispatch.addActionListener(e -> {
            airSystem.reset();
            selectedSeatIndex = -1;
            updateGrid();
            System.out.println("Vuelo despachado. Todos los asientos liberados.");
        });
    }

    private void updateGrid() {
        Ticket[] seats = airSystem.getSeats();
        for (int i = 0; i < 30; i++) {
            if (seats[i] != null) {
                seatButtons[i].setBackground(Color.RED);
                seatButtons[i].setText("Ocupado (" + (i + 1) + ")");
            } else if (i == selectedSeatIndex) {
                seatButtons[i].setBackground(Color.GRAY);
                seatButtons[i].setText("Seleccionado (" + (i + 1) + ")");
            } else {
                seatButtons[i].setBackground(Color.GREEN);
                seatButtons[i].setText("Asiento " + (i + 1));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Recursividad1().setVisible(true);
        });
    }
}
