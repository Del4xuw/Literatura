package com.alura.literatura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LiteraturaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LiteraturaApplication.class, args);
		AplicacionConsola aplicacionConsola = context.getBean(AplicacionConsola.class);
		aplicacionConsola.iniciar();
	}

}
