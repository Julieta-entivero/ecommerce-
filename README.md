# E-Commerce Automation - Page Object Model

Framework de automatizacion para testing de una tienda e-commerce usando **Selenium WebDriver** con el patron **Page Object Model (POM)**.

## Tecnologias
- Java 11
- Selenium WebDriver 4.18
- TestNG 7.9
- Maven
- WebDriverManager
- ExtentReports

## Estructura del proyecto

```
src/
├── main/java/com/shopzone/pages/    -> Page Objects
│   ├── BasePage.java                -> Clase base con metodos comunes
│   ├── LoginPage.java
│   ├── ProductsPage.java
│   ├── CartPage.java
│   └── CheckoutPage.java
└── test/java/com/shopzone/
    ├── base/
    │   └── BaseTest.java            -> Setup y teardown del driver
    └── tests/
        ├── LoginTest.java           -> Tests de login (valido, invalido, bloqueado)
        ├── ProductsTest.java        -> Tests de productos y ordenamiento
        ├── CartTest.java            -> Tests del carrito
        └── CheckoutTest.java        -> Tests del flujo de compra
```

## Sitio testeado
[SauceDemo](https://www.saucedemo.com/) - Sitio de practica para automation testing.

## Como correr los tests

```bash
# todos los tests
mvn test

# modo headless (sin navegador visible)
mvn test -Dheadless=true

# un test especifico
mvn test -Dtest=LoginTest

# con el archivo testng.xml
mvn test -DsuiteXmlFile=testng.xml
```

## Tests incluidos

| Suite | Tests | Que valida |

| LoginTest | 5 | Login valido, usuario invalido, campos vacios, usuario bloqueado |
| ProductsTest | 7 | Carga de productos, agregar/remover del carrito, ordenamiento por precio y nombre |
| CartTest | 4 | Carrito vacio, agregar items, eliminar items, navegacion |
| CheckoutTest | 5 | Checkout completo, validaciones de campos obligatorios, compra con varios productos |

Total: **21 test cases**
