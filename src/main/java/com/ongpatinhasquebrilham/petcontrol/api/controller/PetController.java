package com.ongpatinhasquebrilham.petcontrol.api.controller;

import com.ongpatinhasquebrilham.petcontrol.api.assembler.PetRequestDisassembler;
import com.ongpatinhasquebrilham.petcontrol.api.assembler.PetResponseAssembler;
import com.ongpatinhasquebrilham.petcontrol.api.model.PetRequest;
import com.ongpatinhasquebrilham.petcontrol.api.model.PetResponse;
import com.ongpatinhasquebrilham.petcontrol.core.validation.group.PostValidation;
import com.ongpatinhasquebrilham.petcontrol.core.validation.group.PutValidation;
import com.ongpatinhasquebrilham.petcontrol.domain.model.Pet;
import com.ongpatinhasquebrilham.petcontrol.domain.service.PetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("/pets")
public class PetController {

	private PetService petService;
	private PetResponseAssembler petResponseAssembler;
	private PetRequestDisassembler petRequestDisassembler;

	@GetMapping
	public ResponseEntity<List<PetResponse>> getPets(@RequestParam(required = false) String status) {
		List<Pet> pets = (Objects.nonNull(status))
				? petService.findAllByStatus(status)
				: petService.findAll();

		return ResponseEntity.ok(petResponseAssembler.toCollectionModel(pets));
	}

	@PostMapping
	public ResponseEntity<PetResponse> savePet(@Validated(PostValidation.class) @RequestBody PetRequest petRequest) {
		Pet newPet = petService.save(petRequestDisassembler.toDomainObject(petRequest));
		return ResponseEntity.status(HttpStatus.CREATED).body(petResponseAssembler.toModel(newPet));
	}

	@GetMapping("/{petId}")
	public ResponseEntity<PetResponse> getPet(@PathVariable Long petId) {
		Pet currentPet = petService.find(petId);
		return ResponseEntity.ok(petResponseAssembler.toModel(currentPet));
	}

	@PutMapping("/{petId}")
	public ResponseEntity<PetResponse> updatePet(@PathVariable Long petId, @Validated(PutValidation.class) @RequestBody PetRequest petRequest) {
		Pet currentPet = petService.find(petId);
		petRequestDisassembler.copyToDomainObject(petRequest, currentPet);

		Pet updatedPet = petService.save(currentPet);
		return ResponseEntity.ok(petResponseAssembler.toModel(updatedPet));
	}

	@DeleteMapping("/{petId}")
	public ResponseEntity<Void> deletePet(@PathVariable Long petId) {
		petService.delete(petId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{petId}/available")
	public ResponseEntity<Void> turnAvailable(@PathVariable Long petId) {
		petService.turnAvailable(petId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{petId}/available")
	public ResponseEntity<Void> turnUnavailable(@PathVariable Long petId) {
		petService.turnUnavailable(petId);
		return ResponseEntity.noContent().build();
	}

}