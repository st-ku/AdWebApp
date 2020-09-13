package com.company.repository.impl;

import com.company.entity.UploadFile;
import com.company.repository.FileUploadDAO;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class FileUploadDAOImpl implements FileUploadDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public void save(UploadFile uploadFile) {
        SessionFactory sessionFactory = entityManager.unwrap(SessionFactory.class);
        sessionFactory.getCurrentSession().
                save(uploadFile);
    }

}