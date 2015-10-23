package com.natanverdes;

import com.natanverdes.Service.LigaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LigaBaloncestoApplication {
    private static LigaService ligaService;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LigaBaloncestoApplication.class, args);
        ligaService = context.getBean(LigaService.class);

        ligaService.crearEntidades();
        ligaService.testEntidades();

    }
}
