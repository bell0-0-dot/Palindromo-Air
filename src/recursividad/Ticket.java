
package recursividad;

public class Ticket {
    private String nombre;
    private double total_pagado;

    public Ticket(String nombre, double total_pagado) {
        this.nombre = nombre;
        this.total_pagado = total_pagado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTotal_pagado() {
        return total_pagado;
    }

    public void setTotal_pagado(double total_pagado) {
        this.total_pagado = total_pagado;
    }
    
    public void print(){
        System.out.println("El nombre del pasajero es: "+getNombre());
        System.out.println("El total pagado es de: "+getTotal_pagado());
    }
}
