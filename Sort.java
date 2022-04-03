

public class Sort {

    public static void main(String[] args)
    {
        // Create iterable Queue of numbers
        Object[] number1 = new String[] { "1", "4", "5", "8"};
        Object[] number2 = new String[] { "2", "3", "6", "7"};
//        QueueManager qNumb1 = new QueueManager("number1", number1);
//        QueueManager qNumb2 = new QueueManager("number2", number2);
        QueueManager q1 = new QueueManager(number1);
        q1.getQueue();
      //sorting numbers
        QueueManager q2 = new QueueManager(number2);
        q1.getQueue();
        q1.sort(q2);


    }
}
