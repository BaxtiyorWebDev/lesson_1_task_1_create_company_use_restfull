package uz.pdp.online.lesson_1_task_1_create_company_use_restfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.entity.Address;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.entity.Company;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.payload.ApiResponse;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.payload.CompanyDto;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.repository.AddressRepos;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.repository.CompanyRepos;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepos companyRepos;
    @Autowired
    AddressRepos addressRepos;

    public ApiResponse addCompany(CompanyDto companyDto) {
        boolean exists = companyRepos.existsByCorpNameAndDirectorNameAndAddressId(companyDto.getCorpName(), companyDto.getDirectorName(), companyDto.getAddressId());
        if (exists)
            return new ApiResponse("Bunday tashkilot mavjud", true);
        Company company = new Company();
        Address address = new Address();
        address.setStreet(companyDto.getStreet());
        address.setHomeNumber(companyDto.getHomeNumber());
        Address savedAddress = addressRepos.save(address);
        company.setAddress(savedAddress);

        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        companyRepos.save(company);
        return new ApiResponse("Ma'lumot saqlandi", true);
    }

    public List<Company> getCompanies() {
        List<Company> companies = companyRepos.findAll();
        return companies;
    }

    public Company getCompanyById(Integer id) {
        Optional<Company> optionalCompany = companyRepos.findById(id);
        return optionalCompany.orElse(null);
    }

    public ApiResponse editCompany(Integer id, CompanyDto companyDto) {
        boolean exists = companyRepos.existsByCorpNameAndDirectorNameAndAddressIdAndIdNot(companyDto.getCorpName(), companyDto.getDirectorName(), companyDto.getAddressId(), id);
        if (exists)
            return new ApiResponse("Bunday ma'lumot mavjud", false);
        Optional<Company> optionalCompany = companyRepos.findById(id);
        if (!optionalCompany.isPresent())
            return new ApiResponse("Bunday ma'lumot topilmadi", false);
        Optional<Address> optionalAddress = addressRepos.findById(companyDto.getAddressId());
        if (!optionalAddress.isPresent()) {
            return new ApiResponse("Bunday address topilmadi", false);
        }
        Company editingCompany = optionalCompany.get();
        Address editingAddress = optionalAddress.get();
        editingAddress.setStreet(companyDto.getStreet());
        editingAddress.setHomeNumber(companyDto.getHomeNumber());
        Address savedAddress = addressRepos.save(editingAddress);

        editingCompany.setCorpName(companyDto.getCorpName());
        editingCompany.setDirectorName(companyDto.getDirectorName());
        editingCompany.setAddress(savedAddress);
        companyRepos.save(editingCompany);
        return new ApiResponse("Ma'lumot saqlandi",true);
    }

    public ApiResponse deleteCompany(Integer id) {
        try{
            companyRepos.deleteById(id);
            return new ApiResponse("Ma'lumot o'chirildi",true);
        }catch (Exception e) {
            return new ApiResponse("Xatolik",false);
        }
    }
}

