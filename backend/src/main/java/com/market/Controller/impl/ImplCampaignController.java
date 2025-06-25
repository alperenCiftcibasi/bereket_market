package com.market.Controller.impl;

import com.market.Dto.CampaignRequestDTO;
import com.market.Dto.CampaignResponseDTO;
import com.market.Services.ICampaignServices;
import com.market.Controller.ICampaignController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000") // React portu
@RestController
@RequestMapping("/api/admin/campaigns")
public class ImplCampaignController implements ICampaignController {

    private final ICampaignServices campaignServices;

    public ImplCampaignController(ICampaignServices campaignServices) {
        this.campaignServices = campaignServices;
    }

    @PostMapping
    public ResponseEntity<CampaignResponseDTO> createCampaign(@RequestBody CampaignRequestDTO dto) {
        return ResponseEntity.ok(campaignServices.createCampaign(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Long id) {
        campaignServices.deleteCampaign(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{campaignId}/products/{productId}")
    public ResponseEntity<Void> addProductToCampaign(@PathVariable Long campaignId, @PathVariable Long productId) {
        campaignServices.addProductToCampaign(campaignId, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{campaignId}/products/{productId}")
    public ResponseEntity<Void> removeProductFromCampaign(@PathVariable Long campaignId, @PathVariable Long productId) {
        campaignServices.removeProductFromCampaign(campaignId, productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/get")
    public ResponseEntity<List<CampaignResponseDTO>> getAllCampaigns() {
        return ResponseEntity.ok(campaignServices.getAllCampaigns());
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Void> setActive(@PathVariable Long id, @RequestParam boolean isActive) {
        campaignServices.setActive(id, isActive);
        return ResponseEntity.ok().build();
    }
}
