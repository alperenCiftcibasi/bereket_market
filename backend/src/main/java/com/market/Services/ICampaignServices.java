package com.market.Services;

import com.market.Dto.CampaignRequestDTO;
import com.market.Dto.CampaignResponseDTO;
import java.util.List;

public interface ICampaignServices {
    CampaignResponseDTO createCampaign(CampaignRequestDTO dto);
    void deleteCampaign(Long id);
    void addProductToCampaign(Long campaignId, Long productId);
    void removeProductFromCampaign(Long campaignId, Long productId);
    List<CampaignResponseDTO> getAllCampaigns();
    void setActive(Long id, boolean isActive);
}
