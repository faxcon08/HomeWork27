package ru.skypro.lesson_27;

public class Employee {
    private int id;
    private String first_name;
    private String last_name;
    private String gender;
    private int age;
    private int city_id;

    public Employee(String first_name, String last_name, String gender, int age, int city_id) {
        this.id=-1;
        this.first_name=first_name;
        this.last_name=last_name;
        this.gender=gender;
        this.age=age;
        this.city_id=city_id;
    }
    public Employee(int id, String first_name, String last_name, String gender, int age, int city_id) {
        this.id=id;
        this.first_name=first_name;
        this.last_name=last_name;
        this.gender=gender;
        this.age=age;
        this.city_id=city_id;
    }

    ///// getters //////
    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getCity_id() {
        return city_id;
    }
    ////// setters ///////

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    @Override
    public String toString() {
        String printID = (id==-1)? "   ":"#"+id+" ";
        return printID+first_name+" "+last_name+" "+gender+" "+age+" лет city_id="+city_id;
    }
}
