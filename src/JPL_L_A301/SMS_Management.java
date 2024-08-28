package JPL_L_A301;

import JPL_L_A301.DAO.Impl.CustomerDAOImpl;
import JPL_L_A301.DAO.Impl.LineItemImpl;
import JPL_L_A301.DAO.Impl.OrderImpl;
import JPL_L_A301.Entity.Customer;
import JPL_L_A301.Entity.LineItem;
import JPL_L_A301.Entity.Order;
import JPL_L_A301.Utils.Helper;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SMS_Management {
    public static CustomerDAOImpl customerDAOImpl = new CustomerDAOImpl();
    public static LineItemImpl lineItemImpl = new LineItemImpl();
    public static OrderImpl orderImpl = new OrderImpl();
    public static Scanner scanner = new Scanner(System.in);

    public static void insertNewOrder(){
        Order order = new Order();
        while(true){
            System.out.print("Enter order date (dd/MM/yyyy): ");
            String orderDate = scanner.nextLine();
            if(Helper.validateDate(orderDate)){
                order.setOrderDate(Helper.convertStringToDate(orderDate));
                break;
            }
            System.out.println("Date invalid!");
        }
        System.out.print("Enter Customer ID: ");
        order.setCustomerId(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter Employee ID: ");
        order.setEmployeeId(scanner.nextInt());
        scanner.nextLine();
        try {
            if(orderImpl.insertOrder(order)) System.out.println("Insert Order Successfully!");
            else System.out.println("Insert Order Failed!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void insertNewLineItem() {
        LineItem lineItem = new LineItem();
        System.out.print("Enter Order ID: ");
        lineItem.setOrderId(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter product ID: ");
        lineItem.setProductId(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter quantity: ");
        lineItem.setQuantity(scanner.nextInt());
        scanner.nextLine();

        if(lineItemImpl.addLineItem(lineItem)) System.out.println("Insert LineItem Successfully!");
        else System.out.println("Insert LineItem Failed!");
    }

    public static void updateTotalOrder(){
        System.out.println("Enter Order ID: ");
        int orderId = scanner.nextInt();
        if(orderImpl.updateOrderTotal(orderId)) System.out.println("Update Total Order Successfully!");
        else System.out.println("Update Total Order Failed!");
    }

    public static void computeTotalPrice(){
        System.out.println("Enter Order ID: ");
        int orderId = scanner.nextInt();
        System.out.format("Total price: %f", orderImpl.computeOrderTotal(orderId));
    }

    public static void insertNewCustomer(){
        Customer customer = new Customer();
        System.out.println("Enter customer name: ");
        String customerName = scanner.nextLine();
        customer.setCustomerName(customerName);

        if(customerDAOImpl.addCustomer(customer)) System.out.println("Insert Customer Successfully!");
        else System.out.println("Insert Customer Failed!");
    }

    public static void deleteCustomer(){
        System.out.println("Enter customer ID: ");
        int customerId = scanner.nextInt();
        scanner.skip("\n");
        if(customerDAOImpl.deleteCustomer(customerId)) System.out.println("Delete Customer Successfully!");
        else System.out.println("Delete Customer Failed!");
    }

    public static void main(String[] args) throws Exception {
        String option = "";
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("========= SMS Management ==========");
            System.out.println("1. Show list customer");
            System.out.println("2. Show list order by customer_id");
            System.out.println("3. Show line item by order_id");
            System.out.println("4. Insert new order");
            System.out.println("5. Add new Line item");
            System.out.println("6. Update total order");
            System.out.println("7. Compute total price");
            System.out.println("8. Add new customer");
            System.out.println("9. Delete customer by id");
            System.out.println("0. Exit");
            System.out.print("Enter option: ");

            option = sc.nextLine();

            if(option.equals("1")) customerDAOImpl.getAllCustomer().forEach(System.out::println);
            else if(option.equals("2")) customerDAOImpl.getAllOrdersByCustomerId(1).forEach(System.out::println);
            else if(option.equals("3")) lineItemImpl.getAllItemsByOrderId(1).forEach(System.out::println);
            else if(option.equals("4")) insertNewOrder();
            else if(option.equals("5")) insertNewLineItem();
            else if(option.equals("6")) updateTotalOrder();
            else if(option.equals("7")) computeTotalPrice();
            else if(option.equals("8")) insertNewCustomer();
            else if(option.equals("9")) deleteCustomer();
            else if("0".equals(option)) break;
            else System.out.println("Invalid option!");
        }
    }
}
