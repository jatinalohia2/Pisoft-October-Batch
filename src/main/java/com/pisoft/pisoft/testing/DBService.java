package com.pisoft.pisoft.testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    @Autowired
    DB db;

    public String getDb(){
         return db.getDatabase();
    }

}
