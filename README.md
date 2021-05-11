# 스프링 부트 JWT Parse 처리
```
1. jwt 토큰을 발행한다. 
  curl --location --request POST 'http://localhost:8080/api/create-jwt' \
  --header 'Content-Type: application/json' \
  --data-raw '{
      "userId" : 1,
      "name" : "정민영",
      "age" : 28
  }'
```

