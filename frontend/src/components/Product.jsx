import React from 'react';
import { useCart } from '../context/CartContext';

function Product({ product, onClick }) {
  const { addToCart } = useCart();

  return (
<div onClick={onClick} style={{ cursor: 'pointer', /* diğer stiller */ }}>
      <img
        src={product.imageUrl}   // Burada imageUrl olmalı modal ile uyumlu
        alt={product.name}
        style={{ width: '100%', height: '150px', objectFit: 'cover', borderRadius: '8px' }}
      />
      <h3 style={{ margin: '1rem 0 0.5rem 0' }}>{product.name}</h3>
      <p style={{ color: '#888', marginBottom: '0.5rem' }}>{product.category}</p>
      <p style={{ fontWeight: 'bold', marginBottom: '1rem' }}>{product.price}₺</p>
      <button
        onClick={() => addToCart(product)}
        style={{
          padding: '0.5rem 1rem',
          backgroundColor: '#000dff',
          color: 'white',
          border: 'none',
          borderRadius: '6px',
          cursor: 'pointer',
          fontWeight: '600',
          transition: '0.2s ease'
        }}
        onMouseOver={e => e.target.style.backgroundColor = '#4c66ff'}
        onMouseOut={e => e.target.style.backgroundColor = '#000dff'}
      >
        Sepete Ekle
      </button>
    </div>
  );
}

export default Product;
