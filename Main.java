import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.RunnableFuture;

public class Main {
    String title;
    Runnable action;

    public Main(String title, Runnable action) {
        this.title = title;
        this.action = action;
    }

    public String getTitle() {
        return this.title;
    }

    public Runnable getAction() {
        return this.action;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<Integer, Main> Main = new HashMap<>();

        Main.put(1, new Main("Matrix", () -> Matrix.main(null)));
        Main.put(2, new Main("IntByReference", () -> IntByReference.main(null)));
        Main.put(3, new Main("Queue", () -> QueueTester.main(null)));
        Main.put(4, new Main("Sort", () -> Sort.main(null)));
        Main.put(5, new Main("stack", () -> stack2.main(null)));
        Main.put(6, new Main("Calculator", () -> Calculator.main(null)));
        System.out.println("Main:");

      Boolean temp = true;
      
      while (temp = true) {
        System.out.print("\n");
        for (Map.Entry<Integer, Main> pair : Main.entrySet()) {
            System.out.println(pair.getKey() + " ==> " + pair.getValue().getTitle());
        }
        System.out.print("Enter any key besides the stated keys to exit" + "\n" + "\n" + "input:");
            try {
                int input = sc.nextInt();
                Main m = Main.get(input);
                m.getAction().run();
            } catch (Exception e) {
                System.out.println("\n"+"Exiting...");
                return;
            }
        }
    }

}



