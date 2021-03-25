import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(final String[] args) {
        final int arraySize = 10;
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        System.out.print("Введите число и нажмите Enter: ");
        String modifierStr = scanner.nextLine();
        while (!(isDigitsOnly(modifierStr))) {
            System.out.println("Не верно введено число! Попробуем еще раз");
            System.out.print("Введите число и нажмите Enter: ");
            modifierStr = scanner.nextLine();
        }
        int inputModifier = Integer.parseInt(modifierStr);
        int[] arrayOne = createFillArray(arraySize, inputModifier, Integer::sum);
        System.out.println(Arrays.toString(arrayOne));
        int[] arrayTwo = createFillArray(arraySize, inputModifier, (index, modifier) -> index * modifier);
        System.out.println(Arrays.toString(arrayTwo));
        int[] arrayThree = createFillArray(arraySize, inputModifier, (index, modifier) -> {
            if (index % 2 == 0) {
                return index / 2 + modifier;
            }
            return index * index - modifier;
        });
        System.out.println(Arrays.toString(arrayThree));
        /*Число = если модификатор больше или равен индексу в квадрате
          <индекс в квадрате + модификатор в квадрате>, если индекс больше или равен модификатору
          в квадрате <(модификатор плюс индекс) в квадрате> иначе <индекс + модификатор>*/
        int[] arrayFour = createFillArray(arraySize, inputModifier, (index, modifier) -> {
            if (modifier >= index * index) {
                return index * index + modifier * modifier;
            }
            if (index >= modifier * modifier) {
                return (modifier + index) * (modifier + index);
            }
            return index + modifier;
        });
        System.out.println(Arrays.toString(arrayFour));
        // Число = <случайное число, меньшее или равное сумме (модификатора
        // в квадрате и индекса в квадрате)>
        int[] arrayFive = createFillArray(arraySize, inputModifier, (index, modifier) -> {
            Random random = new Random();
            if (modifier == 0 && index == 0) {
                return 0;
            }
            return random.nextInt(modifier * modifier + index * index);
        });
        System.out.println(Arrays.toString(arrayFive));
    }

    public static int[] createFillArray(final int arraySize, final int inputModifier,
                                        final Runnable a) {
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = a.process(i, inputModifier);
        }
        return array;
    }

    public static boolean isDigitsOnly(final String str) {
        for (int i = 0; i < str.length(); i++) {
            char symbol = str.charAt(i);
            if (i == 0 && symbol == '-' && str.length() != 1) {
                continue;
            }
            if (!(symbol >= '0' && symbol <= '9')) {
                return false;
            }
        }
        return true;
    }
}