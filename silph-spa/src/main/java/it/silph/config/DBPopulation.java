package it.silph.config;


import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import it.silph.model.Album;
import it.silph.model.Dipendente;
import it.silph.model.Foto;
import it.silph.model.Fotografo;
import it.silph.model.Tag;
import it.silph.repository.DipendenteRepository;
import it.silph.repository.FotografoRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

@Component
public class DBPopulation implements ApplicationRunner {

    @Autowired
    private DipendenteRepository dipendenteRepository;
    
    @Autowired
	private FotografoRepository fotografoRepository;


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
                
        Fotografo f1 = new Fotografo("Luigi", "Verdi", extractBytes("imgProfile1.jpg"));
        Fotografo f2 = new Fotografo("Carla", "Bianchi", extractBytes("imgProfile3.jpg"));
        Fotografo f3 = new Fotografo("Richard", "Lidons", extractBytes("imgProfile2.jpg"));
        Fotografo f4 = new Fotografo("Mark", "Vaso", extractBytes("imgProfile4.jpg"));
        
        Album a1 = new Album("Album di Mark", "Album di vasi", extractBytes("fotoTest1.jpg"), f4);
        Album a2 = new Album("Album di Richard", "Album di pesci", extractBytes("fotoTest8.jpg"), f3);
        Album a3 = new Album("Album di Carla", "Matrimoni", extractBytes("fotoTest16.jpg"), f2);
        Album a4 = new Album("Album di Luigi", "Volatili", extractBytes("fotoTest22.jpg"), f1);
        Album a5 = new Album("Album di Carla 2", "Lauree", extractBytes("fotoTest20.jpg"), f2);
        
        Tag t1 = new Tag("ponte");
        Tag t2 = new Tag("fotocamera");
        Tag t3 = new Tag("calendario");
        Tag t4 = new Tag("neve");
        Tag t5 = new Tag("strada");
        Tag t6 = new Tag("linea");
        
        List<Tag> lt1 = new LinkedList<>();
        lt1.add(t1);
        lt1.add(t2);
        List<Tag> lt2 = new LinkedList<>();
        lt2.add(t3);
        List<Tag> lt3 = new LinkedList<>();
        lt3.add(t4);
        List<Tag> lt4 = new LinkedList<>();
        lt4.add(t5);
        List<Tag> lt5 = new LinkedList<>();
        lt5.add(t6);
        
        populateAlbum(a1, 5, lt5);
        populateAlbum(a2, 3, lt4);
        populateAlbum(a3, 7, lt2);
        populateAlbum(a4, 5, lt1);
        populateAlbum(a5, 2, lt3);
        
        f1.getAlbum().add(a4);
        f2.getAlbum().add(a3);
        f2.getAlbum().add(a5);
        f3.getAlbum().add(a2);
        f4.getAlbum().add(a1);
        
        this.fotografoRepository.save(f1);
        this.fotografoRepository.save(f2);
        this.fotografoRepository.save(f3);
        this.fotografoRepository.save(f4);
        System.out.println("Done.\n");
    }
    
    
    private void populateAlbum(Album a, int nFoto, List<Tag> tags) throws IOException {
    	for(int i=1; i<=nFoto; i++) {
    		Foto foto = new Foto("Foto " + i, "Una bella Foto", tags, extractBytes("fotoTest" + i + ".jpg"), a);
    		a.inserisciFoto(foto);
    	}
    	
    }
    public byte[] extractBytes(String imgName) throws IOException {
    	InputStream in = new ClassPathResource("static/testImg/" + imgName).getInputStream();
    	return IOUtils.toByteArray(in);
    }
}
