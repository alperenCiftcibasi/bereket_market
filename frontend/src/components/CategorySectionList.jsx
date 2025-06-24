import React from 'react';
import CategorySection from './CategorySection';
import { useCategory } from '../context/CategoryContext';

function CategorySectionList() {
  const { categories } = useCategory();

  return (
    <div style={{ padding: '1rem' }}>
      {categories.map(category => (
        <CategorySection key={category.id} category={category} />
      ))}
    </div>
  );
}

export default CategorySectionList;
