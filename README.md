# meomory-pond
graduation project of team 99

## project discription

This project is graduation project of team 99. Concept of this project is to connect art with web community service. If you create some posts at the web-app, lotus will be created at unity client and it will spawn some texts with post content. and If you create some comments at the web-app related with posts, A fish will be created at unity client and eat some texts spawend from the lotus.

## Requirement

1. Spring boot Back-end web server / api server
2. [node.js socket server](https://github.com/PaperCrafter/memory-pond-socket-server) 
(transfer data to unity client)
3. unity client

## Dependencies
```
Spring boot
Spring Security
JPA
thymeleaf
junit
```

## How to use

```
1. Spring boot Back-end web server / api server
./gradlew --exclude-task test;
2. change directory to build/libs and run jar background
nohup java -jar ~~.jar &
```

## Trouble shooting

```
1. if you encountered permission problem, use chmode to change permission of gradlew
2. 
```

## TODO

```
1. implement test codes
2. CICD
```

## Preview


## License
[MIT](https://choosealicense.com/licenses/mit/)