package org.n52.project.enforce.cs8.api.impl.compartments;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * <p>
 * Data DTO.
 * </p>
 *
 * @author Benjamin Pross (b.pross @52north.org)
 * @since 1.0.0
 */
@Entity
@Table(
        name = "cs8_compartments")
public class Cs8Compartments {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cs8_compartments_generator")
    @SequenceGenerator(name = "cs8_compartments_generator", sequenceName = "cs8_compartments_seq", allocationSize = 1)
    private Integer id;
    
    @Column(
            name = "name")
    private String name;
    
    @Column(
            name = "name_eng")
    private String nameEng;
    
    public Cs8Compartments() { }

    public Cs8Compartments(Integer id) {
        this.id = id;
    }

    public Cs8Compartments(Integer id, String name, String nameEng) {
        this(id);
        this.name = name;
        this.nameEng = nameEng;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: " + id + ", ");
        sb.append("name" + name + ", ");
        sb.append("nameEng" + nameEng);
        return sb.toString();
    }
}
