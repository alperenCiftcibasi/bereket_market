import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../components/Header';

function RegisterPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!email || !password || !confirmPassword) {
      alert('Lütfen tüm alanları doldurun.');
      return;
    }

    if (password !== confirmPassword) {
      alert('Şifreler eşleşmiyor.');
      return;
    }

    // TODO: Backend API ile kayıt işlemi yapılacak

    alert(`Kayıt tamamlandı: ${email}`);
    navigate('/login');  // Kayıttan sonra giriş sayfasına yönlendir
  };

  return (
    <>
      <Header />
      <div style={{
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        minHeight: '80vh',
        background: 'linear-gradient(135deg, #ff758c 0%, #ff7eb3 100%)',
        padding: '2rem'
      }}>
        <form
          onSubmit={handleSubmit}
          style={{
            backgroundColor: 'white',
            padding: '2.5rem',
            borderRadius: '12px',
            boxShadow: '0 8px 24px rgba(0, 0, 0, 0.2)',
            width: '100%',
            maxWidth: '400px'
          }}
        >
          <h2 style={{
            textAlign: 'center',
            marginBottom: '2rem',
            color: '#333',
            fontWeight: '700',
            fontSize: '1.75rem'
          }}>
            Kayıt Ol
          </h2>

          <label htmlFor="email" style={{ display: 'block', marginBottom: '0.5rem', color: '#555' }}>
            Email
          </label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={e => setEmail(e.target.value)}
            style={{
              width: '100%',
              padding: '0.75rem',
              marginBottom: '1.5rem',
              borderRadius: '8px',
              border: '1px solid #ccc',
              fontSize: '1rem'
            }}
            placeholder="email@example.com"
            required
          />

          <label htmlFor="password" style={{ display: 'block', marginBottom: '0.5rem', color: '#555' }}>
            Şifre
          </label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={e => setPassword(e.target.value)}
            style={{
              width: '100%',
              padding: '0.75rem',
              marginBottom: '1.5rem',
              borderRadius: '8px',
              border: '1px solid #ccc',
              fontSize: '1rem'
            }}
            placeholder="Şifrenizi girin"
            required
          />

          <label htmlFor="confirmPassword" style={{ display: 'block', marginBottom: '0.5rem', color: '#555' }}>
            Şifre Tekrar
          </label>
          <input
            type="password"
            id="confirmPassword"
            value={confirmPassword}
            onChange={e => setConfirmPassword(e.target.value)}
            style={{
              width: '100%',
              padding: '0.75rem',
              marginBottom: '2rem',
              borderRadius: '8px',
              border: '1px solid #ccc',
              fontSize: '1rem'
            }}
            placeholder="Şifrenizi tekrar girin"
            required
          />

          <button
            type="submit"
            style={{
              width: '100%',
              padding: '0.75rem',
              backgroundColor: '#ff007a',
              color: 'white',
              fontWeight: '600',
              fontSize: '1.1rem',
              borderRadius: '8px',
              border: 'none',
              cursor: 'pointer',
              transition: 'background-color 0.3s ease'
            }}
            onMouseOver={e => (e.target.style.backgroundColor = '#ff4081')}
            onMouseOut={e => (e.target.style.backgroundColor = '#ff007a')}
          >
            Kayıt Ol
          </button>
        </form>
      </div>
    </>
  );
}

export default RegisterPage;
