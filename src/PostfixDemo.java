/** Standalone Part A3 demonstration; it is intentionally outside the main menu. */
public final class PostfixDemo {
    private PostfixDemo() {
    }

    public static void main(String[] args) {
        String expression = args.length == 0 ? "5 3 + 2 *" : join(args);
        StringBuilder trace = new StringBuilder();
        double result = DoubleStack.evaluatePostfix(expression, trace);
        System.out.println("Expression: " + expression);
        System.out.print(trace);
        System.out.println("Final result: " + result);
    }

    private static String join(String[] values) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                text.append(' ');
            }
            text.append(values[i]);
        }
        return text.toString();
    }
}
