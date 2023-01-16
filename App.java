package sg.edu.nus.iss;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {

        String dirPath = "\\data2";
        String fileName = "";
        
        //instantiate a file/directory object
        File newDirectory = new File(dirPath);

        if (newDirectory.exists()) {
            System.out.println("Directory already exists");

        } else {
            newDirectory.mkdir();
        }

        System.out.println("Welcome to my shopping cart");

        //Array that stores user cart items
        List<String> cartItems = new ArrayList<String>();

        //To read input
        Console con = System.console();
        String input = "";

        while (!input.equals("quit")) {
            input = con.readLine("What do you want to perform? (Type quit to exit program) ");

            //use switch for static function to perform
            switch(input) {
                case "help":
                    displayMessage("'list' to show a list of items in shopping cart");
                    displayMessage("'login <name>' to access your shopping cart");
                    displayMessage("'add <item>' to add items into shopping cart");
                    displayMessage("'delete <item #>'");
                    displayMessage("'quit' to exit this program");
                    break;
                case "list":
                    if (cartItems.size() > 0) {
                        for (String item: cartItems) {
                            System.out.println(item);
                        }

                    } else {
                        displayMessage("Your cart is empty");
                    }
                    break;
                case "users":
                    break;
                default:

            }

            if (input.startsWith("login")) {
                input = input.replace(',', ' ');

                Scanner scanner = new Scanner(input.substring(6));

                while (scanner.hasNext()) {
                    fileName = scanner.next();
                }

                //Define the file path and file name
                File loginFile = new File(dirPath + File.separator + fileName);

                //try to create a file
                //isCreated is set to true if its a new file to create
                //is created is false if file exist
                boolean isCreated = loginFile.createNewFile();

                if (isCreated) {
                    displayMessage("new file created successfully" + loginFile.getCanonicalFile());
                } else {
                    displayMessage("File already created");
                }

            }

            String strValue = "";

            if (input.startsWith("add")) {
                input = input.replace(',', ' ');
                Scanner scanner = new Scanner(input.substring(4));
                
                while (scanner.hasNext()) {
                    strValue = scanner.next();
                    cartItems.add(strValue);
                }
            }
            //delete, starts from posiiton 6.
            // if (input.startsWith("delete")) {
            
            //     Scanner scanner = new Scanner(input.substring(6));

            //     while (scanner.hasNext()) {
            //         strValue = scanner.next();
            //         int listItemIndex = Integer.parseInt(strValue);

            //         if (listItemIndex < cartItems.size()) {
            //             cartItems.remove(listItemIndex);
            //         } else {
            //             displayMessage("Incorrect item index");
            //         }

            //     }

            cartItems = deleteCartItem(cartItems, input);



        }
    }

    public static void createLoginFile(String input, String dirPath, String fileName) {

        
    }



    public static List<String> deleteCartItem(List<String> cartItems, String input) {

        String strValue = "";
        //delete, starts from posiiton 6.
        if (input.startsWith("delete")) {
            
            Scanner scanner = new Scanner(input.substring(6));

            while (scanner.hasNext()) {
                strValue = scanner.next();
                int listItemIndex = Integer.parseInt(strValue);

                if (listItemIndex < cartItems.size()) {
                    cartItems.remove(listItemIndex);
                } else {
                    displayMessage("Incorrect item index");
                }

            }
    
        }

        return cartItems;

    }

    public static void listCart(List<String> cartItems) {

        if (cartItems.size() > 0) {
            // for (String item: cartItems) {
            //     System.out.println(item);
            // }

            for (int i = 0; i < cartItems.size(); i++) {
                System.out.printf("%d: %s\n", i, cartItems.get(i));
            }

        } else {
            displayMessage("Your cart is empty");
        }

    }
    public static void displayMessage (String message) {
        System.out.println(message);
    }
}
