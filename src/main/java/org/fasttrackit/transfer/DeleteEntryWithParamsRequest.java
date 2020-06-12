package org.fasttrackit.transfer;

public class DeleteEntryWithParamsRequest {
    private String firsName;
    private String lastName;
    private int number;
    private String email;

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "DeleteEntryWithParamsRequest{" +
                "firsName='" + firsName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", number=" + number +
                ", email='" + email + '\'' +
                '}';
    }
}
