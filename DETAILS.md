# Age Calculator

A Simple API for calculating the age in years from a given timestamp.

## Features

- Get Age from Provided Timestamp
- Rate Limiting (With appropriate response headers)
- Basic Error Handling

## How Does This Work
The age in years is calculated by the API by making a `GET` request to the `/howold` endpoint of the API, and 
supplying a valid timestamp to the `dob` query parameter:
```bash
curl {BASE_URL}/howold?dob={VALID_NUMERIC_TIMESTAMP}
```


### Local Environment

```bash
curl localhost:8080/howold?dob=236504400000
```

### Live Demo

```bash
curl https://ageinyrs.herokuapp.com/howold?dob=236504400000
```


## Design & Implementation Summary

- Java & Spring Boot tech stack
- MVC architecture
- [Token bucket](https://en.wikipedia.org/wiki/Token_bucket) algorithm for rate-limiting
- Rate-limit bucket caching using ConcurrentHashMap
