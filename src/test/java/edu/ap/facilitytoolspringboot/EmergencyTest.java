package edu.ap.facilitytoolspringboot;

import edu.ap.facilitytoolspringboot.models.Emergency;
import edu.ap.facilitytoolspringboot.models.Report;
import edu.ap.facilitytoolspringboot.repositories.EmergencyRepository;
import edu.ap.facilitytoolspringboot.repositories.ReportRepository;
import edu.ap.facilitytoolspringboot.services.EmergencyService;
import edu.ap.facilitytoolspringboot.services.ReportService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmergencyTest {

    @MockBean
    EmergencyRepository mockEmergencyRepository = Mockito.mock(EmergencyRepository.class);

    EmergencyService emergencySystemUnderTest = new EmergencyService(mockEmergencyRepository);

    @Mock
    private static Emergency emergency;

    private static List<Emergency> emergencyList;

    @Before
    public void setUp() {
        emergency = new Emergency("Tom Welling","tom.welling@ap.be");
        emergencyList = new ArrayList<>();
        emergencyList.add(new Emergency("Tom Cruise", "tom.cruise@ap.be"));
        emergencyList.add(new Emergency("Tom Hanks", "tom.hanks@ap.be"));
    }


    @Test
    public void shouldReturnCorrectAmountOfEmergencyitems(){
        //arrange
        List<Emergency> expectedEmergency = new ArrayList<Emergency>();
        expectedEmergency.add(new Emergency("Selena Gomez", "P105106"));
        expectedEmergency.add(new Emergency("Ariana Grande", "P106809"));

        Mockito.doReturn(emergencyList).when(mockEmergencyRepository).findAll();

        //act
        List<Emergency> result =  emergencySystemUnderTest.getAllEmergencies();

        //assert
        assertThat(result).containsExactlyInAnyOrderElementsOf(emergencyList);
        //Mockito.verify(mockEmergencyRepository).saveAll(expectedEmergency);
    }


    @Test
    public void findAddedEmergencies() {
        //act
        when(mockEmergencyRepository.findAll()).thenReturn(emergencyList);
        List<Emergency> expectedList = emergencySystemUnderTest.getAllEmergencies();

        //assert
        assertEquals(2, expectedList.size());
    }

}
