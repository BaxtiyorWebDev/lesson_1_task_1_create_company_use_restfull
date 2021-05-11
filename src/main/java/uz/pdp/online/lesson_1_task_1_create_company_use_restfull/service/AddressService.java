package uz.pdp.online.lesson_1_task_1_create_company_use_restfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.entity.Address;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.payload.AddressDto;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.payload.ApiResponse;
import uz.pdp.online.lesson_1_task_1_create_company_use_restfull.repository.AddressRepos;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepos addressRepos;

    public ApiResponse addAddress(AddressDto addressDto) {
        boolean exists = addressRepos.existsByStreetAndHomeNumber(addressDto.getStreet(), addressDto.getHomeNumber());
        if (exists)
            return new ApiResponse("Bunday address mavjud", false);
        Address address = new Address();
        address.setStreet(addressDto.getStreet());
        address.setHomeNumber(addressDto.getHomeNumber());
        addressRepos.save(address);
        return new ApiResponse("Ma'lumot saqlandi",true);
    }

    public List<Address> getAddress() {
        List<Address> addressList = addressRepos.findAll();
        return addressList;
    }

    public Address getAddressById(Integer id) {
        Optional<Address> optionalAddress = addressRepos.findById(id);
        return optionalAddress.orElse(null);
    }

    public ApiResponse editAddress(Integer id, AddressDto addressDto) {
        boolean exists = addressRepos.existsByStreetAndHomeNumberAndIdNot(addressDto.getStreet(), addressDto.getHomeNumber(), id);
        if (exists)
            return new ApiResponse("Bunday address mavjud",false);
        Optional<Address> optionalAddress = addressRepos.findById(id);
        if (!optionalAddress.isPresent())
            return new ApiResponse("Bunday address topilmadi",false);
        Address editingAddress = optionalAddress.get();
        editingAddress.setStreet(addressDto.getStreet());
        editingAddress.setHomeNumber(addressDto.getHomeNumber());
        addressRepos.save(editingAddress);
        return new ApiResponse("Ma'lumot o'zgartirildi",true);
    }


    public ApiResponse deleteAddress(Integer id) {
        Optional<Address> optionalAddress = addressRepos.findById(id);
        if (!optionalAddress.isPresent())
            return new ApiResponse("Bunday address topilmadi",false);
        addressRepos.delete(optionalAddress.get());
        return new ApiResponse("Ma'lumot o'chirildi",true);
    }

}
