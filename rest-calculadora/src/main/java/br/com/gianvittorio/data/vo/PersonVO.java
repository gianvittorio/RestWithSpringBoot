package br.com.gianvittorio.data.vo;

public class PersonVO {
    private static final long serialVersionUID = -8363645875551701160L;

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;

    public PersonVO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonVO personVO = (PersonVO) o;

        if (id != null ? !id.equals(personVO.id) : personVO.id != null) return false;
        if (firstName != null ? !firstName.equals(personVO.firstName) : personVO.firstName != null) return false;
        if (lastName != null ? !lastName.equals(personVO.lastName) : personVO.lastName != null) return false;
        if (address != null ? !address.equals(personVO.address) : personVO.address != null) return false;
        return gender != null ? gender.equals(personVO.gender) : personVO.gender == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
