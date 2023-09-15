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

            String string = stringBuilder.toString().replaceAll(" ", "");
            String signs = string.replaceAll("[1234567890]", "");
            if (signs.isEmpty()) {
                continue;
            }

            double[] values = Arrays.stream(string.split("[/\\-*+]")).mapToDouble(Double::parseDouble).toArray();

            ArrayList<Double> valueList = new ArrayList<>();
            for (double value : values) valueList.add(value);

            int j = 0;
            for (int i = 0; i < valueList.size() - 1; i++) {
                char currentSign = signs.charAt(j);
                if (currentSign == '*' || currentSign == '/') {
                    i = updateValueList(valueList, i, currentSign);
                }
                j++;
            }

            signs = signs.replaceAll("[*/]", "");
            if (!signs.isEmpty()) {
                int l = 0;
                for (int i = 0; i < valueList.size() - 1; i++) {
                    char currentSign = signs.charAt(l);
                    if (currentSign == '+' || currentSign == '-') {
                        i = updateValueList(valueList, i, currentSign);
                    }
                    l++;
                }
            }

            if (Math.abs(valueList.get(0) - 100d) < 0.0001d) {
                System.out.println(string + " = " + valueList.get(0));
            }

            nextOperatorsId();
        }
    }

    private static int updateValueList(ArrayList<Double> valueList, int index, char operator) {
        double value;
        switch (operator) {
            case '+':
                value = valueList.get(index) + valueList.get(index + 1);
                break;
            case '-':
                value = valueList.get(index) - valueList.get(index + 1);
                break;
            case '/':
                value = valueList.get(index) / valueList.get(index + 1);
                break;
            case '*':
                value = valueList.get(index) * valueList.get(index + 1);
                break;
            default:
                return index;
        }


        valueList.set(index, value);
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
