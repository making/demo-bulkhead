# Demo Bulkhead

max allowed calls for `/hello` endpoint is configured to `1`.

```
$ curl localhost:8080/hello -v
```

in another terminal

```
$ curl localhost:8080/hello -v
> GET /hello HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.64.1
> Accept: */*
> 
< HTTP/1.1 503 
< Content-Type: text/plain;charset=UTF-8
< Content-Length: 58
< Date: Tue, 08 Sep 2020 07:28:01 GMT
< Connection: close
< 
Bulkhead 'hello' is full and does not permit further calls
```

metrics

```
$ curl -s localhost:8080/actuator/prometheus | grep resilience4j
# HELP resilience4j_bulkhead_max_allowed_concurrent_calls The maximum number of available permissions
# TYPE resilience4j_bulkhead_max_allowed_concurrent_calls gauge
resilience4j_bulkhead_max_allowed_concurrent_calls{name="hello",} 1.0
# HELP resilience4j_bulkhead_available_concurrent_calls The number of available permissions
# TYPE resilience4j_bulkhead_available_concurrent_calls gauge
resilience4j_bulkhead_available_concurrent_calls{name="hello",} 0.0
```
