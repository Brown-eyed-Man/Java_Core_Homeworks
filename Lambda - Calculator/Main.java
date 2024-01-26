import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        Calculator calc = Calculator.instance.get();

        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1, 1);
        /*Ошибка возникала за счет того, что в int b получалась переменная со значением 0, которая
        в будущем использовалась в качестве делителя. В случае чего происходила ошибка, говорящая
        о невозможности деления на 0. Предлагаю предусмотреть такой поворот событий в самой ссылке
        divide класса Calculator, говоря о таком исходе и приравнивать результат деления к нулю.
        */
        int c = calc.divide.apply(a, b);
        calc.println.accept(c);
    }
}

class Calculator {
    static Supplier<Calculator> instance = Calculator::new;

    BinaryOperator<Integer> plus = (x, y) -> x + y;
    BinaryOperator<Integer> minus = (x, y) -> x - y;
    BinaryOperator<Integer> multiply = (x, y) -> x * y;
    BinaryOperator<Integer> divide = (x, y) -> {
        if (y == 0) {
            System.out.println("Деление на 0");
            return 0;
        } else {
            return x / y;
        }
    };

    UnaryOperator<Integer> pow = x -> x * x;
    UnaryOperator<Integer> abs = x -> x > 0 ? x : x * -1;

    Predicate<Integer> isPositive = x -> x > 0;

    Consumer<Integer> println = System.out::println;
}