import React, { createContext, useContext, useState } from 'react';

// Örnek kategori ve ürün verisi
const initialCategories = [
  {
    id: '1',
    name: 'Meyve',
    products: [
      { id: 101, name: 'Elma', price: 5, imageUrl: '/assets/apple.jpg' },
      { id: 102, name: 'Muz', price: 7, imageUrl: '/assets/banana.jpg' },
      { id: 103, name: 'Çilek', price: 10, imageUrl: '/assets/strawberry.jpg' },
      { id: 104, name: 'Portakal', price: 6, imageUrl: '/assets/orange.jpg' },
    ],
  },
  {
    id: '2',
    name: 'Sebze',
    products: [
      { id: 201, name: 'Domates', price: 4, imageUrl: '/assets/tomato.jpg' },
      { id: 202, name: 'Salatalık', price: 3, imageUrl: '/assets/cucumber.jpg' },
    ],
  },
];

const CategoryContext = createContext();

export function CategoryProvider({ children }) {
  const [categories, setCategories] = useState(initialCategories);

  // İleride backend'den veri çekme ve güncelleme fonksiyonları eklenebilir

  return (
    <CategoryContext.Provider value={{ categories, setCategories }}>
      {children}
    </CategoryContext.Provider>
  );
}

export function useCategory() {
  return useContext(CategoryContext);
}
