# 🚀 Next Step Edu -- API Documentation

Production URL:

https://next-step-edu-deployment-devops-production.up.railway.app

------------------------------------------------------------------------

## 📌 Base URL Rules

### 🔐 Auth Endpoints

https://next-step-edu-deployment-devops-production.up.railway.app/auth

### 🌍 All Other Endpoints

https://next-step-edu-deployment-devops-production.up.railway.app/api/v1

Example:
https://next-step-edu-deployment-devops-production.up.railway.app/api/v1/scholarship

------------------------------------------------------------------------

# 🔐 Authentication

## Register

POST /auth/register\
Content-Type: multipart/form-data

Fields: - email (text, required) - password (text, required) - firstname
(text, required) - lastname (text, required) - phone (text, required) -
image (file, optional)

------------------------------------------------------------------------

## Login

POST /auth/login\
Content-Type: application/json

Example: { "email": "user@gmail.com", "password": "yourPassword123" }

Response: { "accessToken": "your_jwt_token" }

Use for protected routes: Authorization: Bearer your_jwt_token

------------------------------------------------------------------------

# 🎓 Scholarship API

Base: /api/v1/scholarship\
Requires: Bearer Token

## Create

POST /api/v1/scholarship\
Content-Type: multipart/form-data

Fields: - data (JSON text, required) - logo (file, optional) -
coverImage (file, optional)

Example data: { "name": "Scholarship A", "description": "Full tuition
scholarship", "level": 1, "benefits": "Tuition, Stipend",
"requirements": "GPA \> 3.5", "howToApply": "Apply online", "applyLink":
"https://example.com/apply", "deadline": "2026-03-01T12:00:00",
"programId": 1, "universityId": 1, "status": "ACTIVE" }

## Get All

GET /api/v1/scholarship?page=0&size=10&sortDir=desc

## Get By ID

GET /api/v1/scholarship/{id}

## Update

PUT /api/v1/scholarship/{id}

## Delete

DELETE /api/v1/scholarship/{id}

------------------------------------------------------------------------

# 📞 Scholarship Contact API

Base: /api/v1/scholarship-contact\
Requires: Bearer Token

## Create

POST /api/v1/scholarship-contact

{ "label": "Admissions Office", "email": "admissions@example.com",
"phone": "+1234567890", "websiteUrl": "https://example.com",
"scholarshipId": 2 }

Standard CRUD supported (GET, PUT, DELETE).

------------------------------------------------------------------------

# 🏫 University API

Base: /api/v1/universities

## Create

POST /api/v1/universities\
Content-Type: multipart/form-data

Fields: - name - slug - country - city - description - logoUrl (file) -
coverImageUrl (file) - status - officialWebsite

Standard CRUD supported.

------------------------------------------------------------------------

# 🏛 Faculty API

Base: /api/v1/faculties\
Requires: Bearer Token

## Create

POST /api/v1/faculties

{ "name": "Faculty of Engineering", "description": "Engineering and
technology programs" }

Standard CRUD supported.

------------------------------------------------------------------------

# 🧪 Testing Flow

1.  Register
2.  Login
3.  Copy accessToken
4.  Add Authorization header
5.  Test Create → Get → Update → Delete

------------------------------------------------------------------------

# 📅 Date Format

YYYY-MM-DDTHH:mm:ss\
Example: 2026-12-31T23:59:59
