import java.io.*;
   import java.util.Scanner;

public class Restaurant {
   private String restaurantName;
   private Order[] orderList ;
   private int orderCount;

  //constructor:
   public Restaurant(String restaurantName, int capacity){
      this.restaurantName=restaurantName;
      orderList = new Order[capacity];
      orderCount=0;
   }

   public void addOrder(Order o){
      if(orderCount<orderList.length)
         orderList[orderCount++]=o;
   }
   
  // Removes an order by ID and shifts the remaining elements.
   public boolean removeOrder(int id){
      for(int i = 0; i<orderCount; i++)
         if(orderList[i].getOrderId() == id){
         
            for(int j = i; j<orderCount-1; j++)
               orderList[j] = orderList[j+1];
         
            orderCount--;
            orderList[orderCount]=null;
            return true;}
          
      return false;
      
   
   }

   public int searchOrder(int id) throws OrderNotFoundException {
      for (int i = 0; i<orderCount; i++)
         if(orderList[i].getOrderId() == id)
            return i;
      
      throw new OrderNotFoundException ("Order with ID " +id+" does not exist");            
          
   }
   
  //Recursive Method:
   public Order findOrderRecursive(int id, int index ) throws OrderNotFoundException{
      if(index >= orderCount){
         throw new OrderNotFoundException ("Order with ID " +id+" does not exist");    }
                 
      if(orderList[index].getOrderId()==id)
         return orderList[index];
   
      return findOrderRecursive(id, index + 1);
   }
   

   public void saveOrdersToFile() {

      try{
         PrintWriter writer = new PrintWriter("orders.txt");
         writer.println("Resturnt Name: "+restaurantName);
         writer.println("Number of orders: "+orderCount);
         writer.println("----------------------");

      for (int i = 0; i < orderCount; i++) {
         writer.println("Order id: "+orderList[i].getOrderId());
         writer.println("Item Count: "+orderList[i].getCount());
         writer.println("----------------------");

         
          
      }
      writer.close();
      System.out.println("Orders have been saved!");
    }
    catch(IOException e){
      System.out.println("Error saving orders to file "+ e.getMessage());

    }
   }

   public void readOrdersFromFile(){
      try{
         Scanner input = new Scanner(new File("orders.txt"));
         while (input.hasNext()) {
             String line = input.nextLine();
             System.out.println(line);
         }

      input.close();

             
         } catch(Exception e){
            System.out.println("Error reading from file");
         }
      }
   

   
   //setter and getter:
   public String getRestaurantName() {
      return restaurantName;
   }

   public void setRestaurantName(String restaurantName) {
      this.restaurantName = restaurantName;
   }

   public int getOrderCount() {
      return orderCount;
   }

   public String toString(){
      return "The restaurant name is: "+restaurantName+"\n The number of orders is: "+orderCount;
   }
} //end of class. 