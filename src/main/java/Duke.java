import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<Task>();
    static boolean isTerminated = false;

    public static void printList() {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++)
        {
            System.out.println("    " + (i + 1) + ". " + list.get(i).toString());
        }
    }

    public static void printNoOfTasks() {
        System.out.printf("    Now you have %d tasks in the list.\n", list.size());
    }

    public static void main(String[] args) throws DukeException {
        while(!isTerminated) {
            try {
                System.out.println("Hello! I'm Jacky\nWhat can I do for you?");
                Scanner sc = new Scanner(System.in);
                String input = sc.nextLine();
                while (!input.equals("bye")) {
                    if (input.equals("list")) {
                        printList();
                        input = sc.nextLine();
                        continue;
                    } else if (input.contains("done")) {
                        System.out.println("    Nice! I've marked this task as done: ");
                        int taskNo = Integer.parseInt(input.substring(5));
                        Task task = list.get(taskNo - 1);
                        task.markAsDone();
                        System.out.println("      " + task);
                        input = sc.nextLine();
                        continue;
                    } else if (input.contains("delete")) {
                        System.out.println("    Noted. I've removed this task: ");
                        int taskNo = Integer.parseInt(input.substring(7));
                        Task task = list.get(taskNo - 1);
                        list.remove(taskNo - 1);
                        System.out.println("      " + task);
                        printNoOfTasks();
                        input = sc.nextLine();
                        continue;
                    } else if (input.contains("todo")) {
                        if (input.length() < 6) {
                            throw new DukeException("    OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            ToDo toDo = new ToDo(input.substring(5));
                            list.add(toDo);
                            System.out.println("    Got it. I've added this task:\n      " + toDo);
                            printNoOfTasks();
                        }
                        input = sc.nextLine();
                        continue;
                    } else if (input.contains("deadline")) {
                        if (input.length() < 10) {
                            throw new DukeException("    OOPS!!! The description of a deadline cannot be empty.");
                        } else {
                            String[] split = input.split("/");
                            Deadline deadline = new Deadline(split[0].substring(9), split[1].substring(3));
                            list.add(deadline);
                            System.out.println("    Got it. I've added this task:\n      " + deadline);
                            printNoOfTasks();
                            input = sc.nextLine();
                            continue;
                        }
                    } else if (input.contains("event")) {
                        if (input.length() < 7) {
                            throw new DukeException("    OOPS!!! The description of an event cannot be empty.");
                        } else {
                            String[] split = input.split("/");
                            Event event = new Event(split[0].substring(6), split[1].substring(3));
                            list.add(event);
                            System.out.println("    Got it. I've added this task:\n      " + event);
                            printNoOfTasks();
                            input = sc.nextLine();
                            continue;
                        }
                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch(DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("    Bye. Hope to see you again soon!");
        isTerminated = true;
        System.exit(0);
    }
}
