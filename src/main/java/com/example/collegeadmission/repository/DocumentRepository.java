package com.example.collegeadmission.repository;

import com.example.collegeadmission.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DocumentRepository extends JpaRepository<Document, Integer> {
}
