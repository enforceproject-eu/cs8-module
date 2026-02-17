CREATE TABLE IF NOT EXISTS public.cs8_compartments
(
    id int NOT NULL,
    name character varying(255),
    name_eng character varying(255),
    CONSTRAINT cs8_compartments_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS public.cs8_compartments_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 100
    CACHE 1;    

INSERT INTO public.cs8_compartments VALUES (1, 'ander', 'other');
INSERT INTO public.cs8_compartments VALUES (2, '7.zaad', 'seed');
INSERT INTO public.cs8_compartments VALUES (3, '1.grond', 'ground');
INSERT INTO public.cs8_compartments VALUES (4, '2.wortel', 'root');
INSERT INTO public.cs8_compartments VALUES (5, '3.hout', 'wood');
INSERT INTO public.cs8_compartments VALUES (6, '4.vezel', 'fiber');
INSERT INTO public.cs8_compartments VALUES (7, '5.blad', 'leaf');
INSERT INTO public.cs8_compartments VALUES (8, '6.bloem', 'flower');

CREATE TABLE IF NOT EXISTS public.cs8_data
(
    id int not null,
    name character varying(50),
    name_1 character varying(50),
    compartment_id int,
    coordinate geometry(Point,4326),
    pfba double precision,
    pfpea double precision,
    pfhxa double precision,
    pfhpa double precision,
    pfoa double precision,
    pfna double precision,
    pfda double precision,
    pfunda double precision,
    pfdoa double precision,
    pfbs double precision,
    pfpes double precision,
    pfhxs double precision,
    pfhps double precision,
    pfos double precision,
    "6:2_fts" double precision,
    "4:2_fts" double precision,
    biomass double precision,
    "biomass x pfos" double precision,
    "total abov" double precision,
    Date date,
    CONSTRAINT cs8_data_pkey PRIMARY KEY (id),
    CONSTRAINT cs8_compartments_fkey FOREIGN KEY (compartment_id)
        REFERENCES public.cs8_compartments (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS public.cs8_data_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 10000
    CACHE 1;    

CREATE OR REPLACE FUNCTION getCs8CompartmentName(id int)
RETURNS jsonb AS
$BODY$
      SELECT jsonb_build_object(
	           'compartment', property.name_eng
	          )
      FROM (SELECT o.name_eng FROM public.cs8_data as d 
	              join public.cs8_compartments as o on d.compartment_id = o.id 
                  where d.id = $1) property;
$BODY$
LANGUAGE SQL;


CREATE OR REPLACE FUNCTION ST_Cs8DataToGeoJson()
RETURNS jsonb AS
$BODY$
    SELECT jsonb_build_object(
        'type',     'FeatureCollection',
        'features', jsonb_agg(feature)
    )
    FROM (
      SELECT jsonb_build_object(
        'type',       'Feature',
        'id',         row.id,
        'geometry',   ST_AsGeoJSON(coordinate)::jsonb,
        'properties', to_jsonb(row) - 'id' - 'coordinate' - 'name' - 'name_1' - 'compartment_id' || getCs8CompartmentName(row.id)
      ) AS feature
      FROM (SELECT * FROM public.cs8_data) row) features;
$BODY$
LANGUAGE SQL;