import java.util.Scanner;

public class Sigma {
    public static void main(String[] args) {
        sayIntro();

        // initialise scanner object
        Scanner in = new Scanner(System.in);

        while (true){
            String userInput = in.nextLine();
            if (userInput.equals("bye")){
                sayBye();
                return;
            }

            if (userInput.equals("sigma")){
                echo("SIGMA INDEED!!!!");
                continue;
            }
            echo(userInput);
        }
    }

    private static void sayIntro(){
        String logo = " ____  ___  ____  __  __    _    \n"
                + "/ ___||_ _|/ ___||  \\/  |  / \\   \n"
                + "\\___ \\ | || |  _ | |\\/| | / _ \\  \n"
                + " ___) || || |_| || |  | |/ ___ \\ \n"
                + "|____/|___|\\____||_|  |_/_/   \\_\\\n";
        System.out.println("____________________________________________________________");
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can i do for you?\n");
        System.out.println("____________________________________________________________");
    }

    private static void echo(String userInput){
        System.out.println("____________________________________________________________");
        System.out.println(userInput);
        System.out.println("____________________________________________________________");
    }

    private static void sayBye(){
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}