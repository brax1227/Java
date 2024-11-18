import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Todo {
    private String task;
    private boolean isCompleted;

    public Todo(String task) {
        this.task = task;
        this.isCompleted = false;
    }

    public void markCompleted() {
        isCompleted = true;
    }

    @Override
    public String toString() {
        return (isCompleted ? "[✔] " : "[ ] ") + task;
    }

    public String toFileFormat() {
        return task + ";" + isCompleted;
    }

    public static Todo fromFileFormat(String line) {
        String[] parts = line.split(";");
        Todo todo = new Todo(parts[0]);
        if (Boolean.parseBoolean(parts[1])) {
            todo.markCompleted();
        }
        return todo;
    }
}

public class TodoListManager {
    private static final String FILE_NAME = "tasks.txt";
    private static ArrayList<Todo> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasks();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nTodo List Manager:");
            System.out.println("1. View tasks\n2. Add task\n3. Complete task\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> viewTasks();
                case 2 -> addTask(scanner);
                case 3 -> completeTask(scanner);
                case 4 -> {
                    saveTasks();
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void addTask(Scanner scanner) {
        System.out.print("Enter task: ");
        String task = scanner.nextLine();
        tasks.add(new Todo(task));
        System.out.println("Task added.");
    }

    private static void completeTask(Scanner scanner) {
        viewTasks();
        System.out.print("Enter task number to complete: ");
        int taskNumber = scanner.nextInt();
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.get(taskNumber - 1).markCompleted();
            System.out.println("Task marked as completed.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void loadTasks() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(Todo.fromFileFormat(line));
            }
        } catch (IOException e) {
            System.out.println("No saved tasks found.");
        }
    }

    private static void saveTasks() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Todo task : tasks) {
                bw.write(task.toFileFormat());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }
}
