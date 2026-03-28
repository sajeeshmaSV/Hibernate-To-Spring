package com.petshop.Petshop.service.impl;

import com.petshop.Petshop.dto.OwnerDTO;
import com.petshop.Petshop.entity.Owner;
import com.petshop.Petshop.exception.DuplicateOwnerFoundException;
import com.petshop.Petshop.exception.OwnerNotFoundException;
import com.petshop.Petshop.repository.OwnerRepository;
import com.petshop.Petshop.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import com.petshop.Petshop.util.MapperUtil;
import org.springframework.beans.factory.annotation.Value;

import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Value("${owner.already.exists}")
    private String ownerAlreadyExistsMsg;

    @Value("${owner.not.found}")
    private String ownerNotFoundMsg;

    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public void saveOwner(Owner owner) {

    }

    @Override
    @Transactional
    public void saveOwner(OwnerDTO ownerDTO) throws DuplicateOwnerFoundException {
        if (ownerRepository.existsById(ownerDTO.getId())) {
            throw new DuplicateOwnerFoundException(String.format(ownerAlreadyExistsMsg, ownerDTO.getId()));
        }
        ownerRepository.save(MapperUtil.convertOwnerDtoToEntity(ownerDTO));
    }

    @Override
    public OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new OwnerNotFoundException(String.format(ownerNotFoundMsg, ownerId)));
        return MapperUtil.convertOwnerEntityToDto(owner);
    }

    @Override
    @Transactional
    public void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException {
        if (!ownerRepository.existsById(ownerId)) {
            throw new OwnerNotFoundException(String.format(ownerNotFoundMsg, ownerId));
        }
        ownerRepository.updatePetDetails(ownerId, petName);
    }

    @Override
    @Transactional
    public void deleteOwner(int ownerId) throws OwnerNotFoundException {
        if (!ownerRepository.existsById(ownerId)) {
            throw new OwnerNotFoundException(String.format(ownerNotFoundMsg, ownerId));
        }
        ownerRepository.deleteById(ownerId);
    }

    @Override
    public List<OwnerDTO> findAllOwners() {
        return ownerRepository.findAll().stream()
                .map(MapperUtil::convertOwnerEntityToDto)
                .collect(Collectors.toList()).reversed();
    }
}