import java.util.Scanner;

public class Sigma {
    // attributes
    private static String[] taskList = new String[100]; // initialise an array of size 100
    private static int taskListHead = 0;

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
            if (userInput.equals("list")){
                printList();
                continue;
            }

            if (userInput.equals("sigma")){
                echo("SIGMA INDEED!!!!");
            }
            addList(userInput);
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

    private static void addList(String userInput){
        taskList[taskListHead] = userInput;
        taskListHead += 1;
        System.out.println("____________________________________________________________");
        System.out.println("added: " + userInput);
        System.out.println("____________________________________________________________");
    }

    private static void printList(){
        System.out.println("____________________________________________________________");
        for (int i = 0; i < taskListHead; i += 1){
            System.out.println((i+1) + ". " + taskList[i]);
        }
        System.out.println("____________________________________________________________");
    }
}