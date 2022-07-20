package com.dinesh.oktaloginexample

import android.app.Activity
import android.content.Context
import com.okta.oidc.*
import com.okta.oidc.clients.sessions.SessionClient
import com.okta.oidc.clients.web.WebAuthClient
import com.okta.oidc.net.response.UserInfo
import com.okta.oidc.storage.SharedPreferenceStorage
import com.okta.oidc.util.AuthorizationException


class OktaManager(applicationContext: Context) {

    /**
     * Authorization client using chrome custom tab as a user agent.
     */
    private var webAuth: WebAuthClient

    /**
     * The authorized client to interact with Okta's endpoints.
     */
    private var sessionClient: SessionClient

    init {
//        val config = OIDCConfig.Builder()
//            .clientId("0oa5r75k1gkJkrpE65d7")
//            .discoveryUri("https://dev-38379248.okta.com")
//            .redirectUri("com.okta.dev-38379248:/callback")
//            .endSessionRedirectUri("com.okta.dev-38379248:/")
//            .scopes("openid", "profile", "offline_access")
//            .create()
        val config = OIDCConfig.Builder()
            .clientId("0oa5upbailCvAYSoJ5d7")
            .discoveryUri("https://dev-79231153.okta.com")
            .redirectUri("com.okta.dev-79231153:/callback")
            .endSessionRedirectUri("com.okta.dev-79231153:/")
            .scopes("openid", "profile", "offline_access")
            .create()
        webAuth = Okta.WebAuthBuilder()
            .withConfig(config)
            .withContext(applicationContext)
            .withCallbackExecutor(null)
            .withStorage( SharedPreferenceStorage(applicationContext))
//            .withEncryptionManager(DefaultEncryptionManager(applicationContext))
//            .setRequireHardwareBackedKeyStore(true)
            .setRequireHardwareBackedKeyStore(false)
            .create()
        sessionClient = webAuth.sessionClient
    }

    fun isAuthenticated(): Boolean {

        return sessionClient.isAuthenticated
    }

    fun registerWebAuthCallback(callback: ResultCallback<AuthorizationStatus, AuthorizationException>, activity: Activity) {
        webAuth.registerCallback(callback, activity)
    }

    fun registerUserProfileCallback(callback: RequestCallback<UserInfo, AuthorizationException>) {
        sessionClient.getUserProfile(callback)
    }

    fun signIn(activity: Activity, payload: AuthenticationPayload) {
        webAuth.signIn(activity, payload)
    }

    fun signOut(activity: Activity, callback: RequestCallback<Int, AuthorizationException>) {
        webAuth.signOut(activity, callback)
    }

    fun clearUserData() {
        sessionClient.clear()
    }
}