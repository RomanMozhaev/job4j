package ru.job4j.inheritance;

public class Doctor extends Profession {
    private String medicineSpeciality;

    private Doctor(String name, String professionalArea, String jobPlace){
        super(name, professionalArea, jobPlace);
    }
    public Doctor(String name, String professionalArea, String jobPlace, String medicineSpeciality) {
        this(name, professionalArea, jobPlace);
        this.medicineSpeciality = medicineSpeciality;
    }

    public String getMedicineSpeciality() {
        return medicineSpeciality;
    }

    public Diagnose heal(Pacient pacient){
        Diagnose diagnose = new Diagnose();
        return diagnose;
    }
}
