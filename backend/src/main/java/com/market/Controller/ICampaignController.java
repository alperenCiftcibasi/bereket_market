package com.market.Controller;

import com.market.Dto.CampaignRequestDTO;
import com.market.Dto.CampaignResponseDTO;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ICampaignController {
    ResponseEntity<CampaignResponseDTO> createCampaign(CampaignRequestDTO dto);
    ResponseEntity<Void> deleteCampaign(Long id);
    ResponseEntity<Void> addProductToCampaign(Long campaignId, Long productId);
    ResponseEntity<Void> removeProductFromCampaign(Long campaignId, Long productId);
    ResponseEntity<List<CampaignResponseDTO>> getAllCampaigns();
    ResponseEntity<Void> setActive(Long id, boolean isActive);
}
