
import java.util.Stack;
public class stack2 {

        static Stack<Integer> st= new Stack<>();
        private int numbers;

//pushing 
  
        public static void push_digits(int number)
        {
            while(number != 0)
            {
                st.push(number % 10);
                number = number / 10;
            }
        }
//reverse numbers
        public static String reverse_number(int number)
        {

            push_digits(number);
            int reverse = 0;
            int i = 1;

            while (!st.isEmpty()) {
                reverse = reverse + (st.peek() * i);

                st.pop();
                i = i * 10;
            }
            String temp = String.valueOf(reverse);
            String newString = "";
            for (int j = 0; temp.length() > j; j++) {
                newString += temp.charAt(j) + " ";
            }


            return newString;
        }

        public static void main(String[] args)
        {
          //create list to be reversed
            Object[] SL = new String[] {"1", "2", "3"};
            QueueManager stac = new QueueManager(SL);
            System.out.println("After: " + reverse_number(stac.getNumbers()));

        }
    }



