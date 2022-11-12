package personal_training_app.model;

public class Trainer {
    Long id;
    String firstName;
    String lastName;
    String email;

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
    public String getFirstName(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Trainer ID = " + id + ", First Name = " + firstName + ", Last Name = " + lastName
               + ", Email = " + email;
    }


}