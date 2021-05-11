# 스프링 부트 JWT Parse 처리

### 1. JWT Parse 로직
```
1. jwt 토큰을 발행한다.  
2. jwt 검증 로직은 먼저 Interceptor에서 토큰 유효성을 체크한다.
3. jwt 검증을 성공했으면 aop를 이용하여 인자 값이 UserDTO가 있으면 jwt payload를 파싱한다.
```

### 2. 토큰 발행

```
curl --location --request POST 'http://localhost:8080/api/create-jwt' \
--header 'Content-Type: application/json' \
--data-raw '{
    "userId" : 1,
    "name" : "정민영",
    "age" : 28
}'
```

### 3. 토큰 검증

```
curl --location --request GET 'http://localhost:8080/api/verify-jwt?name=%EC%A0%95%EB%AF%BC%EC%98%81&age=28' \
--header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEsIm5hbWUiOiLsoJXrr7zsmIEiLCJhZ2UiOjI4LCJzdWIiOiJ1c2VyIiwiZXhwIjoxNjIwNzI5NTgwfQ.Y1DHU-o2YC1DgpyQAWlGfu6hP8wM_weTM7yZjuxmCCk'
```
