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
      { id: 105, name: 'Karpuz', price: 22, imageUrl: '/assets/orange.jpg' },
      { id: 1023, name: 'Karpuz', price: 22, imageUrl: '/assets/orange.jpg' }

    ],
  },
  {
    id: '2',
    name: 'Sebze',
    products: [
      { id: 201, name: 'Domates', price: 4, imageUrl: '/assets/tomato.jpg' },
      { id: 202, name: 'Salatalık', price: 3, imageUrl: '/assets/cucumber.jpg' },
      { id: 203, name: 'sa', price: 223, imageUrl: '/assets/asa.jpg' },

    ],
  },
];

const CategoryContext = createContext();

export function CategoryProvider({ children }) {
  const [categories, setCategories] = useState(initialCategories);

  const addNewProduct = (categoryId, newProduct) => {
    setCategories(prev =>
      prev.map(cat =>
        cat.id === categoryId
          ? { ...cat, products: [...cat.products, newProduct] }
          : cat
      )
    );
  };

  return (
    <CategoryContext.Provider value={{ categories, setCategories, addNewProduct }}>
      {children}
    </CategoryContext.Provider>
  );
}


export function useCategory() {
  return useContext(CategoryContext);
}
