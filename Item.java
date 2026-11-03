public abstract class Item implements Discountable {
   protected String name;
   protected double price;
   
   //constructor:
   public Item (String name, double price){
      this.name=name;
      this.price=price;
   }

   //abstract method:
   public abstract double calculateFinalPrice();

   //interface:
   public double applyDiscount (double price){
      double rate=price;
      double discountAmount=this.price*(rate/100);
      return this.price-discountAmount;
   }
   
   //setter and getter:
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
 
   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   public String toString() {
      return "Item name: " + name + " , Price: " + price + " SAR";
   }
    
} //end of class
























