package com.smartvoting.repository;

import com.smartvoting.entity.Responses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsesRepository extends JpaRepository<Responses,String> {
}
