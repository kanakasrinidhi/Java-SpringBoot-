package com.example.Restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/restaurant")
public class RestaurantApplication {

	private static List<FoodItem> menu = new ArrayList<>();
	private static List<OrderItem> cart = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
		menu.add(new FoodItem("Idli", 20));
		menu.add(new FoodItem("Dosa", 40));
		menu.add(new FoodItem("Vada", 15));
		menu.add(new FoodItem("Pongal", 30));
		menu.add(new FoodItem("Coffee", 10));
		menu.add(new FoodItem("Tea", 10));

	}

	// 1. Display Menu
	@GetMapping("/menu")
	public List<FoodItem> getMenu() {
		return menu;
	}

	// 2. Select + 3. Add to Order
	@PostMapping("/order")
	public List<OrderItem> addToCart(@RequestBody OrderItem newItem) {
		for (FoodItem item : menu) {
			if (item.getName().equalsIgnoreCase(newItem.getName())) {
				cart.add(new OrderItem(item.getName(), newItem.getQuantity(), item.getPrice()));
				break;
			}
		}
		return cart;
	}

	// 4. Calculate Bill
	@GetMapping("/bill")
	public double calculateBill() {
		double total = 0;
		for (OrderItem order : cart) {
			total += order.getTotalPrice();
		}
		return total;
	}

	// 5. Confirm Order
	@PostMapping("/confirm")
	public String confirmOrder() {
		double total = calculateBill();
		return "Order confirmed! Final amount to pay: ₹" + total;
	}

	// 6. Order Summary
	@GetMapping("/summary")
	public List<OrderItem> getOrderSummary() {
		return cart;
	}

	// Extra: Clear cart
	@DeleteMapping("/clear")
	public String clearCart() {
		cart.clear();
		return "Cart has been cleared!";
	}
	// 7. Update quantity in order
	@PutMapping("/update")
	public String updateOrder(@RequestBody OrderItem updatedItem) {
		for (OrderItem order : cart) {
			if (order.getName().equalsIgnoreCase(updatedItem.getName())) {
				order.setQuantity(updatedItem.getQuantity());
				return "Updated " + updatedItem.getName() + " to quantity: " + updatedItem.getQuantity();
			}
		}
		return "Item not found in the order.";
	}

}
