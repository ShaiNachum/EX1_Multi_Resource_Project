package com.example.ex1_multi_resource_project;

import java.util.UUID;

public class Card {
    private UUID id;
    private String flag;
    private boolean isFlipped;

    public Card(){
    }

    public UUID getId() {
        return id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void setFlipped(boolean isFlipped) {
        this.isFlipped = isFlipped;
    }

    public Card setId(UUID id) {
        this.id = id;
        return this;
    }
}
