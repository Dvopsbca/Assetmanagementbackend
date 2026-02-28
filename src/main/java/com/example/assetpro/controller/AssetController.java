package com.example.assetpro.controller;
import com.example.assetpro.model.Asset;
import com.example.assetpro.repository.AssetRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
@CrossOrigin(origins = "http://localhost:3000")
public class AssetController {

    private final AssetRepository assetRepository;

    public AssetController(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    // ✅ NEW – Root endpoint (added only this)
    @GetMapping("/")
    public String home() {
        return "AssetPro Backend is Running Successfully!";
    }

    // READ – get all assets
    @GetMapping
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    // CREATE – add new asset
    @PostMapping
    public Asset createAsset(@RequestBody Asset asset) {
        return assetRepository.save(asset);
    }

    // UPDATE – update asset
    @PutMapping("/{id}")
    public Asset updateAsset(@PathVariable Long id, @RequestBody Asset asset) {
        Asset existing = assetRepository.findById(id).orElseThrow();

        existing.setAssetName(asset.getAssetName());
        existing.setAssetType(asset.getAssetType());
        existing.setAssetCategory(asset.getAssetCategory());
        existing.setSerialNumber(asset.getSerialNumber());
        existing.setModelNumber(asset.getModelNumber());
        existing.setPurchaseDate(asset.getPurchaseDate());
        existing.setPurchaseCost(asset.getPurchaseCost());
        existing.setVendorName(asset.getVendorName());
        existing.setAssetStatus(asset.getAssetStatus());
        existing.setAssignedTo(asset.getAssignedTo());
        existing.setLocation(asset.getLocation());
        existing.setWarrantyExpiryDate(asset.getWarrantyExpiryDate());

        return assetRepository.save(existing);
    }

    // DELETE – delete asset
    @DeleteMapping("/{id}")
    public void deleteAsset(@PathVariable Long id) {
        assetRepository.deleteById(id);
    }
}
