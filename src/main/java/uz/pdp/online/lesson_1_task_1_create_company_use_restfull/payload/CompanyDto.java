package uz.pdp.online.lesson_1_task_1_create_company_use_restfull.payload;

import lombok.Data;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.entity.Address;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Data
public class CompanyDto {

    @NotNull(message = "Tashkilot nomi bo'sh bo'lmasligi kerak")
    private String corpName;

    @NotNull(message = "Rahbar ismi kiritilishi shart")
    private String directorName;

    @NotNull(message = "Address kiritilishi shart")
    private Integer addressId;
}
