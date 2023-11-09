package com.ongpatinhasquebrilham.petcontrol.domain.repository;

import com.ongpatinhasquebrilham.petcontrol.domain.model.Pet;
import com.ongpatinhasquebrilham.petcontrol.domain.model.PetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findAllByStatus(PetStatus status);
}