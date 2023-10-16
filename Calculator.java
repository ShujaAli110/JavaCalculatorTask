import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Welcome to the Calculator App!");
            System.out.print("Enter an arithmetic operation (e.g., 2 + 3 - 4) or 'exit' to quit: ");
            String input = scanner.nextLine();
            
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }
            
            try {
                double result = evaluateExpression(input);
                System.out.println("Result: " + result);
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
    
    static double evaluateExpression(String input) {
        String[] parts = input.split(" ");
        
        if (parts.length != 5 || !isValidOperator(parts[1]) || !isValidOperator(parts[3])) {
            throw new IllegalArgumentException("Invalid input. Please use the format: a + b - c, a - b + c, a * b * c, or a / b / c.");
        }
        
        double num1 = Double.parseDouble(parts[0]);
        double num2 = Double.parseDouble(parts[2]);
        double num3 = Double.parseDouble(parts[4]);
        char operator1 = parts[1].charAt(0);
        char operator2 = parts[3].charAt(0);
        
        double result = 0.0;
        if (operator1 == '+' && operator2 == '-') {
            result = num1 + num2 - num3;
        } else if (operator1 == '-' && operator2 == '+') {
            result = num1 - num2 + num3;
        } else if (operator1 == '*' && operator2 == '*') {
            result = num1 * num2 * num3;
        } else if (operator1 == '+' && operator2 == '*') {
            result = num1 + num2 * num3;
        }else if (operator1 == '-' && operator2 == '*') {
            result = num1 - num2 * num3;
        }else if (operator1 == '+' && operator2 == '/') {
            result = num1 + num2 / num3;
        }else if (operator1 == '-' && operator2 == '/') {
            result = num1 - num2 / num3;
        }    else if (operator1 == '/' && operator2 == '/') {
            if (num2 == 0 || num3 == 0) {
                throw new IllegalArgumentException("Division by zero is not allowed.");
            }
            result = num1 / num2 / num3;
        } else {
            throw new IllegalArgumentException("Invalid operators. Please use +, -, *, or /.");
        }
        
        return result;
    }
    
    static boolean isValidOperator(String operator) {
        return operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/");
    }
}
