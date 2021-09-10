package com.ecommerce.util;

import com.ecommerce.models.*;
import com.ecommerce.services.specifications.ProductService;
import com.ecommerce.services.specifications.ReviewService;
import com.ecommerce.services.specifications.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMocker {

    private final ProductService productService;
    private final ReviewService reviewService;
    private final UserService userService;
    private final UserUtils userUtils;

    @Autowired
    public ProductMocker(ProductService productService, ReviewService reviewService, UserService userService, UserUtils userUtils) {
        this.productService = productService;
        this.reviewService = reviewService;
        this.userService = userService;
        this.userUtils = userUtils;
    }

    public void mockProducts() {
        Category pc = new Category();
        pc.setName(CategoryTypes.PC.name());
        Category laptop = new Category();
        laptop.setName(CategoryTypes.LAPTOP.name());
        Category accessories = new Category();
        accessories.setName(CategoryTypes.ACCESSORIES.name());

        List<Product> products = new ArrayList<>();

        Review review = ReviewBuilder.builder()
                .review(4)
                .comment("Aici e un produs frumos")
                .reviewDate(LocalDate.now())
                .build();

        Review review2 = ReviewBuilder.builder()
                .review(4)
                .comment("Produs cumsecade")
                .reviewDate(LocalDate.now())
                .build();

        Review review3 = ReviewBuilder.builder()
                .review(5)
                .comment("Best of the best")
                .reviewDate(LocalDate.now())
                .build();

        User admin = new User();
        admin.setUsername("admin");
        CustomPasswordEncoder enc = new CustomPasswordEncoder();
        Password password = new Password();
        password.setPassword(enc.encode("admin123"));
        UserInformation userInformation = new UserInformation();
        userInformation.setPhone("0770122133");
        userInformation.setEmail("admin@gmail.com");
        userInformation.setFirstName("admin");
        userInformation.setLastName("admin");
        admin.setUserInformation(userInformation);
        admin.setPassword(password);
        admin.setRole("ADMIN");
        this.userService.save(admin);

        Product product = ProductBuilder.builder()
                .name("Laptop ASUS X509JA")
                .category(laptop)
                .price(3250.64)
                .sale(0)
                .quantity(0)
                .stars(5)
                .description("Laptop ASUS X509JA cu procesor Intel® Core™ i5-1035G1 pana la 3.60 GHz, 15.6\", Full HD, 8GB, 512GB SSD, Intel® UHD Graphics, Free DOS, Transparent Silver")
                .url("https://s13emagst.akamaized.net/products/30088/30087492/images/res_47ff68ac2acf00887ea0e07b72460711.jpg?width=450&height=450&hash=302FF064CCD36B97C3D5A576D992734E")
                .build();
        products.add(product);

        product = ProductBuilder.builder()
                .name("Laptop Gaming ASUS ROG Strix")
                .category(laptop)
                .price(4805.33)
                .sale(10)
                .stars(3)
                .quantity(50)
                .description("Laptop Gaming ASUS ROG Strix G15 G512LV cu procesor Intel® Core™ i7-10870H pana la 5.00 GHz, 15.6\" Full HD, 144Hz, 16GB, 512GB SSD, NVIDIA® GeForce RTX™ 2060 6GB, FreeDOS, Original Black")
                .url("https://s13emagst.akamaized.net/products/29978/29977714/images/res_c458571a0e0d073a3c09292ac056cf83.jpg?width=450&height=450&hash=5E5440B62B76CFC1278C40392A88403C")
                .build();
        products.add(product);

        product = ProductBuilder.builder()
                .name("Laptop Apple MacBook Air 13")
                .category(laptop)
                .price(4400)
                .sale(15)
                .stars(4)
                .quantity(120)
                .description("MacBook Air de 11 inchi are o autonomie de pana la 9 ore intre incarcari, iar modelul de 13 inchi are o autonomie incredibila, de pana la 12 ore.")
                .url("https://s13emagst.akamaized.net/products/7956/7955582/images/res_b26c819ded922348fa27b909d1831cb7.jpg?width=450&height=450&hash=FDEBF030531B77D0702801E2372BCA69")
                .build();
        products.add(product);

        product = ProductBuilder.builder()
                .name("Laptop Gaming Acer Predator Helios")
                .category(laptop)
                .price(5450.64)
                .sale(0)
                .quantity(5)
                .description("Laptop Gaming Acer Predator Helios 300 PH315-53 cu procesor Intel Core i5-10300H pana la 4.50 GHz, 15.6\", Full HD, 144Hz, 16GB, 256GB SSD, NVIDIA® GeForce RTX™ 2060 6GB, No OS, Black")
                .url("https://s13emagst.akamaized.net/products/33379/33378903/images/res_0b58efefb690bdbcd33928c3239fd35a.jpg?width=450&height=450&hash=96B550FD145E5FABB6B1F0AB2598BBF5")
                .build();
        products.add(product);

        product = ProductBuilder.builder()
                .name("Laptop Apple MacBook Pro 16\" Touch Bar")
                .category(laptop)
                .price(12250.64)
                .sale(5)
                .quantity(7)
                .description("Conceput pentru cei care sfideaza limitele si schimba lumea, noul MacBook Pro este de departe cel mai puternic notebook crea vreodata de Apple. Cu un ecran Retina captivant de 16 inchi, procesoare super rapide, grafica din generatia urmatoare.")
                .url("https://s13emagst.akamaized.net/products/26981/26980959/images/res_7e0f8f6e52fe167ec158ffa7677c6a38.jpg")
                .build();
        products.add(product);

        product = ProductBuilder.builder()
                .name("Sistem Desktop PC Gaming Serioux")
                .category(pc)
                .price(4200.99)
                .sale(8)
                .quantity(15)
                .description("Sistem Desktop PC Gaming Serioux Powered by ASUS cu procesor Intel® Core™ i5-9400F pana la 4.10GHz, 16GB DDR4, 512GB SSD, GeForce® GTX 1660 Super™ 6GB GDDR6, Microsoft Windows 10 Home")
                .url("https://s13emagst.akamaized.net/products/33995/33994917/images/res_3a7bfd27ad5626968a2f0220fc396056.jpg?width=450&height=450&hash=17B6296E8035BB24D81069431D12B1EE")
                .build();
        products.add(product);

        product = ProductBuilder.builder()
                .name("PC Gaming Corvus 2")
                .category(pc)
                .price(1900)
                .sale(0)
                .quantity(22)
                .description("PC Gaming Corvus 2, AMD Ryzen 5 3400G 3.7GHz, 8GB DDR4, 1TB HDD, AMD Radeon™ Vega 11")
                .url("https://3.grgs.ro/images/products/1/1634078/1637356/normal/gaming-corvus-amd-ryzen-3-2200g-35ghz-4-nuclee-8gb-ddr4-1tb-hdd-amd-radeon-vega-8-53403fe86777173c53af50da7ba93f6e.jpg")
                .build();
        products.add(product);

        product = ProductBuilder.builder()
                .name("Mouse gaming Razer DeathAdder Essential")
                .category(accessories)
                .price(112.00)
                .sale(7)
                .quantity(100)
                .description("Timp de mai bine de un deceniu, linia Razer DeathAdder a reprezentat un punct de sprijin in arena globala a esporturilor." +
                        "Acesta a castigat o reputatie pentru fiabilitatea pe care jucatorii o jura din cauza durabilitatii si ergonomiei sale dovedite.")
                .url("https://s13emagst.akamaized.net/products/16892/16891644/images/res_dfef25a7e8bc261567ae0d357137e884.jpg")
                .build();
        products.add(product);

        product = ProductBuilder.builder()
                .name("Tastatura gaming Razer Ornata V2, iluminare Chroma RGB, switch Mecha-Membrane, butoane multimedia")
                .category(accessories)
                .price(300.00)
                .sale(3)
                .quantity(50)
                .description("Razer Ornata V2 experimentează cel mai bun din ambele lumi - oferind un comutator hibrid" +
                        " care îmbină avantajele tastelor cu membrană și întrerupătoarele mecanice într-un singur" +
                        " design.")
                .url("https://s13emagst.akamaized.net/products/31566/31565622/images/res_a6529c06c76fbbac9a33726aafc9987b.jpg")
                .build();
        products.add(product);

        product = ProductBuilder.builder()
                .name("Tastatura gaming Logitech G213 RGB")
                .category(accessories)
                .price(317.99)
                .sale(1)
                .quantity(500)
                .description("Tastatura de gaming G213 Prodigy prezinta cele mai bune performante in ceea ce priveste " +
                        "simtul tactil prin taste construite in mod special pentru jucatori.")
                .url("https://s13emagst.akamaized.net/products/4112/4111006/images/res_3f2a1a08765223abece77a35a1d371d0.jpg")
                .build();
        products.add(product);

        product = ProductBuilder.builder()
                .name("Casti Gaming Razer Kraken Tournament Edition, THX Surround, telecomanda pe fir, Verde")
                .category(accessories)
                .price(450.00)
                .sale(6)
                .quantity(100)
                .description("Sunetul nu este doar o experienta!\n" +
                        "\n" +
                        "Ci iti intareste simturile. Cand stii exact unde inamicul este pozitionat, tu esti cel care pune capcane.\n" +
                        "Simtul auzului devine arma ta principala, astfel devenind maestru al jocului.")
                .url("https://s13emagst.akamaized.net/products/18313/18312573/images/res_0aaa509a7b9bcb53e57886a19728dc02.jpg")
                .build();
        products.add(product);

        product = ProductBuilder.builder()
                .name("Sistem Desktop PC Gaming Serioux Powered by ASUS cu procesor Intel® Core™ i5-10400F pana" +
                        " la 4.30GHz, 16GB DDR4, 500GB SSD, GeForce® RTX 2060 6GB GDDR6, Microsoft Windows 10 Home")
                .category(pc)
                .price(5299.00)
                .sale(3)
                .quantity(5)
                .description("Sistemele Powered by ASUS sunt dedicate celor ce accepta doar perfectiunea. " +
                        "Echipele noastre de cercetare-dezvoltare de talie mondiala sunt complet devotate motto-ului" +
                        " companiei noastre – In Cautarea Incredibilului.")
                .url("https://s13emagst.akamaized.net/products/34032/34031395/images/res_2909cc511cc1fad8e4f8d784c2022f4f.jpg")
                .build();
        products.add(product);
        products.forEach(prod -> productService.save(prod));

        reviewService.save(review,1L,1L);
        reviewService.save(review2,2L,1L);
        reviewService.save(review3,3L,1L);

    }
}
