
import java.util.*;

public class Calculator {

    private final String expression;
    private ArrayList<String> tokens;
    private ArrayList<String> reverse_polish;
    private Double result;

    // Takes tokens and converts to Reverse Polish Notation (RPN), this is one where the operator follows its operands.
    private void tokensToReversePolishNotation () {
        // contains final list of tokens in RPN
        this.reverse_polish = new ArrayList<>();

        // stack is used to reorder for appropriate grouping and precedence
        Stack tokenStack = new Stack();
        for (String token : tokens) {
            switch (token) {
                // If left bracket push token on to stack
                case "(":
                    tokenStack.push(token);
                    break;
                case ")":
                    while (tokenStack.peek() != null && !tokenStack.peek().equals("("))
                    {
                        reverse_polish.add( (String)tokenStack.pop() );
                    }
                    tokenStack.pop();
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "%":
                case "sqrt(":
                    // While stack
                    // not empty AND stack top element
                    // and is an operator
                    while ((!tokenStack.empty()) && (isOperator((String) tokenStack.peek())))
                    {
                        if ( isPrecedent(token, (String) tokenStack.peek() )) {
                            reverse_polish.add((String)tokenStack.pop());
                            continue;
                        }
                        break;
                    }
                    // Push the new operator on the stack
                    tokenStack.push(token);
                    break;
                default:    // Default should be a number, there could be test here
                    this.reverse_polish.add(token);
            }
        }
        // Empty remaining tokens
        while (!tokenStack.empty()) {
            reverse_polish.add((String)tokenStack.pop());
        }
    }

    // Helper definition for supported operators
    private final Map<String, Integer> OPERATORS = new HashMap<>();
    {
        // Map<"token", precedence>
        OPERATORS.put("*", 3);
        OPERATORS.put("/", 3);
        OPERATORS.put("%", 3);
        OPERATORS.put("+", 4);
        OPERATORS.put("-", 4);
    }


    // Helper definition for supported operators
    private final Map<String, Integer> SEPARATORS = new HashMap<>();
    {
        // Map<"separator", not_used>
        SEPARATORS.put(" ", 0);
        SEPARATORS.put("(", 0);
        SEPARATORS.put(")", 0);
    }

    // Test if token is an operator
    private boolean isOperator(String token) {
        // find the token in the hash map
        return OPERATORS.containsKey(token);
    }

    // Test if token is an separator
    private boolean isSeperator(String token) {
        // find the token in the hash map
        return SEPARATORS.containsKey(token);
    }

    // Compare precedence of operators.
    private Boolean isPrecedent(String token1, String token2) {
        // token 1 is precedent if it is greater than token 2
        return (OPERATORS.get(token1) - OPERATORS.get(token2) >= 0) ;
    }
    // Create a 1 argument constructor expecting a mathematical expression
    public Calculator(String expression) {
        // original input
        this.expression = expression;

        // parse expression into terms
        this.termTokenizer();

        // place terms into reverse polish notation
        this.tokensToReversePolishNotation();

        // calculate reverse polish notation
        this.rpnToResult();
    }

    // Term Tokenizer takes original expression and converts it to ArrayList of tokens
    private String termTokenizer() {
        // contains final list of tokens
        this.tokens = new ArrayList<>();

        int start = 0;  // term split starting index
        StringBuilder multiCharTerm = new StringBuilder();    // term holder
        for (int i = 0; i < this.expression.length(); i++) {
            Character c = this.expression.charAt(i);
            if (isOperator(c.toString()) || isSeperator(c.toString())) {
                // 1st check for working term and add if it exists
                if (multiCharTerm.length() > 0) {
                    tokens.add(this.expression.substring(start, i));
                }
                // Add operator or parenthesis term to list
                if (c != ' ') {
                    tokens.add(c.toString());
                }
                // Get ready for next term
                start = i + 1;
                multiCharTerm = new StringBuilder();
            } else {
                // multi character terms: numbers, functions, perhaps non-supported elements
                // Add next character to working term
                multiCharTerm.append(c);
            }

        }
        // Add last term
        if (multiCharTerm.length() > 0) {
            tokens.add(this.expression.substring(start));
        }
        // Key instance variables
        return null;
    }
        // Print the expression, terms, and result
        public String toString() {
            return ("Original expression: " + this.expression + "\n" +
                    "Tokenized expression: " + this.tokens.toString() + "\n" +
                    "Reverse Polish Notation: " +this.reverse_polish.toString() + "\n" +
                    "Final result: " + String.format("%.2f", this.result));
        }
        // Takes RPN and produces a final result
        private void rpnToResult()
        {
            // Stack used to hold calculation while process RPN
            Stack calculation = new Stack();
//            System.out.println(reverse_polish);
            Double z = 0.0;
            for (String token : reverse_polish)
            {
                //check if token is not operator
//                System.out.println((token));
                if (!isOperator(token)) {
//                    System.out.println((token));
                    calculation.push(token);

                }
                else{
//                    System.out.println(calculation.pop().toString());
                    Double y = Double.parseDouble(calculation.pop().toString());
                    Double x = Double.parseDouble(calculation.pop().toString());

                    if (token.equals("*")){
                        z = x * y;
                    }
                    else if (token.equals("/")){
                        z = x / y;
                    }
                    else if (token.equals("+")){
                        z = x + y;
                    }
                    else if (token.equals("-")){
                        z = x - y;
                    }
                    else if (token.equals("%")){
                        z = x % y;
                    }
//                    System.out.println(z);
                    // Based off of Token operator calculate result

                    // Push result back onto the stack
                    calculation.push(z);
                }
            }
            // Pop final result and set as final result for expression

            result = Double.parseDouble(calculation.pop().toString());
        }

    public static void main(String[] args)
    {
        Calculator simpleMath = new Calculator("100 + 200  * 3");
        System.out.println("Simple Math\n" + simpleMath);

        Calculator parenthesisMath = new Calculator("(100 + 200)  * 3");
        System.out.println("Parenthesis Math\n" + parenthesisMath);

        Calculator allMath = new Calculator("200 % 300 + 5 + 300 / 200 + 1 * 100");
        System.out.println("All Math\n" + allMath);

        Calculator allMath2 = new Calculator("200 % (300 + 5 + 300) / 200 + 1 * 100");
        System.out.println("All Math2\n" + allMath2);

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter problem");

        String userName = myObj.nextLine();  // Read user input
        Calculator user = new Calculator(userName);
        System.out.println(user);

    }
}
