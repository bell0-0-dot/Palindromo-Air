
package recursividad;

public class Ticket {
    private String Name;
    private double FinalAmount;
    private double OriginalAmount;
    private boolean isPalindrome;

    public Ticket(String Name, double FinalAmount, double OriginalAmount, boolean isPalindrome) {
        this.Name = Name;
        this.FinalAmount = FinalAmount;
        this.OriginalAmount = OriginalAmount;
        this.isPalindrome = isPalindrome;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public double getFinalAmount() {
        return FinalAmount;
    }

    public void setFinalAmount(double FinalAmount) {
        this.FinalAmount = FinalAmount;
    }

    public double getOriginalAmount() {
        return OriginalAmount;
    }

    public void setOriginalAmount(double OriginalAmount) {
        this.OriginalAmount = OriginalAmount;
    }

    public boolean isIsPalindrome() {
        return isPalindrome;
    }

    public void setIsPalindrome(boolean isPalindrome) {
        this.isPalindrome = isPalindrome;
    }

    
    
    public void print(){
        System.out.println("El nombre del pasajero es: "+getName());
        System.out.println("El total pagado es de: "+getFinalAmount());
    }
}
