import React from 'react';
import { useNavigate } from 'react-router-dom';

const categories = [
  { id: '1', name: 'Meyve' },
  { id: '2', name: 'Sebze' },
  { id: '3', name: 'Süt Ürünleri' },
  // ...
];

function CategoryScroll() {
  const navigate = useNavigate();

  return (
    <div style={{
      overflowX: 'auto',
      whiteSpace: 'nowrap',
      padding: '0.5rem 1rem',
      borderBottom: '1px solid #ddd'
    }}>
      {categories.map(cat => (
        <button
          key={cat.id}
          onClick={() => navigate(`/category/${cat.id}`)}
          style={{
            display: 'inline-block',
            padding: '0.5rem 1rem',
            marginRight: '0.5rem',
            border: '1px solid #ccc',
            borderRadius: '20px',
            backgroundColor: '#f0f0f0',
            cursor: 'pointer'
          }}
        >
          {cat.name}
        </button>
      ))}
    </div>
  );
}

export default CategoryScroll;
