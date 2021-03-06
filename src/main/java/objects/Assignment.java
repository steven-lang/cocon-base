package objects;

import org.json.JSONObject;
import utils.JSONParser;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

/**
 * Created by tak3r07 on 11/9/14.
 */
public class Assignment implements Serializable {

    static final long serialVersionUID = 62872256170663659L;

    //Index of the Assignment
    private int index;

    // Maximal reachable points of this assignment
    private double maxPoints;

    //Achieved number of points of this assignment
    private double achievedPoints;

    //Boolean whether this is an extra-assignment or not
    private boolean isExtraAssignment = false;

    private UUID id;

    //Date of creation
    private long date;

    private UUID course_id;

    /**
     * Constructor for new Assignments
     *
     * @param id             -1 = new Assignment, else = read from DATABASE
     * @param index          index in assignments list
     * @param maxPoints      maximum reachable points
     * @param achievedPoints achieved points in this assignment
     * @param course_id      reference to the course
     */
    public Assignment(UUID id, int index, double maxPoints, double achievedPoints, UUID course_id) {
        setIndex(index);
        setMaxPoints(maxPoints);
        setAchievedPoints(achievedPoints);
        setCourse_id(course_id);


        //Check if assignment comes from database or should be created as "new assignment"
        if (id == null) {
            //Set random id
            Random rand = new Random();
            this.id = UUID.randomUUID();
            setDate(Calendar.getInstance().getTimeInMillis());
        } else {
            setId(id);
            setDate(0);
        }


    }


    //Constructor for assignment which will be read from the database

    //Calculates and return the achieved percentage of max points of this assignment
    public Double getPercentage() {
        Double percentage = 0d;
        //Round on 4 digits
        if (maxPoints != 0) {
            percentage = Math.round(achievedPoints / maxPoints * 1000) / 10d;
        } else {
            return percentage;
        }

        return percentage;


    }


    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return this.id;
    }

    public void setCourse_id(UUID id) {
        this.course_id = id;
    }

    public UUID getCourse_id() {
        return course_id;
    }

    public void setMaxPoints(double maxPoints) {
        this.maxPoints = maxPoints;
    }

    public double getAchievedPoints() {
        return achievedPoints;
    }

    public void setAchievedPoints(double achievedPoints) {
        this.achievedPoints = achievedPoints;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isExtraAssignment() {
        return isExtraAssignment;
    }

    public void isExtraAssignment(boolean isExtraAssignment) {

        this.isExtraAssignment = isExtraAssignment;
        if (isExtraAssignment) this.maxPoints = 0d;
    }

    public double getMaxPoints() {
        return this.maxPoints;
    }
    public void setDate(long date){
        this.date = date;
    }

    public long getDate(){
        return this.date;
    }
    
    /**
     * Generates a JSONObject from this assignment
     * @return JSONObject of this assignment
     */
    public JSONObject toJson(){
        return JSONParser.assignmentToJSON(this);
    }
}
