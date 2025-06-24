import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

function Header() {
  const navigate = useNavigate();
  const { user, logout } = useAuth();

  const goHome = () => navigate('/');

  const handleLogout = () => {
    logout();
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
      {/* Logo ve Market İsmi ve Admin Paneli */}
      <div style={{ display: 'flex', alignItems: 'center', gap: '10px', cursor: 'pointer' }}>
        <div onClick={goHome} title="Ana Sayfa" style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
          <img src="/logo192.png" alt="Logo" style={{ width: '40px', height: '40px' }} />
          <h1 style={{ fontSize: '1.5rem', fontWeight: 'bold', margin: 0 }}>Bereket Market</h1>
        </div>
        {user?.role === 'admin' && (
          <button
            onClick={() => navigate('/admin')}
            style={{
              padding: '0.4rem 1rem',
              fontSize: '1rem',
              cursor: 'pointer',
              borderRadius: '6px',
              border: '1px solid #000dff',
              backgroundColor: '#000dff',
              color: 'white',
              fontWeight: '600',
            }}
            title="Admin Paneli"
          >
            Admin Paneli
          </button>
        )}
      </div>

      {/* Sağdaki butonlar */}
      <div style={{ display: 'flex', alignItems: 'center', gap: '1rem' }}>
        {user ? (
          <>
            <span>Merhaba, {user.isim} {user.soyisim}</span>
            <button onClick={handleLogout}>Çıkış Yap</button>
            <button onClick={() => navigate('/cart')}>🛒</button>
            <button onClick={() => navigate('/orders')}>Sipariş Geçmişi</button>
            <button onClick={() => navigate('/addresses')}>Adreslerim</button>
            <button onClick={() => navigate('/payment-methods')}>Ödeme Yöntemlerim</button>
          </>
        ) : (
          <>
            <button onClick={() => navigate('/login')}>Giriş Yap</button>
            <button onClick={() => navigate('/register')}>Kayıt Ol</button>
            <button onClick={() => navigate('/cart')}>🛒</button>
          </>
        )}
      </div>
    </header>
  );
}

export default Header;
