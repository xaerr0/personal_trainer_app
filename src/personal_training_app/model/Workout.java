package personal_training_app.model;

public class Workout {
    Long id;
    String name;
    String type;
    int length;

    public Workout() {
    }

    public Workout(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }


    @Override
    public String toString() {
        return "Workout ID = " + id + ", Workout Name = " + name + ", Type = " + type +
               ", Length = " + length + " min";
    }


}