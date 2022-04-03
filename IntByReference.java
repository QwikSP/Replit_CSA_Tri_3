public class IntByReference {
        private int value;

        // Hack: create IntByReference, swapToLowHighOrder and toString methods
        public IntByReference(int value){
            this.value = value;
        }
        // static method that enables me to see numbers swapped by reference (before, after)
        public int getValue(){
            return value;
        }

        public void setValue(int v){
            value = v;
        }

        public void swapToLowHighOrder(IntByReference b){
            //object a has b value
            if (value > b.getValue()){
                int temp = value;
                value = b.getValue();
                b.setValue(temp);
            }

        }

    public String toString(){
            return ""+value;
    }

        public static void swapper(int n0, int n1) {
            IntByReference a = new IntByReference(n0);
            IntByReference b = new IntByReference(n1);
            System.out.println("Before: " + a + " " + b);
            a.swapToLowHighOrder(b);  // conditionally build swap method to change values of a, b
            System.out.println("After: " + a + " " + b);
            System.out.println();
        }

        // static main method that provides some simple test cases
        public static void main(String[] ags) {
            IntByReference.swapper(21, 16);
            IntByReference.swapper(16, 21);
            IntByReference.swapper(16, -1);
        }

}
