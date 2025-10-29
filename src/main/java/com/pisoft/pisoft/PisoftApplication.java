package com.pisoft.pisoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PisoftApplication  implements  CommandLineRunner{

    @Autowired
    Mango mango;

    @Autowired
    Mango mango2;

    @Autowired
    Mango mango3;

    @Autowired
    private  DBService dbService1;

    private final DBService dbService;

    PisoftApplication(DBService dbService){
        this.dbService = dbService;
    }

    public static void main(String[] args) {
		SpringApplication.run(PisoftApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        System.out.println(mango.hashCode());
        System.out.println(mango2.hashCode());
        System.out.println(mango3.hashCode());

        System.out.println(mango == mango2);
        String db = dbService.getDb();
        System.out.println(db);


    }
}
