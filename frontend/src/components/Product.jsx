import React from 'react';
import { useCart } from '../context/CartContext';

function Product({ product, onClick }) {
  const { cartItems, addToCart, increaseQuantity, decreaseQuantity } = useCart();

  // Sepette ürün var mı?
  const cartItem = cartItems.find(item => item.productId === product.id);

  return (
    <div
      onClick={onClick}
      style={{ cursor: 'pointer', padding: '1rem', border: '1px solid #ddd', borderRadius: '8px', backgroundColor: '#fff' }}
    >
      <img
        src={product.imageUrl}
        alt={product.name}
        style={{ width: '100%', height: '150px', objectFit: 'cover', borderRadius: '8px' }}
      />
      <h3 style={{ margin: '1rem 0 0.5rem 0' }}>{product.name}</h3>
      <p style={{ color: '#888', marginBottom: '0.5rem' }}>{product.categoryName || product.category || ''}</p>
      <p style={{ fontWeight: 'bold', marginBottom: '1rem' }}>{product.price}₺</p>

      {cartItem ? (
        <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'center', gap: '0.5rem' }}>
          <button
            onClick={(e) => {
              e.stopPropagation();
              decreaseQuantity(product.id);
            }}
            style={{
              padding: '0.3rem 0.8rem',
              fontSize: '1rem',
              cursor: 'pointer',
            }}
          >
            -
          </button>
          <span>{cartItem.quantity}</span>
          <button
            onClick={(e) => {
              e.stopPropagation();
              increaseQuantity(product.id);
            }}
            style={{
              padding: '0.3rem 0.8rem',
              fontSize: '1rem',
              cursor: 'pointer',
            }}
          >
            +
          </button>
        </div>
      ) : (
        <button
          onClick={(e) => {
            e.stopPropagation();
            addToCart(product);
          }}
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
          onMouseOver={e => (e.target.style.backgroundColor = '#4c66ff')}
          onMouseOut={e => (e.target.style.backgroundColor = '#000dff')}
        >
          Sepete Ekle
        </button>
      )}
    </div>
  );
}

export default Product;
