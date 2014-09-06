package com.stefankendall.BigLifts.billing.util;

import com.stefankendall.BigLifts.App;

public class IabService {
    private static IabService instance;
    private IabHelper iabHelper;
    private boolean loaded;
    private boolean successful;

    public static synchronized IabService getInstance() {
        if (instance == null) {
            instance = new IabService();
        }
        return instance;
    }

    public void start() {
        String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2/QQ47bghojItDNBkt9ZVTQW+ZESzUlBLp8Ac7jcjBsEX1eUi5z9gvVVtM8COLXqgnLkY7hcMiVAF7HLaM5SKId5ID9DkKJ50icblK/jkD+f4g7t9GrUJVHBnl6c0WTwhWc8ktmgbYrB5PdMxen/l9IhdFU7ufA2ksIMBgr7Xu6H7Q2CzJt70eRNHzofmx2CrNbD65fM0M3xwlBJtJDHsM0IOczirdXJFWxt/l9U2Ohp4BKXzflG4WfqjO/slICuDDjsclPHtEMLwcdJMR7/4tqKv5hQXDE3hZt71lHE5taA3PNM6W9skr3lWuc3gt+UlGFj5886kdg5ZwqwkHhlBwIDAQAB";
        this.iabHelper = new IabHelper(App.getContext(), base64EncodedPublicKey);
        this.iabHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                loaded = true;
                successful = result.isSuccess();
            }
        });
    }
}
