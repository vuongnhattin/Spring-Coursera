# Coursera Clone Project - Backend
## Giới thiệu 
Dự án fullstack tạo nên một **website học tập** tương tự như Coursera. Project dùng Java Spring Boot cho backend và Angular cho frontend (repository của Angular: https://github.com/vuongnhattin/Angular-Coursera). 
## Chức năng
### 1. Link website
[https://angular-coursera.nicebeach-963cb903.northcentralus.azurecontainerapps.io/](https://angular-coursera.nicebeach-963cb903.northcentralus.azurecontainerapps.io/)
### 2. Sơ lược về chức năng.
- Đăng kí, đăng nhập.
- Các chức năng xem, thêm, sửa, xoá dành cho khoá học (chỉ các quản trị viên của khoá học mới có quyền xoá, sửa).
- **Tìm kiếm** khoá học dựa theo tên hoặc description.
- Quản trị viên của một khoá học có thể tìm kiếm và **thêm user khác làm quản trị** viên cho khoá học đó, có thể chỉnh sửa thông tin hoặc xoá khoá học.
- Người dùng có thể **xem video, đọc file pdf** và **download** về máy. Quản trị viên có thể **upload**, **xoá** video hoặc file pdf cho một học phần.
- Những người dùng trong một khoá học có thể **chat nhóm** với nhau theo **thời gian thực**.
- Tạo **Swagger Api Document** cung cấp cho phía Front End.
### 2. Công nghệ
- **MongoDB** để lưu trữ tin nhắn chat giữa các user và **MySQL** cho phần còn lại.
- **Flyway** dùng để **migrate**, tạo **version control** cho MySQL.
- **WebSocket** dùng để tạo hiển thị tin nhắn thời gian thực giữa các user.
- Dùng api từ **Amazon S3 Bucket** để **lưu trữ, upload file**, video lên Cloud.
- Dùng **Docker Compose** để tạo môi trường database.
- **Swagger** dùng để tạo Api Document.
### 3. Một số chức năng đặc biệt của các api
- Một số api có chức năng **phân trang, sắp xếp, tìm kiếm**.
- Tất cả api đều có chức năng **xác thực** và **phân quyền** (chẳng hạn chỉ quản trị viên của khoá học mới được chỉnh sửa khoá học)
- Các api thực hiện POST request đều có **validate body** từ form.
- Dùng AOP (Aspect Oriented Programming) để bắt các **exception** từ các api.
- Các api sử dụng **DTO** (Data Transfer Object) để thêm hoặc bớt các trường trả về, giúp phù hợp với Front End.
- Dùng **Presigned Url** (url tạm thời) của Amazon S3 để **phân quyền** cho url của các file đã upload.
## Deploy
- Database MySQL được host trên [https://fly.io](https://fly.io).
- Backend và Frontend được deploy lên **Azure**.
