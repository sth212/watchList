package com.example.demo.service;

import com.example.demo.exception.DuplicateTitleException;
import com.example.demo.models.WatchlistItem;

import java.util.List;

public interface WatchlistService {
    List<WatchlistItem> getWatchlistItems();

    int getWatchlistItemsSize();

    WatchlistItem findWatchlistItemById(Integer id);

    void addOrUpdateWatchlistItem(WatchlistItem watchlistItem) throws DuplicateTitleException;
}
