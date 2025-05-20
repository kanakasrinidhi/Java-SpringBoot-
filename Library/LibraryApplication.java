package com.example.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/books")
public class LibraryApplication {
	private static List<library> libList = new ArrayList<>();
	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@GetMapping
	public List<library> getBooks() {
		return libList;
	}

	@PostMapping
	public List<library> addBook(@RequestBody library book) {
		libList.add(book);
		return libList;
	}

	@GetMapping("/{title}")
	public library getBookByTitle(@PathVariable String title) {
		for (library b : libList) {
			if (b.getTitle().equalsIgnoreCase(title)) {
				return b;
			}
		}
		return null;
	}

	@PutMapping("/{title}")
	public library updatelibrary(@PathVariable String title, @RequestBody library updatedBook) {
		for (library b : libList) {
			if (b.getTitle().equalsIgnoreCase(title)) {
				b.setTitle(updatedBook.getTitle());
				b.setAuthor(updatedBook.getAuthor());
				b.setPrice(updatedBook.getPrice());
				return b;
			}
		}
		return null;
	}

	@DeleteMapping("/{title}")
	public void deleteBook(@PathVariable String title) {
		libList.removeIf(b -> b.getTitle().equalsIgnoreCase(title));
	}
}