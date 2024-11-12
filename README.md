<div align="center">
<img src="https://github.com/user-attachments/assets/a01db6f4-38cb-4f72-b7f6-92fb14d0fc60" />
</div>

# 🔖 목차

> ## [✨ Doctor-Fish 프로젝트 소개](#-프로젝트-소개-notion)
>
> ## [👥 팀원 소개](#-팀원-소개-1)
>
> ## [🛠 개발 도구](#-개발-도구-1)
>
> ## [📄 API 명세서 ERD 설계도](#-API-명세서-ERD-설계도-1)
>
> ## [🤝 협업 방식](#-협업-방식-1)
>
> ## [📚 프로젝트 진행 상황 관리](#-프로젝트-진행-상황-관리-1)
>
> ## [🔍 브랜치 전략](#-브랜치-전략-1)
>
> ## [✔ 컨벤션](#-컨벤션-1)
>
> ## [✨ 화면 구현 및 코드 리뷰](#-화면-구현-및-코드-리뷰-1)
>
> ## [💡 느낀점](#-느낀점-1)

<br/>
<br/>

## ✨ Doctor-Fish 프로젝트 소개 [Notion](https://www.notion.so/Doctor-fish-Project-9f3d1aeb38044644b760dce23112bd2a?pvs=4)

## 🌟 제목

-  병원 어플 MEDIBOOK 개발

## 🚀 목적

-  배운 내용 복습, 협업에 대한 이해

## 📆 제작 기간

-  2024.09.30 ~ 2024.11.13

-  관리자 로그인
-  관리자 메뉴

    >  전체 예약 & 당일 예약

    >  리뷰 및 댓글 관리

    >  회원 관리

    >  관리자 추가(회원가입)

    >  연차 신청 & 연차 관리

    >  의사 정보 수정 (소개글 및 약력)

    >  공지사항

    >  설정(준비중)

-  사용자 로그인 & 회원가입
-  사용자 메뉴

    >  예약 신청 & 예약 조회

    >  공지사항 및 예약 알림

    >  리뷰 작성 & 리뷰 좋아요 & 댓글 작성 

    >  의사 정보 

    >  사용자 정보 수정

    >  내 작성 리뷰 & 댓글 조회  -> 삭제 및 수정 가능

<br/>
<br/>

## 👥 팀원 소개 및 역할 분담

>  **팀장: 백승주**
-  사용자 페이지 CSS
-  관리자 페이지 CSS
-  ppt 제작
-  최종 발표

>  **팀원: 성창해**
- 사용자 페이지 api 기능 추가
- 관리자 페이지 api 기능 추가

>  **팀원: 김해민**
- 백엔드 로그인, 회원가입 기능 구현
- 백엔드 전반적인 CRUD 기능 구현
- 검색 기능
- ERD 작성

>  **팀원: 김지현**
- 공지사항 CRUD 기능 구현
- 백엔드 의사 삭제 기능 구현
- 백엔드 예약 정보 수정 기능 구현
- 백엔드 공지사항 조회 수정 기능 구현
- 백엔드 관리자 리뷰 단건 조회, 관리자 본인 정보 조회, 유저 리뷰 단건 조회 기능 구현

>  **공동작업**
-  API명세서 작성
-  DB 테이블 구조 구성

<br/>
<br/>

## **🛠 개발 도구**

### FrontEnd

<p>
<img src="https://img.shields.io/badge/react-%2320232a.svg?style=for-the-badge&logo=react&logoColor=%2361DAFB"> 
<img src="https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E"> 
<img src="https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white"> 
<img src="https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white"> 
<img src="https://img.shields.io/badge/Visual%20Studio%20Code-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white">
</p>

### BackEnd

<p>
<img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white"> 
<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white">
<img src="https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white">
</p>

### DataBase
<p>
    <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white"/>
    <img src="https://img.shields.io/badge/firebase-a08021?style=for-the-badge&logo=firebase&logoColor=ffcd34">
</p>

### SCM & Deployment

<p>
<img src="https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white">
</p>

### Library

- ### BackEnd
    <p>
    <img src="https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens">
    <img src="https://img.shields.io/badge/MyBatis-271e1f?style=for-the-badge&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAACXBIWXMAAAsTAAALEwEAmpwYAAABuElEQVR4nO3US4hPcRQH8H9RI+Wx8FohkVfkUdQkC5FXxs6WhZSysSBCTWyRZpryWDHZi7LxSGRBKUtKMmGiRB6ZIvPRqbP4u917/zP/maXv6t77u+f7Ped8z/k1GiMANuI29mJuY7yBM/7F2bGQLcR2TMR8rMVsHMJd/MSftirBKgwrx03Mwv1839BuBdHnAfzCezzH1yR9gjv53IdH+IR3uIWudkXn4G0Sf8GQapxuV2QLvmENJqRXXTiGG0k+lAmsrCLpRTc2Y2rJ+YqKuGhVYFmrLPubSh1ODx7jepSOzoq4+E8mNg+TyxKMH1fnCNahP1pUiNuKF2n2ASzBDxwuE7moNQ5iJqZVVLQYr7G+7HA6XrYQuIxd+I2HOIUdafyUaA/WFSttFlmEjzUCx7G/5rynzuiOVI9snpYED2R77tUI7C4jjrKOYjAn6Fx+25bP13ACM3L2qxDxHc3Em3A1l6iID7iAPblo0ZYH6nGkmPl544dncQuXtWcfvo+RfBALWl1ol3L0RotXWF5JXhBamstW5kkRkcyV2J0RkReEJmFnehTGvsHnvLZjuU7GKI+a+D8aBfwFL0hrRFV1ciwAAAAASUVORK5CYII=&logoColor=white">
    <img src="https://img.shields.io/badge/Lombok-ca0124?style=for-the-badge&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAACXBIWXMAAAsTAAALEwEAmpwYAAAEeElEQVR4nO2aW4hVVRjHP01HzZJGJcpuU6l00Uqzgl4sCrqgDz5IZZjZQxlEaKBJWVA9pEEG3QkqESrtAqWhY+WDQZBpF2ys1DLz0s3spqU2Nr/42GvR19eZaVpzBtaZs39wmLPP3vs/a+219ndbS6SkpKSkpKSk1gEOA14H/gBukXoDmMHfvCf1BHAU8L15AHOlngAeNJ3/Augv9QIwAjhoHsAkqSeAl03nV0tPAjgDuBt4E9gItAAXm/NnAW2h8/r3HOkJAKcElxY7Z1lmrnvE/L5cegLAJGAflTkE3GCu3WnOfQyMlFoGmBw6GWkFloTfTwcGmWsHVHhAvwDjpRYBTnMj/2lH7zQwtJ1Zshc4U2oNYIXr/OBOhL77jQHUjkc+AHpLrQCMcu/52Z2870Vz33bgN3N8tdQKwP2m4Uv/x30nAdvCfa8C9xidt6RWoAhfIxPduaOBdcAmf87kAZcBhwPHGNeps6FBcgcYZzr/M9DPnZ/mjNzzQFMHel+aa0+U3AEWmAYvrnB+mOtUdI865a8HRodZol5kivMkx0nOAL2Are1Nf3PdIOCpdiLD9mjNPisELu1o+le4/oIw8n924gF8KLkDrDQNfiMYLv3b9z/uawJuDpng2mBE1VD+aPRulBrw/W2hsTqi35nGj0rQ09kR+QkYKDkDLDINft98/wrok6C32GgslJyhmMK2imOt/KwEPfUCB8L9OqtGSM4AL5kOf+YM4ZEJevOMRrPkDDDeNLbNjf78BL2BwG6jMUFyhSKD+8g0dr35/quGsgmas41GS9ZZIIXrivwO7DHHdyToaVHkW6MxWXIFODWMcuRdl8oOSNCcaTQ+yXb0gb4hYLGuzkZz1yVo9gd2GY1rJFeA+1yMriMeWa05QRct/2a1L5Kx1T9kGvuOM3xNCZrDXAnsKskRYLhzUS0uo0uK14EXjMbbKTOo2wEGhypOZI8zgs2JU3+i0dCZNUZyA+gHrDEN3e8ehhrBoQm6jW4x5CHJDaCPFjZNI3XKL3P+f2xi8URrAZHPtQ4oOQE0AK/wT+4EnjPH0xK1b3NTP68VIAq/vNx1/olw7njgMWB6ovYVwX1G7pKcoEhIVrnOP1mNsrS+Ls7lNWcV8VGEuBtc51eFabqxK++prg26SpHqNUouUCxI2IRGmQ+8Zo7PTdQ+D/jB6HytK0GSAxQW+XYX4WmF56Zw/vyQ9i5KCVGBqcFbRL5JqRN256akNW7UdXQurIK2prePOu1tWZS4KPz7bDcyMbY/tgr6lwBbnLYWTE6oTg+6bujWuca1hve9oQrl8aUVVn2ezmJlh6Liandhxg0IY7ug2Vt3e4WFDb/Ko//rWskFim1qNqafm1i31/f7IuABYAf/Rh/EM8AQyQngWdPIe0NCYj9HmDC4MdT8dUPTlcCtwMMhZY11+0odX5KNlfcAc+ge1HMs1IclOQMMcSWsVNrCXj7d3Hh5tiWsSgAnh50ZO8MKrP3EjUkHwvHWELLq9tbHQxY3ISX/LykpKSkpKZHu5C8+ETRdu+5D6AAAAABJRU5ErkJggg==&logoColor=white">
    </p>

- ### FrontEnd
    <p>
    <img src="https://img.shields.io/badge/-React%20Query-FF4154?style=for-the-badge&logo=react%20query&logoColor=white">
    <img src="https://img.shields.io/badge/React_Router-CA4245?style=for-the-badge&logo=react-router&logoColor=white">
    <img src="https://img.shields.io/badge/chart.js-F5788D.svg?style=for-the-badge&logo=chart.js&logoColor=white">
    </p>

### 형상 관리
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">

<br/>

### ⛓️ pom.xml(Back-end)

