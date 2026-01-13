## Java 버전

- 본 프로젝트는 **Java 17** 환경을 기준으로 개발 및 테스트되었다.
- Maven 빌드 시 Java 17 사용을 전제로 한다.
- `pom.xml` 내 `java.version` 또는 `maven-compiler-plugin` 설정이 필요하다.

---

## 데이터베이스(DB) 구성 관련 안내

본 프로젝트의 DB 구성은 master MQTT 예제 코드와 일부 차이가 있다.

- 테이블 구조, 컬럼명, ID 생성 방식은
  일반적인 샘플 코드와 다를 수 있다.
- DB 연결 정보 및 환경별 설정은
  `application.yaml` 파일을 통해 관리한다.

---