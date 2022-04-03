package com.epam;

import com.epam.entities.Category;
import com.epam.entities.Pet;
import com.epam.entities.PetStatus;
import com.epam.entities.Tag;
import com.epam.log.Log;
import com.epam.steps.PetServiceSteps;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetServiceTest {

    Pet pet = createPet();

    @Test
    public void createPetTest() {
        Log.info("Pet creation test starts...");
        Response responseCreatedPet = PetServiceSteps.addPet(pet);
        Assert.assertEquals(responseCreatedPet.getStatusCode(), 200
                , "Status code is not as expected");
        Assert.assertEquals(responseCreatedPet.jsonPath().getString("name"), pet.getName()
                ,"Pet name is incorrect");
        Log.info(pet + " created successfully!");
    }

    @Test
    public void getPetsByStatusTest() {
        Log.info("Getting pets by status test starts...");
        Response responsePetsByStatus = PetServiceSteps.getPetsByStatus("available");
        Assert.assertEquals(responsePetsByStatus.getStatusCode(), 200
                ,"Status code is not as expected");
        Assert.assertTrue(responsePetsByStatus.jsonPath().getList("name").size() > 20
                ,"Less than 20 pets by requested status found");
        Log.info(responsePetsByStatus.jsonPath().getList("name").size() + " pets retrieved!");
    }

    @Test
    public void deletePetByIdTest() {
        Log.info("Delete pet by ID test starts...");
        Response responseCreatedPet = PetServiceSteps.addPet(pet);
        PetServiceSteps.deletePetById(pet.getId());
        Response responseDeletedPet = PetServiceSteps.getPetById(pet.getId());
        Assert.assertEquals(responseDeletedPet.getStatusCode(), 404
                ,"Status code is not as expected");
        Log.info(pet + " deleted successfully!");
    }

    private Pet createPet() {
        return new Pet()
                .setId(1)
                .setCategory(new Category()
                        .setId(1)
                        .setName("cat"))
                .setName("Mara")
                .setPhotoUrls(new String[]{"https://pl.wikipedia.org/wiki/Plik:European_shorthair_cat.png"})
                .setTags(new Tag[]{new Tag()
                        .setId(1)
                        .setName("domestic")})
                .setStatus(PetStatus.available);
    }
}