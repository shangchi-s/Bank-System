# Bank-System
This Java program connects to a MySQL database and provides a simple banking system with options to create, update, delete, and view accounts. It uses JDBC with prepared statements for secure queries, a looped menu for user interaction, and handles database operations like insert, update, delete, and select efficiently.
mysql> use bank;
Database changed
mysql> show tables;
+----------------+
| Tables_in_bank |
+----------------+
| accounts       |
+----------------+
1 row in set (0.04 sec)

mysql> select * from accounts;
+-----+------+
| acc | bal  |
+-----+------+
|   1 | 5600 |
|   2 | 3800 |
|   3 | 4000 |
+-----+------+
3 rows in set (0.04 sec)
