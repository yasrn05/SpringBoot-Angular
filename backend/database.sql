--CREATE TABLE users, thông tin người dùng
    CREATE TABLE users(
	    id INT PRIMARY KEY AUTO_INCREMENT,
	    fullname VARCHAR(100) DEFAULT '',
	    phone_number VARCHAR(20) NOT NULL,
	    address VARCHAR(200) DEFAULT '',
	    password VARCHAR(100) NOT NULL DEFAULT '',
	    created_at DATETIME,
	    updated_at DATETIME,
	    is_activate TINYINT(1) DEFAULT 1,
	    date_of_birth DATE,
	    facebook_account_id INT DEFAULT 0,
	    google_account_id INT DEFAULT 0
    );
--CREATE TABLE roles, phân quyền cho người dùng
    CREATE TABLE roles(
        id INT PRIMARY KEY,
        name VARCHAR(20) NOT NULL
    );
--Tạo liên kết giữa users và roles
    ALTER TABLE users ADD COLUMN role_id INT;
    ALTER TABLE users ADD FOREIGN KEY (role_id) REFERENCES roles(id);
--CREATE TABLE tokens, key để check đăng nhập
    CREATE TABLE tokens(
	    id INT PRIMARY KEY AUTO_INCREMENT,
	    token VARCHAR(255) UNIQUE NOT NULL,
	    token_type VARCHAR(50) NOT NULL,
	    expiration_date DATETIME,
	    revoked TINYINT(1) NOT NULL,
	    expired TINYINT(1) NOT NULL,
	    user_id INT,
	
	    FOREIGN KEY (user_id) REFERENCES users(id)
    );
--CREATE TABLE social_accounts, đăng nhập từ fb or gg
    CREATE TABLE social_accounts(
	    id INT PRIMARY KEY AUTO_INCREMENT,
	    provider VARCHAR(20) NOT NULL COMMENT 'Tên nhà soicial network',
        provider_id VARCHAR(50) NOT NULL,
        email VARCHAR(150) NOT NULL COMMENT 'Email tài khoản',
        name VARCHAR(100) NOT NULL COMMENT 'Tên người dùng',
        user_id INT,

        FOREIGN KEY (user_id) REFERENCES users(id)
    );
--CREATE TABLE category, danh mục sản phẩm
    CREATE TABLE categories(
        id INT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(100) NOT NULL DEFAULT '' COMMENT 'Tên danh mục'
    );
--CREATE TABLE products, thông tin về sản phẩm
    CREATE TABLE products(
        id INT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(350) COMMENT 'Tên sản phẩm',
        price FLOAT NOT NULL CHECK(price >= 0),
        thumbnail VARCHAR(300) DEFAULT '',
        description LONGTEXT DEFAULT '',
        created_at DATETIME,
        updated_at DATETIME,
        category_id INT
    );
-- CREATE TABLE product_images, chứa url ảnh của sản phẩm
    CREATE TABLE product_images(
        id INT PRIMARY KEY AUTO_INCREMENT,
        product_id INT,
        image_url VARCHAR(300),

        FOREIGN KEY (product_id) REFERENCES products(id),
        CONSTRAINT fk_product_images_product_id
            FOREIGN KEY (product_id) 
            REFERENCES products(id) ON DELETE CASCADE
    );
--CREATE TABLE orders, đơn  hàng
    CREATE TABLE orders(
        id INT PRIMARY KEY AUTO_INCREMENT,
        user_id INT,
        fullname VARCHAR(100) DEFAULT '',
        email VARCHAR(100) DEFAULT '',
        phone_number VARCHAR(20) DEFAULT '',
        address VARCHAR(200) DEFAULT '',
        note VARCHAR(100) DEFAULT '',
        order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
        status VARCHAR(20),
        total_money FLOAT CHECK(total_money >= 0),

        FOREIGN KEY (user_id) REFERENCES users(id)
    );
--Bổ xung thêm các trường dữ liệu vào bảng orders
    ALTER TABLE orders ADD COLUMN shipping_method VARCHAR(100);
    ALTER TABLE orders ADD COLUMN shipping_address VARCHAR(200);
    ALTER TABLE orders ADD COLUMN shipping_date DATE;
    ALTER TABLE orders ADD COLUMN tracking_number VARCHAR(100);
    ALTER TABLE orders ADD COLUMN payment_method VARCHAR(100);
--Khi xóa đơn hàng thì ko xóa trực tiếp mà thêm trường activae để kiếm soát
    ALTER TABLE orders ADD COLUMN activae TINYINT(1);
--Kiểm soát các giá trị mà status đơn hàng có thể nhận
    ALTER TABLE orders
    MODIFY COLUMN status ENUM('pending', 'processing', 'shipped', 'delivered', 'cancelled')
    COMMENT 'Trạng thái đơn hàng';

--CREATE TABLE order_details, chi tiết đơn hàng
    CREATE TABLE order_details(
        id INT PRIMARY KEY AUTO_INCREMENT,
        order_id INT,
        product_id INT,
        price FLOAT CHECK(price >= 0),
        number_of_products INT CHECK(number_of_products > 0),
        total_money FLOAT CHECK(total_money >= 0),
        color VARCHAR(20) DEFAULT '',

        FOREIGN KEY (order_id) REFERENCES orders(id),
        FOREIGN KEY (product_id) REFERENCES products(id)
    );