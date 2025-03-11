package com.devsu.bank_client.infrastructure.adapters.output.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer", uniqueConstraints = @UniqueConstraint(columnNames = "customer_id"))
@Getter
@Setter
@NoArgsConstructor
public class CustomerEntity extends PersonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @NotBlank
    @Size(min = 8)
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "status", nullable = false)
    private Boolean status;

    public CustomerEntity(String name, String gender, Integer age, String identification, String address, String phone,
                          String password, Boolean status) {
        super(name, gender, age, identification, address, phone);
        this.password = password;
        this.status = status;
    }

}
