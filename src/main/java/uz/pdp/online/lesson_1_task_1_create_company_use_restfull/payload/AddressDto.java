package uz.pdp.online.lesson_1_task_1_create_company_use_restfull.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class AddressDto {

    @NotNull(message = "Ko'cha nomi bo'sh bo'lmasligi kerak")
    private String street;

    @NotNull(message = "Uy raqami bo'sh bo'lmasligi kerak")
    private String homeNumber;
}
