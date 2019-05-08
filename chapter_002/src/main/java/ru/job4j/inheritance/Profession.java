package ru.job4j.inheritance;

public class Profession {
    private String name;
    private String professionalArea;
    private String jobPlace;

    public Profession(String name, String professionalArea, String jobPlace) {
        this.name = name;
        this.professionalArea = professionalArea;
        this.jobPlace = jobPlace;
    }

    public String getName() {
        return name;
    }

    public String getProfessionalArea() {
        return professionalArea;
    }

    public String getJobPlace() {
        return jobPlace;
    }
}