| 라이브러리                          | 용도                                  |
| ----------------------------------- | ------------------------------------- |
| lombok                              | 어노테이션(@)으로 코드 자동 생성      |
| spring-boot-starter-security        | spring security 적용                  |
| spring-boot-starter-mail            | 이메일 인증                          |
| spring-boot-starter-validation      | 입력 값 검증                          |
| spring-boot-starter-web             | Spring Web 프로젝트에 필수 라이브러리 |
| spring-boot-starter-test            | 프로젝트 작동을 테스트하는 역할       |
| mybatis-spring-boot-starter         | 백엔드에서 MyBatis 문법 적용          |
| spring-boot-devtools                | 빠른 재시작                           |
| mysql-connector-java                | DB인 MySQL과 연결                     |
| jjwt-api / jjwt-impl / jjwt-jackson | JWT(JSON Web Token) 활용              |
| spring-boot-starter-oauth2-client   | Oauth2 인증                           |
| spring-boot-starter-aop             | spring aop 적용                       |

### ⛓️ node_modules(Front-end)

| 모듈 이름                                                                   | 용도                                       |
| --------------------------------------------------------------------------- | ------------------------------------------ |
| emotion/react, emotion/styled                                               | 리액트 내 css 적용                         |
| testing-library/jest-dom, testing-library/react, testing-library/user-event | 리액트 dom 테스트                          |
| axios                                                                       | 서버로 요청을 보내 통신                    |
| firebase                                                                    | 저장된 이미지를 업로드                     |
| react-dom, react-router-dom                                                 | 주소 요청 발생 시 각 페이지로 이동         |
| react-calendar                                                              | 달력을 커스텀하여 사용                     |
| react-icons                                                                 | 리액트 아이콘을 불러와 사용                |
| react-query                                                                 | 서버 데이터 동기화(fetching, caching) 지원 |
| react-quill                                                                 | 글 작성 요소로 커스텀하여 사용             |
| react-js-pagination                                                         | 여러개의 컨텐츠를 여러개의 페이지로 이동    |
| react-select                                                                | select 요소를 쉽게 사용                    |
| react-modal                                                                 | 페이지를 modal창으로 사용                   |
| react, react-scripts                                                        | 리액트 라이브러리 적용                     |
| chart.js                                                                    | 가져온 데이터를 차트로 시각화하는 역할     |
| recoil                                                                      | 전역 상태 관리                             |
| styled-reset                                                                | 초기 CSS 스타일 재설정                     |
| sweetalert2                                                                 | 알림창 구현                                |
| uuid                                                                        | 고유 아이디 값을 생성하는 함수 제공        |

<br/>
<br/>

