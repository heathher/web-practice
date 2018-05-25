package org.badcoding.dao.interfaces;

import org.badcoding.dao.JobEntity;

import java.util.List;

public interface JobInterface {
    void save(JobEntity job);
    void update(JobEntity job);
    void delete(JobEntity job);
    JobEntity getById(int id);
    List<JobEntity> getList();
}
