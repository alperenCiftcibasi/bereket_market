import React from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import Header from '../components/Header';
import Product from '../components/Product';
import { useCategory } from '../context/CategoryContext';

function CategoryPage() {
  const { categoryId } = useParams();
  const navigate = useNavigate();
  const { categories } = useCategory();

  // categoryId URL’den string olarak gelir, o yüzden string karşılaştırıyoruz
  const category = categories.find(cat => String(cat.id) === categoryId);
  console.log('Kategori:', category);
console.log('Ürünler:', category?.products);


  if (!category) {
    return (
      <>
        <Header />
        <div style={{ padding: '2rem' }}>
          <h2>Kategori bulunamadı.</h2>
          <button onClick={() => navigate(-1)}>Geri Dön</button>
        </div>
      </>
    );
  }

  return (
    <>
      <Header />
      <div style={{ padding: '2rem' }}>
        <h1>{category.name}</h1>
        <div
          style={{
            display: 'grid',
            gridTemplateColumns: 'repeat(auto-fit, minmax(150px, 1fr))',
            gap: '1rem',
          }}
        >
          {category.products.map(product => (
            <Product key={product.id} product={product} />
          ))}
        </div>
      </div>
    </>
    
  );
}

export default CategoryPage;
