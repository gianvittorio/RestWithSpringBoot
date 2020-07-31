package br.com.gianvittorio.data.vo.v1;

import br.com.gianvittorio.data.model.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import java.io.Serializable;

@JsonPropertyOrder({"id", "address", "first_name", "last_name", "gender", "enabled"})
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {
    private static final long serialVersionUID = -5682588825283278676L;

    @Mapping("id")
    @JsonProperty("id")
    private Long key;

    //    @JsonProperty("first_name")
    private String firstName;

    //    @JsonProperty("last_name")
    private String lastName;

    private String address;

    //    @JsonIgnore
    private String gender;

    private Boolean enabled;

    public PersonVO() {
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PersonVO personVO = (PersonVO) o;

        if (key != null ? !key.equals(personVO.key) : personVO.key != null) return false;
        if (firstName != null ? !firstName.equals(personVO.firstName) : personVO.firstName != null) return false;
        if (lastName != null ? !lastName.equals(personVO.lastName) : personVO.lastName != null) return false;
        if (address != null ? !address.equals(personVO.address) : personVO.address != null) return false;
        if (gender != null ? !gender.equals(personVO.gender) : personVO.gender != null) return false;
        return enabled != null ? enabled.equals(personVO.enabled) : personVO.enabled == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        return result;
    }
}
