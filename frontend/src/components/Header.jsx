import React from 'react';
import { useNavigate } from 'react-router-dom';

function Header() {
  const navigate = useNavigate();

  const goHome = () => {
    navigate('/');
  };

  return (
    <header style={{
      display: 'flex',
      justifyContent: 'space-between',
      alignItems: 'center',
      padding: '1rem',
      borderBottom: '1px solid #ccc',
      backgroundColor: '#f8f8f8',
      cursor: 'default'
    }}>
      {/* Logo ve Market İsmi */}
      <div
        onClick={goHome}
        style={{ display: 'flex', alignItems: 'center', gap: '10px', cursor: 'pointer' }}
        title="Ana Sayfa"
      >
        <img src="/logo192.png" alt="Logo" style={{ width: '40px', height: '40px' }} />
        <h1 style={{ fontSize: '1.5rem', fontWeight: 'bold', margin: 0 }}>Bereket Market</h1>
      </div>

      {/* Sağdaki butonlar */}
      <div style={{ display: 'flex', gap: '1rem' }}>
        <button onClick={() => navigate('/login')}>Giriş Yap</button>
        <button onClick={() => navigate('/register')}>Kayıt Ol</button>
        <button onClick={() => navigate('/cart')}>🛒</button>
      </div>
    </header>
  );
}

export default Header;
