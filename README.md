_Deployment link_ : https://eshop-tutorial-murifq.koyeb.app/product/list

# _Module_ 1
## _Reflection_ 1

Terdapat beberapa aturan _clean code_ yang telah saya terapkan :

1. **Penamaan _variable_ yang baik**. Saya telah membuat _variable_ dengan nama yang sesuai dengan kegunaan dari *variable*-nya.
2. **Aturan fungsi _clean code_**. Saya telah menerapakan aturan fungsi jangan terlalu panjang (aturan tidak bakunya adalah fungsi harus terlihat dalam 1 layar tanpa scroll). Saya juga telah menerapkan aturan bahwa fungsi hanya melakukan satu hal saja. Saya juga telah menerapkan nama yang cukup jelas bagi fungsi.
3. **_Comments_**. Saya juga telah menerapkan menghindari _comment_ yang tidak diperlukan. Sebisa mungkin, kode saya sudah menjelaskan maksudnya tanpa memerlukan _comment_.
4. **_Layout and formatting_**. Saya juga telah menerapkan aturan _blank lines_ untuk memisahkan konsep yang berbeda.
5. **_Objects_ _and_ _data structure_**. Saya juga telah menerapkan aturan _private_ untuk setiap field yang ada. Lalu field tersebut dapat diakses atau diubah dengan menggunakan fungsi.

Namun, terdapat kekurangan dalam kode saya. Salah satunya yang saya sadari, saya kurang menerapkan _error handling_. Bebeberapa fungsi yang saya gunakan, apabila terdapat kesalahan masih belum memberikan informasi kesalahan tersebut ke _user_. Mungkin bisa ditingkatkan dengan memberikan informasi kepada _user_ apabila terdapat kesalahan.

Dalam hal _secure coding_, saya menerapkan _private access modifier_ untuk _field-field_ dari class saya. Tetapi saya tidak menerapkan _authentication_, _authorization_, _input data validation_, dan _output data encoding_


# _Reflection_ 2
1. * Setelah menuliskan _unit test_, saya merasa yakin bahwa kode saya setidaknya bisa berjalan. Saya juga merasa lebih fleksibel untuk mengemabangkan kode saya sebelumnya, tanpa khawatir akan merusak kode saya sebelumnya. 
   * Menurut saya, jumlah _unit test_ tidak boleh terlalu banyak dan terlalu dikit. Saya juga hanya membuat _unit test_ untuk fungsi yang didalamnya terdapat _logic pengerjaan_.
   * Salah satu caranya dapat menggunakan _tools_ yang dapat mengecek _code coverage_ dari _unit test_ kita. Salah satu _tools_ yang dapat digunakan di Java ada JaCoCo.
   * _Code coverage_ 100% memanglah baik. Namun, bukan berarti kode tersebut tidak memiliki kesalahan. Bisa saja terdapat _edge cases_ yag tidak dicek oleh _unit test._
2. Salah satu masalah yang mungkin terjadi adalah penggunaan kode yang berulang. Karena hal yang dilakukan kurang lebih serupa pengerjaannya, maka akan ada kemungkinan kode yang dihasilkan akan tidak jauh berbeda dengan `CreateProductFunctionalTest.java`. Hal yang mungkin bisa dilakukan adalah dengan menaruh kode yang sering dilakukan berulang kali ke dalam sebuah _class_ atau fungsi, sehingga dapat digunakan di bagian kode lainnya.

# _Module_ 2
## _Reflection_
1. Saya menemukan 2 _issues_ dalam program saya dengan menggunakan Sonarcloud, yaitu:
   * Pengulangan penulisan kode
      Saya menuliskan _redirect_ return pada `ProductController` sebanyak 3 kali. Yakni pada saat `"redirect:/product/list";
     ` Oleh karena itu, saya membuat sebuah _constant variable_, yakni `redirectToProductListPage`. Yang nantinya bisa dipakai di mana saja saat diperlukan. Hal tersebut lebih aman karena jika ada perubahan pada _redirect_, hanya perlu mengubah satu _constant variable_.
   * Penggunaan _if-then statement_
      Saya menggunakan _if-else statement_ untuk me-_return_ dua kemungkinan. Oleh karena itu, solusi saya adalah dengan menggunakan `==` sehingga langsung mengecek kesamaan dan ketidaksamaan.
