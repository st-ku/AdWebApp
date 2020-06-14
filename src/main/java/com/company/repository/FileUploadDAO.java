package com.company.repository;

import com.company.entity.UploadFile;

import javax.transaction.Transactional;

public interface FileUploadDAO {
    @Transactional
    void save(UploadFile uploadFile);
}
