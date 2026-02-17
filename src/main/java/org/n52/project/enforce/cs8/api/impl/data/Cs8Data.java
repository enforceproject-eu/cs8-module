package org.n52.project.enforce.cs8.api.impl.data;

import java.time.LocalDate;

import org.locationtech.jts.geom.Point;

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
        name = "cs8_data")
public class Cs8Data {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cs8_data_generator")
    @SequenceGenerator(name = "cs8_data_generator", sequenceName = "cs8_data_seq", allocationSize = 1)
    private Integer id;
    
    @Column(
            name = "name")
    private String name;
    
    @Column(
            name = "name_1")
    private String name_1;
    
    @Column(
            name = "compartment_id")
    private int compartmentId;

    @Column(
            name = "pfba")
    private Double pfba;
    
    @Column(
            name = "pfpea")
    private Double pfpea;
    
    @Column(
            name = "pfhxa")
    private Double pfhxa;
    
    @Column(
            name = "pfhpa")
    private Double pfhpa;
    
    @Column(
            name = "pfoa")
    private Double pfoa;
    
    @Column(
            name = "pfna")
    private Double pfna;
    
    @Column(
            name = "pfda")
    private Double pfda;
    
    @Column(
            name = "pfunda")
    private Double pfunda;
    
    @Column(
            name = "pfdoa")
    private Double pfdoa;
    
    @Column(
            name = "pfbs")
    private Double pfbs;
    
    @Column(
            name = "pfpes")
    private Double pfpes;
    
    @Column(
            name = "pfhxs")
    private Double pfhxs;
    
    @Column(
            name = "pfhps" )
    private Double pfhps;
    
    @Column(
            name = "pfos" )
    private Double pfos;
    
    @Column(
            name = "6:2_fts" )
    private Double sixToTwo_fts;
    
    @Column(
            name = "4:2_fts" )
    private Double fourToTwo_fts;
    
    @Column(
            name = "biomass" )
    private Double biomass;
    
    @Column(
            name = "biomass x pfos" )
    private Double biomass_x_pfos;
    
    @Column(
            name = "total abov" )
    private Double total_abov;
    
    @Column(
            name = "date")
    private LocalDate date;

    @Column(
            name = "coordinate",
            columnDefinition = "geometry(Point,4326)")
    private Point coordinate;

    public Cs8Data() { }

    public Cs8Data(LocalDate date, Point coordinate) {
        this.date = date;
        this.coordinate = coordinate;
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

    public String getName_1() {
        return name_1;
    }

    public void setName_1(String name_1) {
        this.name_1 = name_1;
    }

    public int getCompartmentId() {
        return compartmentId;
    }

    public void setCompartmentId(int compartmentId) {
        this.compartmentId = compartmentId;
    }

    public Double getPfba() {
        return pfba;
    }

    public void setPfba(Double pfba) {
        this.pfba = pfba;
    }

    public Double getPfpea() {
        return pfpea;
    }

    public void setPfpea(Double pfpea) {
        this.pfpea = pfpea;
    }

    public Double getPfhxa() {
        return pfhxa;
    }

    public void setPfhxa(Double pfhxa) {
        this.pfhxa = pfhxa;
    }

    public Double getPfhpa() {
        return pfhpa;
    }

    public void setPfhpa(Double pfhpa) {
        this.pfhpa = pfhpa;
    }

    public Double getPfoa() {
        return pfoa;
    }

    public void setPfoa(Double pfoa) {
        this.pfoa = pfoa;
    }

    public Double getPfna() {
        return pfna;
    }

    public void setPfna(Double pfna) {
        this.pfna = pfna;
    }

    public Double getPfda() {
        return pfda;
    }

    public void setPfda(Double pfda) {
        this.pfda = pfda;
    }

    public Double getPfunda() {
        return pfunda;
    }

    public void setPfunda(Double pfunda) {
        this.pfunda = pfunda;
    }

    public Double getPfdoa() {
        return pfdoa;
    }

    public void setPfdoa(Double pfdoa) {
        this.pfdoa = pfdoa;
    }

    public Double getPfbs() {
        return pfbs;
    }

    public void setPfbs(Double pfbs) {
        this.pfbs = pfbs;
    }

    public Double getPfpes() {
        return pfpes;
    }

    public void setPfpes(Double pfpes) {
        this.pfpes = pfpes;
    }

    public Double getPfhxs() {
        return pfhxs;
    }

    public void setPfhxs(Double pfhxs) {
        this.pfhxs = pfhxs;
    }

    public Double getPfhps() {
        return pfhps;
    }

    public void setPfhps(Double pfhps) {
        this.pfhps = pfhps;
    }

    public Double getPfos() {
        return pfos;
    }

    public void setPfos(Double pfos) {
        this.pfos = pfos;
    }

    public Double getSixToTwo_fts() {
        return sixToTwo_fts;
    }

    public void setSixToTwo_fts(Double sixToTwo_fts) {
        this.sixToTwo_fts = sixToTwo_fts;
    }

    public Double getFourToTwo_fts() {
        return fourToTwo_fts;
    }

    public void setFourToTwo_fts(Double fourToTwo_fts) {
        this.fourToTwo_fts = fourToTwo_fts;
    }

    public Double getBiomass() {
        return biomass;
    }

    public void setBiomass(Double biomass) {
        this.biomass = biomass;
    }

    public Double getBiomass_x_pfos() {
        return biomass_x_pfos;
    }

    public void setBiomass_x_pfos(Double biomass_x_pfos) {
        this.biomass_x_pfos = biomass_x_pfos;
    }

    public Double getTotal_abov() {
        return total_abov;
    }

    public void setTotal_abov(Double total_abov) {
        this.total_abov = total_abov;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: " + id + ", ");
        sb.append("date: " + date + ", ");
        return sb.toString();
    }
}
