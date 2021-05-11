package uz.pdp.online.lesson_1_task_1_create_company_use_restfull.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WorkerDto {
    @NotNull(message = "Ism kiritilishi shart")
    private String name;

    @NotNull(message = "Telefon raqam kiritilishi shart")
    private String phoneNumber;

    private String street;
    private String homeNumber;
    private Integer addressId;
    private Integer departmentId;
}
