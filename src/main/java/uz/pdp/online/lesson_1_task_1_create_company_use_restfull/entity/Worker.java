package uz.pdp.online.lesson_1_task_1_create_company_use_restfull.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String phoneNumber;

    @OneToOne
    private Address address;

    @ManyToOne
    private Department department;
}