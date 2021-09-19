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
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class StatusChangeTest {
        @Test
        public void checkIfUpdateStatusChangesTheReport() {
            // Arrange
            final String txtStatus = "IN_WACHT";
            EnumStatus status;
            ReportRepository mockReportRepository = Mockito.mock(ReportRepository.class);
            ReportService sut = new ReportService(mockReportRepository);

            Report report1 = new Report("5ebe886a80237718543143","Younes Hlalem", "P123456", new Date(), new Date()," Opdracht ", "00.09 PROJECTROOM",
                    "Raam", "Sluit raam","Aan de linkerkant");

            report1.setStatus(EnumStatus.VOLTOOID);
            status = EnumStatus.valueOf(txtStatus);

            Mockito.when(mockReportRepository.findById(report1.getId())).thenReturn(Optional.of(report1));

            sut.changeStatus(report1.getId(), status);

            assertEquals(report1.getStatus().toString(), txtStatus);
        }
}
