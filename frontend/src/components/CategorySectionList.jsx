import React from 'react';
import CategorySection from './CategorySection';
import { useCategory } from '../context/CategoryContext';

function CategorySectionList() {
  const { categories } = useCategory();

  if (!categories.length) return <p>Kategoriler yükleniyor...</p>;

  return (
    <div style={{ padding: '1rem' }}>
      {categories.map(category => (
        <CategorySection key={category.id} category={category} />
      ))}
    </div>
  );
}

export default CategorySectionList;
