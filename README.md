User role validation (initially forked from keycloak\examples\providers\authenticator)
========================

Example authentication flow:
![Authentication flow](auth_flow.jpg "Authentication flow")

1. Build a package with :
mvn clean package

2. copy artifact from target folder to deployments folder of KeyCloak Server, in case of standalone : KEYCLOAK_DIR/standalone/deployments