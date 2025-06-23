import React from 'react';
import CategorySection from './CategorySection';

// Örnek kategoriler ve ürünler
const categories = [
  {
    id: 1,
    name: 'Meyve',
    products: [
      { id: 101, name: 'Elma', price: 5, imageUrl: 'https://picsum.photos/seed/1/200/150' },
      { id: 102, name: 'Muz', price: 7, imageUrl: 'https://picsum.photos/seed/2/200/150' },
      { id: 103, name: 'Çilek', price: 10, imageUrl: 'https://picsum.photos/seed/3/200/150' },
      { id: 104, name: 'Portakal', price: 6, imageUrl: 'https://picsum.photos/seed/4/200/150' },
      { id: 105, name: 'Karpuz', price: 8, imageUrl: 'https://picsum.photos/seed/5/200/150' },
    ],
  },
  {
    id: 2,
    name: 'Sebze',
    products: [
      { id: 201, name: 'Domates', price: 4, imageUrl: 'https://picsum.photos/seed/6/200/150' },
      { id: 202, name: 'Salatalık', price: 3, imageUrl: 'https://picsum.photos/seed/7/200/150' },
      { id: 203, name: 'Patlıcan', price: 6, imageUrl: 'https://picsum.photos/seed/8/200/150' },
      { id: 204, name: 'Biber', price: 5, imageUrl: 'https://picsum.photos/seed/9/200/150' },
    ],
  },
];

function CategorySectionList() {
  return (
    <div style={{ padding: '1rem' }}>
      {categories.map(category => (
        <CategorySection key={category.id} category={category} />
      ))}
    </div>
  );
}

export default CategorySectionList;
