package sem_01_cw;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        System.out.println(showTotalAmount(15));

//        System.out.println(findSimpleNumbers(100));

//        System.out.println(getCombinations(6));
//        System.out.println(combinationCount(3, 3));

//        System.out.println(fibo(10));

//        System.out.println(linearFibo(10));

        test();
    }


//    ----------
//    Задание 1:
//    Необходимо написать алгоритм, считающий сумму всех чисел от 1 до N.
//    Согласно свойствам линейной сложности, количество итераций цикла
//    будет линейно изменяться относительно изменения размера N.

    public static String showTotalAmount(int value) {
        return (value * (value + 1) / 2) + "";
    }


//    ----------
//    Задание 2:
//    Написать алгоритм поиска простых чисел (делятся только на себя и на 1) в диапазоне от 1 до N.
//    В алгоритме будет использоваться вложенный for, что явно говорит о квадратичной сложности,
//    на этом стоит акцентировать внимание

    public static List<Integer> findSimpleNumbers(int num) {
        List<Integer> simpleNumbers = new ArrayList<>();
        for (int i = 1; i < num; i++) {
            boolean isSimple = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isSimple = false;
                    break;
                }
            }
            if (isSimple) {
                simpleNumbers.add(i);
            }
        }
        return simpleNumbers;
    }


//    ----------
//    Задание 3.
//    1. Необходимо написать алгоритм поиска всех доступных комбинаций
//       (посчитать количество) для количества кубиков K с количеством граней N.
//    2. У вас есть 2 варианта на выбор – количество кубиков может быть строго
//       ограничено (4 кубика, например), либо их количество будет динамическим. Выбор за вами.
//    3. Если вы реализуете простой вариант, обращает внимание, что данное
//       решение имеет сложность O(n4), но если количество кубиков сделать переменной,
//       то она трансформируется в O(nk), что будет представлять собой экспоненциальную сложность.
//       Для второго решения очевидно, что его сложность O(nk) с самого начала.

    public static int getCombinations(int sides) {
        int combinations = 0;
        for (int i = 0; i < sides; i++) {
            for (int j = 0; j < sides; j++) {
                for (int k = 0; k < sides; k++) {
                    for (int l = 0; l < sides; l++) {
                        combinations++;
                    }
                }
            }
        }
        return combinations;
    }

    public static int combinationCount(int count, int faces) {
        if (count > 0) {
            return recursiveCounter(1, count, faces);
        } else {
            return 0;
        }
    }

    private static int recursiveCounter(int depth, int maxDepth, int faces) {
        int count = 0;
        for (int i = 1; i <= faces; i++) {
            if (depth == maxDepth) {
                count++;
            } else {
                count += recursiveCounter(depth + 1, maxDepth, faces);
            }
        }
        return count;
    }


//    ----------
//    Задание 4.
//    1. Пишем алгоритм поиска нужного числа последовательности Фибоначчи.
//    2. Считаем, что 1 и 2 значения последовательности равны 1.
//    3. Искать будем по формуле On=On-1+On-2 что предполагает использовать
//       рекурсивного алгоритма.

    public static int fibo(int num) {
        if (num == 1) {
            return 0;
        }
        if (num == 2) {
            return 1;
        }
        return fibo(num - 1) + fibo(num - 2);
    }


//    ----------
//    Задание 5.
//    1.Пишем алгоритм поиска нужного числа последовательности Фибоначчи,
//    но в этот раз откажемся от рекурсии и воспользуемся обычным алгоритмом,
//    что даст нам линейную сложность, т.к. вычисление каждого из чисел
//    последовательности будет происходить ровно 1 раз.
//    2.Вариантов решения может быть несколько, но нужно предложить студентам
//    считать последовательность с первого числа и далее до тех пор, пока не
//    доберемся до нужного номера. При этом значение предыдущих элементов нужно сохранять,
//    чтобы не приходилось вычислять заново, как это происходило при рекурсивном методе.

    public static int linearFibo(int num) {
        if (num >= 1) {
            int[] arr = new int[num];
            arr[0] = 0;
            arr[1] = 1;
            for (int i = 2; i < num; i++) {
                arr[i] = arr[i - 2] + arr[i - 1];
            }
            return arr[num - 1];
        }
        return 0;
    }


//    ----------
//    Задание 6.
//    Необходимо сравнить скорость работы 2 алгоритмов вычисления
//    чисел Фибоначчи и определить, какой из них работает быстрее.
//    Так различия начнутся уже с 40 члена последовательности.

    public static void test() {
        for (int i = 10; i <= 50; i = i + 10) {
            Date startDate = new Date();
            linearFibo(i);
            Date endDate = new Date();
            long lineDuration = endDate.getTime() - startDate.getTime();
            startDate = new Date();
            fibo(i);
            endDate = new Date();
            long recursiveDuration = endDate.getTime() - startDate.getTime();
            System.out.printf("i: %s, line duration: %s, recursive duration: %s%n", i, lineDuration, recursiveDuration);
        }
    }
}
