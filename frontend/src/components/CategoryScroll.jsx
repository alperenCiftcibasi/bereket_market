import React, { useEffect } from 'react';
import { useCategory } from '../context/CategoryContext';
import { useNavigate } from 'react-router-dom';

function CategoryScroll() {
  const { categories, findAllCategories } = useCategory();
  const navigate = useNavigate();

  useEffect(() => {
    findAllCategories();
  }, [findAllCategories]);

  return (
    <div style={{ display: 'flex', overflowX: 'auto', padding: '1rem 0' }}>
      {categories.length === 0 ? (
        <p>Kategoriler yükleniyor...</p>
      ) : (
        categories.map(cat => (
          <div
            key={cat.id}
            style={{
              flex: '0 0 auto',
              marginRight: '1rem',
              padding: '0.5rem 1rem',
              backgroundColor: '#f0f0f0',
              borderRadius: '20px',
              cursor: 'pointer',
              whiteSpace: 'nowrap',
            }}
            onClick={() => navigate(`/category/${cat.id}`)} // Yönlendirme burada
          >
            {cat.name}
          </div>
        ))
      )}
    </div>
  );
}

export default CategoryScroll;
