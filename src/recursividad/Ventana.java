
package recursividad;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ventana extends JFrame {
    public Ventana (){
        setBounds(200, 200, 500, 500); //estableclemos la locacion de la ventana y el tamaño
        //setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("PalindromoAir");
        //this.setLocation(200,200);
        setLocationRelativeTo(null);
        setResizable(false); //si la ventana puede cambiar de tamaño
        iniciar_componentes();
    }
    
    private void iniciar_componentes(){
        JPanel panel=new JPanel(); //creacion del panel lienzo
        panel.setLayout(null);
        this.getContentPane().add(panel);//agregamos el panel a la ventana
        panel.setBackground(Color.white);
        
        JLabel etiqueta=new JLabel();
        etiqueta.setText("Bienvenidos al Palindromo AIR");
        etiqueta.setBounds(150, 20, 500, 20);
        JLabel e2 = new JLabel();
        e2.setText("<html>Menu<br><br>1. Vender ticket<br>2. Cancelar ticket<br>3. Despachar Avion</html>");
        e2.setBounds(150, 50, 300, 100);
        panel.add(e2);
        panel.add(etiqueta);
        
        
        
    }
    
    
}
