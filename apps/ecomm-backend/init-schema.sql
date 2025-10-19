-- File: init-schema.sql
-- This script will create the ecommerce_schema if it doesn't exist
CREATE SCHEMA IF NOT EXISTS ecommerce_schema;

-- Optional: Verify the schema was created
SELECT * FROM pg_namespace;
