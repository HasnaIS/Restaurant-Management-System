public class Dessert extends Item {
   protected String dessertType;

   //constructor:
   public Dessert (String name, double price, String dessertType){
      super(name, price);
      this.dessertType=dessertType;
   }

   public double calculateFinalPrice(){
      if (dessertType.equalsIgnoreCase("souffle"))
         return price + 5.0; // we charge extra 5 SAR for souffle.
      else
         return price;
   }
   
 //setter and getter:
   public String getDessertType() {
      return dessertType;
   }

   public void setDessertType (String dessertType) {
      this.dessertType = dessertType;
   }

   public String toString(){
      return super.toString() + " , Dessert Type: " + dessertType + " , Final price: "+calculateFinalPrice()+ " SAR.";
   }

} //end of class.