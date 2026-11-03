public class SpecialCake extends Dessert {

   private String messageOnCake;

   //constructor:
   public SpecialCake (String name, double price, String dessertType, String messageOnCake){
      super(name, price, dessertType);
      this.messageOnCake=messageOnCake;
   }  
      
   public double calculateFinalPrice() {
      return super.calculateFinalPrice() + 10.0; // we charge extra 10 SAR for special cake.
   }

   //setter and getter:
   public String getMessage() {
      return messageOnCake;
   }

   public void setMessage (String messageOnCake) {
      this.messageOnCake = messageOnCake;
   }

   public String toString(){
      return super.toString() + " , Message On Cake: " + messageOnCake + " , Final price: "+calculateFinalPrice()+ " SAR.";
   }
   
}//end of class.