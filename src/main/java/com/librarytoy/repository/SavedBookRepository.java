package com.librarytoy.repository;

import com.librarytoy.entity.SavedBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavedBookRepository extends JpaRepository<SavedBook, Long> {

}
