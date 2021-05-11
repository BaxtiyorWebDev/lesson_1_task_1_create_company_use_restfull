package uz.pdp.online.lesson_1_task_1_create_company_use_restfull.payload;

import lombok.Data;

@Data
public class WorkerDto {
    private String name;
    private String phoneNumber;
    private String street;
    private String homeNumber;
    private Integer addressId;
    private Integer departmentId;
}
