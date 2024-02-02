package com.example.ordering.item.service;

import com.example.ordering.item.domain.Item;
import com.example.ordering.item.dto.ItemReqDto;
import com.example.ordering.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepo;

    @Autowired
    public ItemService(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }


    public Item createItem(ItemReqDto itemReqDto)  {
        MultipartFile multipartFile = itemReqDto.getItemImage();
        String fileName = multipartFile.getOriginalFilename();

        Item itemTemp = Item.builder()
                .name(itemReqDto.getName())
                .category(itemReqDto.getCategory())
                .price(itemReqDto.getPrice())
                .stockQuantity(itemReqDto.getStockQuantity())
                .build();
        Long itemId = itemRepo.save(itemTemp).getId();

        Path path = Paths
                .get(
                        "C:\\Users\\Playdata\\IdeaProjects\\Ordering\\src\\main\\resources\\temp",
                         itemId + "_"+ fileName);
        System.out.println(fileName);
        itemTemp.setImagePath(path.toString());

        try {
            byte[] bytes = multipartFile.getBytes();
            Files.write(path, bytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new IllegalArgumentException("image not available");
        }

        return itemRepo.save(itemTemp);
    }

    public Object delete(Long id) {
        Item item = itemRepo.findById(id).orElseThrow();
        item.setDelYn("Y");
        return item;
    }
}
