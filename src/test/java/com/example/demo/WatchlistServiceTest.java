package com.example.demo;

import com.example.demo.models.WatchlistItem;
import com.example.demo.repository.WatchlistRepository;
import com.example.demo.service.MovieRatingServiceLiveImpl;
import com.example.demo.service.WatchlistServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WatchlistServiceTest {

    @InjectMocks
    private WatchlistServiceImpl watchlistServiceImpl;

    @Mock
    private WatchlistRepository watchlistRepositoryMock;

    @Mock
    private MovieRatingServiceLiveImpl movieRatingServiceLiveImplMock;
    @Test
    public void testGetwatchlistItemsRatingFormOmdbServiceOverrideTheValueInItems() {

        //Arrange
        WatchlistItem item1 = new WatchlistItem("Star Wars", "7.7", "M" , "" );
        List<WatchlistItem> mockItems = Arrays.asList(item1);

        when(watchlistRepositoryMock.getList()).thenReturn(mockItems);
        when(movieRatingServiceLiveImplMock.getMovieRating(any(String.class))).thenReturn("10");

        //Act
        List<WatchlistItem> result = watchlistServiceImpl.getWatchlistItems();

        //Assert
        assertTrue(result.get(0).getRating().equals("10"));
    }
    @Test
    public void testGetWatchlistItemsReturnsAllItemsFromRepository() {

        //Arrange
        WatchlistItem item1 = new WatchlistItem("Star Wars", "7.7", "M" , "" );
        WatchlistItem item2 = new WatchlistItem("Star Treck", "8.8", "M" , "" );
        List<WatchlistItem> mockItems = Arrays.asList(item1, item2);

        when(watchlistRepositoryMock.getList()).thenReturn(mockItems);

        //Act
        List<WatchlistItem> result = watchlistServiceImpl.getWatchlistItems();

        //Assert
        assertTrue(result.size() == 2);
        assertTrue(result.get(0).getTitle().equals("Star Wars"));
        assertTrue(result.get(1).getTitle().equals("Star Treck"));
    }
}