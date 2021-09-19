package edu.ap.facilitytoolspringboot;

import edu.ap.facilitytoolspringboot.models.Report;
import edu.ap.facilitytoolspringboot.repositories.ReportRepository;
import edu.ap.facilitytoolspringboot.services.ReportService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
//import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class TaskTest {
    @MockBean
    ReportRepository mockReportRepository = Mockito.mock(ReportRepository.class);

    ReportService reportSystemUnderTest = new ReportService(mockReportRepository);

    private static Report report;
    private static List<Report> reportList;

    @Before
    public void setUp() {
        report = new Report("Younes", "P106204");
        reportList = new ArrayList<>();
        reportList.add(new Report("Amine", "P105106"));
        reportList.add(new Report("Kjenti", "P106809"));
    }

    //Task created test
    @Test
    public void checkTaskCreated(){
        //arrange
        List<Report> expectedReport = new ArrayList<Report>();
        expectedReport.add(new Report("Jordan", "P105106"));
        expectedReport.add(new Report("Anil", "P106809"));

        Mockito.doReturn(reportList).when(mockReportRepository).findAll();

        //act
        List<Report> result =  reportSystemUnderTest.getAllReports();

        //assert
        assertThat(result).containsExactlyInAnyOrderElementsOf(reportList);
        //Mockito.verify(mockReportRepository).saveAll(expectedReport);
    }

    //Task created test
    /*@Test
    public void checkTaskCreated(){
        //arrange
        int count = (int) mockReportRepository.count();

        //act
        mockReportRepository.save(report);
        int count2 = (int) mockReportRepository.count();
        count++;count2++;

        //assert
        assertEquals(count, count2);
    }*/

    @org.springframework.context.annotation.Configuration
    public static class ContextConfiguration {
    }
}
