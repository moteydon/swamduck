package com.system.swimmingpool_management_system.Service.Impl;


import com.system.swimmingpool_management_system.Pojo.SwimmingpoolPojo;
import com.system.swimmingpool_management_system.Repo.SwimmingpoolRepo;
import com.system.swimmingpool_management_system.Service.SwimmingpoolService;
import com.system.swimmingpool_management_system.entity.Swimmingpool;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SwimmingpoolServiceImpl implements SwimmingpoolService {

    private final SwimmingpoolRepo swimmingpoolRepo;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/images/";


    @Override
    public SwimmingpoolPojo saveswimmingpool(SwimmingpoolPojo swimmingpoolPojo) throws IOException {
        Swimmingpool swimmingpool = new Swimmingpool();
        if (swimmingpoolPojo.getFid()!= null){
            swimmingpool.setFut_salId(swimmingpoolPojo.getFid());
        }
        swimmingpool.setSwimmingpoolname(swimmingpoolPojo.getFname());
        swimmingpool.setSwimmingpoolprice(swimmingpoolPojo.getFprice());
        swimmingpool.setSwimmingpoolcontact(swimmingpoolPojo.getFcontact());
        swimmingpool.setSwimmingpoollocation(swimmingpoolPojo.getFlocation());
        swimmingpool.setDescription(swimmingpoolPojo.getDescription());



        if(swimmingpoolPojo.getImage1()!=null){
//            System.out.println(UPLOAD_DIRECTORY);
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, swimmingpoolPojo.getImage1().getOriginalFilename());
            fileNames.append(swimmingpoolPojo.getImage1().getOriginalFilename());
            Files.write(fileNameAndPath, swimmingpoolPojo.getImage1().getBytes());

            swimmingpool.setSwimmingpoolimage1(swimmingpoolPojo.getImage1().getOriginalFilename());
        }
        if(swimmingpoolPojo.getImage2()!=null){
//            System.out.println(UPLOAD_DIRECTORY);
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, swimmingpoolPojo.getImage2().getOriginalFilename());
            fileNames.append(swimmingpoolPojo.getImage2().getOriginalFilename());
            Files.write(fileNameAndPath, swimmingpoolPojo.getImage2().getBytes());

            swimmingpool.setSwimmingpoolimage2(swimmingpoolPojo.getImage2().getOriginalFilename());
        }
        if(swimmingpoolPojo.getImage()!=null){
//            System.out.println(UPLOAD_DIRECTORY);
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, swimmingpoolPojo.getImage().getOriginalFilename());
            fileNames.append(swimmingpoolPojo.getImage().getOriginalFilename());
            Files.write(fileNameAndPath, swimmingpoolPojo.getImage().getBytes());

            swimmingpool.setSwimmingpoolimage(swimmingpoolPojo.getImage().getOriginalFilename());
        }
        swimmingpoolRepo.save(swimmingpool);
        return new SwimmingpoolPojo(swimmingpool);
    }

    @Override
    public Swimmingpool fetchById(Integer id) {
        Swimmingpool swimmingpool= swimmingpoolRepo.findById(id).orElseThrow(()-> new RuntimeException("Couldnot find"));
        swimmingpool = Swimmingpool.builder()
                .fut_salId(swimmingpool.getFut_salId())
                .imageBase64(getImageBase64(swimmingpool.getSwimmingpoolimage()))
                .image1Base64(getImageBase64(swimmingpool.getSwimmingpoolimage1()))
                .image2Base64(getImageBase64(swimmingpool.getSwimmingpoolimage2()))
                .swimmingpoolname(swimmingpool.getSwimmingpoolname())
                .swimmingpoolcontact(swimmingpool.getSwimmingpoolcontact())
                .swimmingpoolprice(swimmingpool.getSwimmingpoolprice())
                . swimmingpoollocation(swimmingpool.getSwimmingpoollocation())
                .Description(swimmingpool.getDescription())
                .build();
        return swimmingpool;
    }

    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/images/";
        File file = new File(filePath + fileName);
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Base64.getEncoder().encodeToString(bytes);
    }

    @Override
    public List<Swimmingpool> fetchAll() {
        return swimmingpoolRepo.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        swimmingpoolRepo.deleteById(id);
    }

}
