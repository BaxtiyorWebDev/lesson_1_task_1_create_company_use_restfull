package uz.pdp.online.lesson_1_task_1_create_company_use_restfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.entity.Address;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.entity.Department;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.entity.Worker;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.payload.ApiResponse;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.payload.WorkerDto;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.repository.AddressRepos;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.repository.DepartmentRepos;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.repository.WorkerRepos;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepos workerRepos;
    @Autowired
    AddressRepos addressRepos;
    @Autowired
    DepartmentRepos departmentRepos;

    public ApiResponse addWorker(WorkerDto workerDto) {
        boolean exists = workerRepos.existsByNameAndPhoneNumber(workerDto.getName(), workerDto.getPhoneNumber());
        if (exists)
            return new ApiResponse("bunday ishchi majud", false);
        Optional<Address> optionalAddress = addressRepos.findById(workerDto.getAddressId());
        Optional<Department> optionalDepartment = departmentRepos.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent())
            return new ApiResponse("Bunday tashkilot topilmadi", false);

        Worker worker = new Worker();
        Address address = new Address();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setDepartment(optionalDepartment.get());
        address.setStreet(workerDto.getStreet());
        address.setHomeNumber(workerDto.getHomeNumber());
        Address savedAddress = addressRepos.save(address);
        worker.setAddress(savedAddress);
        workerRepos.save(worker);
        return new ApiResponse("Ma'lumot saqlandi", true);
    }

    public List<Worker> getWorkers() {
        List<Worker> workers = workerRepos.findAll();
        return workers;
    }

    public Worker getWorkerById(Integer id) {
        Optional<Worker> optionalWorker = workerRepos.findById(id);
        return optionalWorker.orElse(null);
    }

    public ApiResponse editWorker(Integer id, WorkerDto workerDto) {
        boolean exists = workerRepos.existsByNameAndPhoneNumberAndIdNot(workerDto.getName(), workerDto.getPhoneNumber(), id);
        if (exists)
            return new ApiResponse("Bunday ishchi mavjud", false);
        Optional<Worker> optionalWorker = workerRepos.findById(id);
        if (!optionalWorker.isPresent())
            return new ApiResponse("Bunday ishchi mavjud emas", false);
        Optional<Department> optionalDepartment = departmentRepos.findById(workerDto.getDepartmentId());
        if (!optionalDepartment.isPresent())
            return new ApiResponse("Bunday tashkilot topilmadi", false);
        Optional<Address> optionalAddress = addressRepos.findById(workerDto.getAddressId());
        if (!optionalAddress.isPresent())
            return new ApiResponse("Bunday manzil topilmadi", false);

        Worker editingWorker = optionalWorker.get();
        Address editingAddress = optionalAddress.get();
        editingAddress.setStreet(workerDto.getStreet());
        editingAddress.setHomeNumber(workerDto.getHomeNumber());
        Address savedAddress = addressRepos.save(editingAddress);
        editingWorker.setAddress(savedAddress);
        editingWorker.setName(workerDto.getName());
        editingWorker.setPhoneNumber(workerDto.getPhoneNumber());
        editingWorker.setDepartment(optionalDepartment.get());
        workerRepos.save(editingWorker);
        return new ApiResponse("Bunday ishchi mavjud emas", true);
    }

    public ApiResponse deleteWorkerById(Integer id) {
        try {
            workerRepos.deleteById(id);
            return new ApiResponse("Ma'lumot o'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Xatolik", false);
        }
    }
}
