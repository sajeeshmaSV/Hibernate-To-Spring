package com.petshop.Petshop;


import com.petshop.Petshop.dto.OwnerDTO;
import com.petshop.Petshop.service.OwnerService;
import com.petshop.Petshop.util.InputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private OwnerService ownerService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) {
        try (Scanner sc = new Scanner(System.in)) {
            do {
                System.out.println("\nWelcome to Petistaan (Spring Boot Edition)");
                int menuOption = InputUtil.acceptMenuOption(sc);

                switch (menuOption) {
                    case 1 -> {
                        OwnerDTO ownerDTO = InputUtil.acceptOwnerDetailsToSave(sc);
                        ownerService.saveOwner(ownerDTO);
                        System.out.println("Owner has been saved successfully.");
                    }
                    case 2 -> {
                        int ownerId = InputUtil.acceptOwnerIdToOperate(sc);
                        System.out.println(ownerService.findOwner(ownerId));
                    }
                    case 3 -> {
                        int ownerId = InputUtil.acceptOwnerIdToOperate(sc);
                        String petName = InputUtil.acceptPetDetailsToUpdate(sc);
                        ownerService.updatePetDetails(ownerId, petName);
                        System.out.println("Pet details updated successfully.");
                    }
                    case 4 -> {
                        int ownerId = InputUtil.acceptOwnerIdToOperate(sc);
                        ownerService.deleteOwner(ownerId);
                        System.out.println("Owner deleted successfully.");
                    }
                    case 5 -> {
                        ownerService.findAllOwners().forEach(System.out::println);
                    }
                }
            } while (InputUtil.wantToContinue(sc));

            System.out.println("Thank you for using Petistaan!");

        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}