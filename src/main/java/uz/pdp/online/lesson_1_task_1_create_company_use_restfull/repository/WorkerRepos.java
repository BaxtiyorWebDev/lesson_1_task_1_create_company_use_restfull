package uz.pdp.online.lesson_1_task_1_create_company_use_restfull.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.entity.Worker;

public interface WorkerRepos extends JpaRepository<Worker, Integer> {

    boolean existsByNameAndPhoneNumber(String name, String phoneNumber);

    boolean existsByNameAndPhoneNumberAndIdNot(String name, String phoneNumber, Integer id);
}
