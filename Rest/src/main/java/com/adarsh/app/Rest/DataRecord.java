package com.adarsh.app.Rest;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "data")
public class DataRecord {

    @Id
    private String id;  // MongoDB ID field (String type, auto-generated)

    private String name;
    private String age;

    // Constructors, Getters, and Setters

    public DataRecord() {}

    public DataRecord(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
