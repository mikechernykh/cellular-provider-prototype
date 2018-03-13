package dev.chernykh.cellular.tariff;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "tariffs")
@Builder
public class Tariff {
    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "date_of_change")
    private LocalDate dateOfChange;
    @Column(name = "activity_sign")
    private boolean activitySign;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "tariff_id")
    private List<Options> options = new ArrayList<>();
}
