package com.company.repository.impl;

import com.company.repository.FileUploadDAO;
import com.company.entity.UploadFile;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FileUploadDAOImpl implements FileUploadDAO {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @Transactional
    public void save(UploadFile uploadFile) {
        sessionFactory.getCurrentSession().
                save(uploadFile);
    }

}