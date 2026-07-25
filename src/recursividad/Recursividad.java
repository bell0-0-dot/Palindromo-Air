
package recursividad;


import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;

public class Recursividad extends JFrame {

    private PalindromoAir avion = new PalindromoAir();
    private JButton[] seatButtons = new JButton[30];
    private JTextField txtName = new JTextField(18);
    private JTextArea consola = new JTextArea();
    private int asientoSeleccionado = -1;

    private static final Color DISPONIBLE = new Color(76, 175, 80);   
    private static final Color OCUPADO = new Color(211, 47, 47);      
    private static final Color PALINDROMO = new Color(255, 193, 7);   
    private static final Color SELECCIONADO = new Color(128, 128, 128); 

    public Recursividad() {
        setTitle("PalindromoAir - Venta y Control de Boletos");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.add(new JLabel("Nombre del pasajero:"));
        topPanel.add(txtName);

        JButton btnSell = new JButton("Sell Ticket");
        JButton btnCancel = new JButton("Cancel Ticket");
        JButton btnDispatch = new JButton("Dispatch");
        JButton btnPrint = new JButton("Print Passengers");
        JButton btnIncome = new JButton("View Income");
        JButton btnSearch = new JButton("Search Passenger");

        topPanel.add(btnSell);
        topPanel.add(btnCancel);
        topPanel.add(btnDispatch);
        topPanel.add(btnPrint);
        topPanel.add(btnIncome);
        topPanel.add(btnSearch);

        add(topPanel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(5, 6, 8, 8));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        for (int i = 0; i < 30; i++) {
            final int index = i;
            seatButtons[i] = new JButton();
            seatButtons[i].setOpaque(true);
            seatButtons[i].setBorderPainted(false);
            seatButtons[i].addActionListener(e -> {
                Ticket[] asientos = avion.getAvion();
                if (asientos[index] != null) {
                    JOptionPane.showMessageDialog(this, "El asiento " + (index + 1) + " ya esta ocupado por " + asientos[index].getName());
                    return;
                }
                asientoSeleccionado = (asientoSeleccionado == index) ? -1 : index;
                actualizarAsientos();
            });
            gridPanel.add(seatButtons[i]);
        }
        add(gridPanel, BorderLayout.CENTER);

        consola.setEditable(false);
        consola.setBackground(Color.BLACK);
        consola.setForeground(Color.GREEN);
        consola.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(consola);
        scroll.setPreferredSize(new Dimension(1000, 180));
        scroll.setBorder(BorderFactory.createTitledBorder("Consola del sistema"));
        add(scroll, BorderLayout.SOUTH);

        redirigirConsola();
        actualizarAsientos();

        btnSell.addActionListener(e -> {
            String name = txtName.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el nombre del pasajero.");
                return;
            }

            if (asientoSeleccionado != -1) {
                Ticket[] asientos = avion.getAvion();
                if (asientos[asientoSeleccionado] != null) {
                    JOptionPane.showMessageDialog(this, "Ese asiento ya fue ocupado, elija otro.");
                    asientoSeleccionado = -1;
                    actualizarAsientos();
                    return;
                }
                boolean palindromo = avion.isPalindromo(name);
                double original = 800, total = palindromo ? original * 0.80 : original;
                asientos[asientoSeleccionado] = new Ticket(name, total, original, palindromo);
                JOptionPane.showMessageDialog(this,
                        "Pasajero: " + name + "\n" +
                        "Asiento: " + (asientoSeleccionado + 1) + "\n" +
                        (palindromo ? "Descuento aplicado: 20% (nombre palindromo)\n" : "") +
                        "Monto original: " + original + "\n" +
                        "Total a pagar: " + total);
                asientoSeleccionado = -1;
            } else {
                avion.sellTicket(name);
            }

            txtName.setText("");
            actualizarAsientos();
        });

        btnCancel.addActionListener(e -> {
            String name = txtName.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el nombre del pasajero.");
                return;
            }
            avion.cancelTicket(name);
            txtName.setText("");
            actualizarAsientos();
        });

        btnDispatch.addActionListener(e -> {
            avion.dispatch();
            asientoSeleccionado = -1;
            actualizarAsientos();
            JOptionPane.showMessageDialog(this, "Vuelo despachado. Todos los asientos fueron liberados.");
        });

        btnPrint.addActionListener(e -> {
            consola.append("LISTA DE PASAJEROS");
            avion.printPassenger(0);
        });

        btnIncome.addActionListener(e -> {
            double total = avion.income(0);
            consola.append("\nIngreso total generado: " + total + " LPS\n");
            JOptionPane.showMessageDialog(this, "Ingreso total: " + total + " LPS");
        });

        btnSearch.addActionListener(e -> {
            String name = txtName.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el nombre del pasajero.");
                return;
            }
            int pos = avion.searchPassenger(name, 0);
            if (pos != -1) {
                JOptionPane.showMessageDialog(this, "Pasajero encontrado en el asiento " + (pos + 1));
            } else {
                JOptionPane.showMessageDialog(this, "Pasajero no encontrado.");
            }
        });
    }


    private void actualizarAsientos() {
        Ticket[] asientos = avion.getAvion();
        for (int i = 0; i < 30; i++) {
            Ticket t = asientos[i];
            if (t == null && i == asientoSeleccionado) {
                seatButtons[i].setBackground(SELECCIONADO);
                seatButtons[i].setText("Asiento " + (i + 1) + "\n(seleccionado)");
                seatButtons[i].setToolTipText("Seleccionado - presione Sell Ticket");
            } else if (t == null) {
                seatButtons[i].setBackground(DISPONIBLE);
                seatButtons[i].setText("Asiento " + (i + 1));
                seatButtons[i].setToolTipText("Disponible - clic para seleccionar");
            } else if (t.isIsPalindrome()) {
                seatButtons[i].setBackground(PALINDROMO);
                seatButtons[i].setText((i + 1) + "\n" + t.getName());
                seatButtons[i].setToolTipText(t.getName() + " (palindromo, 20% descuento) - " + t.getFinalAmount());
            } else {
                seatButtons[i].setBackground(OCUPADO);
                seatButtons[i].setText((i + 1) + "\n" + t.getName());
                seatButtons[i].setToolTipText(t.getName() + " - " + t.getFinalAmount());
            }
        }
    }

    private void redirigirConsola() {
        PrintStream original = System.out;
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) {
                original.write(b);
                consola.append(String.valueOf((char) b));
                consola.setCaretPosition(consola.getDocument().getLength());
            }
        };
        System.setOut(new PrintStream(out, true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Recursividad().setVisible(true));
    }
        }
       

    

