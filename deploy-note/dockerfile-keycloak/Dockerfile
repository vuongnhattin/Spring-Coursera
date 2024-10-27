FROM quay.io/keycloak/keycloak:22.0

ENV KEYCLOAK_ADMIN=admin
ENV KC_DB=postgres
ENV KC_DB_URL=jdbc:postgresql://host.docker.internal:5432/
ENV KC_DB_USERNAME=postgres

ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev"]