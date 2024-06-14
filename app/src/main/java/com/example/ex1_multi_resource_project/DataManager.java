package com.example.ex1_multi_resource_project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class DataManager {
    private static String[] flags = new String[]{
            "https://www.countryflags.com/wp-content/uploads/china-flag-png-large.png",
            "https://www.countryflags.com/wp-content/uploads/india-flag-png-large.png",
            "https://www.countryflags.com/wp-content/uploads/united-states-of-america-flag-png-large.png",
            "https://www.countryflags.com/wp-content/uploads/brazil-flag-png-large.png",
            "https://www.countryflags.com/wp-content/uploads/mexico-flag-png-large.png",
            "https://www.countryflags.com/wp-content/uploads/germany-flag-png-large.png",
            "https://www.countryflags.com/wp-content/uploads/england-flag-jpg-xl-1024x615.jpg",
            "https://www.countryflags.com/wp-content/uploads/canada-flag-png-large.png"
    };

    public static ArrayList<Card> getCards(){
        ArrayList<Card> countries = new ArrayList<>();

        for (int i = 0 ; i < flags.length ; i++){
            Card c = new Card();
            c.setFlag(flags[i]);
            c.setId(UUID.randomUUID());
            c.setFlipped(false);

            countries.add(c);
            countries.add(c);
        }

        Collections.shuffle(countries);

        return countries;
    }
}
