package uz.pdp.online.lesson_1_task_1_create_company_use_restfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.entity.Address;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.entity.Company;

public interface CompanyRepos extends JpaRepository<Company,Integer> {
    boolean existsByCorpNameAndDirectorNameAndAddressId(String corpName, String directorName, Integer addressId);

    boolean existsByCorpNameAndDirectorNameAndAddressIdAndIdNot(String corpName, String directorName, Integer addressId, Integer id);
}