2. Menurut saya, _CI/CD workflows_ yang saya buat telah memenuhi ketentuan _Continuous Integration and Continuous Deployment_. Dalam konsep _CI_, saya telah menerapkan _test_ dan _automation_ pada kode saya. Yang mana kedua hal tersebut merupakan salah dua hal terpenting pada _CI_. Pada tahap _CD_, saya telah menerakan otomatis _Deployment_ dengan PaaS Koyeb. Sehingga, setiap ada _push_ atau _merge_ ke _master branch_, akan melakukan deployment secara otomatis.

# _Module_ 3
## _Reflection_
1. Saya telah menerapkan setidak 3 prinsip SOLID, yaitu:
   * Single Responsibility Principle (SRP)
      Saya telah memisahkan _class_ Product dan Car. Hal tersebut lebih memudahkan karena apabila dibutuhkan untuk mengubah salah satunya, kemungkinannya kecil untuk mengubah _Class_ yang tidak perlu diubah. 
   * Liskov Substitution Principle (LSP)
      Saat ini, saya telah mengubah _Class_ `Car` menjadi _child_ dari _Class_ `Product`. Semua yang dimiliki oleh _Class_ `product` juga dimiliki oleh _Class_ `Car`
   * Dependency Inversions Principle (DIP)
      Saya telah mengubah _dependency_ pada `CarController.java`. Saat ini, pada `CarController.java` menggunakan `carService` dibandingkan implementasi dari `carService`. 
2. Menurut saya, ada beberapa manfaat dari diterapkannya SOLID, yaitu:
   * **Mengurangi pengulangan kode yang sama**. Dapat dilihat contohnya pada kode saya di branch `after-solid`, dengan class `Car` menjadi _subclass_ dari clsas `Product`, saya tidak perlu menulis semua _field_ yang telah ada di `Product` untuk ditulis lagi di `Car`
   * **Memudahkan keterbacaan kode**. Contohnya dalam tugas saya di branch `after-solid` adalah pemisahan `CarController` dengan `ProductController`. Dengan dipisahkan kedua _class_ tersebut, keterbacaannya menjadi lebih baik karena kita dapat mengetahui tujuan dari kodenya hanya dengan melihat nama filenya saja.
   * **Memudahkan untuk melakukan pengembangan kode**. Sebagai contoh di branch `after-solid`, saya memisahkan kode yang berkaitan dengan `Product` dengan kode yang berkaitan dengan `Car`. Sehingga, apabila kedepannya diperlukan perubahan pada salah satunya saja, tidak diperlukan untuk mengubah keseluruhan kode.
3. Menurut saya ada beberapa kelemahan dari tidak diterapkannya SOLID, yaitu:
   * **Banyaknya pengulangan kode yang sama**. Dapat dilihat contohnya pada kode saya di branch `before-solid`, dengan class `Car` tidak menjadi _subclass_ dari clsas `Product`, saya perlu menulis semua _field_ yang telah ada di `Product` untuk ditulis lagi di `Car`
   * **Menyulitkan keterbacaan kode**. Contohnya dalam tugas saya di branch `before-solid` adalah penggabungan file `CarController` dengan `ProductController`. Dengan digabungkan kedua _class_ tersebut, keterbacaannya menjadi lebih buruk karena kita harus membacanya isi kode nya terlebih dahulu untuk dapat mengetahui tujuan dari kodenya.
   * **Kesulitan melakukan testing**. Contohnya dalam tugas saya di branch `before-solid` adalah bedanya implementasi kode yang berkaitan dengan `Product` dan `Car`. Sehingga, apabila saya ingin menerapkan _testing_, saya harus memahami kedua jenis kode yang _logic_-nya berbeda terlebih dahulu dan menyulitkan.