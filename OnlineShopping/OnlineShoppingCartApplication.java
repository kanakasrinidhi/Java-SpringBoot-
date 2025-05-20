package com.example.OnlineShoppingCart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/carts")
public class OnlineShoppingCartApplication {

	private static List<Product> cart = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(OnlineShoppingCartApplication.class, args);
	}

	// 1. Add product to cart
	@PostMapping("/add")
	public List<Product> addProduct(@RequestBody Product p) {
		cart.add(p);
		return cart;
	}

	// 2. Get all products in cart
	@GetMapping("/items")
	public List<Product> getCartItems() {
		return cart;
	}

	// 3. Get total cost
	@GetMapping("/total")
	public String getTotalCost() {
		double total = 0;
		for (Product p : cart) {
			total += p.getPrice() * p.getQuantity();
		}
		return "Total Cost: Rs. " + total;
	}

	// 4. Update product by name
	@PutMapping("/update/{name}")
	public Product updateProduct(@PathVariable String name, @RequestBody Product updated) {
		for (Product p : cart) {
			if (p.getName().equalsIgnoreCase(name)) {
				p.setPrice(updated.getPrice());
				p.setQuantity(updated.getQuantity());
				return p;
			}
		}
		return null;
	}

	// 5. Remove product by name
	@DeleteMapping("/remove/{name}")
	public String removeProduct(@PathVariable String name) {
		boolean removed = cart.removeIf(p -> p.getName().equalsIgnoreCase(name));
		return removed ? "Product removed." : "Product not found.";
	}
}

