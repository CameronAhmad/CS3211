package edu.westga.cs3211.pirateinventory.services;


public class ServiceRegistry {

    private static final StockChangeService stockChangeService = new StockChangeService();
    private static final StorageService storageService = new StorageService();

    public static StockChangeService getStockChangeService() {
        return stockChangeService;
    }

    public static StorageService getStorageService() {
        return storageService;
    }
}
