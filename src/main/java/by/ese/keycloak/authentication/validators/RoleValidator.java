package by.ese.keycloak.authentication.validators;

import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.authentication.AuthenticationFlowError;
import org.keycloak.authentication.Authenticator;
import org.keycloak.models.AuthenticatorConfigModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RoleModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.utils.KeycloakModelUtils;
import org.jboss.logging.Logger;

public class RoleValidator implements Authenticator {
    public static final RoleValidator SINGLETON = new RoleValidator();
    private static final Logger logger = Logger.getLogger(RoleValidator.class);

    public boolean matchCondition(AuthenticationFlowContext context) {
        UserModel user = context.getUser();
        RealmModel realm = context.getRealm();
        AuthenticatorConfigModel authConfig = context.getAuthenticatorConfig();

        if (user != null && authConfig!=null && authConfig.getConfig()!=null) {
            String requiredRole = authConfig.getConfig().get(RoleValidatorFactory.USER_ROLE);
            RoleModel role = KeycloakModelUtils.getRoleFromString(realm, requiredRole);
            if (role == null) {
                logger.errorv("Invalid role name submitted: {0}", requiredRole);
                return false;
            }
            return user.hasRole(role);
        }
        return false;
    }

    @Override
    public void authenticate(AuthenticationFlowContext authenticationFlowContext) {
        if(matchCondition(authenticationFlowContext)) {
            authenticationFlowContext.success();
        } else {
            authenticationFlowContext.failure(AuthenticationFlowError.INVALID_CREDENTIALS);
        }
    }

    @Override
    public void action(AuthenticationFlowContext context) {
        // Not used
    }

    @Override
    public boolean requiresUser() {
        return true;
    }

    @Override
    public boolean configuredFor(KeycloakSession keycloakSession, RealmModel realmModel, UserModel userModel) {
        return true;
    }

    @Override
    public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) {
        // Not used
    }

    @Override
    public void close() {
        // Does nothing
    }
}
