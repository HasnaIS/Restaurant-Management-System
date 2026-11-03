import java.util.Scanner;

public class RestaurantTest {
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      
      Restaurant myRestaurant = new Restaurant("KSU Restaurant", 100);
      
      int choice = 0;
      
      System.out.println("***************** Restaurant Management System *****************");
      
      //menu:
      // Start a loop to add multiple items to the current order.
      while (choice != 7) {
         System.out.println("\nWelcome! Please choose an option:");
         System.out.println("1. Add a New Order");
         System.out.println("2. Find an Order (Recursive Search)");
         System.out.println("3. Remove an Order");
         System.out.println("4. Display Restaurant Details");
         System.out.println("5. Save Orders to file");
         System.out.println("6. read Orders from file");
         System.out.println("7. Exit");
         System.out.print("Your choice: ");
         choice = input.nextInt();
         
         switch (choice) {
            case 1:        //1. Add a New Order.
               System.out.print("Enter Order ID: ");
               int id = input.nextInt();
               char addMore = 'y';
               Order newOrder= null;
               int cap=0;
               try{
                  myRestaurant.findOrderRecursive(id, 0); {
                     System.out.println("Error: This Order ID already exists. Please use a unique ID.");
                     break; 
                  }
               }               catch (OrderNotFoundException e) {}
            
               System.out.print("Enter order capacity: ");
               cap = input.nextInt();
               
               newOrder = new Order(id, cap);
               
               while ((addMore == 'y' || addMore == 'Y') && newOrder.getCount() < cap) {
                  System.out.println("Add item: (1: Main Dish, 2: Dessert, 3: Special Cake)");
                  int itemType = input.nextInt();
                  input.nextLine(); // clear buffer.
                  
                  System.out.print("Enter item name: ");
                  String name = input.nextLine();
                  System.out.print("Enter price: ");
                  double price = input.nextDouble();
                  input.nextLine(); 
                  try{
                     if (itemType == 1) {
                        System.out.print("Enter dish type (e.g. seafood): ");
                        String type = input.nextLine();
                        newOrder.addItem(new MainDish(name, price, type));
                     } else if (itemType == 2) {
                        System.out.print("Enter dessert type (e.g. souffle): ");
                        String dType = input.nextLine();
                        newOrder.addItem(new Dessert(name, price, dType));
                     } else if (itemType == 3) {
                        System.out.print("Enter dessert type: ");
                        String dType = input.nextLine();
                        System.out.print("Enter cake message: ");
                        String msg = input.nextLine();
                        newOrder.addItem(new SpecialCake(name, price, dType, msg));
                     }
                  
                  }
                  
                  catch (NullPointerException e){
                     System.out.println(e.getMessage());}
               }
               System.out.print("Add another item to this order? (y/n): ");
               addMore = input.next().charAt(0);
               
               
               myRestaurant.addOrder(newOrder);
               if (newOrder.getCount() == cap) {
                  System.out.println("Note: You have reached the maximum capacity for this order.");
               }
               System.out.println("Order added to restaurant. Total price: " + newOrder.calculateTotal() + " SAR");
               break;
                
            case 2:       //2. Find an Order (Recursive Search).
               System.out.print("Enter Order ID to search: ");
               int searchId = input.nextInt();
               try{
                  Order found = myRestaurant.findOrderRecursive(searchId, 0);
                  if (found != null) {
                     System.out.println("Order Found: " + found.toString());
                     System.out.println("Total Amount: " + found.calculateTotal());}
                                    
                  //Applying discount:
                  System.out.print("Apply a special discount for this order? (y/n): ");
                  char apply = input.next().toLowerCase().charAt(0);
               
                  if (apply == 'y') {
                     System.out.print("Enter discount percentage (e.g., 10 for 10%): ");
                     double percent = input.nextDouble();
                     double finalPrice = found.applyDiscount(percent); 
                     System.out.println("Price after " + percent + "% discount: " + finalPrice + " SAR");
                  }
               } catch (OrderNotFoundException e){
                  System.out.println(e.getMessage());}
            
            
               break;
                
            case 3:     //3. Remove an Order.
               System.out.print("Enter Order ID to remove: ");
               int removeId = input.nextInt();
               if (myRestaurant.removeOrder(removeId)) {
                  System.out.println("Order removed successfully.");
               } else {
                  System.out.println("Order ID not found.");
               }
               break;
                
            case 4:      //4. Display Restaurant Details.
               System.out.println(myRestaurant.toString());
               break;

            case 5:   //5. save orders to files
               myRestaurant.saveOrdersToFile();
               break;
            
            case 6:  //6. read orders from files
            myRestaurant.readOrdersFromFile();
            break;
                
            case 7:      //5. Exit.
               System.out.println("Exiting system. Goodbye!");
               break;
                
            default:
               System.out.println("Invalid choice!");
         }
      }
      input.close();
   }
}//end of class.