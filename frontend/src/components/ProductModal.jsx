import React from 'react';

function ProductModal({ product, onClose }) {
  if (!product) return null;

  return (
    <div
      onClick={onClose}
      style={{
        position: 'fixed',
        top: 0, left: 0, right: 0, bottom: 0,
        backgroundColor: 'rgba(0,0,0,0.5)',
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        zIndex: 1000,
      }}
    >
      <div
        onClick={e => e.stopPropagation()}
        style={{
          backgroundColor: 'white',
          borderRadius: '8px',
          padding: '2rem',
          maxWidth: '500px',
          width: '90%',
          position: 'relative',
        }}
      >
        <button
          onClick={onClose}
          style={{
            position: 'absolute',
            top: '10px',
            right: '10px',
            fontSize: '1.5rem',
            background: 'none',
            border: 'none',
            cursor: 'pointer',
          }}
          aria-label="Kapat"
        >
          &times;
        </button>

        <img
          src={product.imageUrl}
          alt={product.name}
          style={{ width: '100%', borderRadius: '8px', marginBottom: '1rem' }}
        />
        <h2>{product.name}</h2>
        <p style={{ fontWeight: 'bold', fontSize: '1.2rem', color: '#28a745' }}>
          {product.price} ₺
        </p>
        <p>Buraya ürün açıklaması eklenebilir.</p>
      </div>
    </div>
  );
}

export default ProductModal;
