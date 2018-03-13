package dev.chernykh.cellular.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "tariff_id")
    private long tariffId;
}
