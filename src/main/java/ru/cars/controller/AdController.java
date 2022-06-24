package ru.cars.controller;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.cars.model.Advertisement;
import ru.cars.model.User;
import ru.cars.service.AdService;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class AdController {
    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        model.addAttribute("ads", adService.findAll());
        model.addAttribute("brands", adService.findAllBrands());
        model.addAttribute("bodies", adService.findAllBody());
        model.addAttribute("transmission", adService.findAllTransmission());
        userSession(model, session);
        return "index";
    }

    @GetMapping("/addAd")
    public String addTask(Model model, HttpSession session) {
        userSession(model, session);
        model.addAttribute("brand", adService.findAllBrands());
        model.addAttribute("body", adService.findAllBody());
        model.addAttribute("transmission", adService.findAllTransmission());
        return "ad/addAd";
    }

    @PostMapping("/createAd")
    public String createTask(@ModelAttribute Advertisement advertisement,
                             HttpSession session,
                             @RequestParam(value = "brandID", required = false) int brandID,
                             @RequestParam(value = "bodyID", required = false) int bodyID,
                             @RequestParam(value = "transmissionID", required = false) int transmissionID,
                             @RequestParam("file") MultipartFile file) throws IOException {
        advertisement.setUser((User) session.getAttribute("user"));
        advertisement.setBrand(adService.findBrandById(brandID));
        advertisement.setBody(adService.findBodyById(bodyID));
        advertisement.setTransmission(adService.findTransmissionById(transmissionID));
        advertisement.setPhoto(file.getBytes());
        adService.add(advertisement);
        return "redirect:/index";
    }

    @GetMapping("/photoAd/{adID}")
    public ResponseEntity<Resource> download(@PathVariable("adID") Integer adID) {
        Advertisement advertisement = adService.findByID(adID);
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(advertisement.getPhoto().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(advertisement.getPhoto()));
    }

    @GetMapping("/descriptionAd/{adID}")
    public String description(Model model, @PathVariable("adID") int id, HttpSession session) {
        userSession(model, session);
        model.addAttribute("ad", adService.findByID(id));
        return "ad/description";
    }

    @GetMapping("/delete/{adID}")
    public String delete(Model model, @PathVariable("adID") int id, HttpSession session) {
        userSession(model, session);
        adService.delete(id);
        return "redirect:/index";
    }

    @GetMapping("/setSold/{adID}")
    public String setSold(Model model, @PathVariable("adID") int id, HttpSession session) {
        userSession(model, session);
        adService.setSoldById(id);
        return "redirect:/descriptionAd/{adID}";
    }

    @GetMapping("/editAd/{adID}")
    public String editAd(Model model, @PathVariable("adID") int id, HttpSession session) {
        userSession(model, session);
        model.addAttribute("ad", adService.findByID(id));
        model.addAttribute("brand", adService.findAllBrands());
        model.addAttribute("body", adService.findAllBody());
        model.addAttribute("transmission", adService.findAllTransmission());
        return "ad/editTask";
    }

    @PostMapping("/editAd")
    public String editAd(@ModelAttribute Advertisement advertisement, HttpSession session,
                         @RequestParam(value = "brandID", required = false) int brandID,
                         @RequestParam(value = "bodyID", required = false) int bodyID,
                         @RequestParam(value = "transmissionID", required = false) int transmissionID,
                         @RequestParam("file") MultipartFile file) throws IOException {
        advertisement.setUser((User) session.getAttribute("user"));
        advertisement.setBrand(adService.findBrandById(brandID));
        advertisement.setBody(adService.findBodyById(bodyID));
        advertisement.setTransmission(adService.findTransmissionById(transmissionID));
        advertisement.setPhoto(file.getBytes());
        adService.update(advertisement);
        return "redirect:/descriptionAd/" + advertisement.getId();
    }

    private void userSession(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
    }
}
