package model;

import java.util.Objects;

public class Subject {

    public enum type {
        EXAM, CREDIT
    }

    private int id;
    private String name;
    private type typeOfSubject;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTypeOfSubject() {
        if(this.typeOfSubject == type.EXAM) {
            return "EXAM";
        }
        else return "CREDIT";
    }

    public Subject() {}

    public Subject(String name, type typeOfSubject) {
        this.name = name;
        this.typeOfSubject = typeOfSubject;
    }

    public Subject(int id, String name, type typeOfSubject) {
        this.id = id;
        this.name = name;
        this.typeOfSubject = typeOfSubject;
    }

    public Subject(String data) {
        String[] parts = data.split("_");
        this.name = parts[0];
        this.typeOfSubject = type.valueOf(parts[1]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(name, subject.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name + "_" + typeOfSubject;
    }
}
// JAVAX.validation
// enum