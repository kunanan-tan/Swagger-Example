# Example-Swagger

REST API ตัวอย่างพร้อม OpenAPI 3 (springdoc) และ HTTP Basic auth

## Stack

| | |
|---|---|
| Spring Boot | 4.0.7 |
| Java | 21 |
| API docs | springdoc-openapi 3.0.3 (`/swagger-ui.html`) |
| Packaging | `war` (executable — รันตรงหรือ deploy ลง container ก็ได้) |

## ข้อกำหนด

- JDK 21+
- Maven 3.9+

## Build

```bash
mvn clean package                 # profile dev (default)
mvn clean package -P qa
mvn clean package -P prod
```

ได้ `target/swagger.war`

## Run

```bash
# executable war
java -jar target/swagger.war

# หรือผ่าน maven
mvn spring-boot:run
```

เปิด http://localhost:9000/swagger-ui.html

รหัสผ่าน Basic auth ถูก generate ใหม่ทุกครั้งที่ start และพิมพ์ใน log:

```
Using generated security password: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
```

ตั้งเองได้ด้วย `spring.security.user.name` / `spring.security.user.password`

## Configuration

| Env var | Default | คำอธิบาย |
|---|---|---|
| `APP_CORS_ALLOWED_ORIGINS` | ว่าง (dev: `http://localhost:3000,http://localhost:4200`) | รายการ origin คั่นด้วย comma — ระบุชัดเท่านั้น ห้ามใช้ `*` |
| `SWAGGER_ENABLED` | `true` (prod: `false`) | เปิด/ปิด Swagger UI + `/v3/api-docs` |

Profile: `dev` (default) / `qa` / `prod` เลือกผ่าน maven profile (`-P prod`) ซึ่ง filter ค่าเข้า `spring.profiles.active` ตอน build
หรือ override ตอนรัน: `--spring.profiles.active=prod`

## API

| Method | Path | Auth | คำอธิบาย |
|---|---|---|---|
| `GET` | `/rest/testSwagger?testId={long}` | Basic | echo ค่า `testId` กลับ |
| `GET` | `/v3/api-docs` | ไม่ต้อง* | OpenAPI 3 document |
| `GET` | `/swagger-ui.html` | ไม่ต้อง* | Swagger UI |

\* เปิดให้เข้าถึงเฉพาะเมื่อ `SWAGGER_ENABLED=true` — ใน prod ปิดทั้งหมด

```bash
curl -u user:<password> "http://localhost:9000/rest/testSwagger?testId=5"
# 5

curl -u user:<password> "http://localhost:9000/rest/testSwagger?testId=-1"
# 400 — testId ต้องเป็นค่าบวก
```

## Test

โปรเจกต์นี้ยังไม่มี automated test — `spring-security-test` ถูกใส่ไว้พร้อมสำหรับเพิ่ม MockMvc test

## หมายเหตุ

- ไม่ประกาศ `PasswordEncoder` bean โดยตั้งใจ — โปรเจกต์นี้ไม่มี user store และการประกาศ encoder จะทำให้ `UserDetailsServiceAutoConfiguration` ปล่อย generated password เป็น plain text แล้ว Basic auth จะ reject ตัวเอง
- Security header ที่ตั้งไว้: HSTS, `X-Content-Type-Options: nosniff`, `X-Frame-Options: DENY`, `Referrer-Policy: no-referrer`
- `server.error.include-message/stacktrace/binding-errors=never` — error response ไม่หลุด internal detail

## การเปลี่ยนแปลงจากเวอร์ชันเดิม

- Spring Boot 2.7.14 → 4.0.7, Java 11 → 21, `javax.*` → `jakarta.*`
- springfox 3.0.0 (เลิก maintain แล้ว ใช้กับ Boot 3+ ไม่ได้) → springdoc-openapi 3.0.3
- `WebSecurityConfigurerAdapter` (ถูกลบตั้งแต่ Spring Security 6) → `SecurityFilterChain` bean
- `WebMvcConfigurationSupport` → `WebMvcConfigurer` — ตัวเดิมปิด MVC auto-configuration ของ Boot ทั้งหมดโดยไม่แจ้ง
- เพิ่ม `ServletInitializer` — ก่อนหน้านี้ war ที่ deploy ลง external container จะไม่ boot เลย
- แก้ `spring.profile.active` เป็น `spring.profiles.active` — key เดิมพิมพ์ผิด profile จึงไม่เคยถูก activate
- เอา `@PropertySources` ที่โหลด `application.properties` ซ้ำออก — ตัวเดิม load ที่ precedence ต่ำกว่า env/CLI ทำให้ override เงียบ ๆ ไม่ติด
- ตัด dependency ที่ไม่ได้ใช้ (`spring-boot-starter-web-services`, `spring-boot-starter-data-jpa`)
