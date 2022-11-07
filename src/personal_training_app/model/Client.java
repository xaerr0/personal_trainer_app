package personal_training_app.model;

public class Client {

    Long id;
    String firstName;
    String lastName;
    String email;

    public Client(Long id) {
        this.id = id;
    }

    public Client() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Clients{" +
               "id=" + id +
               ", first_name='" + firstName + '\'' +
               ", last_name='" + lastName + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}