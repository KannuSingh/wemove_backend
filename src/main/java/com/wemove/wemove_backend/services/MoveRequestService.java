package com.wemove.wemove_backend.services;

import com.wemove.wemove_backend.entities.*;
import com.wemove.wemove_backend.repositories.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MoveRequestService {


    private final MoveRequestRepository moveRequestRepository;

    private final MRStatusItemRepository mrStatusItemRepository;

    private final InventoryItemRepository inventoryItemRepository;

    private final PriceQuoteRepository priceQuoteRepository;

    public MoveRequestService(MoveRequestRepository moveRequestRepository , MRStatusItemRepository mrStatusItemRepository, InventoryItemRepository inventoryItemRepository, PriceQuoteRepository priceQuoteRepository) {
        this.moveRequestRepository = moveRequestRepository;
        this.mrStatusItemRepository = mrStatusItemRepository;
        this.inventoryItemRepository = inventoryItemRepository;
        this.priceQuoteRepository = priceQuoteRepository;
    }

    public MoveRequest createMoveRequest(MoveRequest moveRequest){
        System.out.println("MoveRequestService : "+moveRequest);
        MoveRequest savedMoveRequest = moveRequestRepository.save(moveRequest);

        if(savedMoveRequest != null ) {

            MRStatusItem mrStatusItem = new MRStatusItem();
            mrStatusItem.setMoveStatus(savedMoveRequest.getMoveRequestStatus());
            mrStatusItem.setMoveRequestId(savedMoveRequest.getMoveRequestId());
            mrStatusItem.setCreatedOn(new Date());
            mrStatusItemRepository.save(mrStatusItem);

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

    public List<MoveRequestDto> getAllCustomerMoveRequest(String ownerEmail){
        List<MoveRequestDto> moveRequestDtos = new ArrayList<>();
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
            List<PriceQuote> priceQuotes = this.priceQuoteRepository.findAllPriceQuoteByMoveRequestId(moveRequest.getMoveRequestId());
            MoveRequestDto moveRequestDto = new MoveRequestDto();
            moveRequestDto.setMoveRequest(moveRequest);
            moveRequestDto.setPriceQuotes(priceQuotes);
            moveRequestDtos.add(moveRequestDto);
        }
        return moveRequestDtos;
    }
    public List<MoveRequestDto> getAllCompletedMoveRequest(String email){
        List<MoveRequestDto> moveRequestDtos = new ArrayList<>();
        List<MoveRequest> moveRequests =  moveRequestRepository.findAllMoveRequestForStatusCompleted(email);
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
            List<PriceQuote> priceQuotes = this.priceQuoteRepository.findAllPriceQuoteByMoveRequestId(moveRequest.getMoveRequestId());
            MoveRequestDto moveRequestDto = new MoveRequestDto();
            moveRequestDto.setMoveRequest(moveRequest);
            moveRequestDto.setPriceQuotes(priceQuotes);
            moveRequestDtos.add(moveRequestDto);
        }
        return moveRequestDtos;
    }

    public List<MoveRequestDto> getAllMoveRequestDeliveries(){
        List<MoveRequestDto> moveRequestDtos = new ArrayList<>();
        List<MoveRequest> moveRequests =  moveRequestRepository.findAllMoveRequestForStatusActive();
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
            List<PriceQuote> priceQuotes = this.priceQuoteRepository.findAllPriceQuoteByMoveRequestId(moveRequest.getMoveRequestId());
            MoveRequestDto moveRequestDto = new MoveRequestDto();
            moveRequestDto.setMoveRequest(moveRequest);
            moveRequestDto.setPriceQuotes(priceQuotes);
            moveRequestDtos.add(moveRequestDto);
        }
        return moveRequestDtos;
    }


    public List<MoveRequestDto> getMyAllMoveRequestDeliveries(String moverEmail){
        List<MoveRequestDto> moveRequestDtos = new ArrayList<>();
        List<PriceQuote> priceQuotes = priceQuoteRepository.findPriceQuoteByMoverEmail(moverEmail);
        for(PriceQuote priceQuote : priceQuotes){


            Optional<MoveRequest> moveRequest = moveRequestRepository.findById(priceQuote.getMoveRequestId());

            moveRequest.ifPresentOrElse(moveRequest1 -> {
                List<InventoryItem> inventoryItemList = inventoryItemRepository.findAllInventoryItemByMoveRequest(moveRequest1.getMoveRequestId());
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
                moveRequest1.setItemInventory(inventoryItemGroups);




                MoveRequestDto moveRequestDto = new MoveRequestDto();
                List<PriceQuote> priceQuotes1 = priceQuoteRepository.findAllPriceQuoteByMoveRequestId(priceQuote.getMoveRequestId());
                moveRequestDto.setMoveRequest(moveRequest1);
                moveRequestDto.setPriceQuotes(priceQuotes1);
                moveRequestDtos.add(moveRequestDto);

            },() -> new RuntimeException("Some Error Occurred"));
        }


        return moveRequestDtos;
    }

    public boolean submitPriceQuote(PriceQuote priceQuote){
        PriceQuote priceQuote1 = this.priceQuoteRepository.save(priceQuote);
        updateMoveRequestStatus(priceQuote.getMoveRequestId(), MoveStatus.SUGGESTED);
        return true;
    }
    public List<PriceQuote> getAllPriceQuote(int requestId){
        return this.priceQuoteRepository.findAllPriceQuoteByMoveRequestId(requestId);
    }

    public List<PriceQuote> getPriceQuoteByMoverEmail(String moverEmail){
        return this.priceQuoteRepository.findPriceQuoteByMoverEmail(moverEmail);
    }

    public void acceptPriceQuote( String moverEmail, int moveRequestId){
         this.priceQuoteRepository.updatePriceQuoteStatusToAccepted(moverEmail,moveRequestId);
    }

    public void updateMoveRequestStatus(int moveRequestId, MoveStatus moveStatus){
        MRStatusItem mrStatusItem = new MRStatusItem();
        mrStatusItem.setMoveStatus(moveStatus);
        mrStatusItem.setMoveRequestId(moveRequestId);
        mrStatusItem.setCreatedOn(new Date());
        mrStatusItemRepository.save(mrStatusItem);
        this.moveRequestRepository.updateMoveRequestStatus(moveRequestId,moveStatus);
    }


    public void declinePriceQuote(String moverEmail, int moveRequestId) {
        this.priceQuoteRepository.updatePriceQuoteStatusToDeclined(moverEmail,moveRequestId);
    }

    public List<MRStatusItem> getMoveStatusTimeline(int moveRequestId){
        return mrStatusItemRepository.findAllForMoveRequest(moveRequestId);
    }
}
