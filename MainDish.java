public class MainDish extends Item {

   private String type;

   //constructor:
   public MainDish (String name, double price, String type){
      super(name, price);
      this.type=type;
   }

   public double calculateFinalPrice(){
      if (type.equalsIgnoreCase("seafood"))
         return price + 15.0; // we charge extra 15 SAR for seafood.
      else
         return price;
   }

 //setter and getter:
   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String toString(){
      return super.toString() + " , Type: " + type + " , Final price: "+calculateFinalPrice()+ " SAR.";
   }

}//end of class.