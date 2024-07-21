# how to use jsonnullable
mvn spring-boot:run

example output
```shell
ozkan@hp-envy-2021-i7-nvidia MINGW64 ~
$ curl -H "Accept: application/json" http://localhost:8080/test2
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100    35    0    35    0     0    112      0 --:--:-- --:--:-- --:--:--   111{"myBooleanField":{"present":true}}

```