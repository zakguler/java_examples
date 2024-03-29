package com.java.streams.puttinItAllExample;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class AllStreamsExample {
	
	public static void main(String...args) {
		
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario","Milan");
		Trader alan = new Trader("Alan","Cambridge");
		Trader brian = new Trader("Brian","Cambridge");
		
		List<TransactionAllEX> transactions = Arrays.asList(
		new TransactionAllEX(brian, 2011, 300),
		new TransactionAllEX(raoul, 2012, 1000),
		new TransactionAllEX(raoul, 2011, 400),
		new TransactionAllEX(mario, 2012, 710),
		new TransactionAllEX(mario, 2012, 700),
		new TransactionAllEX(alan, 2012, 950)
		);
		
//		1 Find all transactions in the year 2011 and sort them by value (small to high).
//		2 What are all the unique cities where the traders work?
//		3 Find all traders from Cambridge and sort them by name.
//		4 Return a string of all traders� names sorted alphabetically.
//		5 Are any traders based in Milan?
//		6 Print the values of all transactions from the traders living in Cambridge.
//		7 What�s the highest value of all the transactions?
//		8 Find the transaction with the smallest value.
		
//----------	
		System.out.println("==================================================================================");
		System.out.println("1 Find all transactions in the year 2011 and sort them by value (small to high)");
		transactions.stream()
					.filter(t -> t.getYear() == 2011)
					.sorted(comparing(TransactionAllEX::getValue))
					//.collect(toList())
					.forEach(System.out::println);
		
		//----------	
		System.out.println("==================================================================================");
		System.out.println("2 What are all the unique cities where the traders work?");
		transactions.stream()
					.map (t -> t.getTrader().getCity())
					.distinct()
					//.collect(toList())
					.forEach(System.out::println);
		
		//OR drop [.distinct()] and add [.collect(toSet())]
		Set<String> cities =
				transactions.stream()
				.map(transaction -> transaction.getTrader().getCity())
				.collect(toSet());
		
		//----------	
		System.out.println("==================================================================================");
		System.out.println("3 Find all traders from Cambridge and sort them by name");
		transactions.stream()
					.map(t -> t.getTrader())
					.filter(t -> t.getCity().equalsIgnoreCase("Cambridge"))
					.distinct()
					.sorted(comparing(Trader::getCity))
					.forEach(System.out::println);
		
		
		//----------	
		System.out.println("==================================================================================");
		System.out.println("4 Return a string of all traders� names sorted alphabetically");
		
		System.out.println(
			transactions.stream()
						.map(t -> t.getTrader().getName())
						.distinct()
						.sorted()
						.collect(joining(" "))
						);
		
		
		//----------	
		System.out.println("==================================================================================");
		System.out.println("5 Are any traders based in Milan?");

		boolean yes = transactions.stream()
					.anyMatch(t -> t.getTrader().getCity().equalsIgnoreCase("Milan"));
		System.out.println(yes);		
		
		
		//----------	
		System.out.println("==================================================================================");
		System.out.println("6 Print the values of all transactions from the traders living in Cambridge");
		transactions.stream()
					.filter(t -> t.getTrader().getCity().equalsIgnoreCase("Cambridge"))
					.map(TransactionAllEX::getValue)
					.forEach(System.out::println);

		
		//----------	
		System.out.println("==================================================================================");
		System.out.println("7 What�s the highest value of all the transactions?");
		Optional<Integer> vMax = transactions.stream()
										  .map(TransactionAllEX::getValue)
										  .reduce(Integer::max);
		System.out.println(vMax.get());

		
		//----------	
		System.out.println("==================================================================================");
		System.out.println("8 Find the transaction with the smallest value.");
		Optional<Integer> vMin = transactions.stream()
				  .map(TransactionAllEX::getValue)
				  .reduce(Integer::min);
		System.out.println(vMin.get());
		//OR
		Optional<Integer> vMin2 = transactions.stream()
				  .reduce( (t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2 )
				  .map(TransactionAllEX::getValue);
		System.out.println(vMin2.get());
		

		
	}

}
