import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Product from './Product';
import ProductModal from './ProductModal';

function CategorySection({ category }) {
  const [selectedProduct, setSelectedProduct] = useState(null);
  const navigate = useNavigate();

  const handleSeeMore = () => {
    navigate(`/category/${category.id}`);
  };

  if (!category || !Array.isArray(category.products)) return null;

  return (
    <section style={{ marginBottom: '2rem' }}>
      <div style={{
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        marginBottom: '1rem'
      }}>
        <h2>{category.name}</h2>
        <button
          onClick={handleSeeMore}
          style={{
            backgroundColor: 'transparent',
            border: 'none',
            color: '#007bff',
            cursor: 'pointer',
            fontSize: '1rem'
          }}
        >
          Devamını Gör →
        </button>
      </div>

      <div style={{
        display: 'grid',
        gridTemplateColumns: 'repeat(auto-fit, minmax(150px, 1fr))',
        gap: '1rem'
      }}>
        {category.products.slice(0, 2).map(product => (
          <Product
            key={product.id}
            product={product}
            onClick={() => setSelectedProduct(product)}
          />
        ))}
      </div>

      <ProductModal
        product={selectedProduct}
        onClose={() => setSelectedProduct(null)}
      />
    </section>
  );
}

export default CategorySection;
