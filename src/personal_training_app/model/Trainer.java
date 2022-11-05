package personal_training_app.model;

public class Trainer {
    Long id;
    String firstName;
    String lastName;

    public Trainer() {
    }

    public Trainer(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Trainers{" +
               "trainer_id=" + id +
               ", first_name='" + firstName + '\'' +
               ", last_name='" + lastName + '\'' +
               '}';
    }
}