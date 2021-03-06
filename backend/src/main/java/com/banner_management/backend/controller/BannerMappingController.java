
package com.banner_management.backend.controller;

import com.banner_management.backend.dto.BannerInfoDto;
import com.banner_management.backend.dto.BannerMappingDto;
import com.banner_management.backend.entity.BannerEntity;
import com.banner_management.backend.entity.BannerMappingEntity;
import com.banner_management.backend.entity.SectionEntity;
import com.banner_management.backend.service.BannerMappingService;
import com.banner_management.backend.service.BannerService;
import com.banner_management.backend.service.SectionService;
import com.banner_management.backend.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class BannerMappingController {

    @Autowired
    private BannerMappingService bannerMappingService;

    @Autowired
    private BannerService bannerService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private ViewService viewService;

    //  lấy random banner theo số lượng và khu vực
    @GetMapping("/banner-mapping/percentage/{sectionID}/{bannerID}")
    public BannerEntity listPercentageBannerStatus(@PathVariable("sectionID") @Valid int sectionID, @PathVariable("bannerID") @Valid int bannerID) {
        BannerMappingEntity bannerMappingEntity = bannerMappingService.getByBannerIDAndSectionID(bannerID, sectionID);
        System.out.println("kiem tra : "+ bannerMappingEntity);
        BannerEntity bannerEntity = bannerService.getById(bannerMappingEntity.getBannerID());
        System.out.println("data : "+ bannerEntity);
        return bannerEntity;
    }

    // cap nhat du lieu thoi gian bat dau khi chon random
    @PutMapping("/banner-mapping/random/{id}")
    public void updateBannerStatus (@RequestBody BannerMappingEntity bannerMappingEntity, @PathVariable("id") @Valid Integer id){
        BannerMappingEntity existBannerMappingEntity = bannerMappingService.getById(id);
        System.out.println("banner status dau vao o day : "+ bannerMappingEntity);
        if(bannerMappingEntity.getSectionID() >= 0){
            existBannerMappingEntity.setSectionID(bannerMappingEntity.getSectionID());
        }
        bannerMappingService.save(existBannerMappingEntity);
    }

//     cap nhat du lieu khi chon hien thi theo ti trong
    @PutMapping("/banner-mapping/percentage")
    public void updateBannerStatusOnPercentage(@RequestBody @Valid List<BannerMappingEntity> bannerMappingEntityList){
        // du lieu can co bannerID, sectionID, percentage, timeDisplay, expired
        System.out.println("du lieu dau vao list : "+ bannerMappingEntityList);
        for(int i = 0; i < bannerMappingEntityList.size(); i++){
            BannerMappingEntity bannerMappingEntity = bannerMappingEntityList.get(i);
            System.out.println("phần tử "+ i + " "+ bannerMappingEntity);
            bannerMappingService.updatePercentage(bannerMappingEntity.getPercentage(), bannerMappingEntity.getBannerID(), bannerMappingEntity.getSectionID());
        }
    }

    @GetMapping("/banner-mapping/percentage/{sectionID}")
    public BannerMappingDto getImageUrlByPercentage(@PathVariable("sectionID") int sectionId){
        // tim sectionId - lay thong tin xem cai section day dat la ti trong hay random ?
        SectionEntity section = sectionService.getById(sectionId);
        System.out.println("section : "+ section);
        short status = section.getStatus();
        // neu ti trong
            BannerMappingEntity bannerMappingEntity = bannerMappingService.getBannerByPercentage(sectionId);
            if(bannerMappingEntity == null){
                return null;
            }
            BannerEntity bannerEntity = bannerService.getById(bannerMappingEntity.getBannerID());
            if (bannerMappingEntity.getNumberView() == 0) {
                bannerMappingEntity.setNumberView(1);
            } else {
                int countViews = bannerMappingEntity.getNumberView();
                bannerMappingEntity.setNumberView(countViews + 1);
            }
            bannerMappingService.save(bannerMappingEntity);
            BannerMappingDto bannerMappingDto = new BannerMappingDto(
                    bannerEntity.getId(), bannerEntity.getImgUrl(), bannerEntity.getUrl(), bannerMappingEntity.getSectionID());
            System.out.println("banner Dto : " + bannerMappingDto);
            return bannerMappingDto;
        // neu random
        // chay getBannerByRandom
    }

    //
    @PutMapping("/banner-status/update")
    public void updateBannerStatus(@RequestBody @Valid List<BannerInfoDto> bannerDtoList){
        System.out.println(bannerDtoList);
        for(int i = 0; i < bannerDtoList.size(); i++){
            BannerInfoDto bannerInfoDto = bannerDtoList.get(i);
            BannerMappingEntity bannerMappingEntity = bannerMappingService.getById(bannerInfoDto.getId());
            short state = bannerInfoDto.getState();
            int percentage = bannerInfoDto.getPercentage();
            bannerMappingEntity.setState(state);
            bannerMappingEntity.setPercentage(percentage);
            bannerMappingService.save(bannerMappingEntity);
        }
    }


    @GetMapping("/banner-status/all")
    public ResponseEntity<List<BannerMappingEntity>> getAllBannerStatus(){
        try {
            List<BannerMappingEntity> banners = bannerMappingService.getAllBannerStatus();
            return new ResponseEntity<>(banners, HttpStatus.OK);
        } catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

