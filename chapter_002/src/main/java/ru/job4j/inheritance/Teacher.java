package ru.job4j.inheritance;

public class Teacher extends Profession {
    private String subject;

    private Teacher(String name, String professionalArea, String jobPlace) {
        super(name, professionalArea, jobPlace);
    }
    public Teacher(String name, String professionalArea, String jobPlace, String subject) {
        this(name, professionalArea, jobPlace);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
    public char mark(Pupil pupil) {
        char markPpl = 0;
        return markPpl;
    }
}
