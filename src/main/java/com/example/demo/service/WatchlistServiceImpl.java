package com.example.demo.service;



import java.util.List;
import java.util.Optional;

import com.example.demo.exception.DuplicateTitleException;
import com.example.demo.models.WatchlistItem;
import com.example.demo.repository.WatchlistRepository;
import org.springframework.stereotype.Service;


@Service
public class WatchlistServiceImpl implements WatchlistService {

    WatchlistRepository watchlistRepository = new WatchlistRepository();
    private MovieRatingServiceLive movieRatingServiceLiveImpl = new MovieRatingServiceLiveImpl();
    @Override
    public List<WatchlistItem> getWatchlistItems(){
        List<WatchlistItem> watchlistItems = watchlistRepository.getList();
        for (WatchlistItem watchlistItem : watchlistItems) {

            String rating = movieRatingServiceLiveImpl.getMovieRating(watchlistItem.getTitle());

            if (rating != null) {
                watchlistItem.setRating(rating);
            }
        }
        return watchlistItems;
    }

    @Override
    public int getWatchlistItemsSize() {
        return watchlistRepository.getList().size();
    }

    @Override
    public WatchlistItem findWatchlistItemById(Integer id) {
        return watchlistRepository.findById(id);
    }

    @Override
    public void addOrUpdateWatchlistItem(WatchlistItem watchlistItem) throws DuplicateTitleException {

        WatchlistItem existingItem = findWatchlistItemById(watchlistItem.getId());

        if (existingItem == null) {
            if (watchlistRepository.findByTitle(watchlistItem.getTitle())!=null) {
                throw new DuplicateTitleException();
            }
            watchlistRepository.addItem(watchlistItem);
        } else {
            existingItem.setComment(watchlistItem.getComment());
            existingItem.setPriority(watchlistItem.getPriority());
            existingItem.setRating(watchlistItem.getRating());
            existingItem.setTitle(watchlistItem.getTitle());
        }
    }
}
