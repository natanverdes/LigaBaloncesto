package com.natanverdes;

import com.natanverdes.Service.JugadorService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LigaBaloncestoApplication {
    private static JugadorService jugadorService;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LigaBaloncestoApplication.class, args);
        jugadorService = context.getBean(JugadorService.class);
        jugadorService.testJugador();
    }
}
