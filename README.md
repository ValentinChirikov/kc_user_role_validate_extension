User role validation (initially forked from keycloak\examples\providers\authenticator)
========================

Provide the possibility to authorize only users who have specified role. 

Example authentication flow:
![Authentication flow](auth_flow.jpg "Authentication flow")

1. Build a package with :
mvn clean package

2. copy artifact from target folder to deployments folder of KeyCloak Server, in case of standalone : KEYCLOAK_DIR/standalone/deployments

3. add new "execution" in authentication flow under "Username Password Form" (or other form which authenticates user)

4. configure the "execution" role - Actions -> Config



