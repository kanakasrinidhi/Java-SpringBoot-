package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/users")
public class DemoApplication {
	private static List<demo> userList = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@GetMapping
	public List<demo> getTodos(){
		return  userList;
	}

	@PostMapping
	public List<demo> addTodo(@RequestBody demo d){

		userList.add(d);
		return  userList;
	}

	@PutMapping("/{name}")
	public demo updateuser(@PathVariable String name, @RequestBody demo updatedUser){
		for(demo d :userList){
			if(d.getName().equalsIgnoreCase(name)){
				d.setName(updatedUser.getName());
				d.setEmail(updatedUser.getEmail());
				d.setPwd(updatedUser.getPwd());
				return d;
			}
		}
		return null;
	}

	@DeleteMapping("/{name}")
	public void deleteTodo(@PathVariable String name){
		userList.removeIf(userList-> userList.getName().equalsIgnoreCase(name));
	}

}