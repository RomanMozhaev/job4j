package ru.job4j.inheritance;

public class Engineer extends Profession {
    private String engineeringField;
    private int doneProjects;

    private Engineer(String name, String professionalArea, String jobPlace) {
        super(name, professionalArea, jobPlace);
    }
    public Engineer(String name, String professionalArea, String jobPlace, String engineeringField, int doneProjects) {
        this(name, professionalArea, jobPlace);
        this.engineeringField = engineeringField;
        this.doneProjects = doneProjects;
    }

    public String getEngineeringField() {
        return engineeringField;
    }

    public int getDoneProjects() {
        return doneProjects;
    }

    public SpareParts repair(Equipment equipment) {
        SpareParts parts = new SpareParts();
        return parts;
    }
}
