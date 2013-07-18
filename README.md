CookieCRUD
==========
This provides functionality for 
1. multiple values in one cookie
2. encryption of those values using a symetric key
3. decryption of those values using a symetric key

Cipher is not thread safe; but the CookieBakery is threadsafe.
Performance test has not been conducted.
Problems may exist when the delimeters of the string are characters found within the key value pairs themselves.