package edu.ap.facilitytoolspringboot;

import edu.ap.facilitytoolspringboot.models.Employee;
import edu.ap.facilitytoolspringboot.models.Report;
import edu.ap.facilitytoolspringboot.models.enums.EnumStatus;
import edu.ap.facilitytoolspringboot.repositories.EmployeeRepository;
import edu.ap.facilitytoolspringboot.repositories.ReportRepository;
import edu.ap.facilitytoolspringboot.services.EmployeeService;
import edu.ap.facilitytoolspringboot.services.ReportService;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EmployeeTest {
    @Test
    public void addAssignedReportIdShouldAddReportIdToEmployeeAndGetAllReportsShouldReturnCorrectReports() {
        // Arrange
        Employee employee = new Employee("5ebe886a8023", "Anil", "P123456");

        Report report = new Report("5ebe886a802377185431452", "Anil Sarioglu", "P123456", new Date(), new Date(), " Opdracht ", "00.09 PROJECTROOM",
                "Raam", "Barst in raam","Aan de linkerkant");

        List<Report> returnedReports;
        String actualReportId = report.getId();
        String expectedReportId = "";

        ReportRepository mockReportRepository = Mockito.mock(ReportRepository.class);
        ReportService reportService = new ReportService(mockReportRepository);

        EmployeeRepository mockEmployeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService sut = new EmployeeService(mockEmployeeRepository, reportService);

        Mockito.when(mockEmployeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        Mockito.when(mockReportRepository.findById(report.getId())).thenReturn(Optional.of(report));

        // Act
        sut.addAssignedReportId(employee.getId(), actualReportId);
        returnedReports = sut.getAllReports(employee.getId());
        expectedReportId = returnedReports.get(0).getId();

        // Assert
        assertThat(actualReportId, is(expectedReportId));

    }
}
