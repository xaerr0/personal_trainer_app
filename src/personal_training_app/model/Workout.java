package personal_training_app.model;

public class Workout {
    Long id;
    String name;
    String type;

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

    public Workout(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Workout{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", type='" + type + '\'' +
               '}';
    }
}