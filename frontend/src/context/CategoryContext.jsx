import React, { createContext, useContext, useState, useCallback } from 'react';
import axios from '../api/axiosInstance';

const CategoryContext = createContext();

export function CategoryProvider({ children }) {
  const [categories, setCategories] = useState([]);

  // Kategoriler ve ürünler backend’den alınır
const findAllCategories = useCallback(async () => {
  try {
    const res = await axios.get('/rest/api/categories/list');
    console.log('Backend kategori cevabı:', res.data);  // Bunu ekle
    setCategories(res.data);
  } catch (error) {
    console.error('Kategori çekme hatası:', error);
  }
}, []);


  return (
    <CategoryContext.Provider value={{ categories, findAllCategories }}>
      {children}
    </CategoryContext.Provider>
  );
}

export function useCategory() {
  return useContext(CategoryContext);
}
