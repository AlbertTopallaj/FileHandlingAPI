package com.albert.api_file.repositories;

import com.albert.api_file.models.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IFolderRepository extends JpaRepository<Folder, UUID> {
    Folder delete(Optional<Folder> folder);
}
