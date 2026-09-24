/**
 * Array-based stack used by the independent postfix-expression exercise.
 */
public final class DoubleStack {
    private final double[] items;
    private int top;

    public DoubleStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Stack capacity must be positive.");
        }
        items = new double[capacity];
        top = -1;
    }

    public void push(double value) {
        if (top == items.length - 1) {
            throw new IllegalStateException("Stack overflow.");
        }
        items[++top] = value;
    }

    public double pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack underflow.");
        }
        return items[top--];
    }

    public double peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty.");
        }
        return items[top];
    }

    public boolean isEmpty() {
        return top < 0;
    }

    public int size() {
        return top + 1;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder("[");
        for (int i = 0; i <= top; i++) {
            if (i > 0) {
                text.append(", ");
            }
            text.append(formatNumber(items[i]));
        }
        return text.append(']').toString();
    }

    public static double evaluatePostfix(String expression, StringBuilder trace) {
        if (expression == null || expression.trim().isEmpty()) {
            throw new IllegalArgumentException("Postfix expression is required.");
        }

        String[] tokens = expression.trim().split("\\s+");
        DoubleStack stack = new DoubleStack(tokens.length);
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (isOperator(token)) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException(
                            "Not enough operands before operator " + token + ".");
                }
                double right = stack.pop();
                double left = stack.pop();
                double result = apply(left, right, token);
                stack.push(result);
                appendTrace(trace, token + ": pop " + formatNumber(left) + " and "
                        + formatNumber(right) + ", push " + formatNumber(result), stack);
            } else {
                double value;
                try {
                    value = Double.parseDouble(token);
                } catch (NumberFormatException error) {
                    throw new IllegalArgumentException("Invalid token: " + token + ".");
                }
                stack.push(value);
                // peek() is deliberately used to demonstrate the required operation.
                appendTrace(trace, "push " + formatNumber(stack.peek()), stack);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Malformed postfix expression.");
        }
        return stack.peek();
    }

    private static boolean isOperator(String token) {
        return "+".equals(token) || "-".equals(token) || "*".equals(token)
                || "x".equalsIgnoreCase(token) || "\u00d7".equals(token)
                || "/".equals(token) || "\u00f7".equals(token);
    }

    private static double apply(double left, double right, String operator) {
        if ("+".equals(operator)) {
            return left + right;
        }
        if ("-".equals(operator)) {
            return left - right;
        }
        if ("*".equals(operator) || "x".equalsIgnoreCase(operator)
                || "\u00d7".equals(operator)) {
            return left * right;
        }
        if (right == 0.0) {
            throw new ArithmeticException("Division by zero.");
        }
        return left / right;
    }

    private static void appendTrace(StringBuilder trace, String action, DoubleStack stack) {
        if (trace != null) {
            trace.append(String.format("%-35s stack = %s%n", action, stack));
        }
    }

    private static String formatNumber(double value) {
        if (value == Math.rint(value)) {
            return Long.toString((long) value);
        }
        return Double.toString(value);
    }
}
