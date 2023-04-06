package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Entry;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Integer> {

}
