package uz.pdp.online.lesson_1_task_1_create_company_use_restfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.entity.Address;

public interface AddressRepos extends JpaRepository<Address, Integer> {

    boolean existsByStreetAndHomeNumber(String street, String homeNumber);

    boolean existsByStreetAndHomeNumberAndIdNot(String street, String homeNumber, Integer id);

}