## 📄 API 명세서 ERD 설계도
### 📄 API 명세서
### 사용자 관련 API
![사용자API명세서](https://github.com/user-attachments/assets/9a890b17-efa2-4c6d-b355-582d60a920da)

### 관리자 관련 API
![관리자API명세서](https://github.com/user-attachments/assets/95dfb748-d030-4609-ade0-bddc4bdecbed)

### 📐 ERD 설계도
![ERD](https://github.com/user-attachments/assets/f8814789-2e6a-476c-8379-6f163ec62673)

<br/>
<br/>

## 📋 메뉴 구조도

### 🧑‍🤝‍🧑 사용자 메뉴 구조도

![사용자 메뉴 구조도](https://github.com/user-attachments/assets/0cf0a537-e9b4-412a-aef1-4f65aaca2c48)

### 👷‍♂️ 관리자 메뉴 구조도

![관리자 메뉴 구조도](https://github.com/user-attachments/assets/f4c6783a-587a-4953-af9c-80aa5da54487)

## 🤝 협업 방식

1. 해당하는 업무에 대해 GitHub Issue를 생성합니다.
2. GitHub Actions에 의해 자동으로 생성된 브랜치로 전환하여 해당하는 업무를 진행합니다.
3. 작업을 완료하면 각자의 생성한 브랜치에서 코드를 push합니다.
4. PR(Pull Request) 을 오픈합니다.

-   PR(pull request)을 오픈하면, 팀장이 코드를 확인하고 승인합니다.

-   코드 리뷰 & 승인은 팀장이 일임했지만, 팀원들의 코드 스타일은 다같이 공유하며 서로 잘 이해할 수 있도록 다 같이 정리하는 시간을 가지도록 하였습니다.

*   컨펌 담당
    -   백승주 : <code>성창해</code>
    -   김해민 : <code>성창해</code>
    -   김지현 : <code>성창해</code>

5. PR이 merge되어 close 되면 해당 이슈는 자동으로 Done상태로 변경됩니다.

<br/>
<br/>

## 📚 프로젝트 진행 상황 관리

-   <a href="https://github.com/doctor-fish-project/doctor-fish-front/issues?q=is%3Aissue+is%3Aclosed">📋 GitHub Issues(Front) & </a>
    <a href="https://github.com/doctor-fish-project/doctor-fish-back/issues?q=is%3Aissue+is%3Aclosed"> GitHub Issues(Back) </a>
    -   간편한 이슈 생성을 위해 이슈 템플릿을 만들어 사용했습니다. + 팀원이 현재 어떤 작업을 진행하고 있는지를 바로 알 수 있어 의사소통 비용을 줄일 수 있었습니다.
-   <a href="https://github.com/orgs/doctor-fish-project/projects/1">📁 GitHub Projects</a>
    -   칸반 보드로 프로젝트 진행 상황을 한 눈에 확인할 수 있어 일정을 관리하기 수월했습니다.

<br/>
<br/>

## 🔍 브랜치 전략

<h3>💡 GitHub Flow 전략</h3>

-   개발과 동시에 지속적으로 배포를 진행할 것이 아니라, 기능을 모두 개발하고 최종적으로 배포를 할 예정이었기 때문에 Git flow에 비해 흐름이 단순해짐에 따라 그 규칙도 단순한 GitHub Flow 전략이 적합하다고 생각했습니다.

-   프로젝트 기간 동안 팀원들이 같은 시간에 작업하기 때문에 잦은 충돌이 발생할 것을 우려하여 충돌의 크기를 줄이고자 GitHub Flow 전략을 채택하여 작은 단위로 이슈를 쪼개 이슈 별로 브랜치를 분기하고 main 브랜치에 지속적으로 merge 하는 방식으로 진행했습니다.

-   기본적으로 master branch에 대한 규칙만 정확하게 정립되어 있다면 나머지 가지들에 대해서는 특별한 관여를 하지 않으며 pull request기능을 사용하도록 권장하였습니다.

<br/>
<br/>

## ✔ 컨벤션

팀원 간의 원활한 소통과 협업을 위해 커밋 컨벤션과, 코드 컨벤션을 만들어 이를 따랐습니다. 리드미에는 간략히 작성하고, 자세한 컨벤션은 각각의 타이틀에 링크된 깃허브 위키에 적어두었습니다.

<h3>
<a href="">📍 커밋 컨벤션</a>
</h3>

<div>
<pre background-color="#dbdbdb">
<p>
1. 커밋 유형 지정
    - 커밋 유형은 영어 대문자로 작성하기
    - 커밋 유형
    - Feat : 새로운 기능 추가
    - Fix : 버그 수정
    - Docs : 문서 수정
    - Style : 코드 formatting, 세미콜론 누락, 코드 자체의 변경이 없는 경우
    - Refactor : 코드 리팩토링
    - Test : 테스트 코드, 리팩토링 테스트 코드 추가
    - Chore : 패키지 매니저 수정, 그 외 기타 수정 ex) .gitignore
    - Design : CSS 등 사용자 UI 디자인 변경
    - Comment : 필요한 주석 추가 및 변경
    - Rename : 파일 또는 폴더 명을 수정하거나 옮기는 작업만인 경우
    - Remove : 파일을 삭제하는 작업만 수행한 경우
    - !BREAKING CHANGE : 커다란 API 변경의 경우
    - !HOTFIX : 급하게 치명적인 버그를 고쳐야 하는 경우

🧾 2. 제목과 본문을 빈행으로 분리 - 커밋 유형 이후 제목과 본문은 한글로 작성하여 내용이 잘 전달될 수 있도록 할 것 - 본문에는 변경한 내용과 이유 설명 (어떻게보다는 무엇 & 왜를 설명)

#️⃣ 3. 제목 첫 글자는 대문자로, 끝에는 . 금지

↩️ 4. 제목은 영문 기준 50자 이내로 할 것

⏺️ 5. 자신의 코드가 직관적으로 바로 파악할 수 있다고 생각하지 말자

👆 6. 여러가지 항목이 있다면 글머리 기호를 통해 가독성 높이기

</p>
</pre>
</div>

<h3>
<a href="">📍 코드 컨벤션</a>
</h3>
<div>
<pre>
<p>
🛼 문자열을 처리할 때는 쌍따옴표를 사용하도록 합니다.

🐫 문장이 종료될 때는 세미콜론을 붙여줍니다.

💄 함수명, 변수명은 카멜케이스로 작성합니다.

🐫 가독성을 위해 한 줄에 하나의 문장만 작성합니다.

❓ 주석은 설명하려는 구문에 맞춰 들여쓰기 합니다.

🔠 연산자 사이에는 공백을 추가하여 가독성을 높입니다.

🔢 콤마 다음에 값이 올 경우 공백을 추가하여 가독성을 높입니다.

💬 생성자 함수명의 맨 앞글자는 대문자로 합니다.

🔚 var는 절대 사용하지 않는다. (const를 let 보다 위에 선언한다)

👆 const와 let은 사용 시점에 선언 및 할당을 한다. (함수는 변수 선언문 다음에 오도록한다.)

✏️ 외부 모듈과 내부 모듈을 구분하여 사용한다.

🧮 배열과 객체는 반드시 리터럴로 선언한다. (new 사용 X)

📠 배열 복사 시 반복문을 사용하지 않는다.

😎 배열의 시작 괄호 안에 요소가 줄 바꿈으로 시작되었다면 끝 괄호 이전에도 일관된 줄 바꿈 해야한다. (일관되게 모두 줄 바꿈을 해주어야 한다.)

🧶 객체의 프로퍼티가 1개인 경우에만 한 줄 정의를 허용하며, 2개 이상일 경우에는 개행을 강제한다. (객체 리터럴 정의 시 콜론 앞은 공백을 허용하지 않음 콜론 뒤는 항상 공백을 강제)

🧂 메서드 문법 사용 시 메서드 사이에 개행을 추가한다.

🌭 화살표 함수의 파라미터가 하나이면 괄호를 생략한다.

🍳 변수 등을 조합해서 문자열을 생성하는 경우 템플릿 문자열을 이용한다.

🧇 변수 등을 조합해서 문자열을 생성하는 경우 템플릿 문자열을 이용한다.

🥞 wildcard import는 사용하지 않는다. (import문으로부터 직접 export하지 않는다.)

🥖 한 줄짜리 블록일 경우라도 {}를 생략하지 않으며 명확히 줄 바꿈 하여 사용한다.

🥯 switch-case 사용 시 첫 번째 case문을 제외하고 case문 사용 이전에 개행한다.

🥐 삼중 등호 연산자인 ===, !==만 사용한다.

🚐 반복문 사용은 일반화된 순회 메서드 사용을 권장한다.

🚑 람다함수 안에서 밖에 있는 변수를 사용하지 말라

🚚 코드 블럭 주석 처리를 위해서는 한 줄 주석을 사용한다. 여러 줄 주석을 작성할 때는 \*의 들여쓰기를 맞춘다. 주석의 첫 줄과 마지막 줄은 비워둠

💫 시작 괄호 바로 다음과 끝 괄호 바로 이전에 공백이 있으면 안 된다.

</p>
</pre>
</div>

<br/>

## **✨ 화면 구현 및 코드 리뷰**

### :smile: 사용자 코드 리뷰

<details>
<summary>로그인 영상과 코드 리뷰</summary>
<div markdown="1">

<br/>

<div align="center" dir="auto">
    <img src="https://github.com/user-attachments/assets/55ab7c1a-b2ae-4c2b-8477-0efa81d11633" align="center" width="100%"/>
    <b>로그인 화면</b>
</div>

<br/><br/>

**controller**

```java

@RestController
public class UserAuthenticationController {

    @Autowired
    private UserUserService userService;

    // 사용자 로그인
    @ValidAop
    @PostMapping("/auth/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody ReqSigninDto dto, BindingResult bindingResult) throws SigninException {
        return ResponseEntity.ok().body(userService.getGeneratedAccessToken(dto));
    }

}

```
<br/>

- 프론트에서 보낸 email, password를 받는다.
- 요청에서 받은 데이터로 유효성 검사 실시 후 성공하면 service로 넘긴다.

---

<br/><br/>

**dto**

```java

@Data
public class ReqSigninDto {
    @NotBlank(message = "이메일을 입력해 주세요.")
    @Email(message = "이메일 형식이어야 합니다.")
    private String email;
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;
}

```
<br/>

- 유효성 검사에 실패하면 해당 메세지를 에러 메세지로 반환해준다.

---

<br/><br/>

**service**

```java

@Service
public class UserUserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserUserMapper userMapper;
   
    public RespSigninDto getGeneratedAccessToken(ReqSigninDto dto) throws SigninException {
            try {
                User user = checkUsernameAndPassword(dto.getEmail(), dto.getPassword());
    
                if(user.getEmailValid() != 1) {
                    emailService.sendAuthMail(dto.getEmail());
                    throw new EmailValidException("이메일 인증 후 로그인 가능합니다.");
                }
    
                return RespSigninDto.builder()
                        .expireDate(jwtProvider.getExpireDate().toLocaleString())
                        .accessToken(jwtProvider.generateAccessToken(user))
                        .build();
            } catch (SigninException e) {
                throw new SigninException(e.getMessage());
            } catch (EmailValidException e) {
                throw new EmailValidException(e.getMessage());
            } catch (Exception e) {
                throw new ExecutionException("실행 도중 오류 발생");
            }
        }

    private User checkUsernameAndPassword(String email, String password) throws SigninException {
        try {
            User user = userMapper.findByEmail(email);

            if(user == null) {
                throw new SigninException("사용자 정보를 다시 확인하세요.");
            }

            if(!passwordEncoder.matches(password, user.getPassword())) {
                throw new SigninException("사용자 정보를 다시 확인하세요.");
            }

            return user;
        } catch (SigninException e) {
            throw new SigninException(e.getMessage());
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }
}

```
<br/>

- controller에서 보낸 email, password를 받아서 email로 데이터베이스에서 사용자를 찾고 password로 해당 사용자의 password와 비교를 한다.
- 사용자가 이메일 인증을 받았을 때만 로그인 할 수 있게 한다.

---

<br/><br/>

**mapper**

```java

@Mapper
public interface UserUserMapper {

     User findByEmail(String email);
}

```
<br/>

- service에서 보낸 email로 데이터베이스에서 해당 email을 가지고 있는 사용자를 찾는다.

---

<br/><br/>

**sql**

```java

<resultMap id="userResultMap" type="com.project.doctor_fish_back.entity.User">
    <id property="id" column="user_id" />
    <result property="email" column="email" />
    <result property="name" column="name" />
    <result property="password" column="password" />
    <result property="phoneNumber" column="phone_number" />
    <result property="img" column="img" />
    <result property="emailValid" column="email_valid" />
    <result property="registerDate" column="register_date" />
    <result property="updateDate" column="update_date" />
    <collection property="userRoles" javaType="java.util.Set" resultMap="userRolesResultMap" />
</resultMap>

<select id="findByEmail" resultMap="userResultMap">
    select
        u.id as user_id,
        u.email,
        u.name,
        u.password,
        u.phone_number,
        u.img,
        u.email_valid,
        u.register_date,
        u.update_date,
        ur.id as user_roles_id,
        ur.user_id as ur_user_id,
        ur.role_id as ur_role_id,
        r.id as role_id,
        r.name as role_name
    from
        user_tb u
        left outer join user_roles_tb ur on(u.id = ur.user_id)
        left outer join role_tb r on(r.id = ur.role_id)
    where
        email = #{email}
</select>

```
<br/>

- id는 mapper에 있는 findByEmail이고 반환 값은 userResultMap이다.
- where문에서 service에서 받은 email로 사용자를 찾는다.

---

</div>
</details>

<details>
<summary>회원가입 영상과 코드 리뷰</summary>
<div markdown="1">

<br/>

<div align="center" dir="auto">
    <img src="https://github.com/user-attachments/assets/9beae154-b402-4731-b83c-b2253324c190" align="center" width="100%"/>
    <b>회원가입 화면</b>
</div>

<br/><br/>

**controller**
```java

@RestController
public class UserAuthenticationController {

    @Autowired
    private UserUserService userService;

    // 사용자 회원가입
    @ValidAop
    @PostMapping("/auth/signup")
    public ResponseEntity<?> userSignup(@Valid @RequestBody ReqSignupDto dto, BindingResult bindingResult) throws SignupException {
        return ResponseEntity.ok().body(userService.insertUserAndUserRoles(dto));
    }
}

```
<br/>

- 사용자를 추가할 때 필요한 데이터들을 객체로 받는다.
- 요청에서 받은 데이터로 유효성 검사 실시 후 성공하면 service로 넘긴다.

---

<br/><br/>

**dto**

```java

@Data
public class ReqSignupDto {
    @NotBlank(message = "이메일은 공백일 수 없습니다.")
    @Email(message = "이메일 형식이어야 합니다.")
    private String email;
    @NotBlank(message = "이름은 공백일 수 없습니다.")
    private String name;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[~!@#$%^&*?])[A-Za-z\\d~!@#$%^&*?]{8,16}$", message = "8자이상 16자이하의 영대소문, 숫자, 특수문자         (~!@#$%^&*?)")
    private String password;
    private String checkPassword;
    @NotBlank(message = "전화번호는 공백일 수 없습니다.")
    private String phoneNumber;

    public User toEntity(BCryptPasswordEncoder passwordEncoder, String defaultProfileImg) {
        return User.builder()
                .email(email)
                .name(name)
                .password(passwordEncoder.encode(password))
                .phoneNumber(phoneNumber)
                .img(defaultProfileImg)
                .build();
    }
}

```
<br/>

- 유효성 검사에 실패하면 해당 메세지를 에러 메세지로 반환해준다.
- 비밀번호는 보안성을 위해 BCrypt로 바꿔준다.

---

<br/><br/>

**service**

```java

@Service
public class UserUserService {

    @Value("${user.profile.user.img.default}")
    private String userDefaultProfileImg;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserUserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRolesMapper userRolesMapper;
    @Autowired
    private EmailService emailService;

    @Transactional(rollbackFor = SignupException.class)
    public Boolean insertUserAndUserRoles(ReqSignupDto dto) throws SignupException {
        User user = null;
        try {
            user = dto.toEntity(passwordEncoder, userDefaultProfileImg);
            userMapper.save(user);

            Role role = roleMapper.findByPosition("ROLE_USER");

            if (role == null) {
                role = Role.builder().name("회원").position("ROLE_USER").build();
                roleMapper.save(role);
            }

            UserRoles userRoles = UserRoles.builder()
                    .userId(user.getId())
                    .roleId(role.getId())
                    .build();

            userRolesMapper.save(userRoles);

            user.setUserRoles(Set.of(userRoles));

            emailService.sendAuthMail(dto.getEmail());
        } catch (Exception e) {
            throw new SignupException(e.getMessage());
        }

        return true;
    }
}

```
<br/>

- 회원가입할 때는 img를 application.yml의 기본 이미지로 추가한다.
- 사용자는 이메일 인증을 해야 하므로 데이터를 다 추가한 후 이메일 인증을 할 수 있는 메일을 보낸다.
- 사용자를 추가할 때 여러 테이블에 연쇄적으로 데이터를 추가해야 하기 때문에 @Transactional을 이용해서 트렌잭션 처리를 해준다.

---

<br/><br/>

**mapper**

```java

@Mapper
public interface UserUserMapper {

    int save(User user);

}

@Mapper
public interface RoleMapper {

    Role findByPosition(String position);
    int save(Role role);

}

@Mapper
public interface UserRolesMapper {

    int save(UserRoles userRoles);

}

```
<br/>

- service에서 받은 user객체로 데이터베이스에 관리자를 추가한다.

---

<br/><br/>

**sql**

```java

<insert id="save" useGeneratedKeys="true" keyProperty="id">
    insert into user_tb
    values(default, #{email}, #{name}, #{password}, #{phoneNumber}, #{img}, default, now(), now())
</insert>

<select id="findByPosition" resultType="com.project.doctor_fish_back.entity.Role">
    select
        id,
        name,
        position
    from
        role_tb
    where
        position = #{position}
</select>

<insert id="save" useGeneratedKeys="true" keyProperty="id">
    insert into role_tb
    values(default, #{name}, #{position})
</insert>

<insert id="save">
    insert into user_roles_tb
    values(default, #{userId}, #{roleId})
</insert>

```
<br/>

- useGeneratedKeys="true" keyProperty="id" -> 데이터베이스에 데이터를 추가한 후 바로 id 값을 들고온다.

---

</div>
</details>


<details>
<summary>예약 추가 & 예약 조회 영상과 코드 리뷰</summary>
<div markdown="1">

<div align="center" dir="auto">
    <img src="https://github.com/user-attachments/assets/424c448c-a893-44c6-baa0-5d3522681082" align="center" width="100%"/>
    <b>예약하기&예약조회</b>
</div>

<br/>

<details>
<summary>예약 추가 코드 리뷰</summary>
<div markdown="1">

<br/>

**controller**

```java

@RestController
public class UserReservationController {

    @Autowired
    private UserReservationService reservationService;

    // 예약하기
    @PostMapping("/reservation")
    public ResponseEntity<?> registerReservation(@RequestBody ReqRegisterReservationDto dto) {
        System.out.println(dto);
        return ResponseEntity.ok().body(reservationService.registerReservation(dto));
    }
}

```
<br/>

- 예약할 때 필요한 데이터들을 객체로 받는다.

---

<br/><br/>

**dto**

```java

@Data
public class ReqRegisterReservationDto {
    private LocalDateTime reserveDate;
    private Long doctorId;

    public Reservation toEntity(Long userId) {
        return Reservation.builder()
                .userId(userId)
                .doctorId(doctorId)
                .reservationDate(reserveDate)
                .build();
    }
}

```
<br/>

- userId는 service에서 받아와서 entity로 바꿔준다.

---

<br/><br/>

**service**

```java

@Service
public class UserReservationService {

    @Autowired
    private UserReservationMapper reservationMapper;

    public Boolean registerReservation(ReqRegisterReservationDto dto) {
            try {
                PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    
                Reservation reservation = dto.toEntity(principalUser.getId());
                reservationMapper.register(reservation);
            } catch (Exception e) {
                throw new ExecutionException("실행 도중 오류 발생");
            }
            return true;
        }
}

```
<br/>

- principalUser에서 userId를 찾아서 toEntity로 넘겨준다.

---

<br/><br/>

**mapper**

```java

@Mapper
public interface UserReservationMapper {

    int register(Reservation reservation);

}

```
<br/>

- service에서 받은 reservation객체로 데이터베이스에 예약을 추가한다.

---

<br/><br/>

**sql**

```java

<insert id="register">
    insert into reservation_tb
    values(default, #{userId}, #{doctorId}, default, #{reservationDate}, now(), default)
</insert>

```
<br/>

- service에서 받은 reservation객체로 데이터베이스에 예약을 추가한다.

---

</div>
</details>

<details>
<summary>예약 조회 코드 리뷰</summary>
<div markdown="1">

<br/>

**controller**

```java

@RestController
public class UserReservationController {

    @Autowired
    private UserReservationService reservationService;

    // 사용자 내 예약 전체 조회
    @GetMapping("/reservation/list")
    public ResponseEntity<?> getAllReservationsToUser(ReqPageAndLimitDto dto) {
        return ResponseEntity.ok().body(reservationService.getReservations(dto));
    }
}

```
<br/>

- 프론트에서 보여줄 페이지와 개수를 받는다.

---

<br/><br/>

**dto**

```java

@Data
public class ReqPageAndLimitDto {
    private Long page;
    private Long limit;
}

```
<br/>

---

<br/><br/>

**service**

```java

@Service
public class UserReservationService {

    @Autowired
    private UserReservationMapper reservationMapper;

    public RespGetReservationListDto getReservations(ReqPageAndLimitDto dto) {
            try {
                Long startIndex = (dto.getPage() - 1) * dto.getLimit();
                PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    
                List<Reservation> reservations = reservationMapper.reservationList(principalUser.getId(), startIndex, dto.getLimit());
                Long totalCount = reservationMapper.reservationCount(principalUser.getId());
                return RespGetReservationListDto.builder()
                        .reservations(reservations)
                        .totalCount(totalCount)
                        .build();
            } catch (Exception e) {
                throw new ExecutionException("실행 도중 오류 발생");
            }
        }
}

```
<br/>

- dto의 데이터들을 mapper로 넘겨준다.

---

<br/><br/>

**mapper**

```java

@Mapper
public interface UserReservationMapper {

    // 유저 페이지 예약 전체 조회
    List<Reservation> reservationList(@Param("userId") Long userId,
                                           @Param("startIndex") Long startIndex,
                                           @Param("limit") Long limit);
    Long reservationCount(Long userId);    

}

```
<br/>

- 데이터베이스에서 예약 리스트와 개수를 가져온다.

---

<br/><br/>

**sql**

```java

<select id="reservationList" resultMap="reservationResultMap">
    select
        rt.id as rt_id,
        rt.user_id as rt_user_id,
        rt.doctor_id,
        rt.status,
        rt.reservation_date,
        rt.register_date,
        rt.review_status,
        dot.id as dot_id,
        dot.user_id as dot_user_id,
        dot.depart_id,
        dot.comment,
        dot.record,
        ut.id as ut_id,
        ut.email,
        ut.name as ut_name,
        ut.password,
        ut.phone_number,
        ut.img as ut_img,
        ut.email_valid,
        det.id as det_id,
        det.name as det_name
    from
        reservation_tb rt
        left outer join doctor_tb dot on(rt.doctor_id = dot.id)
        left outer join user_tb ut on(dot.user_id = ut.id)
        left outer join depart_tb det on(dot.depart_id = det.id)
    where
        rt.user_id = #{userId}
    order by
        Date(rt.reservation_date) desc
    limit #{startIndex}, #{limit}
</select>

<select id="reservationCount" resultType="java.lang.Long">
    select
        count(*)
    from
        reservation_tb
    where
        user_id = #{userId}
</select>

```
<br/>

- 데이터베이스에서 해당 유저의 예약 리스트와 개수를 가져온다.

---

</div>
</details>

---

</div>
</details>

<details>
<summary>예약 수정 영상과 코드 리뷰</summary>
<div markdown="1">

<br/>

<div align="center" dir="auto">
    <img src="https://github.com/user-attachments/assets/b3a609b1-d8de-46a7-babc-a3db796ffb38" align="center" width="100%"/>
    <b>예약수정</b>
</div>

<br/>

**controller**

```java

@RestController
public class UserReservationController {

    // 예약 정보 수정
    @PutMapping("/reservation/{reservationId}")
    public ResponseEntity<?> modifyReservation(@PathVariable Long reservationId, @RequestBody ReqModifyReservationDto dto) {
        System.out.println(dto);
        return ResponseEntity.ok().body(reservationService.modifyReservation(reservationId, dto));
    }
}

```
<br/>

- 프론트에서 수정할 예약의 reservationId와 수정할 때 필요한 데이터들을 객체로 받는다.

---

<br/><br/>

**dto**

```java

@Data
public class ReqModifyReservationDto {
    private Long doctorId;
    private LocalDateTime reserveDate;

    public Reservation toEntity(Long id) {
        return Reservation.builder()
                .id(id)
                .doctorId(doctorId)
                .reservationDate(reserveDate)
                .build();
        }
}

```
<br/>

- service에서 reservationId를 받아와서 entity로 바꾼다.

---

<br/><br/>

**service**

```java

@Service
public class UserReservationService {

    @Autowired
    private UserReservationMapper reservationMapper;

    public Boolean modifyReservation(Long reservationId, ReqModifyReservationDto dto) {
            try {
                reservationMapper.modify(dto.toEntity(reservationId));
            } catch (Exception e) {
                throw new ExecutionException("실행 도중 오류 발생");
            }
            return true;
        }
}

```
<br/>

- controller에서 받은 데이터들을 mapper로 보내서 예약을 수정한다.

---

<br/><br/>

**mapper**

```java

@Mapper
public interface UserReservationMapper {

    int modify(Reservation reservation);

}

```
<br/>

- service에서 받은 reservation객체로 데이터베이스에서 예약을 수정한다.

---

<br/><br/>

**sql**

```java

<update id="modify">
   update reservation_tb
    set
        reservation_date = #{reservationDate}
    where
        id = #{id} and doctor_id = #{doctorId}
</update>

```
<br/>

- 데이터베이스에서 예약날짜를 수정해준다.

---

</div>
</details>

<details>
<summary>리뷰 & 좋아요 영상과 코드 리뷰</summary>
<div markdown="1">

<br/>

<div align="center" dir="auto">
    <img src="https://github.com/user-attachments/assets/c89e577a-1a0b-4168-bb52-ea5825e976c1" align="center" width="100%"/>
    <b>리뷰 & 댓글작성 & 좋아요</b>
</div>

<br/>

<details>
<summary>리뷰 작성 코드 리뷰</summary>
<div markdown="1">

<br/>

**controller**

```java

@RestController
public class UserReviewController {
    @Autowired
    private UserReviewService reviewService;

    // 리뷰 작성
    @ValidAop
    @PostMapping("/review")
    public ResponseEntity<?> writeReview(@Valid @RequestBody ReqWriteReviewDto dto, BindingResult bindingResult) {
        return ResponseEntity.ok().body(reviewService.writeReview(dto));
    }
}

```
<br/>

- 프론트에서 작성할 리뷰 내용 데이터를 객체로 받는다.
- 요청에서 받은 데이터로 유효성 검사 실시 후 성공하면 service로 넘긴다.

---

<br/><br/>

**dto**

```java

@Data
public class ReqWriteReviewDto {
    private Long reservationId;
    private String imgList;
    @NotBlank(message = "내용을 입력하세요.")
    private String content;

    public Review toEntity(Long userId) {
        return Review.builder()
                .userId(userId)
                .reservationId(reservationId)
                .img(imgList)
                .content(content)
                .build();
    }
}

```
<br/>

- 유효성 검사에 실패하면 해당 메세지를 에러 메세지로 반환해준다.

---

<br/><br/>

**service**

```java

@Service
public class UserReviewService {

    @Autowired
    private UserReviewMapper reviewMapper;
    @Autowired
    private UserReservationMapper reservationMapper;

    public Boolean writeReview(ReqWriteReviewDto dto) {
            try {
                PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    
                reviewMapper.save(dto.toEntity(principalUser.getId()));
                reservationMapper.modifyReviewStatusById(dto.getReservationId());
            } catch (Exception e) {
                throw new ExecutionException("실행 도중 오류 발생");
            }
            return true;
        }
}

```
<br/>

- 리뷰를 작성하면 예약의 리뷰 상태를 바꿔준다.

---

<br/><br/>

**mapper**

```java

@Mapper
public interface UserReviewMapper {

    int save(Review review);

}

@Mapper
public interface UserReservationMapper {

    int modifyReviewStatusById(Long id);

}

```
<br/>

- 각각 service에서 id를 받아서 추가, 수정한다.

---

<br/><br/>

**sql**

```java

<insert id="save">
    insert into review_tb
    values(default, #{reservationId}, #{userId}, #{img}, #{content}, now(), now())
</insert>

<update id="modifyReviewStatusById">
    update reservation_tb
    set
        review_status = if(review_status = 1, 0, 1)
    where
        id = #{id}
</update>

```
<br/>

- 데이터베이스에서 리뷰를 추가하고 예약의 리뷰 상태를 해당 예약에 리뷰가 있다는 상태인 1로 바꿔준다.(처음엔 0)

---

</div>
</details>

<details>
<summary>리뷰 조회 코드 리뷰</summary>
<div markdown="1">

<br/>

**controller**

```java

@RestController
public class UserReviewController {

    @Autowired
    private UserReviewService reviewService;

    // 사용자 내 리뷰 전체 조회
    @GetMapping("/review/me")
    public ResponseEntity<?> getReviewsToUser(ReqPageAndLimitDto dto) {
        return ResponseEntity.ok().body(reviewService.getReviewsByUserId(dto));
    }
}

```
<br/>

- 프론트에서 보여줄 페이지와 개수를 받는다.

---

<br/><br/>

**dto**

```java

@Data
public class ReqPageAndLimitDto {
    private Long page;
    private Long limit;
}

```
<br/>

---

<br/><br/>

**service**

```java

@Service
public class UserReviewService {

    @Autowired
    private UserReviewMapper reviewMapper;
    
    public RespGetReviewListDto getReviewsByUserId(ReqPageAndLimitDto dto) {
            try {
                PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                Long startIndex = (dto.getPage() - 1) * dto.getLimit();
                List<Review> reviews = reviewMapper.reviewListByUserId(principalUser.getId(), startIndex, dto.getLimit());
                Long reviewCount = reviewMapper.reviewCountByUserId(principalUser.getId());
    
                return RespGetReviewListDto.builder()
                        .reviews(reviews)
                        .reviewCount(reviewCount)
                        .build();
            } catch (Exception e) {
                throw new ExecutionException("실행 도중 오류 발생");
            }
    }
}

```
<br/>

- principalUser에서 userId를 mapper에 넘겨준다.

---

<br/><br/>

---

</div>
</details>

<details>
<summary>리뷰 좋아요 코드 리뷰</summary>
<div markdown="1">

<br/>

**controller**

```java

@RestController
public class UserReviewController {

    @Autowired
    private UserReviewService reviewService;

    // 리뷰 좋아요
    @PostMapping("/review/like/{reviewId}")
    public ResponseEntity<?> like(@PathVariable Long reviewId) throws ReviewLikeException {
        return ResponseEntity.ok().body(reviewService.like(reviewId));
    }
}

```
<br/>

- 프론트에서 좋아요를 할 리뷰의 reviewId를 받아온다.

---

<br/><br/>

**service**

```java

@Service
public class UserReviewService {

    @Autowired
    private UserReviewLikeMapper reviewLikeMapper;

    public Boolean like(Long reviewId) throws ReviewLikeException {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Long userId = principalUser.getId();

            ReviewLike reviewLike = reviewLikeMapper.findByReviewIdAndUserId(reviewId, userId);

            if(reviewLike != null) {
                throw new ReviewLikeException("이미 좋아요한 리뷰입니다.");
            }

            ReviewLike rl = ReviewLike.builder()
                    .reviewId(reviewId)
                    .userId(userId)
                    .build();

            reviewLikeMapper.save(rl);
        } catch (ReviewLikeException e) {
            throw new ReviewLikeException(e.getMessage());
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }
}

```
<br/>

- 한 번 좋아요한 리뷰는 다시 좋아요를 못하게 한다.(좋아요는 1번만 가능)

---

<br/><br/>

**mapper**

```java

@Mapper
public interface UserReviewLikeMapper {

    ReviewLike findByReviewIdAndUserId(@Param("reviewId") Long reviewId,
                                       @Param("userId")  Long userId);

    int save(ReviewLike reviewLike);

}

```
<br/>

- 이미 좋아요를 했었는지 찾은 후 없으면 데이터베이스에 추가한다.

---

<br/><br/>

**sql**

```java

<select id="findByReviewIdAndUserId" resultType="com.project.doctor_fish_back.entity.ReviewLike">
    select
        id,
        review_id as reviewId,
        user_id as userId
    from
        review_like_tb
    where
        review_id = #{reviewId}
        and user_id = #{userId}
</select>

<insert id="save">
    insert into review_like_tb
    values(default, #{reviewId}, #{userId})
</insert>

```
<br/>

- 데이터베이스에 좋아요를 추가한다.

---

</div>
</details>

---

</div>
</details>

<details>
<summary>리뷰 수정 & 삭제 영상과 코드 리뷰</summary>
<div markdown="1">

<br/>

<div align="center" dir="auto">
    <img src="https://github.com/user-attachments/assets/160804f8-eed2-469d-91f2-143a6bc897aa" align="center" width="100%"/>
    <b>리뷰수정 및 삭제&댓글수정</b>
</div>

<br/>

<details>
<summary>리뷰 수정 코드 리뷰</summary>
<div markdown="1">

<br/>

**controller**

```java

@RestController
public class UserReviewController {

    @Autowired
    private UserReviewService reviewService;

    // 리뷰 수정
    @ValidAop
    @PutMapping("/review/{reviewId}")
    public ResponseEntity<?> modifyReview(@PathVariable Long reviewId, @Valid @RequestBody ReqModifyReviewDto dto, BindingResult bindingResult) {
        return ResponseEntity.ok().body(reviewService.modifyReview(reviewId, dto));
    }
}

```
<br/>

- 프론트에서 수정할 리뷰의 내용을 객체로 받는다.
- 요청에서 받은 데이터로 유효성 검사 실시 후 성공하면 service로 넘긴다.

---

<br/><br/>

**dto**

```java

@Data
public class ReqModifyReviewDto {
    private String imgList;
    @NotBlank(message = "내용을 입력하세요.")
    private String content;

    public Review toEntity(Long reviewId) {
        return Review.builder()
                .id(reviewId)
                .img(imgList)
                .content(content)
                .build();
    }
}

```
<br/>

- 유효성 검사에 실패하면 해당 메세지를 에러 메세지로 반환해준다.

---

<br/><br/>

**service**

```java

@Service
public class UserReviewService {

    @Autowired
    private UserReviewMapper reviewMapper;

    public Boolean modifyReview(Long reviewId, ReqModifyReviewDto dto) {
        try {
            reviewMapper.modify(dto.toEntity(reviewId));
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }
}

```
<br/>

- controller에서 받은 데이터들을 mapper로 보내서 리뷰을 수정한다.

---

<br/><br/>

**mapper**

```java

@Mapper
public interface UserReviewMapper {

    int modify(Review review);

}

```
<br/>

- service에서 받은 review객체로 데이터베이스에서 리뷰을 수정한다.

---

<br/><br/>

**sql**

```java

<update id="modify">
    update review_tb
    set
        img = #{img},
        content = #{content},
        update_date = now()
    where
        id = #{id}
</update>

```
<br/>

- 데이터베이스에서 리뷰를 수정해준다.

---

</div>
</details>

<details>
<summary>리뷰 삭제 코드 리뷰</summary>
<div markdown="1">

<br/>

**controller**

```java

@RestController
public class UserReviewController {

    @Autowired
    private UserReviewService reviewService;

    // 리뷰 삭제
    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok().body(reviewService.deleteReview(reviewId));
    }
}

```
<br/>

- 프론트에서 삭제할 리뷰의 reviewId를 받아온다.

---

<br/><br/>

**service**

```java

@Service
public class UserReviewService {

    @Autowired
    private UserReviewMapper reviewMapper;
    @Autowired
    private UserReservationMapper reservationMapper;
    @Autowired
    private UserReviewLikeMapper reviewLikeMapper;
    @Autowired
    private UserCommentMapper userCommentMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    public Boolean deleteReview(Long reviewId) {
        try {
            PrincipalUser principalUser = (PrincipalUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Review review = reviewMapper.findById(principalUser.getId(), reviewId);
            reservationMapper.modifyReviewStatusById(review.getReservationId());
            reviewLikeMapper.deleteReviewsByReviewId(reviewId);
            userCommentMapper.deleteCommentsByReviewId(reviewId);
            reviewMapper.deleteById(reviewId);
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
        return true;
    }
}

```
<br/>

- 리뷰를 삭제할 때 해당 리뷰의 댓글, 좋아요를 같이 삭제해주고 예약의 리뷰 상태를 바꿔준다.

---

<br/><br/>

**mapper**

```java

@Mapper
public interface UserReviewMapper {

    Review findById(@Param("userId") Long userId,
                        @Param("reviewId") Long reviewId);
    int deleteById(Long id);

}

@Mapper
public interface UserReservationMapper {

    int modifyReviewStatusById(Long id);

}

@Mapper
public interface UserReviewLikeMapper {

    int deleteReviewsByReviewId(Long reviewId);

}

@Mapper
public interface UserCommentMapper {

    int deleteCommentsByReviewId(Long reviewId);

}

```
<br/>

- service에서 받은 reviewId로 리뷰, 댓글, 좋아요를 삭제한다.
- service에서 받은 reservationId로 예약의 리뷰 상태를 바꾼다.

---

<br/><br/>

**sql**

```java

<select id="findById" resultType="com.project.doctor_fish_back.entity.Review">
    select
        rt.id,
        rt.user_id as userId,
        rt.reservation_id as reservationId,
        rt.img,
        rt.content,
        rt.register_date as registerDate,
        rt.update_date as updateDate,
        ut.name as userName,
        ut.img as userImg,
        (select count(*) from review_like_tb where review_id = rt.id) as likeCount,
        (select count(*) from review_like_tb where user_id = #{userId} and review_id = rt.id) as isLike
    from
        review_tb rt
        left outer join user_tb ut on(rt.user_id = ut.id)
    where
        rt.id = #{reviewId}
</select>

<delete id="deleteById">
    delete
    from
        review_tb
    where
        id = #{id}
</delete>

<update id="modifyReviewStatusById">
    update reservation_tb
    set
        review_status = if(review_status = 1, 0, 1)
    where
        id = #{id}
</update>

<delete id="deleteReviewsByReviewId">
    delete
    from
        review_like_tb
    where
        review_id = #{reviewId}
</delete>

<delete id="deleteCommentsByReviewId">
    delete
    from
        comment_tb
    where
        review_id = #{reviewId}
</delete>

```
<br/>

- 데이터베이스에서 리뷰, 삭제할 리뷰의 댓글, 좋아요를 삭제한다.
- 리뷰를 삭제했기 때문에 예약의 리뷰 상태를 다시 리뷰가 없다는 상태인 0으로 바꾼다.

---

</div>
</details>

---

</div>
</details>

<details>
<summary>내작성 리뷰 영상과 코드 리뷰</summary>
<div markdown="1">

<br/>

<div align="center" dir="auto">
    <img src="https://github.com/user-attachments/assets/22dd5f46-64f8-4429-95b6-a5c220b388f5" align="center" width="100%"/>
    <b>내작성리뷰&댓글</b>
</div>

```java

@Mapper
public interface UserReviewMapper {

    // 유저 페이지 사용자의 리뷰 조회
    List<Review> reviewListByUserId(@Param("userId") Long userId,
                                    @Param("startIndex") Long startIndex,
                                    @Param("limit") Long limit);
    Long reviewCountByUserId(Long userId);

}

```
<br/>

- 데이터베이스에서 리뷰 리스트와 개수를 가져온다.

---

<br/><br/>

**sql**

```java

<select id="reviewListByUserId" resultType="com.project.doctor_fish_back.entity.Review">
    select
        rt.id,
        rt.user_id as userId,
        rt.img,
        rt.content,
        rt.register_date as registerDate,
        rt.update_date as updateDate,
        ut.name as userName,
        ut.img as userImg,
        (select count(*) from review_like_tb where review_id = rt.id) as likeCount,
        (select count(*) from review_like_tb where user_id = #{userId} and review_id = rt.id) as isLike
    from
        review_tb rt
        left outer join user_tb ut on(rt.user_id = ut.id)
    where
        user_id = #{userId}
    limit #{startIndex}, #{limit}
</select>

<select id="reviewCountByUserId" resultType="java.lang.Long">
    select
        count(*)
    from
        review_tb
    where
        user_id = #{userId}
</select>

```
<br/>

- 데이터베이스에서 해당 유저의 리뷰 리스트와 개수를 가져온다.

---

</div>
</details>

<details>
<summary>의사 정보 영상</summary>
<div markdown="1">

<br/>

<div align="center" dir="auto">
    <img src="https://github.com/user-attachments/assets/655f781f-4e17-4c59-b7f8-2f6f65929e4b" align="center" width="100%"/>
    <b>의사 정보</b>
</div>

<br/>

</div>
</details>

<details>
<summary>공지사항 영상</summary>
<div markdown="1">

<br/>

<div align="center" dir="auto">
    <img src="https://github.com/user-attachments/assets/c6048932-125d-4beb-892d-e3625f87b0d3" align="center" width="100%"/>
    <b>공지사항 확인</b>
</div>

</div>
</details>

### :lock: 관리자 코드 리뷰

<details>
<summary>관리자 로그인 영상과 코드 리뷰</summary>
<div markdown="1">

<br/>

<div align="center" dir="auto">
    <img src="https://github.com/user-attachments/assets/b1fea71e-463d-4bb1-9471-5de23085fdaf" align="center" width="100%"/>
    <b>관리자 로그인</b>
</div>

<br/>

**controller**

```java

@RestController
@RequestMapping("/admin")
public class AdminAuthenticationController {

    @Autowired
    private AdminUserService userService;

     // 원무과, 의사, 관리자 로그인
    @ValidAop
    @PostMapping("/auth/signin")
    public ResponseEntity<?> adminSignin(@Valid @RequestBody ReqAdminSigninDto dto, BindingResult bindingResult) throws SigninException {
        return ResponseEntity.ok().body(userService.getGeneratedAccessToken(dto));
    }

}

```
<br/>

- 프론트에서 보낸 username, password를 받는다.  
- 요청에서 받은 데이터로 유효성 검사 실시 후 성공하면 service로 넘긴다.

---

<br/><br/>

**dto**

```java

@Data
public class ReqAdminSigninDto {
    @NotBlank(message = "아이디를 입력해 주세요.")
    private String username;
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;
}

```
<br/>

- 유효성 검사에 실패하면 해당 메세지를 에러 메세지로 반환해준다.
  
---

<br/><br/>

**service**

```java

@Service
public class AdminUserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AdminUserMapper userMapper;


     public RespSigninDto getGeneratedAccessToken(ReqAdminSigninDto dto) throws SigninException {
        try {
            User user = checkUsernameAndPassword(dto.getUsername(), dto.getPassword());
    
            return RespSigninDto.builder()
                    .expireDate(jwtProvider.getExpireDate().toLocaleString())
                    .accessToken(jwtProvider.generateAccessToken(user))
                    .build();
        } catch (SigninException e) {
            throw new SigninException(e.getMessage());
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }
    
     private User checkUsernameAndPassword(String email, String password) throws SigninException {
        try {
            User user = userMapper.findByEmail(email);
    
            if(user == null) {
                throw new SigninException("사용자 정보를 다시 확인하세요.");
            }
    
            if(!passwordEncoder.matches(password, user.getPassword())) {
                throw new SigninException("사용자 정보를 다시 확인하세요.");
            }
    
            return user;
        } catch (SigninException e) {
            throw new SigninException(e.getMessage());
        } catch (Exception e) {
            throw new ExecutionException("실행 도중 오류 발생");
        }
    }
}

```
<br/>

- controller에서 보낸 username, password를 받아서 username으로 데이터베이스에서 사용자를 찾고 password로 해당 사용자의 password와 비교를 한다.  
- 데이터베이스에 사용자가 있는 것을 확인하면 토큰을 발급해준다.

---

<br/><br/>

**mapper**

```java

@Mapper
public interface AdminUserMapper {

    User findByEmail(String email);

}

```
<br/>

- service에서 보낸 username으로 데이터베이스에서 해당 username을 가지고 있는 사용자를 찾는다.

---

<br/><br/>

**sql**

```java

<resultMap id="userResultMap" type="com.project.doctor_fish_back.entity.User">
    <id property="id" column="user_id" />
    <result property="email" column="email" />
    <result property="name" column="name" />
    <result property="password" column="password" />
    <result property="phoneNumber" column="phone_number" />
    <result property="img" column="img" />
    <result property="emailValid" column="email_valid" />
    <result property="registerDate" column="register_date" />
    <result property="updateDate" column="update_date" />
    <collection property="userRoles" javaType="java.util.Set" resultMap="userRolesResultMap" />
</resultMap>

<select id="findByEmail" resultMap="userResultMap">
    select
        u.id as user_id,
        u.email,
        u.name,
        u.password,
        u.phone_number,
        u.img,
        u.email_valid,
        u.register_date,
        u.update_date,
        ur.id as user_roles_id,
        ur.user_id as ur_user_id,
        ur.role_id as ur_role_id,
        r.id as role_id,
        r.name as role_name,
        r.position as role_position
    from
        user_tb u
        left outer join user_roles_tb ur on(u.id = ur.user_id)
        left outer join role_tb r on(r.id = ur.role_id)
    where
        email = #{email}
</select>

```
<br/>

- id는 mapper에 있는 findByEmail이고 반환 값은 userResultMap이다.
- where문에서 service에서 받은 email(username)로 사용자를 찾는다.

---

</div>
</details>

<details>
<summary>관리자 추가 영상 및 코드 리뷰</summary>
<div markdown="1">

<br/>

<div align="center" dir="auto">
    <img src="https://github.com/user-attachments/assets/f9c5f656-6e28-4dce-ab44-dbf136624be7" align="center" width="100%"/>
    <b>관리자 추가</b>
</div>

<br/>

**controller**

```java

@RestController
@RequestMapping("/admin")
public class AdminAuthenticationController {

    @Autowired
    private AdminUserService userService;

    // 원무과, 의사, 관리자 회원가입
    @ValidAop
    @PostMapping("/auth/signup")
    public ResponseEntity<?> adminSignup(@Valid @RequestBody ReqAdminSignupDto dto, BindingResult bindingResult) throws SignupException {
        return ResponseEntity.ok().body(userService.adminSignup(dto));
    }

```
<br/>

- 관리자를 추가할 때 필요한 데이터들을 객체로 받는다.
- 요청에서 받은 데이터로 유효성 검사 실시 후 성공하면 service로 넘긴다.

---

<br/><br/>

**dto**

```java

@Data
public class ReqAdminSignupDto {
    private Long roleId;
    @NotBlank(message = "아이디는 공백일 수 없습니다.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "아이디는 영어여야합니다.")
    private String username;
    @NotBlank(message = "이름은 공백일 수 없습니다.")
    private String name;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[~!@#$%^&*?])[A-Za-z\\d~!@#$%^&*?]{8,16}$", message = "비밀번호는 8자이상 16자이하의 영대소문, 숫자, 특수문자(~!@#$%^&*?)를 포함하여합니다.")
    private String password;
    private String checkPassword;
    @NotBlank(message = "전화번호는 공백일 수 없습니다.")
    private String phoneNumber;
    private Long departId;
    private String comment;
    private String record;

    public User toEntity(BCryptPasswordEncoder passwordEncoder, String img) {
        return User.builder()
                .email(username)
                .name(name)
                .password(passwordEncoder.encode(password))
                .phoneNumber(phoneNumber)
                .img(img)
                .build();
    }
}

```
<br/>

- 유효성 검사에 실패하면 해당 메세지를 에러 메세지로 반환해준다.
- 비밀번호는 보안성을 위해 BCrypt로 바꿔준다.

---

<br/><br/>

**service**

```java

@Service
public class AdminUserService {

    @Value("${user.profile.user.img.default}")
    private String userDefaultProfileImg;

    @Value("${user.profile.doctor.img.default}")
    private String doctorDefaultProfileImg;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AdminUserMapper userMapper;
    @Autowired
    private UserRolesMapper userRolesMapper;
    @Autowired
    private AdminDepartMapper departMapper;
    @Autowired
    private AdminDoctorMapper doctorMapper;

    @Transactional(rollbackFor = SignupException.class)
    public Boolean adminSignup(ReqAdminSignupDto dto) throws SignupException {
        User user = null;
        try {
            if (dto.getRoleId() == 2) { // 원무과
                insertInfoOrAdminAndUserRoles(dto, user);
            } else if (dto.getRoleId() == 3) { // 의사
                doctorSignup(dto, user);
            } else if (dto.getRoleId() == 4) { // 관리자
                insertInfoOrAdminAndUserRoles(dto, user);
            } else {
                throw new SignupException("잘못된 요청입니다.");
            }
        } catch (Exception e) {
            throw new SignupException(e.getMessage());
        }

        return true;
    }

    private void insertInfoOrAdminAndUserRoles(ReqAdminSignupDto dto, User user) throws SignupException {
        try {
            user = dto.toEntity(passwordEncoder, userDefaultProfileImg);
            userMapper.save(user);

            userMapper.modifyEmailValidById(user.getId());

            UserRoles userRoles = UserRoles.builder()
                    .userId(user.getId())
                    .roleId(dto.getRoleId())
                    .build();

            userRolesMapper.save(userRoles);

            user.setUserRoles(Set.of(userRoles));
        } catch (Exception e) {
            throw new SignupException("로그인 도중 오류 발생");
        }
    }

    private void doctorSignup(ReqAdminSignupDto dto, User user) throws SignupException {
        try {
            if(dto.getDepartId() == null || dto.getDepartId() == 0) {
                throw new SignupException("부서이름을 입력하세요.");
            }

            user = dto.toEntity(passwordEncoder, doctorDefaultProfileImg);
            userMapper.save(user);

            userMapper.modifyEmailValidById(user.getId());

            UserRoles userRoles = UserRoles.builder()
                    .userId(user.getId())
                    .roleId(dto.getRoleId())
                    .build();

            userRolesMapper.save(userRoles);

            user.setUserRoles(Set.of(userRoles));

            Depart depart = departMapper.findById(dto.getDepartId());

//            if(depart == null) {
//                departMapper.save(Depart.builder().name(dto.getDepartName()).build());
//                depart = departMapper.findByName(dto.getDepartName());
//            }

            Doctor doctor = Doctor.builder()
                    .userId(user.getId())
                    .departId(depart.getId())
                    .comment(dto.getComment())
                    .record(dto.getRecord())
                    .build();

            doctorMapper.save(doctor);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SignupException("로그인 도중 오류 발생");
        }
    }
}

```
<br/>

- 추가하려는 관리자의 role에 따라서 다르게 처리해준다.
- 추가하는 관리자가 의사일 경우 데이터베이스의 doctor테이블에도 데이터를 추가한다.
- 처음에 관리자를 추가할 때는 img를 application.yml의 기본 이미지로 추가한다.
- 관리자는 이메일 인증을 할 필요가 없으므로 이메일 인증 처리를 해준다.
- 관리자를 추가할 때 여러 테이블에 연쇄적으로 데이터를 추가해야 하기 때문에 @Transactional을 이용해서 트렌잭션 처리를 해준다.

---

<br/><br/>

**mapper**

```java
@Mapper
public interface AdminUserMapper {

    int save(User user);
    int modifyEmailValidById(Long id);

}

@Mapper
public interface UserRolesMapper {

    int save(UserRoles userRoles);

}

@Mapper
public interface AdminDepartMapper {

    Depart findById(Long departId);
}

@Mapper
public interface AdminDoctorMapper {

    int save(Doctor doctor);

}

```
<br/>

- service에서 받은 user객체로 데이터베이스에 관리자를 추가한다.
- service에서 받은 doctor객체로 데이터베이스에 의사를 추가한다.

---

<br/><br/>

**sql**

```java

<insert id="save" useGeneratedKeys="true" keyProperty="id">
    insert into user_tb
    values(default, #{email}, #{name}, #{password}, #{phoneNumber}, #{img}, default, now(), now())
</insert>

<update id="modifyEmailValidById">
    update user_tb
    set
        email_valid = if(email_valid = 0, 1, 0)
    where
        id = #{id}
</update>

<insert id="save">
    insert into user_roles_tb
    values(default, #{userId}, #{roleId})
</insert>

<select id="findById" resultType="com.project.doctor_fish_back.entity.Depart">
    select
        id,
        name
    from
        depart_tb
    where
        id = #{departId}
</select>

<insert id="save">
    insert into doctor_tb
    values(default, #{userId}, #{departId}, #{comment}, #{record})
</insert>

```
<br/>

- useGeneratedKeys="true" keyProperty="id" -> 데이터베이스에 데이터를 추가한 후 바로 id 값을 들고온다.

---

</div>
</details>

<details>
<summary>관리자 정보수정 영상</summary>
<div markdown="1">

<br/>

<div align="center" dir="auto">
    <img src="https://github.com/user-attachments/assets/cde2b610-0c21-4cb0-8ad8-d758e0948145" align="center" width="100%"/>
    <b>관리자 정보수정</b>
</div>

<br/>


</div>
</details>

<details>
<summary>관리자 예약관리 영상 및 코드 리뷰</summary>
<div markdown="1">

<br/>

<div align="center" dir="auto">
    <img src="https://github.com/user-attachments/assets/1c6db3ab-7a3a-45f7-9bcf-6149d471526f" align="center" width="100%"/>
    <b>관리자 예약관리</b>
</div>

<br/>

<details>
<summary>예약 수락 코드 리뷰</summary>
<div markdown="1">

<br/>

**controller**

```java

@RestController
@RequestMapping("/admin")
public class AdminReservationController {

    @Autowired
    private AdminReservationService reservationService;

    // 예약 수락
    @PutMapping("/reservation/accept/{reservationId}")
    public ResponseEntity<?> acceptReservation(@PathVariable Long reservationId) {
        return ResponseEntity.ok().body(reservationService.acceptReservation(reservationId));
    }
}

```
<br/>

- 프론트에서 수락해 줄 예약의 reservationId를 받는다.

---

<br/><br/>

**service**

```java

@Service
public class AdminReservationService {

    @Autowired
    private AdminReservationMapper reservationMapper;

    @Autowired
    private AdminAlarmInsertMapper alarmInsertMapper;

    public Boolean acceptReservation(Long reservationId) {
            try {
                reservationMapper.acceptById(reservationId);
                Reservation reservation = reservationMapper.findById(reservationId);
    
                alarmInsertMapper.save(AlarmInsert.builder()
                                .typeId(1L)
                                .alarmId(reservationId)
                                .messageId(1L)
                                .build());
            } catch (Exception e) {
                e.printStackTrace();
                throw new ExecutionException("실행 도중 오류 발생");
            }
            return true;
        }
}

```
<br/>

- controller에서 보낸 reservationId로 예약 status를 바꿔준다.

---

<br/><br/>

**mapper**

```java

@Mapper
public interface AdminReservationMapper {

    int acceptById(Long id);

}

```
<br/>

- service에서 보낸 reservationId로 예약 status를 바꿔준다.

---

<br/><br/>

**sql**

```java

<update id="acceptById">
    update reservation_tb
    set
        status = 2
    where
        id = #{id}
</update>

```
<br/>

- status를 예약 수락 상태인 2로 바꾼다.

---

</div>
</details>

<details>
<summary>예약 취소 코드 리뷰</summary>
<div markdown="1">

<br/>

**controller**

```java

@RestController
@RequestMapping("/admin")
public class AdminReservationController {

    @Autowired
    private AdminReservationService reservationService;

    // 예약 취소
    @PutMapping("/reservation/cancel/{reservationId}")
    public ResponseEntity<?> cancelReservation(@PathVariable Long reservationId) {
        return ResponseEntity.ok().body(reservationService.cancelReservation(reservationId));
    }
}

```
<br/>

- 프론트에서 취소해 줄 예약의 reservationId를 받는다.

---

<br/><br/>

**service**

```java

@Service
public class AdminReservationService {

    @Autowired
    private AdminReservationMapper reservationMapper;

    public Boolean cancelReservation(Long reservationId) {
            try {
                reservationMapper.cancelById(reservationId);
            } catch (Exception e) {
                throw new ExecutionException("실행 도중 오류 발생");
            }
            return true;
        }
}

```
<br/>

- controller에서 보낸 reservationId로 예약 status를 바꿔준다.

---

<br/><br/>

**mapper**

```java

@Mapper
public interface AdminReservationMapper {

    int cancelById(Long id);

}

```
<br/>

- service에서 보낸 reservationId로 예약 status를 바꿔준다.

--- 

<br/><br/>

**sql**

```java

<update id="cancelById">
    update reservation_tb
    set
        status = 3
    where
        id = #{id}
</update>

```
<br/>

- status를 예약 취소 상태인 3으로 바꾼다.

---

</div>
</details>

---

</div>
</details>

<details>
<summary>관리자 리뷰관리 영상 및 코드 리뷰</summary>
<div markdown="1">

<br/>

<div align="center" dir="auto">
    <img src="https://github.com/user-attachments/assets/00d0169c-2e06-4101-aed2-65693e9573fb" align="center" width="100%"/>
    <b>관리자 리뷰관리</b>
</div>

<br/>

<details>
<summary>리뷰 조회 코드 리뷰</summary>
<div markdown="1">

<br/>

**controller**

```java

@RestController
@RequestMapping("/admin")
public class AdminReviewController {

    @Autowired
    private AdminReviewService reviewService;

    // 관리자 페이지 전체 리뷰 조회
    @GetMapping("/review")
    public ResponseEntity<?> getReviewAllByLimit(ReqPageAndLimitDto dto, String searchText) {
        return ResponseEntity.ok().body(reviewService.getReviewAllByLimit(dto, searchText));
    }
}

```
<br/>

- searchText는 검색을 안 할때는 빈 문자열값.

---

<br/><br/>

**dto**

```java

@Data
public class ReqPageAndLimitDto {
    private Long page;
    private Long limit;
}

```
<br/>

---

<br/><br/>

**service**

```java

@Service
public class AdminReviewService {

    @Autowired
    private AdminReviewMapper reviewMapper;

    public RespGetReviewListDto getReviewAllByLimit(ReqPageAndLimitDto dto, String searchText) {
            try {
                Long startIndex = (dto.getPage() - 1) * dto.getLimit();
                List<Review> reviews = reviewMapper.reviewList(startIndex, dto.getLimit(), searchText);
                Long reviewCount = reviewMapper.reviewCount(searchText);
    
                return RespGetReviewListDto.builder()
                        .reviews(reviews)
                        .reviewCount(reviewCount)
                        .build();
            } catch (Exception e) {
                e.printStackTrace();
                throw new ExecutionException("실행 도중 오류 발생");
            }
        }
}

```
<br/>

- dto의 데이터들을 mapper로 넘겨준다.

---

<br/><br/>

**mapper**

```java

@Mapper
public interface AdminReviewMapper {

    // 관리자 페이지 리뷰 전체 조회
    List<Review> reviewList(@Param("startIndex") Long startIndex,
                            @Param("limit") Long limit,
                            @Param("searchText") String searchText);
    Long reviewCount(String searchText);

}

```
<br/>

- 데이터베이스에서 리뷰 리스트와 개수를 가져온다.

---

<br/><br/>

**sql**

```java

<select id="reviewList" resultType="com.project.doctor_fish_back.entity.Review">
    select
        rt.id,
        rt.user_id as userId,
        rt.img,
        rt.content,
        rt.register_date as registerDate,
        rt.update_date as updateDate,
        ut.name as userName,
        ut.img as userImg,
        (select count(*) from comment_tb where review_id = rt.id) as commentCount
    from
        review_tb rt
        left outer join user_tb ut on(rt.user_id = ut.id)
    where
        ut.name like concat("%", #{searchText}, "%")
    order by
        rt.register_date desc
    limit #{startIndex}, #{limit}
</select>

<select id="reviewCount" resultType="java.lang.Long">
    select
        count(*)
    from
        review_tb rt
        left outer join user_tb ut on(rt.user_id = ut.id)
    where
        ut.name like concat("%", #{searchText}, "%")
</select>

```
<br/>

- 데이터베이스에서 리뷰를 작성한 사용자의 이름이 searchText에 포함되어있는 리뷰 리스트와 개수를 가져온다.

---

</div>
</details>

<details>
<summary>리뷰 삭제 코드 리뷰</summary>
<div markdown="1">

<br/>

**controller**
```java

@RestController
@RequestMapping("/admin")
public class AdminReviewController {

    @Autowired
    private AdminReviewService reviewService;

    // 리뷰 삭제
    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok().body(reviewService.deleteReview(reviewId));
    }
}

```
<br/>

- 프론트에서 삭제할 리뷰의 reviewId를 받아온다.

---

<br/><br/>

**service**

```java

@Service
public class AdminReviewService {

    @Autowired
    private UserCommentMapper userCommentMapper;
    @Autowired
    private UserReviewLikeMapper userReviewLikeMapper;
    @Autowired
    private AdminReviewMapper reviewMapper;

    public Boolean deleteReview(Long reviewId) {
            try {
                userCommentMapper.deleteCommentsByReviewId(reviewId);
                userReviewLikeMapper.deleteReviewsByReviewId(reviewId);
                reviewMapper.deleteById(reviewId);
            } catch (Exception e) {
                throw new ExecutionException("실행 도중 오류 발생");
            }
            return true;
        }

```
<br/>

- 리뷰를 삭제할 때 해당 리뷰의 댓글, 좋아요를 같이 삭제해준다.

---

<br/><br/>

**mapper**

```java

@Mapper
public interface UserCommentMapper {

    int deleteCommentsByReviewId(Long reviewId);

}

@Mapper
public interface UserReviewLikeMapper {

    int deleteReviewsByReviewId(Long reviewId);

}

@Mapper
public interface AdminReviewMapper {

    int deleteById(Long id);

}

```
<br/>

- service에서 받은 reviewId로 리뷰, 댓글, 좋아요를 삭제한다.

---

<br/><br/>

**sql**

```java

<delete id="deleteCommentsByReviewId">
    delete
    from
        comment_tb
    where
        review_id = #{reviewId}
</delete>

<delete id="deleteReviewsByReviewId">
    delete
    from
        review_like_tb
    where
        review_id = #{reviewId}
</delete>

<delete id="deleteById">
    delete
    from
        review_tb
    where
        id = #{id}
</delete>

```
<br/>

- 데이터베이스에서 리뷰, 삭제할 리뷰의 댓글, 좋아요를 삭제한다.

---

</div>
</details>

---

</div>
</details>

<details>
<summary>연차 신청 영상</summary>
<div markdown="1">

<br/>

<div align="center" dir="auto">
    <img src="https://github.com/user-attachments/assets/27f805c4-f34d-4dfd-995d-a4e0548da4ce" align="center" width="100%"/>
    <b>연차신청</b>
</div>

<br/>

</div>
</details>

<details>
<summary>공지사항 작성 영상</summary>
<div markdown="1">

<br/>

<div align="center" dir="auto">
    <img src="https://github.com/user-attachments/assets/0ed5c6af-c800-4294-8f22-36ef3191fd4c" align="center" width="100%"/>
    <b>공지사항</b>
</div>

</div>
</details>

<br/>

## 💡 느낀점
### 백승주

---

### 성창해
- 웹 프로젝트를 진행하며 프론트엔드와 백엔드 간의 연결과 상호작용에 대해 깊이 고민하게 되었습니다. 프론트엔드에서 데이터를 효율적으로 처리하고 표현하기 위해 쿼리 최적화 방법을 모색했고, 백엔드 요청을 간소화할 방안도 탐색했습니다. 예를 들어, 예약 기능에서 예약된 시간에 대해 필터링이 필요했을 때, 처음에는 여러 번의 요청을 보내 데이터를 받아와야 하나 생각했지만, 최적화된 쿼리로 한 번에 데이터를 가져오는 것이 훨씬 효율적이라는 점을 깨닫고 쿼리에 대해 깊이 공부하여 결국 해당 기능을 성공적으로 구현했습니다.
다만, 이러한 부가 기능을 조금 더 빨리 인지했다면 프론트엔드와 백엔드에서 코드 최적화를 통해 상태 관리나 요청을 더 효율적으로 구현할 수 있었을 것이라는 아쉬움이 있습니다. 결국 해당 기능을 구현했지만, 앞으로는 기능 설계와 구현 초기에 이런 부분을 더욱 면밀히 고려할 중요한 교훈으로 삼게 될 것입니다.
또한, 기술적인 고민뿐만 아니라 팀원들 간의 소통의 중요성도 다시 한번 느꼈습니다. 요청 방식과 DTO의 변수, 받아올 데이터의 형태에 대해 충분히 공유하고 진행했음에도 불구하고, 각자의 코드 스타일이 달라 예상치 못한 오류가 발생하기도 했습니다. 이를 통해 단순한 정보 공유만으로는 부족할 수 있으며, 세부적인 코드 스타일이나 작성 방식까지 조율하는 것이 원활한 협업과 프로젝트 성공에 필수적임을 깨달았습니다.
---

### 김해민
- 이번 프로젝트를 진행할 때 조금 더 자신 있었던 백엔드 위주로 했으나 진행 과정에서 실수도 많이 하고 잘 모르는 부분도 있어서 제가 많이 부족하다는 것을 알게 되었습니다. 그래서 백이든 프론트든 모르는 부분이 나올 때는 찾아보고 수업 때 작성한 코드를 참고하였고 특히 수업 때 제대로 이해하지 못했던 security 부분도 혼자서 다시 보면서 실제로 적용해 보니 이해가 되었습니다. 이런 식으로 계속 진행하다 보니 프로젝트를 완성할 수 있었고 프로젝트를 하기 전과 후 실력이 발전했다는 것을 느꼈습니다. 그리고 프로젝트를 진행하면서 코드를 작성할 때마다 프로젝트와 이슈, 브랜치를 만들어서 진행해야 한다는 것이 처음에는 한 번씩 잊어버리기도 했지만 나중에는 점점 익숙해지게 되었고 코드를 올렸을 때 충돌이 났을 때 해결하는 법도 알게 되었습니다. 이렇게 이번 프로젝트를 하면서 팀 프로젝트를 할 때 발생할 수 있는 상황들을 겪고 해결해 볼 수 있어서 좋았습니다.
---

### 김지현
- 이번 프로젝트를 진행하면서 그동안 배웠던 백엔드 개념, mvc 패턴 같은 개념들을 머릿속으로 한 번 더 정리 할 수 있었고 수업을 듣는 동안 유독 어려웠던 service 부분을 프로젝트 진행을 통해서 제 것으로 만들 수 있었습니다. 그리고 팀원들과 소통하면서 프로젝트를 본격적으로 진행해 본 것은 처음이었는데 혼자서 하는 것보다 4명에서 함께 하는 것이 저한테는 생각보다 순조롭게 진행되어서 그런지 팀원들과의 특별한 마찰이나 갈등은 없었지만 어쨌든 팀워크가 중요했기에 저에게 주어진 역할에 대한 중압감과 책임감을 가지고 최대한 팀에 도움이 되기 위해서 오히려 더 열심히 공부 할 수 있는 계기가 되었습니다.
---
