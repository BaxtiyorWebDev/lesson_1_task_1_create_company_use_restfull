package uz.pdp.online.lesson_1_task_1_create_company_use_restfull.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DepartmentDto {

    @NotNull(message = "Bo'lim nomi kiritilishi shart")
    private String name;

    @NotNull(message = "Tashkilot tanlanishi shart")
    private Integer companyId;
}
