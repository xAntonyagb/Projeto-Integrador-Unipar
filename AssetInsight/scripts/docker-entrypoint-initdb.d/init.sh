#!/bin/bash
set -e

envsubst < /docker-entrypoint-initdb.d/database.sql.template > /docker-entrypoint-initdb.d/database.sql

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" < /docker-entrypoint-initdb.d/database.sql