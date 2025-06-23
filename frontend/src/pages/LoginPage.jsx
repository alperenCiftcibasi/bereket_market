import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../components/Header';
import { useAuth } from '../context/AuthContext';

function LoginPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();
  const { login } = useAuth();

  // Örnek kullanıcılar
  const validUsers = {
    'admin@example.com': { role: 'admin', password: 'admin123' },
    'user@example.com': { role: 'user', password: 'user123' },
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!email || !password) {
      alert('Lütfen tüm alanları doldurun.');
      return;
    }

    const user = validUsers[email];

    if (!user || user.password !== password) {
      alert('Hatalı email veya şifre!');
      return;
    }

    const userData = {
      username: email,
      role: user.role,
      token: 'fake-jwt-token',
    };

    login(userData); // Context’e kaydet
    if (user.role === 'admin') {
      navigate('/admin');  // Admin ise admin paneline yönlendir
    } else {
      navigate('/');       // Diğer kullanıcılar ana sayfaya gider
    }
  };
  

  return (
    <>
      <Header />
      <div style={{
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        minHeight: '80vh',
        background: 'linear-gradient(135deg, #6b73ff 0%, #000dff 100%)',
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
          }}>Giriş Yap</h2>

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
              marginBottom: '2rem',
              borderRadius: '8px',
              border: '1px solid #ccc',
              fontSize: '1rem'
            }}
            placeholder="Şifrenizi girin"
            required
          />

          <button
            type="submit"
            style={{
              width: '100%',
              padding: '0.75rem',
              backgroundColor: '#000dff',
              color: 'white',
              fontWeight: '600',
              fontSize: '1.1rem',
              borderRadius: '8px',
              border: 'none',
              cursor: 'pointer',
              transition: 'background-color 0.3s ease'
            }}
            onMouseOver={e => (e.target.style.backgroundColor = '#4c66ff')}
            onMouseOut={e => (e.target.style.backgroundColor = '#000dff')}
          >
            Giriş Yap
          </button>
        </form>
      </div>
    </>
  );
}

export default LoginPage;
