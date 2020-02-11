#!/bin/bash

set -e

if [ -z "$1" ]; then
  wait-for-it $DATABASE_HOST:${DATABASE_PORT:-3306} --timeout=30 --strict -- java -jar app.jar
fi

exec "$@"
