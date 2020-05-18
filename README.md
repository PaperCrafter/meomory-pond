# Meomory Pond
graduation project of team 99

## Project discription

This project is graduation project of team 99. Concept of this project is to connect art with web community service. If you create some posts at the web-app, lotus will be created at unity client and it will spawn some texts with post content. and If you create some comments at the web-app related with posts, A fish will be created at unity client and eat some texts spawend from the related lotus(post).

## Project Architecture
<img width="474" alt="architecture" src="https://user-images.githubusercontent.com/37043014/82174670-5bfa2980-990c-11ea-95e1-b6db29ec1b1a.png">

## Requirement

1. Spring boot Back-end web server / api server
2. [node.js socket server](https://github.com/PaperCrafter/memory-pond-socket-server) 
(transfer data to unity client)
3. unity client(will be uploaded)

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
0. go to javascript file and edit ip running socket server
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

#### UI 
-Flow Chart-
![flow chart final](https://user-images.githubusercontent.com/37043014/82179398-36bfe800-9919-11ea-823c-0e6969e86cac.png)

-UI Image-
|welcome page|login page|register page|
|-------|------|-------|
|<img width="195" alt="welcomepage" src="https://user-images.githubusercontent.com/37043014/82176566-18a2b980-9912-11ea-8982-723f8dd7a3b8.png">|<img width="200" alt="loginpage" src="https://user-images.githubusercontent.com/37043014/82176595-2fe1a700-9912-11ea-9edf-7dadc399b797.png">|<img width="201" alt="register" src="https://user-images.githubusercontent.com/37043014/82177119-9d420780-9913-11ea-8650-f142da537576.png">|

|main page|post page|write page|
|-------|------|-------|
|<img width="203" alt="mainpage" src="https://user-images.githubusercontent.com/37043014/82176624-3f60f000-9912-11ea-80bf-f8b39a88bc85.png">|<img width="199" alt="postpage" src="https://user-images.githubusercontent.com/37043014/82177401-48eb5780-9914-11ea-8e8f-7a436c8bd16d.png">|<img width="180" alt="questionpage" src="https://user-images.githubusercontent.com/37043014/82177427-5bfe2780-9914-11ea-9136-9f5c9a0e2e72.png">|

|tutorial begin page|tutorial end page|weight page|
|-------|------|-------|
|<img width="265" alt="tutorial begin" src="https://user-images.githubusercontent.com/37043014/82177675-37567f80-9915-11ea-9b25-830ba36beea3.png">|<img width="202" alt="tutorial end" src="https://user-images.githubusercontent.com/37043014/82178544-39b9d900-9917-11ea-8358-496cd2b05adb.png">|<img width="240" alt="weight" src="https://user-images.githubusercontent.com/37043014/82177934-d2e7f000-9915-11ea-8101-75abea6977db.png">|


-Game View-

<img width="1060" alt="KakaoTalk_20200514_172411733" src="https://user-images.githubusercontent.com/37043014/82179557-93230780-9919-11ea-983f-168a76ce285d.png">


## License
[MIT](https://choosealicense.com/licenses/mit/)
