package com.airline.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class jpaUtil {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("airlinePU");
    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
}
