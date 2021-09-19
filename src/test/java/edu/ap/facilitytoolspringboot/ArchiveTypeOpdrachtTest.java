package edu.ap.facilitytoolspringboot;

import edu.ap.facilitytoolspringboot.models.Report;
import edu.ap.facilitytoolspringboot.models.enums.EnumStatus;
import edu.ap.facilitytoolspringboot.repositories.ReportRepository;
import edu.ap.facilitytoolspringboot.services.ReportService;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class ArchiveTypeOpdrachtTest {
    @Test
    public void getReportsForArchiveShouldReturnReportsWithTypeOpdrachtAndStatusBeeindigdOrGeannuleerdOrWNU() {
        // Arrange
        final String TYPE = "Opdracht";
        List<Report> reports = new ArrayList<>();
        List<Report> expectedReportsList = new ArrayList<>();
        List<Report> actualReportsList;
        ReportRepository mockReportRepository = Mockito.mock(ReportRepository.class);
        ReportService sut = new ReportService(mockReportRepository);

        Report report1 = new Report("Anil Sarioglu", "P123456", new Date(), " Opdracht ", new Date(), "00.09 PROJECTROOM",
                "Raam", "Sluit raam","Aan de linkerkant", EnumStatus.VOLTOOID, new Object());
        Report report2 = new Report("Jos Bedot", "P129656", new Date(), " Opdracht ", new Date(), "00.10 PROJECTROOM",
                "Muur", "Schilder muur","Aan de rechterkant", EnumStatus.WORDT_NIET_UITGEVOERD, new Object());
        Report report3 = new Report("Mamm Samm", "P124266", new Date(), " Opdracht ", new Date(), "00.08 ONTSPANNINGSRUIMTE",
                "Netwerk", "Installeer een access point","", EnumStatus.IN_BEHANDELING, new Object());
        Report report4 = new Report("Hadi Lan", "P847373", new Date(), " Defect ", new Date(), "00.17 SPREEKCEL",
                "Tuin", "Tak afgebroken","Eerste boom links", EnumStatus.GEANNULEERD, new Object());
        Report report5 = new Report("Como Lokko", "P365647", new Date(), " Defect ", new Date(), "00.11 PROJECTROOM",
                "Elektriciteit", "Vloerdoos is stuk","", EnumStatus.IN_WACHT, new Object());


        reports.add(report1);
        reports.add(report2);
        reports.add(report3);
        reports.add(report4);
        reports.add(report5);

        Mockito.when(mockReportRepository.findAll()).thenReturn(reports);

        expectedReportsList.add(report1);
        expectedReportsList.add(report2);

        // Act
        actualReportsList = sut.getReportsForArchive(TYPE);

        // Assert
        assertThat(actualReportsList, is(expectedReportsList));
    }
}
