package com.petshop.Petshop.repository;

import com.petshop.Petshop.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

    @Modifying
    @Query("UPDATE Owner o SET o.petName = :petName WHERE o.id = :ownerId")
    void updatePetDetails(@Param("ownerId") int ownerId, @Param("petName") String petName);
}