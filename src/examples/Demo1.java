package examples;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/*
 * 
 * Themes: lambda expression / stream / predicates / higher order functions
 * 
 * Short demonstration of performing simple tasks with lambda expressions and higher order functions
 * -1) Defining a thread with an anonymous function
 * -2) Defining a thread with a lambda expression
 * -3) Perform computation on a list of integers - imperative style
 * -4) Perform computation on a list of integers - functional style with lambda expression
 * -5) Perform computation on a list of integers - functional style with (static) method references
 * -6) Perform computation on a list of integers - functional style with predicates
 * -7) Perform computation on a list of integers - functional style with higher order functions
 */

public class Demo1 {

	public static void main(String[] args) {
		//-1) Defining a thread with an anonymous function
		Thread thread = new Thread(new Runnable() {
			public void run() {
				System.out.println(1);
			}
		});
		//-2) Defining a thread with a lambda expression
		Thread thread2 = new Thread(() -> System.out.println(1));

		//-3) Perform computation on a list of integers - imperative style
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		int results = 0;
		for (int n : numbers) {
			if (n > 3 && n % 2 == 0) {
				results = n * 2;
				break;
			}
		}
		//-4) Perform computation on a list of integers - functional style with lambda expression
		numbers
			.stream()
			.filter(e -> e % 2 == 0)
			.filter(e -> e > 3)
			.map(e -> e * 2)
			.findFirst();

		//-5) Perform computation on a list of integers - functional style with (static) method references
		numbers
			.stream()
			.filter(Demo1::isEven)
			.filter(Demo1::isGreaterAs3)
			.map(e -> e * 2)
			.findFirst();

		//-6) Perform computation on a list of integers - functional style with predicates
		Predicate<Integer> isGreaterThan3 = p -> p > 3;
		numbers
			.stream()
			.filter(Demo1::isEven)
			.filter(isGreaterThan3)
			.map(e -> e * 2)
			.findFirst();

		//-7) Perform computation on a list of integers - functional style with higher order functions
		Function<Integer, Predicate<Integer>> isGreaterThan = p -> number -> number > p;
		Function<Integer, Function<Integer, Integer>> multiplyBy = p -> n -> n * p;
		numbers
			.stream().
			filter(Demo1::isEven).
			filter(isGreaterThan.apply(4)).
			map(multiplyBy.apply(2)).
			findFirst();
	}

	private static boolean isEven(int number) {
		return number % 2 == 0;
	}

	private static boolean isGreaterAs3(int number) {
		return number > 3;
	}

}
