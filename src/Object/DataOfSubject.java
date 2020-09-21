package Object;

import java.util.ArrayList;

/***
 * Obiekt zawierający dane przedmiotu
 */
public class DataOfSubject {
    private int posX;
    private int posY;
    private String name;
    private String term;
    private int time;
    private String prof;
    private String room;
    private int type;
    private String week;
    private ArrayList<DataOfNote> notes;
    private int absences;
    private int allAbsences;
    private ArrayList<String> testList;

    /***
     * Konstruktor
     * @param posX - współrzędna X przedmiotu
     * @param posY - współrzędna Y przedmiotu
     * @param name - nazwa przedmiotu
     * @param term - termin przedmiotu
     * @param time - czas trwania przedmiotu
     * @param prof - prowadzący
     * @param room - sala
     * @param type - Rodzaj przedmiotu ( Lab lub Wykład)
     * @param week - Tydzień (Parzysty, Nieparzysty, co tydzień)
     * @param noteArrayList - lista notatek
     */
    public DataOfSubject(int posX, int posY, String name, String term, int time, String prof,
                         String room, int type, String week, ArrayList<DataOfNote> noteArrayList,
                         int absences, int allAbsences, ArrayList<String> testList) {
        this.posX = posX;
        this.posY = posY;
        this.name = name;
        this.term = term;
        this.time = time;
        this.prof = prof;
        this.room = room;
        this.type = type;
        this.week = week;
        this.notes = noteArrayList;
        this.absences = absences;
        this.allAbsences = allAbsences;
        this.testList = testList;
    }

//  Getters and Setters
    public void setNotes(ArrayList<DataOfNote> notesList) {
        this.notes = notesList;
    }

    public ArrayList<String> getTestList() {
        return testList;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getName() {
        return name;
    }

    public String getTerm() {
        return term;
    }

    public int getTime() {
        return time;
    }

    public String getProf() {
        return prof;
    }

    public String getRoom() {
        return room;
    }

    public int getType() {
        return type;
    }

    public String getWeek() {
        return week;
    }

    public ArrayList<DataOfNote> getNotes() {
        return this.notes;
    }

    public int getAbsences() {
        return absences;
    }

    public int getAllAbsences() {
        return allAbsences;
    }
}
