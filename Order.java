

public class Order implements Discountable {
   private int orderId;
   private List itemList;
   private int count;

   //constructor:
   public Order(int orderId, int capacity){
      this.orderId=orderId;
      itemList = new List ("Order items");
      count=0;
   }


   public boolean addItem(Item t){
      if (t==null){
         throw new NullPointerException ("Error: cannot add a null item to the order!");}
        
                
      if(t instanceof SpecialCake){
         SpecialCake s = (SpecialCake)t;
         itemList.insertAtBack( new SpecialCake(s.getName(),s.getPrice(),s.getDessertType(),s.getMessage()));
         count++;}
      else
         if(t instanceof Dessert){
            Dessert d = (Dessert) t;
            itemList.insertAtBack(new Dessert(d.getName(),d.getPrice(),d.getDessertType()));
            count++;}
         else 
            if(t instanceof MainDish){
               MainDish m = (MainDish)t;
               itemList.insertAtBack(new MainDish(m.getName(),m.getPrice(),m.getType()));
               count++;}
            else {
               return false;}
   
      return true;
        
         
   
   }

// Removes an item by name and shifts the remaining elements.
   public boolean removeItem(String itemName) {
      if (itemList.isEmpty()) {
         return false;
      }
   
      Node current = itemList.getHead();
      int index = 0;
   
      while (current != null) {
         Item item = (Item) current.getData();
         if (item.getName().equals(itemName)) {
            itemList.removeFromMiddle(index);
            count--;
            return true;
         }
         current = current.getNext();
         index++;
      }
      return false;
   }
   
   public double calculateTotal() {
      double total = 0;
      Node current = itemList.getHead();
      while (current != null) {
         Item item = (Item) current.getData();
         total += item.calculateFinalPrice();
         current = current.getNext();
      }
      return total;
   }
   

   public double applyDiscount(double price){
      double total = calculateTotal();
      double discountAmount = total * (price/100);
   
      return total-discountAmount;
   }
    
   //setter and getter:
   public int getOrderId(){
      return orderId;
   }

   public void setOrderId(int oID){
      orderId=oID;
   }
    
   public int getCount() {
      return count;
   }

   public String toString(){
      return "The order Id is: "+orderId+"\nThe number of items is: "+count;
   }
    
}//end of class.
