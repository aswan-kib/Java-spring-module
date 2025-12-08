package org.example.entity;

import java.util.UUID;

/**
 * @author aswan-kib
 * @since 12/8/25
 */
public abstract class Person implements Identifiable {

    protected String id;
    protected String name;
    protected String email;

    protected Person(String name, String email) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
    }

    @Override
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
