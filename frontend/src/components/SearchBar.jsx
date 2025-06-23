import React, { useState } from 'react';

function SearchBar() {
  const [query, setQuery] = useState('');

  const handleSearch = (e) => {
    e.preventDefault();
    console.log("Aranan:", query);
    // TODO: Backend'den arama sonucu çekilecek
  };

  return (
    <form onSubmit={handleSearch} style={{
      display: 'flex',
      padding: '0.5rem 1rem',
      borderBottom: '1px solid #ddd',
      backgroundColor: '#fdfdfd'
    }}>
      <input
        type="text"
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        placeholder="Ürün veya kategori ara..."
        style={{
          flex: 1,
          padding: '0.5rem 1rem',
          border: '1px solid #ccc',
          borderRadius: '8px'
        }}
      />
      <button type="submit" style={{
        marginLeft: '0.5rem',
        padding: '0.5rem 1rem',
        borderRadius: '8px',
        backgroundColor: '#4caf50',
        color: 'white',
        border: 'none',
        cursor: 'pointer'
      }}>
        Ara
      </button>
    </form>
  );
}

export default SearchBar;
