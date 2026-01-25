# Graph Generator & Analyzer

Bu proje, Çizge Teorisi (Graph Theory) algoritmalarını görselleştirmek, analiz etmek ve üzerinde işlemler yapmak amacıyla geliştirilmiş Java tabanlı bir konsol uygulamasıdır. 

**Enigma Console** kütüphanesi kullanılarak geliştirilen bu araç, kullanıcıya grafikler oluşturma, matris hesaplamaları yapma ve grafiklerin yapısal özelliklerini (izomorfizma, bipartite, complete vb.) test etme imkanı sunar.

## 🚀 Öne Çıkan Özellikler

### 1. Grafik Oluşturma ve Görselleştirme
* **Derece Dizisi (Degree Sequence):** Kullanıcının girdiği derece dizisine uygun (Havel-Hakimi algoritması veya Rastgele yöntem ile) grafikleri otomatik oluşturur.
* **Görsel Çizim:** Düğümleri (Vertices) ve kenarları (Edges) konsol ekranında ASCII/Karakter tabanlı olarak çizer.
* **Dinamik Yerleşim:** Düğümler ızgara (grid) üzerine çakışmayacak şekilde rastgele yerleştirilir.

### 2. Grafik Analizi (Graph Properties)
Oluşturulan veya yüklenen grafikler üzerinde şu testler yapılabilir:
* **Bağlantılılık (Connectivity):** Grafın tek parça olup olmadığını DFS ile kontrol eder.
* **Tam Grafik (Complete Graph):** Tüm düğümlerin birbirine bağlı olup olmadığını test eder.
* **İki Parçalı (Bipartite):** Grafın iki bağımsız kümeye ayrılıp ayrılamayacağını kontrol eder.
* **Döngü, Tekerlek ve Yıldız Grafikleri:** Grafın `Cycle`, `Wheel` veya `Star` yapısında olup olmadığını analiz eder.
* **C3 Döngüsü:** Grafta üçgen (Triangle) yapıları olup olmadığını tarar.
* **İzole Düğümler:** Bağlantısı olmayan düğümleri tespit eder.

### 3. Graf İzomorfizması (Graph Isomorphism)
* Sistemde bulunan **Ana Grafik (Main Graph)** ile **İkincil Grafik (Secondary Graph)** arasındaki yapısal eşliği kontrol eder.
* Eğer grafikler izomorfikse, düğümlerin birbirine nasıl eşlendiğini (Mapping) gösterir (Örn: `A <-- C`, `B <-- D`).

### 4. Matris İşlemleri
* **Komşuluk Matrisi (Adjacency Matrix):** Grafiğin matris gösterimini oluşturur.
* **Kuvvet Matrisleri ($R^n$):** Grafın $R^2, R^3 ... R^n$ erişilebilirlik matrislerini hesaplar.
* **Transitive Closure ($R^*$):** Floyd-Warshall algoritması mantığıyla grafın tam erişilebilirlik matrisini çıkarır.
* **En Kısa Yol Matrisi ($R_{min}$):** Düğümler arası en kısa mesafeleri hesaplar.

### 5. Kayıt ve Yönetim (Depot System)
* Grafikler `.txt` dosyalarına kaydedilebilir ve geri yüklenebilir.
* **9 Adet Depo (Depot):** Hafızada tutulan 9 farklı grafik slotu arasında hızlı geçiş ve kopyalama yapılabilir.

## 🛠 Kurulum ve Çalıştırma

Proje `Enigma-Edited2.jar` kütüphanesine bağımlıdır.

1.  Projeyi indirin.
2.  `src` klasörü içerisindeki `GraphGeneratorTest.java` dosyasını çalıştırın.
3.  **Not:** IDE kullanıyorsanız (IntelliJ, Eclipse), `Enigma-Edited2.jar` dosyasının projenin "Build Path" veya "Library" kısmına eklendiğinden emin olun.

## 💻 Klavye Kontrolleri

Uygulama tamamen klavye kısayolları ile yönetilir:

| Tuş | İşlev |
| :--- | :--- |
| **Z** | **Graph Generation Menu:** Yeni grafik oluşturma ve matris işlemleri menüsünü açar. |
| **X** | **Graph Test Menu:** Grafiğin özelliklerini (İzomorfizma, Bipartite vb.) test eden menüyü açar. |
| **C** | **Graph Transfer Menu:** Kopyalama, kaydetme ve yükleme menüsünü açar. |
| **D** | **Drawing Mode:** Kenar çizim stilini değiştirir (Düz çizgi / Karakter bazlı). |

### Transfer Menüsü Kısayolları (C Menüsü Açıkken):
* **G:** Ana grafiği İkincil grafiğe kopyalar.
* **H:** İkincil grafiği Ana grafiğe kopyalar.
* **S / L:** Ana grafiği dosyaya kaydeder / dosyadan yükler (`graph1.txt`).
* **Q - O (Q,W,E...):** Ana grafiği Depo 1-9'a kopyalar.
* **1 - 9:** Depo 1-9'daki grafiği Ana grafiğe yükler.
* **D / F:** Depoları topluca dosyaya kaydeder / yükler.

## 📂 Dosya Yapısı

* `src/Graph.java`: Graf veri yapısı, çizim algoritmaları ve Havel-Hakimi mantığı.
* `src/UIManager.java`: Kullanıcı arayüzü, menüler ve dosya işlemleri.
* `src/MatrixCalculator.java`: Matris çarpımı ve Floyd-Warshall algoritmaları.
* `src/GraphTestMenu.java`: İzomorfizma ve graf tipi analizleri.
* `src/Game.java`: Enigma kütüphanesi ile fare/klavye testi için ek modül.

## 👨‍💻 Geliştirici

* **Geliştirici:** Burak Şeker

---
