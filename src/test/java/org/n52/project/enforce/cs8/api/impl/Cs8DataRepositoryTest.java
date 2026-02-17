package org.n52.project.enforce.cs8.api.impl;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.n52.project.enforce.cs8.api.impl.data.Cs8Data;
import org.n52.project.enforce.cs8.api.impl.data.Cs8DataRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Cs8DataRepositoryTest extends DBTest {

    @Autowired
    Cs8DataRepository cs5DataRepository;
    
    GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
    
    Random random = new Random();
    
    @Test
    void testCreateData() {
        
        double x = random.nextDouble(50d);
        double y = random.nextDouble(8d);
        Cs8Data csData = new Cs8Data();
        csData.setCompartmentId(1);
        csData.setCoordinate(new GeometryFactory(new PrecisionModel(), 4326).createPoint(new Coordinate(x, y)));
        cs5DataRepository.saveAndFlush(csData);
          
        
    }
    
}
