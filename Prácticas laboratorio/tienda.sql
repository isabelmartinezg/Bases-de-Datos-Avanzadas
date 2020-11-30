-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.1
-- PostgreSQL version: 10.0
-- Project Site: pgmodeler.io
-- Model Author: ---


-- Database creation must be done outside a multicommand file.
-- These commands were put in this file only as a convenience.
-- -- object: new_database | type: DATABASE --
-- -- DROP DATABASE IF EXISTS new_database;
-- CREATE DATABASE new_database;
-- -- ddl-end --
-- 

-- object: public."Tienda" | type: TABLE --
-- DROP TABLE IF EXISTS public."Tienda" CASCADE;
CREATE TABLE public."Tienda"(
	"Id_tienda" integer NOT NULL,
	"Nombre" text,
	"Ciudad" text,
	"Barrio" text,
	"Provincia" text,
	CONSTRAINT "Tienda_pk" PRIMARY KEY ("Id_tienda")

);
-- ddl-end --
ALTER TABLE public."Tienda" OWNER TO postgres;
-- ddl-end --

-- object: public."Trabajador" | type: TABLE --
-- DROP TABLE IF EXISTS public."Trabajador" CASCADE;
CREATE TABLE public."Trabajador"(
	codigo_trabajador integer NOT NULL,
	"DNI" varchar(9),
	"Nombre" varchar,
	"Apellidos" varchar,
	"Puesto" varchar,
	"Salario" integer,
	"Id_tienda_Tienda" integer NOT NULL,
	CONSTRAINT "Trabajador_pk" PRIMARY KEY (codigo_trabajador),
	CONSTRAINT "DNI_UNIQUE" UNIQUE ("DNI")

);
-- ddl-end --
ALTER TABLE public."Trabajador" OWNER TO postgres;
-- ddl-end --

-- object: "Tienda_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Trabajador" DROP CONSTRAINT IF EXISTS "Tienda_fk" CASCADE;
ALTER TABLE public."Trabajador" ADD CONSTRAINT "Tienda_fk" FOREIGN KEY ("Id_tienda_Tienda")
REFERENCES public."Tienda" ("Id_tienda") MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: public."Ticket" | type: TABLE --
-- DROP TABLE IF EXISTS public."Ticket" CASCADE;
CREATE TABLE public."Ticket"(
	"N de tickect" integer NOT NULL,
	"Importe" integer,
	fecha date,
	"codigo_trabajador_Trabajador" integer,
	CONSTRAINT "Ticket_pk" PRIMARY KEY ("N de tickect")

);
-- ddl-end --
ALTER TABLE public."Ticket" OWNER TO postgres;
-- ddl-end --

-- object: "Trabajador_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Ticket" DROP CONSTRAINT IF EXISTS "Trabajador_fk" CASCADE;
ALTER TABLE public."Ticket" ADD CONSTRAINT "Trabajador_fk" FOREIGN KEY ("codigo_trabajador_Trabajador")
REFERENCES public."Trabajador" (codigo_trabajador) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public."Productos" | type: TABLE --
-- DROP TABLE IF EXISTS public."Productos" CASCADE;
CREATE TABLE public."Productos"(
	"Codigo de barras" varchar NOT NULL,
	"Nombre" text,
	"Tipo" varchar,
	"Descripcion" text,
	"Precio" integer,
	CONSTRAINT "Productos_pk" PRIMARY KEY ("Codigo de barras")

);
-- ddl-end --
ALTER TABLE public."Productos" OWNER TO postgres;
-- ddl-end --

-- object: public."Ticket_Productos" | type: TABLE --
-- DROP TABLE IF EXISTS public."Ticket_Productos" CASCADE;
CREATE TABLE public."Ticket_Productos"(
	"N de tickect_Ticket" integer NOT NULL,
	"Codigo de barras_Productos" varchar NOT NULL,
	"Cantidad" smallint,
	CONSTRAINT "Ticket_Productos_pk" PRIMARY KEY ("N de tickect_Ticket","Codigo de barras_Productos")

);
-- ddl-end --

-- object: "Ticket_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Ticket_Productos" DROP CONSTRAINT IF EXISTS "Ticket_fk" CASCADE;
ALTER TABLE public."Ticket_Productos" ADD CONSTRAINT "Ticket_fk" FOREIGN KEY ("N de tickect_Ticket")
REFERENCES public."Ticket" ("N de tickect") MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: "Productos_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Ticket_Productos" DROP CONSTRAINT IF EXISTS "Productos_fk" CASCADE;
ALTER TABLE public."Ticket_Productos" ADD CONSTRAINT "Productos_fk" FOREIGN KEY ("Codigo de barras_Productos")
REFERENCES public."Productos" ("Codigo de barras") MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: public."Tienda_Productos" | type: TABLE --
-- DROP TABLE IF EXISTS public."Tienda_Productos" CASCADE;
CREATE TABLE public."Tienda_Productos"(
	"Id_tienda_Tienda" integer NOT NULL,
	"Codigo de barras_Productos" varchar NOT NULL,
	"Stock" smallint,
	CONSTRAINT "Tienda_Productos_pk" PRIMARY KEY ("Id_tienda_Tienda","Codigo de barras_Productos")

);
-- ddl-end --

-- object: "Tienda_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Tienda_Productos" DROP CONSTRAINT IF EXISTS "Tienda_fk" CASCADE;
ALTER TABLE public."Tienda_Productos" ADD CONSTRAINT "Tienda_fk" FOREIGN KEY ("Id_tienda_Tienda")
REFERENCES public."Tienda" ("Id_tienda") MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: "Productos_fk" | type: CONSTRAINT --
-- ALTER TABLE public."Tienda_Productos" DROP CONSTRAINT IF EXISTS "Productos_fk" CASCADE;
ALTER TABLE public."Tienda_Productos" ADD CONSTRAINT "Productos_fk" FOREIGN KEY ("Codigo de barras_Productos")
REFERENCES public."Productos" ("Codigo de barras") MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --


