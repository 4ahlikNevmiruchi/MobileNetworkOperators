# Laboratory Work 2 — Mobile Network Operators (MNO)

This project implements an XML/XSD-based data model and parsing/validation/transformation 
pipeline for mobile operator tariffs. The implementation includes three XML parsers 
(DOM, SAX, StAX), XSD validation, XSLT grouping transformation, sorting via Comparators, 
proper logging, and full JUnit 5 tests.

## Overview

- Domain: tariffs of mobile network operators (plans with prices and parameters).
- Data sources: XML file validated by an XSD schema.
- Parsers: DOM, SAX, and StAX implementations produce identical Tariff lists.
- Sorting: utility comparators for typical sort orders (by payroll, by name, by operator then payroll).
- Validation: XML is validated against the XSD schema before processing.
- Transformation: XSLT transforms the source XML into a grouped structure by operator.
- Logging: java.util.logging configured via a small helper to print informative messages.
- Tests: comprehensive unit and integration tests (JUnit 5) 
cover the model, utilities, parsers, transformer, and main flow.

- Requirements: JDK 21+, Maven 3.9+.

Output of the XSLT step is written by default to:
- src/main/resources/tariffs-grouped.xml (from Main)
- target/tariffs-grouped-test.xml (from tests)

## Project layout

- src/main/resources/tariffs.xml — source XML
- src/main/resources/tariffs.xsd — XSD schema
- src/main/resources/transform.xslt — XSLT grouping by operator
- src/main/java/com/ideaprojects/mno/parser — DOM/SAX/StAX parsers
- src/main/java/com/ideaprojects/mno/util — comparators, validator, transformer, logging setup
- src/test/java — JUnit 5 tests

---

# Лабораторна робота 2

## Створити файл XML та відповідну йому схему XSD

1. При розробці XSD використовувати прості та комплексні типи, переліки, 
граничні значення, обов'язкове використання атрибутів та типу ID.
2. Згенерувати (створити) Java-клас, що відповідає цьому опису.
3. Створити Java-додаток для розбору XML-документа та ініціалізації 
колекції об'єктів інформацією з XML-файлу. Для розбору використовувати парсери SAX, DOM та StAX. 
Для сортування об'єктів використовувати інтерфейс Comparator.
4. Здійснити валідацію XML-документа з використанням XSD.
5. За допомогою XSL перетворити XML-файл у формат XML зі зміненою структурою,  
при якій початковий список елементів розбивається на підсписки за деякою 
характеристикою, наприклад, Type, Origin etc.
6. Уникати дублювання коду.
7. Весь код повинен бути покритий Unit-тестами.
8. Код повинен бути розміщений на віддаленому репозиторії. 
Історія змін повинна мати логічні commit-повідомлення, репозиторій має мати 
правильно налаштований gitignore файл.
9. Для виведення повідомлень під час роботи програми використати засоби 
та підходи логування за допомогою правильно налаштованого Logger.

## Варіант 3 — Тарифи мобільних компаній

Тарифи мобільних компаній можуть мати наступну структуру:
- Name – назва тарифу.
- Operator name – назва мобільного оператора, якому належить тариф.
- Payroll – абонентська плата на місяць (0 – n гривень).
- Call prices (має бути декілька) – ціни на дзвінки: усередині мережі 
(0 – n гривень за хвилину), поза мережею (0 – n гривень за хвилину), на стаціонарні телефони 
(0 – n гривень за хвилину).
- SMS price – ціна за SMS (0 – n гривень).
- Parameters (має бути декілька) – наявність улюбленого номера (0 – n), тарифікація 
(12-секундна, похвилинна), плата за підключення до тарифу (0 – n гривень).
