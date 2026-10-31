# Домашнее задание. Тема 5. Селекторы и локаторы

## 1.1. Список названий товаров

CSS:

```css
#products-list .product-card h4
```

## 1.2. Список цен товаров

XPath:

```xpath
//div[@id='products-list']//div[contains(@class,'product-card')]/h4/following-sibling::div[1]
```

## 1.3. Название товара с ценой 25

CSS:

```css
.product-card[data-price='25'] h4
```

## 1.4. Цена товара с названием «Стакан»

XPath:

```xpath
//div[contains(@class,'product-card') and @data-name='Стакан']/h4/following-sibling::div[1]
```

## 1.5. Список карточек товаров в корзине

CSS:

```css
#cart-items .cart-item
```

## 1.6. Кнопка корзины

CSS:

```css
#open-cart-btn
```