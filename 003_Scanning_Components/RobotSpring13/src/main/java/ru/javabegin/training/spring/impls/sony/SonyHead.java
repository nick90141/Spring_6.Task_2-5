package ru.javabegin.training.spring.impls.sony;

import org.springframework.stereotype.Component;
import ru.javabegin.training.spring.interfaces.Head;

//@Component
public class SonyHead implements Head {

	private String color;

	public void calc() {
		System.out.println("Thinking about Sony...");
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
