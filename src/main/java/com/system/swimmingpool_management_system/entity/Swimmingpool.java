package com.system.swimmingpool_management_system.entity;



import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Swimmingpool")

public class Swimmingpool {
    @Id
    @SequenceGenerator(name = "shb_product_seq_gen", sequenceName = "shb_product_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "shb_product_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer fut_salId;

    private String swimmingpoolname;

    private String swimmingpoolprice;

    private String swimmingpoolcontact;

    private String swimmingpoollocation;

    private String swimmingpoolimage;
    private String swimmingpoolimage1;
    private String swimmingpoolimage2;

    private String Description;

    @Transient
    private String imageBase64;

    @Transient
    private String image1Base64;


    @Transient
    private String image2Base64;

}
