package Logic;

import java.util.Scanner;

/**
 * Created by akhavan on 2016-05-15.
 */
public class UserInterface {
    Scanner input = new Scanner(System.in);

    /**
     * Prints the message in the output stream.
     * @param message that is going to be printed.
     */

    public void print(String message){
        System.out.println(message);
    }

    /**
     * Gets the input from the user.
     * But how should it return the value to the function that called it??
     * @param message that should be shown to get the certain message.
     */

    public void getInput(String message){

    }
}
