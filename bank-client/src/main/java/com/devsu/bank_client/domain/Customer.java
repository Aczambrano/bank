package com.devsu.bank_client.domain;

public class Customer extends Person{

    private Integer customerId;
    private String password;
    private Boolean status;

    public Customer(String name, String gender, Integer age, String identification, String address, String phone,
                    Integer customerId,String password, Boolean status) {
        super(name, gender, age, identification, address, phone);
        this.customerId = customerId;
        this.password = password;
        this.status = status;
    }

    public Customer(Integer customerId, String password, Boolean status) {
        this.customerId = customerId;
        this.password = password;
        this.status = status;
    }

    public Customer() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }


}
