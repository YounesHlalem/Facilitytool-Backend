package edu.ap.facilitytoolspringboot;

import edu.ap.facilitytoolspringboot.models.Report;
import edu.ap.facilitytoolspringboot.models.enums.EnumStatus;
import edu.ap.facilitytoolspringboot.repositories.ReportRepository;
import edu.ap.facilitytoolspringboot.services.ReportService;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class UpdateReportTest {
    @Test
    public void checkIfUpdateStatusChangesTheReport() {
        // Arrange
        final String newDescr = "Toilet loopt over";
        final String newCat = "Sanitair";
        Report copyreport = null;
        ReportRepository mockReportRepository = Mockito.mock(ReportRepository.class);
        ReportService sut = new ReportService(mockReportRepository);

        Report report1 = new Report("5ebe886a80237718543143","Younes Hlalem", "P123456", new Date(), new Date()," Opdracht ", "Toiletten",
                "Raam", "Toilet ontploft","Aan de linkerkant");
        report1.setStatus(EnumStatus.IN_WACHT);

        copyreport = report1;
        copyreport.setDescription(newDescr);
        copyreport.setCategory(newCat);

        Mockito.when(mockReportRepository.findById(report1.getId())).thenReturn(Optional.of(report1));

        sut.changeReport(report1.getId(), copyreport);

        assertEquals(report1.getDescription(), newDescr);
        assertEquals(report1.getCategory(), newCat);
        assertEquals(copyreport, report1);
    }
}
