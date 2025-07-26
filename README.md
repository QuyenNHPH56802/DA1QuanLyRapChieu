# Spring Boot CRUD với Firebase và Google OAuth

Dự án Spring Boot CRUD hoàn chỉnh với Firebase Database và Google OAuth 2.0, bao gồm giao diện web đẹp mắt.

## 🚀 Tính năng

### ✅ (1đ) Đăng nhập bằng Google
- Xác thực OAuth 2.0 với Google
- Tự động chuyển hướng sau khi đăng nhập
- Hiển thị thông tin người dùng

### ✅ (1đ) Hiển thị danh sách từ Firebase
- Lấy dữ liệu real-time từ Firebase Database
- Hiển thị danh sách users với giao diện đẹp
- Responsive design

### ✅ (1đ) Thêm mới vào Firebase
- Form thêm user mới
- Validation dữ liệu
- Lưu trữ vào Firebase Database

### ✅ (1đ) Cập nhật dữ liệu vào Firebase
- Form chỉnh sửa thông tin user
- Cập nhật real-time trong Firebase
- Timestamp tự động

### ✅ (1đ) Xóa dữ liệu trong Firebase
- Xác nhận trước khi xóa
- Xóa dữ liệu từ Firebase Database
- Thông báo kết quả

## 🛠️ Công nghệ sử dụng

- **Spring Boot 3.2.0**
- **Spring Security** với OAuth2
- **Firebase Admin SDK**
- **Thymeleaf** template engine
- **Bootstrap 5** cho UI
- **Font Awesome** icons
- **Java 17**

## 📋 Yêu cầu hệ thống

- Java 17 hoặc cao hơn
- Maven 3.6+
- Firebase project
- Google OAuth 2.0 credentials

## ⚙️ Cài đặt và cấu hình

### 1. Clone dự án
```bash
git clone <repository-url>
cd spring-boot-crud-firebase
```

### 2. Cấu hình Firebase

#### Tạo Firebase Project:
1. Truy cập [Firebase Console](https://console.firebase.google.com/)
2. Tạo project mới
3. Vào **Project Settings** > **Service accounts**
4. Tạo service account key và tải file JSON

#### Cấu hình Firebase Database:
1. Vào **Realtime Database** trong Firebase Console
2. Tạo database mới
3. Copy URL database

### 3. Cấu hình Google OAuth

#### Tạo OAuth 2.0 Credentials:
1. Truy cập [Google Cloud Console](https://console.cloud.google.com/)
2. Tạo project hoặc chọn project Firebase
3. Vào **APIs & Services** > **Credentials**
4. Tạo **OAuth 2.0 Client ID**
5. Thêm redirect URI: `http://localhost:8080/login/oauth2/code/google`

### 4. Cập nhật cấu hình

#### Cập nhật `application.properties`:
```properties
# Thay thế các giá trị sau:
spring.security.oauth2.client.registration.google.client-id=YOUR_GOOGLE_CLIENT_ID
spring.security.oauth2.client.registration.google.client-secret=YOUR_GOOGLE_CLIENT_SECRET
firebase.project-id=YOUR_FIREBASE_PROJECT_ID
firebase.database-url=https://YOUR_PROJECT_ID.firebaseio.com
```

#### Cập nhật Firebase Service Account:
1. Thay thế nội dung file `src/main/resources/firebase/serviceAccountKey.json`
2. Sử dụng file JSON đã tải từ Firebase Console

### 5. Chạy ứng dụng

```bash
# Build project
mvn clean install

# Chạy ứng dụng
mvn spring-boot:run
```

Ứng dụng sẽ chạy tại: `http://localhost:8080`

## 🎯 Hướng dẫn sử dụng

### 1. Đăng nhập
- Truy cập `http://localhost:8080`
- Click "Đăng nhập bằng Google"
- Chọn tài khoản Google và cấp quyền

### 2. Dashboard
- Sau khi đăng nhập, bạn sẽ được chuyển đến Dashboard
- Hiển thị thông tin người dùng và các tính năng chính

### 3. Quản lý Users
- **Xem danh sách**: Click "Quản lý Users" hoặc "Hiển thị danh sách"
- **Thêm mới**: Click "Thêm User Mới" hoặc nút "+"
- **Chỉnh sửa**: Click icon edit (✏️) bên cạnh user
- **Xóa**: Click icon delete (🗑️) và xác nhận

## 📁 Cấu trúc dự án

```
src/
├── main/
│   ├── java/com/example/springbootcrud/
│   │   ├── config/
│   │   │   ├── FirebaseConfig.java
│   │   │   └── SecurityConfig.java
│   │   ├── controller/
│   │   │   └── MainController.java
│   │   ├── model/
│   │   │   └── User.java
│   │   ├── service/
│   │   │   └── FirebaseService.java
│   │   └── SpringBootCrudApplication.java
│   └── resources/
│       ├── firebase/
│       │   └── serviceAccountKey.json
│       ├── templates/
│       │   ├── index.html
│       │   ├── dashboard.html
│       │   └── users/
│       │       ├── list.html
│       │       └── form.html
│       └── application.properties
└── test/
    └── java/
```

## 🔧 Cấu hình chi tiết

### Firebase Configuration
- Sử dụng Firebase Admin SDK để kết nối
- Realtime Database cho lưu trữ dữ liệu
- Service account authentication

### Security Configuration
- OAuth2 với Google provider
- Role-based access control
- Automatic user mapping

### UI/UX Features
- Responsive design với Bootstrap 5
- Glassmorphism design style
- Smooth animations và transitions
- Intuitive navigation

## 🚨 Troubleshooting

### Lỗi Firebase Connection:
- Kiểm tra file `serviceAccountKey.json`
- Đảm bảo project ID đúng
- Kiểm tra database URL

### Lỗi OAuth:
- Kiểm tra client ID và secret
- Đảm bảo redirect URI đúng
- Kiểm tra Google Cloud Console settings

### Lỗi Build:
- Đảm bảo Java 17+
- Kiểm tra Maven dependencies
- Clean và rebuild project

## 📝 License

MIT License - Xem file LICENSE để biết thêm chi tiết.

## 🤝 Đóng góp

Mọi đóng góp đều được chào đón! Vui lòng:
1. Fork dự án
2. Tạo feature branch
3. Commit changes
4. Push to branch
5. Tạo Pull Request

## 📞 Liên hệ

Nếu có câu hỏi hoặc gặp vấn đề, vui lòng tạo issue trên GitHub.

---

**Lưu ý**: Đảm bảo thay thế tất cả placeholder values (YOUR_*) bằng giá trị thực từ Firebase và Google Cloud Console trước khi chạy ứng dụng.