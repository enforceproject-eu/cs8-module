package org.n52.project.enforce.cs8.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.n52.project.enforce.cs8.api.impl.compartments.Cs8Compartments;
import org.n52.project.enforce.cs8.api.impl.compartments.Cs8CompartmentsRepository;
import org.n52.project.enforce.cs8.api.impl.data.Cs8Data;
import org.n52.project.enforce.cs8.api.impl.data.Cs8DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Cs8Utils {

    private Cs8DataRepository cs8DataRepository;

    private Cs8CompartmentsRepository cs8CompartmentsRepository;

    DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd'T'HH:MM:SS");

    ZoneId zoneIdEuropeRome = ZoneId.of("Europe/Rome");

    GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    ObjectMapper objectMapper = new ObjectMapper();

    private static Logger LOG = LoggerFactory.getLogger(Cs8Utils.class);

    public Cs8Utils(Cs8DataRepository cs8DataRepository, Cs8CompartmentsRepository cs8CompartmentsRepository) {
        this.cs8DataRepository = cs8DataRepository;
        this.cs8CompartmentsRepository = cs8CompartmentsRepository;
    }

    public void readExcelFile(InputStream inputstream) throws IOException {
        File tmpExcelFile = File.createTempFile("excel", ".xlsx");
        IOUtils.copy(inputstream, new FileOutputStream(tmpExcelFile));
        readExcelFile(tmpExcelFile);
    }

    public void readExcelFile(File excelFile) throws IOException {
        Workbook workbook = WorkbookFactory.create(excelFile);
        Sheet sheet = workbook.getSheetAt(0);
        Row firstRow = sheet.getRow(0);
        int rowCount = sheet.getLastRowNum();
        int columnCount = firstRow.getLastCellNum();
        Cs8Data data = null;
        Row row = null;
        Cell cell = null;
        for (int i = 1; i <= rowCount; i++) {
            row = sheet.getRow(i);
            if (row == null) {
                LOG.info(String.format(
                        "Stop parsing excel file at row %d, which is reported null. Estimated row count: %d.", i,
                        rowCount));
                break;
            }
            data = new Cs8Data();
            for (int k = 0; k < columnCount; k++) {
                cell = row.getCell(k);
                if (cell == null) {
                    LOG.info(String.format("Cell %d is null for row %d. Skipping value.", k, i));
                    continue;
                }
                switch (k) {
                case 0:
                    String name = cell.getStringCellValue().trim();
                    data.setName(name);
                    break;
                case 1:
                    String name_1 = cell.getStringCellValue().trim();
                    data.setName_1(name_1);
                    break;
                case 2:
                    String compartmentName = cell.getStringCellValue();
                    if(compartmentName.equalsIgnoreCase("other")) {
                        data.setCompartmentId(1);
                        break;
                    }
                    Cs8Compartments compartment = cs8CompartmentsRepository.findByName(compartmentName);
                    int compartmentId = 1;
                    if(compartment != null) {
                        compartmentId = compartment.getId();
                    }
                    data.setCompartmentId(compartmentId);
                    break;
                case 3:
                    data.setCoordinate(this.createPoint(cell.getStringCellValue()));
                    break;
                case 4:
                    data.setPfba(cell.getNumericCellValue());
                    break;
                case 5:
                    data.setPfpea(cell.getNumericCellValue());
                    break;
                case 6:
                    data.setPfhxa(cell.getNumericCellValue());
                    break;
                case 7:
                    data.setPfhpa(cell.getNumericCellValue());
                    break;
                case 8:
                    data.setPfoa(cell.getNumericCellValue());
                    break;
                case 9:
                    data.setPfna(cell.getNumericCellValue());
                    break;
                case 10:
                    data.setPfda(cell.getNumericCellValue());
                    break;
                case 11:
                    data.setPfunda(cell.getNumericCellValue());
                    break;
                case 12:
                    data.setPfdoa(cell.getNumericCellValue());
                    break;
                case 13:
                    data.setPfbs(cell.getNumericCellValue());
                    break;
                case 14:
                    data.setPfpes(cell.getNumericCellValue());
                    break;
                case 15:
                    data.setPfhxs(cell.getNumericCellValue());
                    break;
                case 16:
                    data.setPfhps(cell.getNumericCellValue());
                    break;
                case 17:
                    data.setPfos(cell.getNumericCellValue());
                    break;
                case 18:
                    data.setSixToTwo_fts(cell.getNumericCellValue());
                    break;
                case 19:
                    data.setFourToTwo_fts(cell.getNumericCellValue());
                    break;
                case 20:
                    data.setBiomass(cell.getNumericCellValue());
                    break;
                case 21:
                    data.setBiomass_x_pfos(cell.getNumericCellValue());
                    break;
                case 22:
                    data.setTotal_abov(cell.getNumericCellValue());
                    break;
                default:
                    break;
                }
            }
            data = cs8DataRepository.saveAndFlush(data);
            LOG.info(String.format("Data with id %d added to cs8 data.", data.getId()));
        }
        workbook.close();
    }

    private Point createPoint(String pointAsString) {
        if (pointAsString != null) {
            String[] coordinateArray = pointAsString.split(",");
            if (coordinateArray.length == 2) {
                String latStrg = coordinateArray[1];
                String lngStrg = coordinateArray[0];
                if ((latStrg != null && !latStrg.isEmpty()) && (lngStrg != null && !lngStrg.isEmpty())) {
                    double lat = Double.parseDouble(latStrg);
                    double lng = Double.parseDouble(lngStrg);
                    return geometryFactory.createPoint(new Coordinate(lat, lng));
                }
            }
        }
        return geometryFactory.createPoint(new Coordinate(0, 0));
    }

}
