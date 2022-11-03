package personal_training_app;

public class Trainer {
    int trainer_id;
    String first_name;

    String last_name;

    public Trainer(int trainer_id) {
        this.trainer_id = trainer_id;
    }

    public int getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(int trainer_id) {
        this.trainer_id = trainer_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "Trainers{" +
               "trainer_id=" + trainer_id +
               ", first_name='" + first_name + '\'' +
               ", last_name='" + last_name + '\'' +
               '}';
    }
}