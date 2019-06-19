package it.silph.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import it.silph.model.Dipendente;
import it.silph.repository.DipendenteRepository;

import java.io.IOException;

@Component
public class DBPopulation implements ApplicationRunner {

    @Autowired
    private DipendenteRepository dipendenteRepository;


    public void run(ApplicationArguments args) throws Exception {
        this.deleteAll();
        this.populateDB();
    }

    private void deleteAll() {
        System.out.println("Deleting all users from DB...");
        dipendenteRepository.deleteAll();
        System.out.println("Done");
    }

    private void populateDB() throws IOException, InterruptedException {

        System.out.println("Storing users...");

        Dipendente d = new Dipendente("mario", null, "Mario", "Rossi", "DIPENDENTE");
        String dipPassword = new BCryptPasswordEncoder().encode("1234");
        d.setPassword(dipPassword);
        d= this.dipendenteRepository.save(d);
        
        System.out.println("Done.\n");
    }
}
