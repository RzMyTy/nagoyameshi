-- restaurantsテーブル --
CREATE TABLE IF NOT EXISTS restaurants (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  category_type INT NOT NULL,
  name VARCHAR(50) NOT NULL,
  image VARCHAR(255),
  description VARCHAR(255) NOT NULL,
  lowest_price VARCHAR(50) NOT NULL,
  highest_price VARCHAR(50) NOT NULL,
  postal_code VARCHAR (50) NOT NULL,
  address VARCHAR(255) NOT NULL,
  opening_time TIME NOT NULL,
  closing_time TIME NOT NULL,
  seating_capacity INT NOT NULL,
  holiday VARCHAR(50) NOT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP 
);
 
 -- categoriesテーブル --
CREATE TABLE IF NOT EXISTS categories (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- rolesテーブル --
CREATE TABLE IF NOT EXISTS roles (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- membersテーブル --
CREATE TABLE IF NOT EXISTS members (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    furigana VARCHAR(50) NOT NULL,
    postal_code VARCHAR(50) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone_number VARCHAR(50) NOT NULL,   
    role_id INT NOT NULL, 
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,    
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

-- 認証用テーブル --
CREATE TABLE IF NOT EXISTS authentications (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	member_id INT NOT NULL,
	email VARCHAR(255) NOT NULL UNIQUE,
	password VARCHAR(255) NOT NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,    
	FOREIGN KEY (member_id) REFERENCES members (id)
);

-- レビュー用テーブル --
CREATE TABLE IF NOT EXISTS reviews (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  member_id INT NOT NULL,
  restaurant_id INT NOT NULL,
  score INT NOT NULL,
  content VARCHAR(255) NOT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id),
  FOREIGN KEY (member_id) REFERENCES members (id)
);

-- 予約用テーブル --
CREATE TABLE IF NOT EXISTS reservations (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	restaurant_id INT NOT NULL,
	member_id INT NOT NULL,
	reserved_date DATE NOT NULL,
	reserved_time VARCHAR(50) NOT NULL,
	number_of_people INT NOT NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	FOREIGN KEY (restaurant_id) REFERENCES restaurants (id),
	FOREIGN KEY (member_id) REFERENCES members (id)
);

-- お気に入り用テーブル --
CREATE TABLE IF NOT EXISTS favorites (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  member_id INT NOT NULL,
  restaurant_id INT NOT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id),
  FOREIGN KEY (member_id) REFERENCES members (id)
);