package com.example.aistudymentor.models;

public class CategoryModel {
    private int id;
    private String name;
    private String descriptions;
    private int statusCategory;
    private String createdAt;
    private String updatedAt;

    public CategoryModel(int id, String name, String descriptions, int statusCategory, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.descriptions = descriptions;
        this.statusCategory = statusCategory;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public int getStatusCategory() {
        return statusCategory;
    }

    public void setStatusCategory(int statusCategory) {
        this.statusCategory = statusCategory;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
