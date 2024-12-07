# UsersApi

All URIs are relative to *http://localhost/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createUser**](UsersApi.md#createUser) | **POST** /users | Create
[**deleteUser**](UsersApi.md#deleteUser) | **DELETE** /users/{id} | Delete user
[**getAllUsers**](UsersApi.md#getAllUsers) | **GET** /users | Get users list
[**getUserById**](UsersApi.md#getUserById) | **GET** /users/{id} | Get user
[**updateUser**](UsersApi.md#updateUser) | **PUT** /users/{id} | Update user


<a name="createUser"></a>
# **createUser**
> UserResponse createUser(body)

Create

Create new user

### Example
```java
// Import classes:
//import org.gfijalkowski.pba_lab_4.api.ApiClient;
//import org.gfijalkowski.pba_lab_4.api.ApiException;
//import org.gfijalkowski.pba_lab_4.api.Configuration;
//import org.gfijalkowski.pba_lab_4.api.auth.*;
//import org.gfijalkowski.pba_lab_4.api.controller.UsersApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure HTTP basic authorization: basicAuth
HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
basicAuth.setUsername("YOUR USERNAME");
basicAuth.setPassword("YOUR PASSWORD");

UsersApi apiInstance = new UsersApi();
CreateRequest body = new CreateRequest(); // CreateRequest | User object that has to be added
try {
    UserResponse result = apiInstance.createUser(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#createUser");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**CreateRequest**](CreateRequest.md)| User object that has to be added |

### Return type

[**UserResponse**](UserResponse.md)

### Authorization

[basicAuth](../README.md#basicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="deleteUser"></a>
# **deleteUser**
> deleteUser(id)

Delete user

Removes user

### Example
```java
// Import classes:
//import org.gfijalkowski.pba_lab_4.api.ApiClient;
//import org.gfijalkowski.pba_lab_4.api.ApiException;
//import org.gfijalkowski.pba_lab_4.api.Configuration;
//import org.gfijalkowski.pba_lab_4.api.auth.*;
//import org.gfijalkowski.pba_lab_4.api.controller.UsersApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: bearerAuth
ApiKeyAuth bearerAuth = (ApiKeyAuth) defaultClient.getAuthentication("bearerAuth");
bearerAuth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//bearerAuth.setApiKeyPrefix("Token");

UsersApi apiInstance = new UsersApi();
UUID id = new UUID(); // UUID | 
try {
    apiInstance.deleteUser(id);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#deleteUser");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getAllUsers"></a>
# **getAllUsers**
> UserListResponse getAllUsers()

Get users list

Gets all users data

### Example
```java
// Import classes:
//import org.gfijalkowski.pba_lab_4.api.ApiClient;
//import org.gfijalkowski.pba_lab_4.api.ApiException;
//import org.gfijalkowski.pba_lab_4.api.Configuration;
//import org.gfijalkowski.pba_lab_4.api.auth.*;
//import org.gfijalkowski.pba_lab_4.api.controller.UsersApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure HTTP basic authorization: basicAuth
HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
basicAuth.setUsername("YOUR USERNAME");
basicAuth.setPassword("YOUR PASSWORD");

UsersApi apiInstance = new UsersApi();
try {
    UserListResponse result = apiInstance.getAllUsers();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#getAllUsers");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**UserListResponse**](UserListResponse.md)

### Authorization

[basicAuth](../README.md#basicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getUserById"></a>
# **getUserById**
> UserResponse getUserById(id)

Get user

Gets specified user data

### Example
```java
// Import classes:
//import org.gfijalkowski.pba_lab_4.api.ApiClient;
//import org.gfijalkowski.pba_lab_4.api.ApiException;
//import org.gfijalkowski.pba_lab_4.api.Configuration;
//import org.gfijalkowski.pba_lab_4.api.auth.*;
//import org.gfijalkowski.pba_lab_4.api.controller.UsersApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure HTTP basic authorization: basicAuth
HttpBasicAuth basicAuth = (HttpBasicAuth) defaultClient.getAuthentication("basicAuth");
basicAuth.setUsername("YOUR USERNAME");
basicAuth.setPassword("YOUR PASSWORD");

UsersApi apiInstance = new UsersApi();
UUID id = new UUID(); // UUID | 
try {
    UserResponse result = apiInstance.getUserById(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#getUserById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |

### Return type

[**UserResponse**](UserResponse.md)

### Authorization

[basicAuth](../README.md#basicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="updateUser"></a>
# **updateUser**
> UserResponse updateUser(id, body)

Update user

Update user data

### Example
```java
// Import classes:
//import org.gfijalkowski.pba_lab_4.api.ApiClient;
//import org.gfijalkowski.pba_lab_4.api.ApiException;
//import org.gfijalkowski.pba_lab_4.api.Configuration;
//import org.gfijalkowski.pba_lab_4.api.auth.*;
//import org.gfijalkowski.pba_lab_4.api.controller.UsersApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: bearerAuth
ApiKeyAuth bearerAuth = (ApiKeyAuth) defaultClient.getAuthentication("bearerAuth");
bearerAuth.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//bearerAuth.setApiKeyPrefix("Token");

UsersApi apiInstance = new UsersApi();
UUID id = new UUID(); // UUID | 
UpdateRequest body = new UpdateRequest(); // UpdateRequest | 
try {
    UserResponse result = apiInstance.updateUser(id, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#updateUser");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | [**UUID**](.md)|  |
 **body** | [**UpdateRequest**](UpdateRequest.md)|  |

### Return type

[**UserResponse**](UserResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

