package com.utm.lostfound.repository;

import com.utm.lostfound.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	long countByTypeIgnoreCase(String type);
	long countByStatusIgnoreCase(String status);

    List<Item> findByTypeAndStatus(String type, String status);
    List<Item> findByStatusIgnoreCase(String status);

}
