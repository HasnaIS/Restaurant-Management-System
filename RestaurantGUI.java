import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RestaurantGUI extends JFrame {

    private Restaurant myRestaurant;

    // First Frame fields (Add Order)
    private JTextField idField, nameField, priceField, extraField;
    private JComboBox<String> typeBox;

    // Second Window (Search Order)
    private JFrame searchWindow;
    private JTextField searchField;
    private JTextArea resultArea;

    public RestaurantGUI() {
        myRestaurant = new Restaurant("KSU Restaurant", 100);

        // Main Frame
        setTitle("KSU Restaurant Management System");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2, 10, 10));

        add(new JLabel(" Order ID:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel(" Item Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel(" Price:"));
        priceField = new JTextField();
        add(priceField);

        add(new JLabel(" Item Type:"));
        String[] options = {"Main Dish", "Dessert", "Special Cake"};
        typeBox = new JComboBox<>(options);
        add(typeBox);

        add(new JLabel(" Extra Info (Type/Msg):"));
        extraField = new JTextField();
        add(extraField);

        // Add Order Button
        JButton addBtn = new JButton("Add Order");
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItemToRestaurant();
            }
        });
        add(addBtn);

        // Save Button
        JButton saveBtn = new JButton("Save to File");
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myRestaurant.saveOrdersToFile();
                JOptionPane.showMessageDialog(null, "Orders saved successfully!");
            }
        });
        add(saveBtn);

        // Open Search Window Button
        JButton searchWindowBtn = new JButton("Open Search Window");
        searchWindowBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchWindow.setVisible(true);
            }
        });
        add(searchWindowBtn);

        prepareSearchWindow();

        setVisible(true);
    }

    // Add Order Method
    private void addItemToRestaurant() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            String selectedType = (String) typeBox.getSelectedItem();
            String extra = extraField.getText();

            try {
                myRestaurant.findOrderRecursive(id, 0);
                JOptionPane.showMessageDialog(this, "Error: Order ID already exists!");
                return;
            } catch (OrderNotFoundException ex) {}

            Order newOrder = new Order(id, 10);

            if (selectedType.equals("Main Dish")) {
                newOrder.addItem(new MainDish(name, price, extra));
            } else if (selectedType.equals("Dessert")) {
                newOrder.addItem(new Dessert(name, price, extra));
            } else if (selectedType.equals("Special Cake")) {
                newOrder.addItem(new SpecialCake(name, price, "Cake", extra));
            }

            myRestaurant.addOrder(newOrder);

            JOptionPane.showMessageDialog(this,
                    "Order added successfully!\nTotal: " + newOrder.calculateTotal() + " SAR");

            clearInputs();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numbers for ID and Price.");
        }
    }

    // Prepare Search Window
    private void prepareSearchWindow() {
        searchWindow = new JFrame("Search Order");
        searchWindow.setSize(400, 300);
        searchWindow.setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new GridLayout(2, 1));

        searchField = new JTextField();

        // ENTER key support
        searchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchForOrder();
            }
        });

        JButton searchBtn = new JButton("Search Order");

        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchForOrder();
            }
        });

        topPanel.add(new JLabel(" Enter Order ID:"));
        topPanel.add(searchField);

        searchWindow.add(topPanel, BorderLayout.NORTH);
        searchWindow.add(searchBtn, BorderLayout.SOUTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        searchWindow.add(new JScrollPane(resultArea), BorderLayout.CENTER);
    }

    // Search Method
    private void searchForOrder() {
        try {
            int id = Integer.parseInt(searchField.getText());

            Order found = myRestaurant.findOrderRecursive(id, 0);

            resultArea.setText("Order Found!\n" + found.toString());

        } catch (OrderNotFoundException ex) {
            resultArea.setText(ex.getMessage());
        } catch (NumberFormatException ex) {
            resultArea.setText("Please enter a valid Order ID.");
        }
    }

    // Clear Inputs
    private void clearInputs() {
        idField.setText("");
        nameField.setText("");
        priceField.setText("");
        extraField.setText("");
    }

    public static void main(String[] args) {
        new RestaurantGUI();
    }
}