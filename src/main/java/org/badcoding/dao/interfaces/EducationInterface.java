package org.badcoding.dao.interfaces;

import org.badcoding.dao.EducationEntity;

import java.util.List;

public interface EducationInterface {
    void save(EducationEntity education);
    void update(EducationEntity education);
    void delete(EducationEntity education);
    EducationEntity getById(int id);
    EducationEntity getByName(String name);
    List<EducationEntity> getList();
}