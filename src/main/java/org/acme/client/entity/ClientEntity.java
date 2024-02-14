package org.acme.client.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.smallrye.common.constraint.NotNull;
import io.smallrye.common.constraint.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class ClientEntity extends PanacheEntity {

    @NotNull
    public String firstName;
    @Nullable
    public String middleName;
    @NotNull
    public String firstLastName;
    @Nullable
    public String secondLastName;
    @NotNull
    public String email;
    @NotNull
    public String address;
    @NotNull
    public String phoneNumber;
    @NotNull
    public String countryCode;
    public String demonym;

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName){
        this.middleName = middleName;
    }

    public void setFirstLastName(String firstLastName){
        this.firstLastName = firstLastName;
    }

    public void setSecondLastName(String secondLastName){
        this.secondLastName = secondLastName;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setCountryCode(String countryCode){
        this.countryCode = countryCode;
    }

    public void setDemonym(String demonym){
        this.demonym = demonym;
    }
}
