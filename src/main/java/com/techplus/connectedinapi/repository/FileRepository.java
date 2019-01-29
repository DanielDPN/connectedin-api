package com.techplus.connectedinapi.repository;

import com.techplus.connectedinapi.model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<FileModel, Long> {

    Optional<FileModel> findByPostId(Long aLong);

}
