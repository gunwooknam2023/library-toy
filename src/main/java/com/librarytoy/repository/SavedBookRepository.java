package com.librarytoy.repository;

import com.librarytoy.entity.SavedBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SavedBookRepository extends JpaRepository<SavedBook, Long> {
    Optional<SavedBook> findByIsbn(String isbn);
}
