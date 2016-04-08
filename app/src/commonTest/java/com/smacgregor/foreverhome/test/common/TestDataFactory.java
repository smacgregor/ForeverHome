package com.smacgregor.foreverhome.test.common;

import com.smacgregor.foreverhome.data.model.Breed;
import com.smacgregor.foreverhome.data.model.Name;
import com.smacgregor.foreverhome.data.model.Profile;
import com.smacgregor.foreverhome.data.model.Ribot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Factory class that makes instances of data models with random field values.
 * The aim of this class is to help setting up test fixtures.
 */
public class TestDataFactory {

    public static String randomUuid() {
        return UUID.randomUUID().toString();
    }

    public static Breed makeBreed(String uniqueSuffix) {
        //return new Breed.create("Dog" + uniqueSuffix);
        return null;
    }

    public static List<Breed> makeListBreeds(int number) {
        List<Breed> breeds = new ArrayList<>(number);
        for (int index = 0; index < number; index++) {
            breeds.add(makeBreed(String.valueOf(index)));
        }
        return breeds;
    }

    public static Ribot makeRibot(String uniqueSuffix) {
        return new Ribot(makeProfile(uniqueSuffix));
    }

    public static List<Ribot> makeListRibots(int number) {
        List<Ribot> ribots = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            ribots.add(makeRibot(String.valueOf(i)));
        }
        return ribots;
    }

    public static Profile makeProfile(String uniqueSuffix) {
        Profile profile = new Profile();
        profile.email = "email" + uniqueSuffix + "@ribot.co.uk";
        profile.name = makeName(uniqueSuffix);
        profile.dateOfBirth = new Date();
        profile.hexColor = "#0066FF";
        profile.avatar = "http://api.ribot.io/images/" + uniqueSuffix;
        profile.bio = randomUuid();
        return profile;
    }

    public static Name makeName(String uniqueSuffix) {
        Name name = new Name();
        name.first = "Name-" + uniqueSuffix;
        name.last = "Surname-" + uniqueSuffix;
        return name;
    }

}