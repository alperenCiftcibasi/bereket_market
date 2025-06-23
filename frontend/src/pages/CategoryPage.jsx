import React from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import Header from '../components/Header';

// Örnek kategori ve ürün verisi (bunu backend'den alacaksın)
const categories = [
  {
    id: '1',
    name: 'Meyve',
    products: [
      { id: 101, name: 'Elma', price: 5, imageUrl: '/assets/apple.jpg' },
      { id: 102, name: 'Muz', price: 7, imageUrl: '/assets/banana.jpg' },
      { id: 103, name: 'Çilek', price: 10, imageUrl: '/assets/strawberry.jpg' },
      { id: 104, name: 'Portakal', price: 6, imageUrl: '/assets/orange.jpg' },
      // daha fazla ürün...
    ],
  },
  {
    id: '2',
    name: 'Sebze',
    products: [
      { id: 201, name: 'Domates', price: 4, imageUrl: '/assets/tomato.jpg' },
      { id: 202, name: 'Salatalık', price: 3, imageUrl: '/assets/cucumber.jpg' },
      // ...
    ],
  },
  // diğer kategoriler
];

function CategoryPage() {
  const { categoryId } = useParams();
  const navigate = useNavigate();

  const category = categories.find(cat => cat.id === categoryId);

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
        <div style={{
          display: 'grid',
          gridTemplateColumns: 'repeat(auto-fit, minmax(150px, 1fr))',
          gap: '1rem'
        }}>
          {category.products.map(product => (
            <div key={product.id} style={{
              border: '1px solid #ddd',
              borderRadius: '8px',
              padding: '1rem',
              textAlign: 'center',
              cursor: 'pointer'
            }}>
              <img
                src={product.imageUrl}
                alt={product.name}
                style={{ width: '100%', height: '150px', objectFit: 'cover', borderRadius: '6px', marginBottom: '0.5rem' }}
              />
              <div style={{ fontWeight: 'bold' }}>{product.name}</div>
              <div style={{ color: '#28a745', marginTop: '0.25rem' }}>{product.price} ₺</div>
            </div>
          ))}
        </div>
      </div>
    </>
  );
}

export default CategoryPage;
