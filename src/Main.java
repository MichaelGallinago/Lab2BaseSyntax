import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final char[] operators = {'+', '-', '*', '/', ' '};
    private static final byte[] operatorsId = {0, 0, 0, 0, 0, 0, 0};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите последовательность из 8 цифр: ");
        String str = in.nextLine();

        while (operatorsId[operatorsId.length - 1] < operators.length) {
            StringBuilder stringBuilder = new StringBuilder(str);

            for (int i = 0; i < operatorsId.length; i++) {
                stringBuilder.insert(1 + i * 2, operators[operatorsId[i]]);
            }

            String line = stringBuilder.toString().replaceAll(" ", "");
            String operators = line.replaceAll("[1234567890]", "");
            if (operators.isEmpty()) {
                continue;
            }

            String[] textNumbers = line.split("[/\\-*+]");
            ArrayList<Double> valueList = new ArrayList<>();
            for (String string : textNumbers) valueList.add(Double.valueOf(string));

            applyOperators(operators, "*/", valueList);

            operators = operators.replaceAll("[*/]", "");

            if (!operators.isEmpty()) {
                applyOperators(operators, "+-", valueList);
            }

            if (Math.abs(valueList.get(0) - 100d) < 0.0001d) {
                System.out.println(line + " = " + valueList.get(0));
            }

            nextOperatorsId();
        }
    }

    private static void applyOperators(String operators, String operatorFilter, ArrayList<Double> valueList) {
        int j = 0;
        for (int i = 0; i < valueList.size() - 1; i++) {
            char currentOperator = operators.charAt(j);
            if (operatorFilter == null || operatorFilter.indexOf(currentOperator) >= 0) {
                i = updateValueList(valueList, i, currentOperator);
            }
            j++;
        }
    }

    private static int updateValueList(ArrayList<Double> valueList, int index, char operator) {
        double operationValue = valueList.get(index + 1);
        double result = valueList.get(index);
        switch (operator) {
            case '+':
                result += operationValue;
                break;
            case '-':
                result -= operationValue;
                break;
            case '/':
                result /= operationValue;
                break;
            case '*':
                result *= operationValue;
                break;
            default:
                return index;
        }


        valueList.set(index, result);
        valueList.remove(index + 1);
        return index - 1;
    }

    private static void nextOperatorsId() {
        operatorsId[0]++;
        for (int i = 0; i < operatorsId.length - 1; i++) {
            if (operatorsId[i] >= operators.length) {
                operatorsId[i] = 0;
                operatorsId[i + 1]++;
            }
        }
    }
}
