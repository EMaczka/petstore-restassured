package com.epam.steps;

import com.epam.entities.Pet;
import com.epam.service.PetService;
import io.restassured.response.Response;

import static com.epam.service.uritemplate.PetServiceUri.*;

public class PetServiceSteps {

    private static final PetService PET_SERVICE = PetService.getInstance();

    public static Response getPetById(int petId) {
        return PET_SERVICE.getRequest(PET_BY_ID, petId);
    }

    public static Response getPetsByStatus(String petStatus) {
        return PET_SERVICE.getRequest(PET_BY_STATUS, petStatus);
    }

    public static Response addPet(Pet pet) {
        return PET_SERVICE.postRequest(PET, pet);
    }

    public static void deletePetById(int id) {
        PET_SERVICE.deleteRequest(PET_BY_ID, id);
    }
}
