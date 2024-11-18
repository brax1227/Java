import java.util.PriorityQueue;
import java.util.Scanner;

class Task implements Comparable<Task> {
    private String name;
    private int priority;
    private int deadline; // Optional: Deadline in hours or days

    public Task(String name, int priority, int deadline) {
        this.name = name;
        this.priority = priority;
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public int getDeadline() {
        return deadline;
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(other.priority, this.priority); // Higher priority first
    }

    @Override
    public String toString() {
        return "Task{name='" + name + "', priority=" + priority + ", deadline=" + deadline + "}";
    }
}

public class TaskScheduler {
    private PriorityQueue<Task> taskQueue;

    public TaskScheduler() {
        taskQueue = new PriorityQueue<>();
    }

    public void addTask(String name, int priority, int deadline) {
        taskQueue.add(new Task(name, priority, deadline));
    }

    public void viewTasks() {
        if (taskQueue.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Tasks:");
            for (Task task : taskQueue) {
                System.out.println(task);
            }
        }
    }

    public void executeTask() {
        if (taskQueue.isEmpty()) {
            System.out.println("No tasks to execute.");
        } else {
            Task task = taskQueue.poll();
            System.out.println("Executing: " + task);
        }
    }

    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTask Scheduler:");
            System.out.println("1. Add Task\n2. View Tasks\n3. Execute Task\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter task name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter priority (higher number = higher priority): ");
                    int priority = scanner.nextInt();
                    System.out.print("Enter deadline (in hours): ");
                    int deadline = scanner.nextInt();
                    scheduler.addTask(name, priority, deadline);
                    System.out.println("Task added.");
                }
                case 2 -> scheduler.viewTasks();
                case 3 -> scheduler.executeTask();
                case 4 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
