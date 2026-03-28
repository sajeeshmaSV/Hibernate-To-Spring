package com.petshop.Petshop.service;

import com.petshop.Petshop.dto.OwnerDTO;
import com.petshop.Petshop.entity.Owner;
import com.petshop.Petshop.exception.DuplicateOwnerFoundException;
import com.petshop.Petshop.exception.OwnerNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OwnerService {

    @Transactional
    void saveOwner(Owner owner);

    void saveOwner(OwnerDTO ownerDTO) throws DuplicateOwnerFoundException;

    Object findOwner(int ownerId) throws OwnerNotFoundException;

    void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException;

    void deleteOwner(int ownerId) throws OwnerNotFoundException;

    <Owner> List<Owner> findAllOwners();
}