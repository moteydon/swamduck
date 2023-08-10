package com.system.swimmingpool_management_system.Pojo;

import com.system.swimmingpool_management_system.entity.Swimmingpool;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class SwimmingpoolPojo {
    private Integer fid;
    private String fname;
    private String fcontact;
    private  String fprice;
    private String flocation;
    private MultipartFile image;
    private MultipartFile image1;
    private MultipartFile image2;
    private String Description;


    public SwimmingpoolPojo(Swimmingpool swimmingpool) {
        this.fid = swimmingpool.getFut_salId();
        this.fname = swimmingpool.getSwimmingpoolname();
        this.fcontact= swimmingpool.getSwimmingpoolcontact();
        this.fprice = swimmingpool.getSwimmingpoolprice();
        this.flocation = swimmingpool.getSwimmingpoollocation();
        this.Description = swimmingpool.getDescription();

    }
}