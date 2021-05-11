package uz.pdp.online.lesson_1_task_1_create_company_use_restfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.entity.Department;

public interface DepartmentRepos extends JpaRepository<Department, Integer> {

    boolean existsByNameAndCompanyId(String name, Integer company_id);

    boolean existsByNameAndCompanyIdAndIdNot(String name, Integer company_id, Integer id);
}
