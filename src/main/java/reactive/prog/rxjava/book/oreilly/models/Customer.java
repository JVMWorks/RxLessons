package reactive.prog.rxjava.book.oreilly.models;

import java.util.Arrays;
import java.util.List;

public class Customer {

	public List<Order> getOrders() {
		return Arrays.asList(new Order(), new Order());
	}

}
