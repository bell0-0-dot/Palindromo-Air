
package recursividad;

import javax.swing.JOptionPane;

public class PalindromoAir {
    
Ticket []avion=new Ticket[30];

    public Ticket[] getAvion() {
        return avion;
    }


public int firstAvailable(int indice){

    if (indice>=avion.length){
        return -1;   
    }
    
    if (avion[indice]==null){
        return indice;
    }

    return firstAvailable(indice+1);
}


public int searchPassenger(String name, int indice){
    String n=name;
    if (indice >=avion.length){
        return -1;
        
    }
   if (avion [indice].equals(name)){
       return indice;
   }
    return searchPassenger (name, indice+1);
}

public boolean isPalindromo(String name){
    return isPalindromo(name,0, name.length());
}

private boolean isPalindromo (String name, int izquierda, int derecha){
    
    if (izquierda>=derecha){
        return true;
    }
    if(name.charAt(izquierda)!= name.charAt(derecha)){
        return false;
    }

    return isPalindromo(name, izquierda + 1, derecha-1);
    
}

public void printPassenger(int indice){
    if(indice>=avion.length){
       return;
    }
    
    if(avion[indice]!=null){
        System.out.println((indice+1)+"El nombre del pasajero es: "+avion[indice].getName());
        System.out.println("El total pagado es: "+avion[indice].getFinalAmount());
    }
    printPassenger(indice+1);
}


public double income(int indice){
    
   double total=0;
   
   if(indice>=avion.length){
       return 0;
   }
   if(avion[indice]!=null){
       total=avion[indice].getFinalAmount();
   }
    return total+income (indice+1);
}

public void reset(int index){
    if(avion.length<index){
        avion[index]=null;
        reset(index + 1);
    }
} 

public void sellTicket(String Name){
    int disponible=firstAvailable(0);
    boolean palindromo=isPalindromo(Name);
    double descuento=0, total_pagar=800;
    
    if(disponible!=-1 && palindromo==true){
        total_pagar=total_pagar-(total_pagar*0.20);
        
    }
    avion[disponible]=new Ticket(Name, total_pagar, 800, palindromo);
    
    JOptionPane.showMessageDialog(null,
        "===== TICKET =====\n" +
        "Pasajero: " + Name + "\n" +
        "Asiento: " + disponible + "\n" +
        (palindromo ? "Descuento aplicado: 20%\n" : "") +
        "Monto original: $" + 800 + "\n" +
        "Total a pagar: $" + total_pagar
    );
    
}

public boolean cancelTicket(String Name){
    int posicion_encontrada=searchPassenger(Name,0);
    if(posicion_encontrada!=-1){
        avion[posicion_encontrada]=null;
        return true;
    }else{
            return false;
    }}
    
 public void dispatch(){
  double ingresos=income(0);
     System.out.println("El total de ingresos generados fue: "+ingresos+" LPS");
     reset(0);
     System.out.println("ASIENTOS DISPONIBLES");
 }
 
    

    
}




