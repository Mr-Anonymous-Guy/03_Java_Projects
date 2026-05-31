package app.model;

import java.sql.Date;

public class Member {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date joinDate;

    public Member(int id, String firstName, String lastName, String email, String phone, Date joinDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.joinDate = joinDate;
    }

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public Date getJoinDate() { return joinDate; }
}
