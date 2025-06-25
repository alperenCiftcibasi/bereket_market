package com.market.Services.impl;

import com.market.Entities.Campaign;
import com.market.Entities.Product;
import com.market.Repository.CampaignRepository;
import com.market.Repository.ProductRepository;
import com.market.Dto.CampaignMapper;
import com.market.Dto.CampaignRequestDTO;
import com.market.Dto.CampaignResponseDTO;
import com.market.Services.ICampaignServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImplCampaignServices implements ICampaignServices {

    private final CampaignRepository campaignRepository;
    private final ProductRepository productRepository;
    private final CampaignMapper campaignMapper;

    public ImplCampaignServices(CampaignRepository campaignRepository,
                                ProductRepository productRepository,
                                CampaignMapper campaignMapper) {
        this.campaignRepository = campaignRepository;
        this.productRepository = productRepository;
        this.campaignMapper = campaignMapper;
    }

    @Override
    public CampaignResponseDTO createCampaign(CampaignRequestDTO dto) {
        Campaign campaign = campaignMapper.toEntity(dto);
        return campaignMapper.toDTO(campaignRepository.save(campaign));
    }

    @Override
    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }

    @Override
    public void addProductToCampaign(Long campaignId, Long productId) {
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        campaign.getProducts().add(product);
        campaignRepository.save(campaign);
    }

    @Override
    public void removeProductFromCampaign(Long campaignId, Long productId) {
        Campaign campaign = campaignRepository.findById(campaignId)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
        campaign.getProducts().removeIf(p -> p.getId().equals(productId));
        campaignRepository.save(campaign);
    }

    @Override
    public List<CampaignResponseDTO> getAllCampaigns() {
        return campaignRepository.findAll().stream()
                .map(campaignMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void setActive(Long id, boolean isActive) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
        campaign.setIsActive(isActive);
        campaignRepository.save(campaign);
    }

    // Yeni eklenen metod: sadece aktif kampanyaları döndürür
    @Override
    public List<CampaignResponseDTO> getAllActiveCampaigns() {
        return campaignRepository.findByIsActiveTrue().stream()
                .map(campaignMapper::toDTO)
                .collect(Collectors.toList());
    }
}
