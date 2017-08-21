package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JokeTeller {
    public String tellJoke() {
        List<String> jokes = getListOfJokes();
        int jokesNumber = jokes.size();

        Random r = new Random();
        int randomNumber = r.nextInt(jokesNumber);

        return jokes.get(randomNumber);
    }

    private List<String> getListOfJokes() {
        List<String> jokes = new ArrayList<>();

        jokes.add("What's the best thing about Switzerland? \nI don't know, but their flag is a huge plus.");
        jokes.add("Can a kangaroo jump higher than a house? \nOf course, a house doesn’t jump at all.");
        jokes.add("Anton, do you think I’m a bad mother? \nMy name is Paul.");
        jokes.add("My dog used to chase people on a bike a lot. \nIt got so bad, finally I had to take his bike away.");
        jokes.add("What is the difference between a snowman and a snowwoman? \nSnowballs.");
        jokes.add("Mom, where do tampons go? \nWhere the babies come from, darling. \nIn the stork?");
        jokes.add("In a boomerang shop: I'd like to buy a new boomerang please. Also, can you tell me how to throw the old one away?");
        jokes.add("Patient: Oh doctor, I’m just so nervous. This is my first operation. \nDoctor: Don't worry. Mine too.");
        jokes.add("I can’t believe I forgot to go to the gym today. That’s 7 years in a row now.");

        return jokes;
    }
}
