package com.youcode.visionarycrofting.repository;

import com.youcode.visionarycrofting.entity.CommandItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandItemRepository extends JpaRepository< CommandItem, Integer > {
}
