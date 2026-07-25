
package recursividad;

import javax.swing.JOptionPane;

public class PalindromoAir {
    
Ticket []avion=new Ticket[30];

public int firtsAvailable(){
    return firstAvailable(avion,0);
}
private int firstAvailable(Ticket []avion, int indice){

    if (indice>=avion.length){
        return -1;   
    }
    
    if (avion[indice]==null){
        return indice;
    }

    return firstAvailable(avion, indice+1);
}

public int searchPassenger(String name){
    return searchPassenger(name,0);
}
private int searchPassenger(String name, int indice){
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

public void printPassengers(){

    printPassenger(0);
}

private void printPassenger(int indice){
    if(indice>=avion.length){
       return;
    }
    
    if(avion[indice]!=null){
        System.out.println((indice+1)+"El nombre del pasajero es: "+avion[indice].getNombre());
        System.out.println("El total pagado es: "+avion[indice].getTotal_pagado());
    }
    printPassenger(indice+1);
}

public double income(){
    return income(0);
}

private double income(int indice){
    
   double total=0;
   
   if(indice>=avion.length){
       return 0;
   }
   if(avion[indice]!=null){
       total=avion[indice].getTotal_pagado();
   }
    return total+income (indice+1);
}


}
