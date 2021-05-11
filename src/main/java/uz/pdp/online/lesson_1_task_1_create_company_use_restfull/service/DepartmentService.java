package uz.pdp.online.lesson_1_task_1_create_company_use_restfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.entity.Company;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.entity.Department;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.payload.ApiResponse;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.payload.DepartmentDto;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.repository.CompanyRepos;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.repository.DepartmentRepos;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepos departmentRepos;
    @Autowired
    CompanyRepos companyRepos;

    public ApiResponse addDepartment(DepartmentDto departmentDto) {
        boolean exists = departmentRepos.existsByNameAndCompanyId(departmentDto.getName(), departmentDto.getCompanyId());
        if (exists)
            return new ApiResponse("Bunday tashkilot mavjud",false);
        Department department = new Department();
        department.setName(departmentDto.getName());
        Optional<Company> optionalCompany = companyRepos.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return new ApiResponse("Bunday tashkilot topilmadi",false);
        department.setCompany(optionalCompany.get());
        departmentRepos.save(department);
        return new ApiResponse("Ma'lumot saqlandi",true);
    }

    public List<Department> getDepartments() {
        List<Department> departments = departmentRepos.findAll();
        return departments;
    }

    public Department getDepartmentById(Integer id) {
        Optional<Department> optionalDepartment = departmentRepos.findById(id);
        return optionalDepartment.orElse(null);
    }

    public ApiResponse editDepartment(Integer id, DepartmentDto departmentDto) {
        boolean exists = departmentRepos.existsByNameAndCompanyIdAndIdNot(departmentDto.getName(), departmentDto.getCompanyId(), id);
        if (exists)
            return new ApiResponse("Bunday bo'lim mavjud",false);
        Optional<Department> optionalDepartment = departmentRepos.findById(id);
        if (!optionalDepartment.isPresent())
            return new ApiResponse("Bunday tashkilot topilmadi",false);
        Department editingDepartment = optionalDepartment.get();
        editingDepartment.setName(departmentDto.getName());
        Optional<Company> optionalCompany = companyRepos.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return new ApiResponse("Bunday tashkilot topilmadi",false);
        editingDepartment.setCompany(optionalCompany.get());
        departmentRepos.save(editingDepartment);
        return new ApiResponse("Ma'lumot saqlandi",true);
    }

    public ApiResponse deleteDepartment(Integer id) {
        try {
            departmentRepos.deleteById(id);
            return new ApiResponse("Ma'lumot o'chirildi",true);
        } catch (Exception e) {
            return new ApiResponse("Xatolik",false);
        }
    }
}

