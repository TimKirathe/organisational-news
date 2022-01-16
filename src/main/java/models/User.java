package models;

import java.util.Objects;

public class User {
    private String name;
    private String department;
    private int id;
    private String position;
    private int yearsActive;

    public User(String name, String department, String position, int yearsActive) {
        this.name = name;
        this.department = department;
        this.position = position;
        this.yearsActive = yearsActive;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getYearsActive() {
        return yearsActive;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && yearsActive == user.yearsActive && name.equals(user.name) && department.equals(user.department) && position.equals(user.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, department, id, position, yearsActive);
    }
}
