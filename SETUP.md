# 🚀 Hướng dẫn cấu hình nhanh

## Bước 1: Cấu hình Firebase

### 1.1 Tạo Firebase Project
1. Truy cập [Firebase Console](https://console.firebase.google.com/)
2. Click "Create a project"
3. Đặt tên project và làm theo hướng dẫn

### 1.2 Tạo Service Account Key
1. Trong Firebase Console, vào **Project Settings** (⚙️)
2. Tab **Service accounts**
3. Click **Generate new private key**
4. Tải file JSON và đặt vào `src/main/resources/firebase/serviceAccountKey.json`

### 1.3 Cấu hình Realtime Database
1. Trong Firebase Console, vào **Realtime Database**
2. Click **Create Database**
3. Chọn location và security rules
4. Copy database URL

## Bước 2: Cấu hình Google OAuth

### 2.1 Tạo OAuth 2.0 Credentials
1. Truy cập [Google Cloud Console](https://console.cloud.google.com/)
2. Chọn project Firebase của bạn
3. Vào **APIs & Services** > **Credentials**
4. Click **Create Credentials** > **OAuth 2.0 Client ID**
5. Chọn **Web application**
6. Thêm Authorized redirect URIs:
   - `http://localhost:8080/login/oauth2/code/google`
7. Copy **Client ID** và **Client Secret**

## Bước 3: Cập nhật cấu hình

### 3.1 Cập nhật application.properties
Thay thế các giá trị trong `src/main/resources/application.properties`:

```properties
# Google OAuth
spring.security.oauth2.client.registration.google.client-id=YOUR_CLIENT_ID
spring.security.oauth2.client.registration.google.client-secret=YOUR_CLIENT_SECRET

# Firebase
firebase.project-id=YOUR_PROJECT_ID
firebase.database-url=https://YOUR_PROJECT_ID.firebaseio.com
```

### 3.2 Cập nhật Firebase Service Account
Thay thế nội dung file `src/main/resources/firebase/serviceAccountKey.json` bằng file JSON đã tải từ Firebase.

## Bước 4: Chạy ứng dụng

```bash
# Build project
mvn clean install

# Chạy ứng dụng
mvn spring-boot:run
```

Truy cập: http://localhost:8080

## ✅ Kiểm tra hoạt động

1. **Đăng nhập Google**: Click "Đăng nhập bằng Google"
2. **Dashboard**: Xem thông tin user và các tính năng
3. **Quản lý Users**: 
   - Xem danh sách users
   - Thêm user mới
   - Chỉnh sửa user
   - Xóa user

## 🚨 Lỗi thường gặp

### Lỗi Firebase Connection
```
java.io.IOException: The Application Default Credentials are not available
```
**Giải pháp**: Kiểm tra file `serviceAccountKey.json` đã đúng chưa

### Lỗi OAuth Redirect
```
Error: redirect_uri_mismatch
```
**Giải pháp**: Kiểm tra redirect URI trong Google Cloud Console

### Lỗi Database Permission
```
Permission denied
```
**Giải pháp**: Cập nhật Firebase Database Rules cho phép read/write

## 📞 Hỗ trợ

Nếu gặp vấn đề, hãy kiểm tra:
1. Java version (yêu cầu Java 17+)
2. Maven version (yêu cầu 3.6+)
3. Network connection
4. Firebase project settings
5. Google OAuth configuration