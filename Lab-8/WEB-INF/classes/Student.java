public class Student {
    private String name;
    private String email;
    private String password;
    private String course;

    public Student(String name, String email, String password, String course) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.course = course;
    }

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getCourse() { return course; }
}
