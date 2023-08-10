package com.system.swimmingpool_management_system.Controller;

import com.system.swimmingpool_management_system.Pojo.BookingPojo;
import com.system.swimmingpool_management_system.Pojo.SwimmingpoolPojo;
import com.system.swimmingpool_management_system.Service.BookingService;
import com.system.swimmingpool_management_system.Service.ContactService;
import com.system.swimmingpool_management_system.Service.SwimmingpoolService;
import com.system.swimmingpool_management_system.Service.UserService;
import com.system.swimmingpool_management_system.entity.Booking;
import com.system.swimmingpool_management_system.entity.Contact;
import com.system.swimmingpool_management_system.entity.Swimmingpool;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final SwimmingpoolService swimmingpoolService;
    private final BookingService bookingService;
    private final ContactService contactService;

    @GetMapping("/dashboard")
    public String fetchAllbooking(Model model){
        List<Booking> adminbooking = bookingService.fetchAll();
        model.addAttribute("book", adminbooking.stream().map(booking ->
                Booking.builder()
                        .bookId(booking.getBookId())
                        .date(booking.getDate())
                        .starting(booking.getStarting())
                        .user(booking.getUser())
                        .swimmingpool(booking.getSwimmingpool())
                        .email(booking.getEmail())
                        .build()
        ));

        model.addAttribute("books", new BookingPojo());



        return "dashboard";
    }

    @GetMapping("/contact")
    public String createcontact(Model model) {
        List<Contact> admincontact = contactService.fetchAll();
        model.addAttribute("contact", admincontact.stream().map(contact ->
                Contact.builder()
                        .contactId(contact.getContactId())
                        .contactname(contact.getContactname())
                        .contactemail(contact.getContactemail())
                        .contactsubject(contact.getContactsubject())
                        .contactmessage(contact.getContactmessage())
                        .build()
        ));
        return "viewreview";
    }

    @GetMapping("/view")
    public String fetchAllSwimmingpool(Model model){
        List<Swimmingpool> adminswimmingpool = swimmingpoolService.fetchAll();
        model.addAttribute("swimmingpools", adminswimmingpool.stream().map(swimmingpool ->
                swimmingpool.builder()
                        .fut_salId(swimmingpool.getFut_salId())
                        .swimmingpoolname(swimmingpool.getSwimmingpoolname())
                        .swimmingpoolprice(swimmingpool.getSwimmingpoolprice())
                        .swimmingpoolcontact(swimmingpool.getSwimmingpoolcontact())
                        .swimmingpoollocation(swimmingpool.getSwimmingpoollocation())
                        .Description(swimmingpool.getDescription())
                        .imageBase64(getImageBase64(swimmingpool.getSwimmingpoolimage()))
                        .image1Base64(getImageBase64(swimmingpool.getSwimmingpoolimage1()))
                        .image2Base64(getImageBase64(swimmingpool.getSwimmingpoolimage2()))
                        .build()
        ));
        return "viewswimmingpool";
    }


    @GetMapping("/del/{id}")
    public String deletereview(@PathVariable("id") Integer id) {
        contactService.deleteById(id);
        return "redirect:/admin/dashboard";
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

//    @GetMapping("/review")
//    public String review() {
//
//        return "viewreview";
//    }
    @GetMapping("/report")
    public String report() {

        return "report";
    }

    @GetMapping("/product/{id}")
    public String getSwimmingpoolProfiile(@PathVariable("id") Integer id, Model model ){
        Swimmingpool swimmingpool = swimmingpoolService.fetchById(id);
        model.addAttribute("swimmingpools", new SwimmingpoolPojo(swimmingpool));

        model.addAttribute("clickedswimmingpool", swimmingpool);
        return "editswimmingpool";
    }
    @GetMapping("/edit/{id}")
    public String editswimmingpool(@PathVariable("id") Integer id, Model model){
        Swimmingpool swimmingpool =swimmingpoolService.fetchById(id);
        model.addAttribute("clickedswimmingpool", new SwimmingpoolPojo(swimmingpool));
        return "redirect:/admin/view";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        swimmingpoolService.deleteById(id);
        return "redirect:/admin/view";
    }


    @PostMapping("/changepassword")
    public String changepassword(@RequestParam("email") String email, Model model, @Valid BookingPojo bookingPojo){
        bookingService.processPasswordResetRequest(bookingPojo.getEmail());
        model.addAttribute("message","Your new password has been sent to your email Please " +
                "check your inbox");
        return "redirect:/admin/report";
    }






}