import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final char[] symbols = new char[] {'+', '-', '*', '/', ' '};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите последовательность из 8 цифр: ");
        String str = in.nextLine();

        for (int a = 0; a <= 4; a++) {
            for (int b = 0; b <= 4; b++) {
                for (int c = 0; c <= 4; c++) {
                    for (int d = 0; d <= 4; d++) {
                        for (int e = 0; e <= 4; e++) {
                            for (int f = 0; f <= 4; f++) {
                                for (int g = 0; g <= 4; g++) {
                                    StringBuilder stringBuilder = new StringBuilder(str);

                                    stringBuilder.insert(1, symbols[a]);
                                    stringBuilder.insert(3, symbols[b]);
                                    stringBuilder.insert(5, symbols[c]);
                                    stringBuilder.insert(7, symbols[d]);
                                    stringBuilder.insert(9, symbols[e]);
                                    stringBuilder.insert(11, symbols[f]);
                                    stringBuilder.insert(13, symbols[g]);

                                    String string = stringBuilder.toString().replaceAll(" ", "");
                                    String signs = string.replaceAll("[1234567890]", "");
                                    if (signs.length() == 0) {
                                        continue;
                                    }

                                    double[] values = Arrays.stream(string.split("[/\\-*+]"))
                                            .mapToDouble(Double::parseDouble).toArray();

                                    ArrayList<Double> valueList = new ArrayList<>();
                                    for(double value : values) valueList.add(value);

                                    var j = 0;
                                    for (int i = 0; i < valueList.size() - 1; i++) {
                                        char currentSign = signs.charAt(j);
                                        if (currentSign == '*') {
                                            valueList.set(i, valueList.get(i) * valueList.get(i + 1));
                                            valueList.remove(i + 1);
                                            i--;
                                        }
                                        else if (currentSign == '/') {
                                            valueList.set(i, valueList.get(i) / valueList.get(i + 1));
                                            valueList.remove(i + 1);
                                            i--;
                                        }
                                        j++;
                                    }

                                    signs = signs.replaceAll("[*/]", "");
                                    if (signs.length() > 0) {
                                        var l = 0;
                                        for (int i = 0; i < valueList.size() - 1; i++) {
                                            char currentSign = signs.charAt(l);
                                            if (currentSign == '+') {
                                                valueList.set(i, valueList.get(i) + valueList.get(i + 1));
                                                valueList.remove(i + 1);
                                                i--;
                                            }
                                            else if (currentSign == '-') {
                                                valueList.set(i, valueList.get(i) - valueList.get(i + 1));
                                                valueList.remove(i + 1);
                                                i--;
                                            }
                                            l++;
                                        }
                                    }

                                    if (Math.abs(valueList.get(0) - 100d) < 0.0000001d)
                                    {
                                        System.out.println(string + "= 100");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
