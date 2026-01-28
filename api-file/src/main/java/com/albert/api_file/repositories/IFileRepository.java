package com.albert.api_file.repositories;

import com.albert.api_file.models.File;
import com.albert.api_file.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IFileRepository extends JpaRepository<File, UUID> {
    List<File> findByTitleContaining(String name);
    List<File> findAllByOwner(User owner);
}
