package com.rebersincar.kampusetkinlik.RestApi;

public class BaseManager {
    protected RestApi getRestApiClient()
    {
        RestApiClient restApiClient = new RestApiClient(BaseUrl.URL);
        return restApiClient.getRestApi();
    }

    protected RestApi getRestApiClientNotification()
    {
        RestApiClient restApiClient = new RestApiClient(BaseUrlNotification.URL);
        return restApiClient.getRestApi();
    }
}
