package org.n52.project.enforce.cs8.api.impl.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * <p>
 * Data repository.
 * </p>
 *
 * @author Benjamin Pross 
 * @since 1.0.0
 */
public interface Cs8DataRepository extends JpaRepository<Cs8Data, Integer> {
    
    /**
     * <p>
     * getGeoJson.
     * </p>
     * 
     * @return a {@link String} object
     */
    @Query("select ST_Cs8DataToGeoJson()")
    String getGeoJson();
}
