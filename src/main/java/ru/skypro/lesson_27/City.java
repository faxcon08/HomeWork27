package ru.skypro.lesson_27;

public class City {
    private int city_id;
    private String city_name;

    public City(int city_id, String city_name) {
        this.city_id = city_id;
        this.city_name = city_name;
    }

    public int getCity_id() {
        return city_id;
    }

    public String getCity_name() {
        return city_name;
    }
}
