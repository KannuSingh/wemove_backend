package com.wemove.wemove_backend.services;

import com.wemove.wemove_backend.entities.*;
import com.wemove.wemove_backend.repositories.InventoryItemRepository;
import com.wemove.wemove_backend.repositories.MoveRequestRepository;
import com.wemove.wemove_backend.repositories.UserCredentialsRepository;
import com.wemove.wemove_backend.repositories.UserDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MoveRequestService {


    private final MoveRequestRepository moveRequestRepository;

    private final InventoryItemRepository inventoryItemRepository;

    public MoveRequestService(MoveRequestRepository moveRequestRepository , InventoryItemRepository inventoryItemRepository) {
        this.moveRequestRepository = moveRequestRepository;
        this.inventoryItemRepository = inventoryItemRepository;
    }

    public MoveRequest createMoveRequest(MoveRequest moveRequest){
        System.out.println("MoveRequestService : "+moveRequest);
        MoveRequest savedMoveRequest = moveRequestRepository.save(moveRequest);
        if(savedMoveRequest != null ) {
            List<InventoryItem> inventoryItemList = new ArrayList<>();
            for (InventoryItemGroup inventoryItemGroup : moveRequest.getItemInventory()) {
                for (String itemName : inventoryItemGroup.getItems()) {
                    InventoryItem inventoryItem = new InventoryItem();
                    inventoryItem.setItemName(itemName);
                    inventoryItem.setCategory(inventoryItemGroup.getCategory());
                    inventoryItem.setMoveRequestId(moveRequest.getMoveRequestId());
                    inventoryItemList.add(inventoryItem);
                }
            }
            if(inventoryItemList.size() > 0) {
                List<InventoryItem> savedInventoryItemList = inventoryItemRepository.saveAll(inventoryItemList);
                if( savedInventoryItemList != null){
                    return moveRequest;
                }
            }
            return moveRequest;

        }
        return null;
    }

    public List<MoveRequest> getAllMoveRequest(String ownerEmail){
        List<MoveRequest> moveRequests =  moveRequestRepository.findAllMoveRequestByOwner(ownerEmail);
        for(MoveRequest moveRequest: moveRequests){
            List<InventoryItem> inventoryItemList = inventoryItemRepository.findAllInventoryItemByMoveRequest(moveRequest.getMoveRequestId());
            List<InventoryItemGroup> inventoryItemGroups = new ArrayList<>();

            Map<String,List<String>> itemsAndCategoryMapping = new HashMap<>();
            for(InventoryItem inventoryItem : inventoryItemList){
                if(itemsAndCategoryMapping.containsKey(inventoryItem.getCategory())){
                    itemsAndCategoryMapping.get(inventoryItem.getCategory()).add(inventoryItem.getItemName());
                }
                else{
                    List<String> items = new ArrayList<>();
                    items.add(inventoryItem.getItemName());
                    itemsAndCategoryMapping.put(inventoryItem.getCategory(),items);
                }
            }
            for (Map.Entry<String,List<String>> entry :itemsAndCategoryMapping.entrySet()) {
                InventoryItemGroup inventoryItemGroup = new InventoryItemGroup();
                inventoryItemGroup.setItems(entry.getValue());
                inventoryItemGroup.setCategory(entry.getKey());
                inventoryItemGroups.add(inventoryItemGroup);
            }
            moveRequest.setItemInventory(inventoryItemGroups);
        }
        return moveRequests;
    }
}
