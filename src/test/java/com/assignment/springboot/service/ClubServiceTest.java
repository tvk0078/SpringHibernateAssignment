package com.assignment.springboot.service;

import com.assignment.springboot.entity.Club;
        import com.assignment.springboot.repository.ClubRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.context.SpringBootTest;
        import org.springframework.boot.test.mock.mockito.MockBean;
        import org.springframework.test.context.junit4.SpringRunner;

        import java.util.Optional;
        import java.util.stream.Collectors;
        import java.util.stream.Stream;

        import static org.junit.Assert.assertEquals;
        import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ClubServiceTest {
    @Autowired
    private ClubService clubService;

    @MockBean
    private ClubRepository clubRepository;

    @Test
     void getAllClubs() {
        when(clubRepository.findAll())
                .thenReturn(Stream.of(new Club("CAME"))
                        .collect(Collectors.toList()));
        assertEquals(1, clubService.findAll().size());
    }
    @Test
     void testFindById() {
        Club club = new Club(1, "CAME", null);

        when(clubRepository.findById(1)).thenReturn(Optional.of(club));

        assertEquals(club, clubService.findById(1));
    }

    @Test
     void testSaveClub() {
        Club club = new Club(1, "CAME", null);
        when(clubRepository.save(club)).thenReturn(club);

        assertEquals(club, clubService.save(club));
    }

    @Test
     void testDeleteClub() {
        Club club = new Club(1, "CAME", null);
        clubService.deleteById(1);
        verify(clubRepository, times(1)).deleteById(1);
    }
}