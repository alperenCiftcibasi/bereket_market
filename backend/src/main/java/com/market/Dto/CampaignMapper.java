package com.market.Dto;

import com.market.Entities.Campaign;
import com.market.Dto.CampaignRequestDTO;
import com.market.Dto.CampaignResponseDTO;
import com.market.Entities.Product;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CampaignMapper {

    public Campaign toEntity(CampaignRequestDTO dto) {
        Campaign campaign = new Campaign();
        campaign.setName(dto.getName());
        campaign.setDescription(dto.getDescription());
        campaign.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true);
        return campaign;
    }

    public CampaignResponseDTO toDTO(Campaign campaign) {
        CampaignResponseDTO dto = new CampaignResponseDTO();
        dto.setId(campaign.getId());
        dto.setName(campaign.getName());
        dto.setDescription(campaign.getDescription());
        dto.setIsActive(campaign.getIsActive());
        dto.setProductIds(
            campaign.getProducts() == null
                ? null
                : campaign.getProducts().stream().map(Product::getId).collect(Collectors.toList())
        );
        return dto;
    }
}