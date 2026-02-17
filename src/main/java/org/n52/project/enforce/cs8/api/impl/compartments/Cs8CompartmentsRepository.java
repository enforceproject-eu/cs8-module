package org.n52.project.enforce.cs8.api.impl.compartments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * <p>
 * Cs8CompartmentsRepository repository.
 * </p>
 *
 * @author Benjamin Pross 
 * @since 1.0.0
 */
public interface Cs8CompartmentsRepository extends JpaRepository<Cs8Compartments, Integer> {
    
    @Query("select d from Cs8Compartments as d where d.name ilike :name")
    Cs8Compartments findByName(@Param("name") String name);
}
