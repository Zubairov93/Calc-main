import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] subStr = str.split(" ");

        if(subStr.length != 3)
        {
            System.out.println("Введены не корректные данные");
            return;
        }

        int numbersType_1 = itCorrectNumbers(subStr[0]);
        int numbersType_2 = itCorrectNumbers(subStr[2]);
        int arifmeticOperationType = ArifmeticOperations.getArifmeticOperationType(subStr[1]);

        if(numbersType_1 < 0)
        {
            System.out.println("Не удалось распознать данное число: " + subStr[0]);
            return;
        }
        else if(numbersType_2 < 0)
        {
            System.out.println("Не удалось распознать данное число: " + subStr[2]);
            return;
        }
        else if(arifmeticOperationType < 0)
        {
            System.out.println("Неизвестный тип арифметической операции: " + subStr[1]);
            return;
        }
        else if(numbersType_1 != numbersType_2)
        {
            System.out.println("Калькулятор не поддерживает вычисление с одновременным использованием арабских и римских цифр");
            return;
        }

        int number_1 = getNumber(subStr[0], numbersType_1);
        int number_2 = getNumber(subStr[2], numbersType_2);

        if(number_1 == 0 || number_1 > 10)
        {
            System.out.println("Калькулятор понимает цифры в диапозоне от 1 до 10, вы ввели число: " + number_1);
            return;
        }
        else if(number_2 == 0 || number_2 > 10)
        {
            System.out.println("Калькулятор понимает цифры в диапозоне от 1 до 10, вы ввели число: " + number_2);
            return;
        }

        int finalValue = calculateValue(number_1, number_2, arifmeticOperationType);

        if(numbersType_1 == 1)
        {
            System.out.println(RomeNumbers.getRomeNumbers(finalValue));
        }
        else
        {
            System.out.println(finalValue);
        }

    }

    public static int getNumber(String numberStr, int numberType)
    {
        int number = 0;

        if(numberType == 1)
        {
            number = RomeNumbers.getArabNumber(numberStr);
        }
        else
        {
            number = Integer.parseInt(numberStr);
        }

        return number;
    }

    public static int calculateValue(int number_1, int number_2, int arifmeticOperationType)
    {
        int finalValue = 0;

        switch (arifmeticOperationType)
        {
            case (0) : finalValue = number_1 + number_2;
                break;
            case (1) : finalValue = number_1 - number_2;
                break;
            case (2) : finalValue = number_1 * number_2;
                break;
            case (3) : finalValue = number_1 / number_2;
                break;
        }

        return finalValue;

    }

    public static int itCorrectNumbers(String strNumber)
    {
        int numbersType = -1;

        if(ArabNumbers.thisIsArabNumbers(strNumber))
        {
            numbersType = 0;
        }
        else if(RomeNumbers.thisIsRomeNumbers(strNumber))
        {
            numbersType = 1;
        }

        return numbersType;

    }

}
